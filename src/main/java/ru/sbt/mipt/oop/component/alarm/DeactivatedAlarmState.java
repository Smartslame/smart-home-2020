package ru.sbt.mipt.oop.component.alarm;

public class DeactivatedAlarmState implements AlarmState {
    private final Alarm alarm;

    public DeactivatedAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.setCode(code);
        alarm.setStatus(AlarmStatusType.ACTIVATED);
        alarm.setState(new ActivatedAlarmState(alarm));
    }

    @Override
    public void deactivate(String code) {
        //already deacivated
    }

    @Override
    public void toAlertMode() {
        //can not set Alert mode from this state
    }
}
