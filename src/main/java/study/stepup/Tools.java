package study.stepup;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class Tools {

    public static final String ROOT_PATH = System.getProperty("user.dir");
    public static final String SRC_DIR_PATH = "/src/main/java/study/stepup/lab3";
    public static final String TARGET_FILE_PATH = "/src/main/resources/lab3.txt";
    public static final String LINE_DELIMITER = "\n---------\n";
    public static final Comparator<Path> FILENAME_STRING_COMPARATOR = Comparator.comparing(o -> o.getFileName().toString());
    public static final char NEW_LINE = '\n';

    public static void main(String[] args) {
        try (
                Stream<Path> stream = Files.list(Paths.get(ROOT_PATH + SRC_DIR_PATH));
                FileWriter fileWriter = new FileWriter(ROOT_PATH + TARGET_FILE_PATH)
        ) {
            var files = stream
                    .filter(file -> !Files.isDirectory(file))
                    .sorted(FILENAME_STRING_COMPARATOR)
                    .toList();
            fileWriter.write(NEW_LINE);
            for (var file : files) {
                fileWriter.append(file.getFileName().toString());
                fileWriter.append(LINE_DELIMITER);
                try (var inputStream = new FileInputStream(file.toFile())) {
                    fileWriter.append(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                fileWriter.append(NEW_LINE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
