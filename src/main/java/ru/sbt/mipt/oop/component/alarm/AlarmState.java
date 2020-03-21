package ru.sbt.mipt.oop.component.alarm;

public abstract class AlarmState {
    protected final Alarm alarm;

    public AlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    public abstract void activate(String code);

    public abstract void deactivate(String code);

    public abstract void toAlertMode();
}
