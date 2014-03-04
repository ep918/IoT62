package gateway_project;

import interfaces_project.IoT6Object;

public interface IConverter {

	public IoT6Object convert(byte[]data, byte protocol);
}
