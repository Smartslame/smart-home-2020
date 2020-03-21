package ru.sbt.mipt.oop.component.alarm;

public class DeactivatedAlarmState extends AlarmState {
    public DeactivatedAlarmState(Alarm alarm) {
        super(alarm);
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
