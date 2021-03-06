package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConnectionRead {
    public static final String URL = "url";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionRead.class.getName());
    private final String path;
    private final Map<String, String> values;

    public ConnectionRead(String path) {
        this.path = path;
        this.values = new HashMap<>();
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .forEach(s -> {
                        String[] lines = s.split("=");
                        if (lines.length == 2) {
                            values.put(lines[0], lines[1]);
                        }
                    });
        } catch (IOException e) {
            LOG.error("Ощибка четния файла конфигурации", e);
        }
    }

    public String get(String key) {
        return values.get(key);
    }
}
