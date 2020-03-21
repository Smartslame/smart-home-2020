package ru.sbt.mipt.oop.eventhandler;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.component.alarm.AlarmStatusType;
import ru.sbt.mipt.oop.type.SensorEventType;

public class AlarmEventHandler implements EventHandler {
    private static final Logger logger = Logger.getLogger(AlarmEventHandler.class);

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {

        if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
            if (smartHome.getAlarm().getStatus() != AlarmStatusType.DEACTIVATED) {
                logger.info("Alarm has been already activated.");
                return;
            }

            smartHome.getAlarm().activate(event.getObjectId());
            logger.info("Alarm activated.");
        }

        if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            AlarmStatusType previousStatus = smartHome.getAlarm().getStatus();

            if (previousStatus == AlarmStatusType.DEACTIVATED) {
                logger.info("Alarm has been already deactivated.");
                return;
            }

            smartHome.getAlarm().deactivate(event.getObjectId());

            if (smartHome.getAlarm().getStatus() == AlarmStatusType.DEACTIVATED) {
                logger.info("Alarm successfully deactivated.");
                return;
            }

            if (previousStatus == AlarmStatusType.ACTIVATED) {
                logger.info("Wrong code. Turn on alert mode");
            }

            if (previousStatus == AlarmStatusType.ON_ALERT_MODE) {
                logger.info("Wrong code.");
            }
        }
    }
}
