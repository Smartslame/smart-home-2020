package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventhandler.DoorEventHandler;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.eventhandler.LightEventHandler;
import ru.sbt.mipt.oop.eventprovider.RandomSensorEventProvider;
import ru.sbt.mipt.oop.eventprovider.SensorEventProvider;
import ru.sbt.mipt.oop.loader.SmartHomeJsonFileLoader;
import ru.sbt.mipt.oop.loader.SmartHomeLoader;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {

        SmartHomeLoader smartHomeLoader = new SmartHomeJsonFileLoader("smart-home-1.js");

        SensorEventProvider sensorEventProvider = new RandomSensorEventProvider();

        EventHandler lightEventHandler = new LightEventHandler();
        EventHandler doorEventHandler = new DoorEventHandler();


        // считываем состояние дома из файла
        SmartHome smartHome = smartHomeLoader.loadSmartHome();

        // начинаем цикл обработки событий
        runEventCycle(sensorEventProvider, lightEventHandler, doorEventHandler, smartHome);
    }

    private static void runEventCycle(SensorEventProvider sensorEventProvider, EventHandler lightEventHandler, EventHandler doorEventHandler, SmartHome smartHome) {
        SensorEvent event = sensorEventProvider.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);

            lightEventHandler.handleEvent(smartHome, event);

            doorEventHandler.handleEvent(smartHome, event);

            event = sensorEventProvider.getNextSensorEvent();
        }
    }

}
