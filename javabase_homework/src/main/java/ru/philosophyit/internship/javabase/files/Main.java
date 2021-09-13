package ru.philosophyit.internship.javabase.files;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

public class Main {

    public static int paddingCounter = 1;

    public static void main(String... args) {
        Path rightPath = Path.of(
                "C:\\Users\\Nikolay\\IdeaProjects\\javabase_homework\\src\\main\\resources\\inner-to-test-recursion"
        );
//        Path wrongPath = Path.of("C:\\Users\\Nikolay\\Desktop\\wrongFolder");
        try {
            System.out.println(tree(rightPath));
//            rightPath = Path.of("C:\\Users\\Nikolay\\Desktop\\test");
//            System.out.println(tree(rightPath));
//            System.out.println(tree(wrongPath));
//            System.out.println(tree(null));
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
