package interfaces_project;

public interface IGateway {

	//Add an actuator to the list of actuators
	public void addActuator(IActuator actuator);

	public void receiveData(byte[] toSend);
//	public IoT6Object parseData(byte[] data, byte protocol);
	public boolean identifyProtocol(byte protocolId);
	
}
