package ru.sbt.mipt.oop.component.alarm;

public class OnAlertModeAlarmState implements AlarmState {
    private final Alarm alarm;

    public OnAlertModeAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        //already active
    }

    @Override
    public void deactivate(String code) {
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
