package interfaces_project;

public class IoT6Object {

	private byte protocolId;
	private byte deviceId;
	private byte data;
	
	//Default constructor
	public IoT6Object()
	{
	}
	
	public IoT6Object(byte protocolId, byte deviceId, byte data)
	{
		this.protocolId = protocolId;
		this.deviceId = deviceId;
		this.data = data;
	}

	public byte getProtocolId() {
		return protocolId;
	}
	public void setProtocolId(byte protocolId) {
		this.protocolId = protocolId;
	}
	public byte getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(byte deviceId) {
		this.deviceId = deviceId;
	}
	public byte getData() {
		return data;
	}
	public void setData(byte data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "IoT6Object [protocolId=" + protocolId + ", deviceId="
				+ deviceId + ", data=" + data + "]";
	}

}
