package ru.sbt.mipt.oop.component.alarm;

public class Alarm {
    private AlarmState state;
    private String code;
    private AlarmStatusType status;

    public Alarm() {
        this.state = new DeactivatedAlarmState(this);
        this.status = AlarmStatusType.DEACTIVATED;
        this.code = "";
    }

    public AlarmStatusType getStatus() {
        return status;
    }

    protected void setStatus(AlarmStatusType status) {
        this.status = status;
    }

    protected void setState(AlarmState state) {
        this.state = state;
    }

    protected String getCode() {
        return code;
    }

    protected void setCode(String code) {
        this.code = code;
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void toAlertMode() {
        state.toAlertMode();
    }
}
