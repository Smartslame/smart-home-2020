package ru.sbt.mipt.oop.component.alarm;

public class OnAlertModeAlarmState extends AlarmState {

    public OnAlertModeAlarmState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activateAlarm(String code) {
        //already active
    }

    @Override
    public void deactivateAlarm(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setStatus(AlarmStatusType.DEACTIVATED);
            alarm.setState(new DeactivatedAlarmState(alarm));
        }
    }

    @Override
    public void toAlertMode() {
        //already on alert mode
    }
}
