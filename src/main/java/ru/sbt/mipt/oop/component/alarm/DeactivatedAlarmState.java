package ru.sbt.mipt.oop.component.alarm;

public class DeactivatedAlarmState extends AlarmState {
    public DeactivatedAlarmState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activateAlarm(String code) {
        alarm.setCode(code);
        alarm.setStatus(AlarmStatusType.ACTIVATED);
        alarm.setState(new AcivatedAlarmState(alarm));
    }

    @Override
    public void deactivateAlarm(String code) {
        //already deacivated
    }

    @Override
    public void toAlertMode() {
        //can not set Alert mode from this state
    }
}
