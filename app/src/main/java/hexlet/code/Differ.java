package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Json;
import hexlet.code.utils.FileReader;

import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Object formatter = Formatter.getFormatter(format);
        Map<String, Object> data1 = FileReader.getData(filePath1);
        Map<String, Object> data2 = FileReader.getData(filePath2);
        return switch (formatter.getClass().getSimpleName()) {
            case "Stylish" -> Stylish.format(data1, data2);
            case "Json" -> Json.format(data1, data2);
            case "Plain" -> Plain.format(data1, data2);
            default -> "";
        };
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
