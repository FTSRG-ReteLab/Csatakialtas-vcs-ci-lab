package hu.bme.mit.train.system;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.sensor.Tachograph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;

public class TachographTest {

    TrainController controller;
    TrainSensor sensor;
    TrainUser user;

    @Before
    public void before() {
        TrainSystem system = new TrainSystem();
        controller = system.getController();
        sensor = system.getSensor();
        user = system.getUser();

        sensor.overrideSpeedLimit(50);
    }

    @Test
    public void TachographNotEmpty() {
        sensor.overrideSpeedLimit(360);

        Assert.assertEquals(0, controller.getReferenceSpeed());

        user.overrideJoystickPosition(5);

        controller.followSpeed();
        Assert.assertEquals(5, controller.getReferenceSpeed());
        controller.followSpeed();
        Assert.assertEquals(10, controller.getReferenceSpeed());
        controller.followSpeed();
        Assert.assertEquals(15, controller.getReferenceSpeed());
        Tachograph tg = ((TrainControllerImpl)controller).getTg();
        Assert.assertTrue(tg.getValues().size()>0);

    }
}
