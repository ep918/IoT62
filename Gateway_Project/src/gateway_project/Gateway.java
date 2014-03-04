package gateway_project;

import java.util.ArrayList;
import java.util.List;

import interfaces_project.IActuator;
import interfaces_project.IGateway;
import interfaces_project.IoT6Object;

public class Gateway implements IGateway{

	private List<IActuator> actuators;
	private IConverter converter;//BE SURE IT'S NOT NULL

	//Default constructor without actuator
	public Gateway()
	{
		initializeActuatorsList();
	}

	//Constructor for 1 actuator
	public Gateway(IActuator actuator)
	{
		initializeActuatorsList();
		addActuator(actuator);
	}

	//Constructor for a list of actuators
	public Gateway(List<IActuator> actuators)
	{
		initializeActuatorsList();
		this.actuators = actuators;
	}

	//Create the list that contains the actuators
	private void initializeActuatorsList()
	{
		actuators = new ArrayList<IActuator>();
	}

	//Add an actuator to the list of actuators
	public void addActuator(IActuator actuator)
	{
		actuators.add(actuator);
	}

	//Receive the data from the sensors
	@Override
	public void receiveData(byte[] data) {

		process(data);
	}

	private void process(byte[] data) {

		boolean canConvertDirectly = identifyProtocol(data[0]);

		IoT6Object iot6object = null;
		
		//If the protocol is known
		if(canConvertDirectly)
		{
			converter = new DirectConvert();
		}
		else
		{
			//Else, call a web service
			converter = new WebServiceConvert();
		}

		try
		{
			iot6object = converter.convert(data, data[0]);
		}
		catch(Exception e)
		{
			System.out.println("---ERROR IN THE CONVERSION---");
			e.printStackTrace();
		}

		System.out.println(iot6object.toString());//TEST
		
		//FOR SPRINT 2: SEND THE OBJECT TO THE CMS
//		IoT6Object decision = cms.takeDecision(iot6object);

		//For example here: protocol = 2 for a type of actuator, actuatorId = 1, action = 1 = do nothing (25° is ok)
		IoT6Object decision = new IoT6Object((byte)0B010, (byte)0B001, (byte)0B001);
		//WHAT IF SEVERAL ACTUATORS MUST DO SOMETHING?????
		
		//When it comes back, identify the actuator that must receive it
		IActuator recipient = identifyActuator(decision.getProtocolId(), decision.getDeviceId());
		
		if(recipient == null)
		{
			System.out.println("The actuator with id:"+decision.getDeviceId()+" doesn't exist");
		}
		else
		{
			//Send command to the actuators
			recipient.receiveData(decision.getData());
		}
		
	}

	private IActuator identifyActuator(byte protocolId, byte deviceId) {
		
		//Go through the list of actuators to find the actuator with the given Ids
		for (IActuator actuator : actuators) {
			if(actuator.getDeviceId() == deviceId)
			{
				if(actuator.getProtocolId() == protocolId)
				{
					return actuator;
				}
			}
		}
		
		return null;
	}

	@Override
	public boolean identifyProtocol(byte protocolId) {

		//Put here all supported protocols
		if(protocolId == 0B001)
		{
			return true;
		}

		return false;
	}

}
