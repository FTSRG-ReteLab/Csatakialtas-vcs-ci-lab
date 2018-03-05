package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    @Before
    public void before() {
        // TODO Add initializations
    }

    /**
     * This test case checks if the relative margin alarm fires correctly.
     */
    @Test
    public void set_RelativeMargin_Alarm() {
        TrainController controller = mock(TrainController.class);
        when(controller.getReferenceSpeed()).thenReturn(150);
        TrainUser user = new TrainUserImpl(controller);
        TrainSensor sensor = new TrainSensorImpl(controller, user);
        sensor.overrideSpeedLimit(50);
        Assert.assertTrue(user.getAlarmState());
    }

    /**
     * This test case checks that in normal conditions, no alarm is fired.
     */
    @Test
    public void set_NormalMargin_NoAlarm() {
        TrainController controller = mock(TrainController.class);
        when(controller.getReferenceSpeed()).thenReturn(50);
        TrainUser user = new TrainUserImpl(controller);
        TrainSensor sensor = new TrainSensorImpl(controller, user);
        sensor.overrideSpeedLimit(50);
        Assert.assertFalse(user.getAlarmState());
    }

    /**
     * This test case checks that invalid speeds are noticed.
     */
    @Test
    public void setUnderZero_AbsoluteMargin_Alarm() {
        TrainController controller = mock(TrainController.class);
        when(controller.getReferenceSpeed()).thenReturn(50);
        TrainUser user = new TrainUserImpl(controller);
        TrainSensor sensor = new TrainSensorImpl(controller, user);
        sensor.overrideSpeedLimit(-100);
        Assert.assertTrue(user.getAlarmState());
    }

    /**
     * This test case checks that there is an alarm if the maximum speed is reached.
     */
    @Test
    public void setOverFivehundred_AbsoluteMargin_Alarm() {
        TrainController controller = mock(TrainController.class);
        when(controller.getReferenceSpeed()).thenReturn(50);
        TrainUser user = new TrainUserImpl(controller);
        TrainSensor sensor = new TrainSensorImpl(controller, user);
        sensor.overrideSpeedLimit(550);
        Assert.assertTrue(user.getAlarmState());
    }

}
