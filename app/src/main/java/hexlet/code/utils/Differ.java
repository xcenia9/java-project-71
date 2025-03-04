package hexlet.code.utils;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder("{\n");
        for (var key : allKeys) {
            appendStringBuilder(result, key, data1.get(key), data2.get(key));
        }
        result.append("}");
        return result.toString();
    }

    public static void appendStringBuilder(StringBuilder result, String key, Object value1, Object value2) {
        if (value1 == null && value2 != null) {
            result.append("+ ").append(key).append((": ")).append(value2).append("\n");
        } else if (value1 != null && value2 == null) {
            result.append("- ").append(key).append(": ").append(value1).append("\n");
        } else if (!Objects.equals(value1, value2)) {
            result.append(("- ")).append(key).append((": ")).append(value1).append("\n")
                    .append("+ ").append(key).append((": ")).append((value2)).append("\n");
        } else {
            result.append("  ").append(key).append((": ")).append(value1).append(("\n"));
        }
    }
}
