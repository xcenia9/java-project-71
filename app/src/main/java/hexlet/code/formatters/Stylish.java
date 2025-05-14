
package hexlet.code.formatters;

import java.util.List;
import java.util.Map;


public class Stylish {
    public static final String REMOVED_LINE_FORMAT = "  - %s: %s\n";
    public static final String ADD_LINE_FORMAT = "  + %s: %s\n";
    public static final String SAME_LINE_FORMAT = "    %s: %s\n";
    public static final String UPDATED_LINE_FORMAT = "  - %s: %s\n  + %s: %s\n";

    public static String formatStylish(List<Map<String, Object>> result1) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<String, Object> diffLine : result1) {
            var fieldStatus = (String) diffLine.get("STATUS");
            var fieldName = (String) diffLine.get("FIELD");
            var oldFieldValue = diffLine.get("OLD_VALUE");
            var newFieldValue = diffLine.get("NEW_VALUE");
            result.append(
                switch (fieldStatus) {
                    case "REMOVED" -> REMOVED_LINE_FORMAT.formatted(fieldName, oldFieldValue);
                    case "ADDED" -> ADD_LINE_FORMAT.formatted(fieldName, newFieldValue);
                    case "SAME" -> SAME_LINE_FORMAT.formatted(fieldName, oldFieldValue);
                    case "UPDATED" -> UPDATED_LINE_FORMAT.formatted(fieldName, oldFieldValue, fieldName, newFieldValue);
                    default -> "";
                });
        }
        return result.append("}").toString();

    }
}
