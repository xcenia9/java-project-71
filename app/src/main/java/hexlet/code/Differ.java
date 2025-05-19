package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.DiffBuilder;

import static hexlet.code.Parser.parse;

public class Differ {
    private static final String DEFAULT_FORMAT = "stylish";

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map1 = fileToMap(filePath1);
        Map<String, Object> map2 = fileToMap(filePath2);
        List<Map<String, Object>> result = DiffBuilder.buildDifference(map1, map2);
        return Formatter.format(format, result);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, DEFAULT_FORMAT);
    }

    public static Map<String, Object> fileToMap(String filePath) throws Exception {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path must be provided");
        }
        Path file = Paths.get(filePath);
        String format = getFormat(filePath);

        if (Files.exists(file)) {
            String content = Files.readString(file);
            return parse(content, format);
        }

        var resourceStream = Differ.class.getClassLoader().getResourceAsStream("files/" + filePath);
        if (resourceStream != null) {
            String content = new String(resourceStream.readAllBytes());
            return parse(content, format);
        }

        throw new Exception("File '" + filePath + "' not found in file system or resources");
    }


    private static String getFormat(String filePath) {
        String lowerCasePath = filePath.toLowerCase();
        int lastDotIndex = lowerCasePath.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == lowerCasePath.length() - 1) {
            return "";
        }
        return lowerCasePath.substring(lastDotIndex + 1);
    }
}
