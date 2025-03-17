package hexlet.code.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.utils.Parser.parse;

public class FileReader {
    public static Map<String, Object> getData(String filepath) throws Exception {
        Path fullPath = Paths.get(filepath);
        if (Files.notExists(fullPath)) {
            Path currentDirectory = Paths.get(System.getProperty("user.dir"));
            Path relativePath = currentDirectory.resolve("src/main/resources/files").resolve(filepath);
            if (Files.exists(relativePath)) {
                fullPath = relativePath;
            }
        }
        String content = Files.readString(fullPath);
        return parse(content);
    }
}
