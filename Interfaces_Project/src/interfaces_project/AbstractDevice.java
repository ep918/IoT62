package interfaces_project;

public abstract class AbstractDevice {

	protected byte deviceId;
	protected byte protocolId;
	
	public byte getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(byte deviceId) {
		this.deviceId = deviceId;
	}
	
	public byte getProtocolId() {
		return protocolId;
	}
	
	public void setProtocolId(byte protocolId) {
		this.protocolId = protocolId;
	}
	
	@Override
	public String toString() {
		return "AbstractDevice [deviceId=" + deviceId + ", protocolId="
				+ protocolId + "]";
	}
	
}
