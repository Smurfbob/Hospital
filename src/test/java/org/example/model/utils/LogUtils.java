package org.example.model.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LogUtils {

    private static final File LOG_OUTPUT = new File("./log");

    public static void writeToLog (final String message) throws RuntimeException {
        try (FileOutputStream outputStream = new FileOutputStream(LOG_OUTPUT)) {
            outputStream.write(message.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void requiresDirectory(final String path) {
        final File file = new File(path);
        try {
            if(!file.isFile() && !file.createNewFile()) {
                throw new RuntimeException(String.format("Unable to create file %s", file.getPath()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
