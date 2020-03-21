package ru.sbt.mipt.oop;

import java.util.Arrays;

import ru.sbt.mipt.oop.eventhandler.DoorEventHandler;
import ru.sbt.mipt.oop.eventhandler.HallDoorEventHandler;
import ru.sbt.mipt.oop.eventhandler.LightEventHandler;
import ru.sbt.mipt.oop.eventprovider.RandomSensorEventProvider;
import ru.sbt.mipt.oop.loader.SmartHomeJsonFileLoader;
import ru.sbt.mipt.oop.loader.SmartHomeLoader;

public class Application {
    private final SmartHomeLoader smartHomeLoader;
    private final EventCycleRunner eventCycleRunner;

    public Application(SmartHomeLoader smartHomeLoader, EventCycleRunner eventCycleRunner) {
        this.smartHomeLoader = smartHomeLoader;
        this.eventCycleRunner = eventCycleRunner;
    }


    public static void main(String... args) {
        new Application(
                new SmartHomeJsonFileLoader("smart-home-1.json"),
                new EventCycleRunner(
                        new RandomSensorEventProvider(),
                        Arrays.asList(
                                new LightEventHandler(),
                                new DoorEventHandler(),
                                new HallDoorEventHandler()
                        )
                )
        ).run();
    }

    public void run() {
        // считываем состояние дома из файла
        SmartHome smartHome = smartHomeLoader.loadSmartHome();

        // начинаем цикл обработки событий
        eventCycleRunner.run(smartHome);
    }

}
