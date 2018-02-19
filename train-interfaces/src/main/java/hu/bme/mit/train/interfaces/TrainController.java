package hu.bme.mit.train.interfaces;

import hu.bme.mit.train.sensor.Tachograph;

public interface TrainController {

	void followSpeed();

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

	Tachograph getTg();

}
