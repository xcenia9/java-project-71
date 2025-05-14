
package hexlet.code.formatters;

import java.util.Map;
import java.util.List;

public class Plain {
    public static final String REMOVED_LINE_FORMAT = "Property '%s' was removed\n";
    public static final String ADD_LINE_FORMAT = "Property '%s' was added with value: %s\n";
    public static final String UPDATED_LINE_FORMAT = "Property '%s' was updated. From %s to %s\n";

    public static String formatPlain(List<Map<String, Object>> diff) {


        StringBuilder result = new StringBuilder();
        for (Map<String, Object> diffLine : diff) {
            var fieldStatus = (String) diffLine.get("STATUS");
            var fieldName = (String) diffLine.get("FIELD");
            var oldFieldValue = diffLine.get("OLD_VALUE");
            var newFieldValue = diffLine.get("NEW_VALUE");
            result.append(
                switch (fieldStatus) {
                    case "REMOVED" -> REMOVED_LINE_FORMAT.formatted(fieldName);
                    case "ADDED" -> ADD_LINE_FORMAT.formatted(fieldName, getValue(newFieldValue));
                    case "UPDATED" -> UPDATED_LINE_FORMAT.formatted(fieldName,
                         getValue(oldFieldValue), getValue(newFieldValue));
                    case "SAME" -> "";
                    default -> ""; });
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }
    private static String getValue(Object obj) {
        if (obj instanceof String) {
            return "'" + obj + "'";
        } else if (obj instanceof List || obj instanceof Map) {
            return "[complex value]";
        }
        return String.valueOf(obj).trim();
    }
}
