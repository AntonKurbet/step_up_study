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
    public static void main(String[] args) {
        try (
                Stream<Path> stream = Files.list(Paths.get(System.getProperty("user.dir") + "/src/main/java/study/stepup/lab3"));
                FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/src/main/resources/lab3.txt")
        ) {
            var files = stream
                    .filter(file -> !Files.isDirectory(file))
                    .sorted(new Comparator<>() {
                        @Override
                        public int compare(Path o1, Path o2) {
                            return o1.getFileName().toString().compareTo(o2.getFileName().toString());
                        }
                    })
                    .toList();
            fileWriter.write('\n');
            for (var file : files) {
                fileWriter.append(file.getFileName().toString());
                fileWriter.append("\n---------\n");
                try (var inputStream = new FileInputStream(file.toFile());) {
                    CharSequence buffer = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                    fileWriter.append(buffer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                fileWriter.append('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
