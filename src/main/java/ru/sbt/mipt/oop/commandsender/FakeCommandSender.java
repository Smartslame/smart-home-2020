package ru.sbt.mipt.oop.commandsender;

import ru.sbt.mipt.oop.SensorCommand;

public class FakeCommandSender implements CommandSender {

    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}