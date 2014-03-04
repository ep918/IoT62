package launcher_project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gateway_project.Gateway;
import interfaces_project.IActuator;
import interfaces_project.IGateway;
import interfaces_project.ISensor;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import actuator_project.Actuator;
import sensor_project.Sensor;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		//THE SYSTEM STARTS HERE
		
		//We will ask these parameters to the user with the GUI
		int numberOfSensor = 1;
		int numberOfActuator = 1;
		
		ExecutorService pool = Executors.newFixedThreadPool(numberOfSensor);//ARE ACTUATORS RUNNABLE??? => start on event
		
		//1. Create the gateway
		IGateway gateway = new Gateway();
		
		//2. Create the actuator
		for(int i=0 ; i<numberOfActuator ; i++)
		{
			IActuator actuator = new Actuator();
			gateway.addActuator(actuator);//We give the reference of each actuator to the gateway
		}
		
		//3. Create the sensors
		for(int i=0 ; i<numberOfSensor ; i++)
		{
			ISensor sensor = new Sensor(gateway);//We give the reference of the gateway to every sensor
			
			//Start the threads
			pool.execute(sensor);
		}
		
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
