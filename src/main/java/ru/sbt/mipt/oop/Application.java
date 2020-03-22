package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.adapter.CCEventHandlerAdapter;
import ru.sbt.mipt.oop.eventhandler.*;
import ru.sbt.mipt.oop.loader.SmartHomeJsonFileLoader;
import ru.sbt.mipt.oop.loader.SmartHomeLoader;

import java.util.Arrays;

public class Application {
    private final SmartHomeLoader smartHomeLoader;
    private final SensorEventsManager sensorEventsManager;

    public Application(SmartHomeLoader smartHomeLoader, SensorEventsManager sensorEventsManager) {
        this.smartHomeLoader = smartHomeLoader;
        this.sensorEventsManager = sensorEventsManager;
    }


    public static void main(String... args) {
        new Application(
                new SmartHomeJsonFileLoader("smart-home-1.json"),
                new SensorEventsManager()
        ).configure().run();
    }

    public Application configure() {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();

        sensorEventsManager.registerEventHandler(
                new CCEventHandlerAdapter(
                        new SecurityEventHandlerDecorator(Arrays.asList(
                                new LightEventHandler(),
                                new DoorEventHandler(),
                                new HallDoorEventHandler())),
                        smartHome
                )
        );

        sensorEventsManager.registerEventHandler(
                new CCEventHandlerAdapter(
                        new AlarmEventHandler(),
                        smartHome
                )
        );

        return this;
    }

    public void run() {
        // начинаем цикл обработки событий
        sensorEventsManager.start();
    }

}
