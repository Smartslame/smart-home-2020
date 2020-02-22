package ru.sbt.mipt.oop.loader;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeJsonFileLoader implements SmartHomeLoader {
    private final String filename;

    public SmartHomeJsonFileLoader(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome loadSmartHome() throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(filename)));
        return gson.fromJson(json, SmartHome.class);
    }
}
