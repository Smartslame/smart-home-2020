package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.component.Door;
import ru.sbt.mipt.oop.component.Room;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.type.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.type.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {


    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (isDoorEvent(event)) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            handleOpenEvent(room, door);
                        } else {
                            handleClosedEvent(room, door);
                        }
                    }
                }
            }
        }
    }

    private void handleClosedEvent(Room room, Door door) {
        door.close();
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
    }

    private void handleOpenEvent(Room room, Door door) {
        door.open();
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
