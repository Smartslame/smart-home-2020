package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.SmartHome;


public class CCEventHandlerAdapter implements EventHandler {
    private final ru.sbt.mipt.oop.eventhandler.EventHandler eventHandler;
    private final SmartHome smartHome;

    public CCEventHandlerAdapter(ru.sbt.mipt.oop.eventhandler.EventHandler eventHandler, SmartHome smartHome) {
        this.eventHandler = eventHandler;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventHandler.handleEvent(smartHome, new CCSensorEventAdapter(event));
    }
}
