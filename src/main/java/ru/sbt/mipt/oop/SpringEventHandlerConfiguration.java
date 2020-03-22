package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.eventhandler.*;

@Configuration
public class SpringEventHandlerConfiguration {
    @Bean
    EventHandler alarmEventHandler() {
        return new AlarmEventHandler();
    }

    @Bean
    EventHandler doorEventHandler() {
        return new DoorEventHandler();
    }

    @Bean
    EventHandler hallDoorEventHandler() {
        return new HallDoorEventHandler();
    }

    @Bean
    EventHandler lightEventHandler() {
        return new LightEventHandler();
    }
}
