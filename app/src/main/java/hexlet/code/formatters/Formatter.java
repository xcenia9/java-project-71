package hexlet.code.formatters;


import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(String format, List<Map<String, Object>> result) throws Exception {

        return switch (format) {
            case "json" -> Json.formatJson(result);
            case "plain" -> Plain.formatPlain(result);
            case "stylish" -> Stylish.formatStylish(result);
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }
}
