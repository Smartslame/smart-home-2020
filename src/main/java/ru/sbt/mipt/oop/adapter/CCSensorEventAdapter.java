package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.type.SensorEventType;

import java.util.HashMap;
import java.util.Map;

public class CCSensorEventAdapter extends SensorEvent {
    private static final Map<String, SensorEventType> ccEventTypeToEventType = new HashMap<String, SensorEventType>() {{
        put("LightIsOn", SensorEventType.LIGHT_ON);
        put("LightIsOff", SensorEventType.LIGHT_OFF);
        put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
    }};

    public CCSensorEventAdapter(CCSensorEvent event) {
        super(ccEventTypeToEventType.getOrDefault(event.getEventType(), SensorEventType.UNSUPPORTED_EVENT), event.getObjectId());
    }
}
