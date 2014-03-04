package gateway_project;

import interfaces_project.IoT6Object;

public class DirectConvert implements IConverter{

	@Override
	public IoT6Object convert(byte[] data, byte protocol) {
		IoT6Object toReturn = null;

		if(protocol == 0B001)
		{
			toReturn = new IoT6Object(data[0], data[1], data[2]);
		}
		
		//Can add here other protocol

		return toReturn;
	}
}
