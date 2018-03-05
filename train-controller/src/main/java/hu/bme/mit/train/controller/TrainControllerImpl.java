package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.sensor.Tachograph;

import java.util.Timer;
import java.util.TimerTask;

public class TrainControllerImpl implements TrainController {

	private Timer timer;
	private int step = 0;


	private int referenceSpeed = 0;
	private int speedLimit = 50;
	private Tachograph tg= new Tachograph();
	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}
		tg.logValue(step,referenceSpeed);
		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
			System.out.println("Speed limit reached");
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

	public Tachograph getTg(){
		return tg;
	}

	public void startSimulation(){
		if(timer!=null)return;
		timer=new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				followSpeed();
			}
		},50);
	}
}
