package ru.sbt.mipt.oop;

import org.apache.log4j.Logger;
import ru.sbt.mipt.oop.eventhandler.DoorEventHandler;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.eventhandler.HallDoorEventHandler;
import ru.sbt.mipt.oop.eventhandler.LightEventHandler;
import ru.sbt.mipt.oop.eventprovider.RandomSensorEventProvider;
import ru.sbt.mipt.oop.eventprovider.SensorEventProvider;
import ru.sbt.mipt.oop.loader.SmartHomeJsonFileLoader;
import ru.sbt.mipt.oop.loader.SmartHomeLoader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {
    private static final Logger logger = Logger.getLogger(Application.class);
    private final SmartHomeLoader smartHomeLoader;
    private final SensorEventProvider sensorEventProvider;
    private final List<EventHandler> eventHandlers;

    public Application(SmartHomeLoader smartHomeLoader, SensorEventProvider sensorEventProvider, List<EventHandler> eventHandlers) {
        this.smartHomeLoader = smartHomeLoader;
        this.sensorEventProvider = sensorEventProvider;
        this.eventHandlers = eventHandlers;
    }


    public static void main(String... args) throws IOException {
        new Application(
                new SmartHomeJsonFileLoader("smart-home-1.js"),
                new RandomSensorEventProvider(),
                Arrays.asList(
                        new LightEventHandler(),
                        new DoorEventHandler(),
                        new HallDoorEventHandler()
                )
        ).run();
    }

    public void run() throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = smartHomeLoader.loadSmartHome();

        // начинаем цикл обработки событий
        runEventCycle(smartHome);
    }

    private void runEventCycle(SmartHome smartHome) {
        SensorEvent event = sensorEventProvider.getNextSensorEvent();
        while (event != null) {
            logger.info("Got event: " + event);

            for (EventHandler handler : eventHandlers) {
                handler.handleEvent(smartHome, event);
            }

            event = sensorEventProvider.getNextSensorEvent();
        }
    }

}
