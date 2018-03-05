package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private int speedLimit = 5;
	private TrainUser user;
	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user=user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
		if(speedLimit<0 || speedLimit>500 || speedLimit<controller.getReferenceSpeed()*0.5)sendAlarm();
	}

	private void sendAlarm(){
		user.setAlarmState(true);
	}

}
