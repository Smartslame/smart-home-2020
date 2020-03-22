package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapter.CCEventHandlerAdapter;
import ru.sbt.mipt.oop.eventhandler.*;
import ru.sbt.mipt.oop.loader.SmartHomeJsonFileLoader;
import ru.sbt.mipt.oop.loader.SmartHomeLoader;

import java.util.Arrays;

@Configuration
public class SpringConfiguration {
    @Bean
    public SmartHomeLoader createSmartHomeLoader() {
        return new SmartHomeJsonFileLoader("smart-home-1.json");
    }

    @Bean
    @Autowired
    public SmartHome createSmartHome(SmartHomeLoader smartHomeLoader) {
        return smartHomeLoader.loadSmartHome();
    }

    @Bean
    @Autowired
    public SensorEventsManager createSensorEventsManager(SmartHome smartHome) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();

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

        return sensorEventsManager;
    }
}
