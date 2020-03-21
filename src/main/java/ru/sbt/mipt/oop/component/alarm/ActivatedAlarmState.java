package ru.sbt.mipt.oop.component.alarm;

public class ActivatedAlarmState extends AlarmState {

    public ActivatedAlarmState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String code) {
        //already acivated
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setStatus(AlarmStatusType.DEACTIVATED);
            alarm.setState(new DeactivatedAlarmState(alarm));
        } else {
            toAlertMode();
        }
    }

    @Override
    public void toAlertMode() {
        alarm.setStatus(AlarmStatusType.ON_ALERT_MODE);
        alarm.setState(new OnAlertModeAlarmState(alarm));
    }
}
