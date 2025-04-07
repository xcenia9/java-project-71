package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Json;
import java.util.Map;

public class Differ {
    public static String generate(Map<String, Object> data1, Map<String, Object> data2, String format) {
        Object formatter = Formatter.getFormatter(format);
        return switch (formatter.getClass().getSimpleName()) {
            case "Stylish" -> Stylish.format(data1, data2);
            case "Json" -> Json.format(data1, data2);
            case "Plain" -> Plain.format(data1, data2);
            default -> "";
        };
    }

    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        return generate(data1, data2, "stylish");
    }
}
