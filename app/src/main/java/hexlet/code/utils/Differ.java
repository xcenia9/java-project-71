package hexlet.code.utils;

import java.util.*;

public class Differ {
    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder("{\n");
        for (var key : allKeys) {
            appendStringBuilder(result, key, Optional.ofNullable(data1.get(key)), Optional.ofNullable(data2.get(key)));
        }
        result.append("}");
        return result.toString();
    }
    public static void appendStringBuilder(StringBuilder result, String key, Optional<Object> value1, Optional<Object> value2) {
        if (value1.isPresent() && value2.isPresent()) {
            appendBothKeys(result, key, value1.get(), value2.get());
        } else if (value1.isPresent()) {
            appendFirstValue(result, key, value1.get());
        } else {
            appendSecondValue(result, key, value2.get());
        }
    }
    public static void appendBothKeys(StringBuilder result, String key, Object value1, Object value2) {
        if (!Objects.equals(value1, value2)) {
            result.append(("- ")).append(key).append((": ")).append(value1).append("\n")
                    .append("+ ").append(key).append((": ")).append((value2)).append("\n");
        } else {
            result.append("  ").append(key).append((": ")).append(value1).append(("\n"));
        }
    }
    public static void appendFirstValue(StringBuilder result, String key, Object value1) {
        result.append("- ").append(key).append(": ").append(value1).append("\n");
    }
    public static void appendSecondValue(StringBuilder result, String key, Object value2) {
        result.append("+ ").append(key).append((": ")).append(value2).append("\n");
    }
}
