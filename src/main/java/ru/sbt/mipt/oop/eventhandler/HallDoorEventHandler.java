package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.commandsender.CommandSender;
import ru.sbt.mipt.oop.commandsender.FakeCommandSender;
import ru.sbt.mipt.oop.component.Door;
import ru.sbt.mipt.oop.component.Light;
import ru.sbt.mipt.oop.component.Room;
import ru.sbt.mipt.oop.type.CommandType;

import static ru.sbt.mipt.oop.type.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements EventHandler {

    private final CommandSender commandSender = new FakeCommandSender();

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (isDoorEvent(event) && isHallDoorEvent(smartHome, event)) {
            for (Room homeRoom : smartHome.getRooms()) {
                for (Light light : homeRoom.getLights()) {
                    light.turnOff();
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    commandSender.sendCommand(command);
                }
            }
        }
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_CLOSED;
    }

    private boolean isHallDoorEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (room.getName().equals("hall")) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
