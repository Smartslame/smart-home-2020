package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.component.Light;
import ru.sbt.mipt.oop.component.Room;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.type.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.type.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (isLightEvent(event)) {
            // событие от источника света
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            light.setOn(true);
                            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                        } else {
                            light.setOn(false);
                            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                        }
                    }
                }
            }
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
