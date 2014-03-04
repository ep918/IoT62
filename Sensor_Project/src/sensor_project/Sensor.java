package sensor_project;

import interfaces_project.AbstractDevice;
import interfaces_project.IGateway;
import interfaces_project.ISensor;

public class Sensor extends AbstractDevice implements ISensor{

	private IGateway gateway;
	private static byte nextId = 0B1;//Create a new id for each sensor 1, 10, 11, ...

	public Sensor(IGateway gateway) {
		this.gateway = gateway;

		super.deviceId = nextId;
		nextId++;

		super.protocolId = 0B001;//We can imagine that this type of sensor always use the protocol 001
		
		//Protocol 001: 8+8+8 (protocolId + deviceId + data)
	}

	//Behavior of the sensor
	@Override
	public void run() {

		while(true)
		{
			waitAMoment(5000);//BASIS TO SEND DATA (random, regular, event, ...)

			byte data = generateData();
			
			//We concatenate the information
			byte[] toSend = new byte[3];
			toSend[0] = protocolId;
			toSend[1] = deviceId;
			toSend[2] = data;
			
			gateway.receiveData(toSend);
		}
	}

	@Override
	public byte generateData() {

		return 0B11001;//25 C°
	}

	private void waitAMoment(int milliseconds) {
		
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
