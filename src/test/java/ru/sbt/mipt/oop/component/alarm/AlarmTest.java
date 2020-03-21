package ru.sbt.mipt.oop.component.alarm;

import org.junit.Assert;
import org.junit.Test;

public class AlarmTest {

    @Test
    public void activateAlarmFirstTimeTest() {
        Alarm alarm = new Alarm();
        alarm.activate("test");

        Assert.assertEquals(AlarmStatusType.ACTIVATED, alarm.getStatus());
    }

    @Test
    public void onAlertModeFromActivatedAlarmTest() {
        Alarm alarm = new Alarm();
        alarm.activate("test");

        alarm.toAlertMode();

        Assert.assertEquals(AlarmStatusType.ON_ALERT_MODE, alarm.getStatus());
    }

    @Test
    public void deactivateActivatedAlarmWithRightCodeTest() {
        Alarm alarm = new Alarm();
        alarm.activate("test");

        alarm.deactivate("test");

        Assert.assertEquals(AlarmStatusType.DEACTIVATED, alarm.getStatus());
    }

    @Test
    public void deactivateActivatedAlarmWithWrongCodeTest() {
        Alarm alarm = new Alarm();
        alarm.activate("test");

        alarm.deactivate("not_test");

        Assert.assertEquals(AlarmStatusType.ON_ALERT_MODE, alarm.getStatus());
    }

    @Test
    public void deactivateOnAlertModeAlarmWithRightCodeTest() {
        Alarm alarm = new Alarm();
        alarm.activate("test");
        alarm.toAlertMode();

        alarm.deactivate("test");

        Assert.assertEquals(AlarmStatusType.DEACTIVATED, alarm.getStatus());
    }

    @Test
    public void deactivateOnAlertModeAlarmWithWrongCodeTest() {
        Alarm alarm = new Alarm();
        alarm.activate("test");
        alarm.toAlertMode();

        alarm.deactivate("not_test");

        Assert.assertEquals(AlarmStatusType.ON_ALERT_MODE, alarm.getStatus());
    }

}