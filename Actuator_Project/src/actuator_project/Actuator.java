package actuator_project;

import interfaces_project.AbstractDevice;
import interfaces_project.IActuator;

public class Actuator extends AbstractDevice implements IActuator{

	private static byte nextId = 0B1;//Create a new id for each actuator 1, 10, 11, ...

	public Actuator()
	{
		super.deviceId = nextId;
		nextId++;

		super.protocolId = 0B010;//We can imagine that this type of sensor always use the protocol 2

		//Protocol 2: 0B001=do nothing, 0B010=heat down, 0B011=heat up
	}

	@Override
	public void receiveData(byte data) {

		//If the actuator has a lot of action, we could use a matching table

		if(data == 0B001)//Do nothing
		{
			System.out.println("The actuator id:"+deviceId+" does nothing");
		}
		else if(data == 0B010)//Heat down the temperature
		{
			System.out.println("The actuator id:"+deviceId+" heats down");
		}
		else if(data == 0B011)//Heat up the temperature
		{
			System.out.println("The actuator id:"+deviceId+" heats up");
		}
		else
		{
			System.out.println("The actuator id:"+deviceId+" isn't able to understand the command");
		}
	}

	@Override
	public void doAction() {//MAYBE TOO GENERAL

	}

}
