package ru.sbt.mipt.oop.component.alarm;

public abstract class AlarmState {
    protected final Alarm alarm;

    public AlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    public abstract void activateAlarm(String code);

    public abstract void deactivateAlarm(String code);

    public abstract void toAlertMode();
}
