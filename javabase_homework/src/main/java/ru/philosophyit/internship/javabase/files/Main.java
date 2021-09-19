package ru.philosophyit.internship.javabase.files;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {

    public static int paddingCounter = 1;

    public static void main(String... args) {
        File file = new File(Paths.get(
                "src", "main", "java", "ru", "philosophyit", "internship", "javabase").toUri());
        Path path = file.toPath();
        try {
            System.out.println(tree(path));
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
        }
    }

    public static String tree(final Path path) {
        if (path == null) throw new RuntimeException();
        StringBuilder result = new StringBuilder();
        File[] listFiles = path.toFile().listFiles();
        for (File f : Objects.requireNonNull(listFiles)) {
            result.append("...".repeat(Math.max(0, paddingCounter))).append(f.getAbsolutePath()).append("\n");
            if (f.isDirectory()) {
                paddingCounter++;
                result.append(tree(f.toPath()));
            }
        }
        paddingCounter--;
        return result.toString();
    }

}
