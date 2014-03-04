package interfaces_project;

public interface IActuator extends IDevice{

	public void receiveData(byte data);
	public void doAction();
}
