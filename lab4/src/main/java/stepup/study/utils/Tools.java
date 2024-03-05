package stepup.study.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class Tools {
    public static List<Path> getFiles(Path dir, String filename, String extension) throws IOException {
        return Files.list(dir)
                .filter(p -> p.toFile().isFile()
                        && p.toFile().getName().startsWith(filename)
                        && p.toFile().getName().endsWith(extension))
                .collect(Collectors.toList());
    }

    public static Integer tryParseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static ZonedDateTime tryParseDate(String text) {
        try {
            return ZonedDateTime.parse(text);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
