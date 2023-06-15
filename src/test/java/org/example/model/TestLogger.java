package org.example.model;

import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestLogger {

    private static final File OUTPUT = new File("./logOutput");
    private final String loggerName;

    public TestLogger(final String loggerName) {
        this.loggerName = loggerName;
    }

    static {
        if(!OUTPUT.isDirectory() && !OUTPUT.mkdir()) {
            throw new RuntimeException(String.format("Unable to create dir %s", OUTPUT));
        }
    }
    private final List<String> messages = new ArrayList<>();

    public void addLog(final String log) {
        this.messages.add(String.format("%s --- %s", log, this.getDate()));
    }

    private String getDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh.mm.ss"));
    }

    public void writeToFile() {
        final File file = new File(String.format("%s/Log-%s-%s",OUTPUT.getAbsolutePath(), this.loggerName, this.getDate() ));
        try {
            if(!file.createNewFile()) throw new RuntimeException(String.format("Unable to create file %s", file.getAbsolutePath()));
            Files.writeString(file.toPath(), String.join("\n", this.messages));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
