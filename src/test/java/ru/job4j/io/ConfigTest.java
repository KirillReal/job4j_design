package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Petr Arsentev")
        );
    }
}