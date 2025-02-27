package hexlet.code.utils;

import java.util.*;

public class Differ {
    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder("{\n");
        for (var key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);
            if (!data1.containsKey(key)) {
                result.append("+ ").append(key).append((": ")).append(value2).append("\n");
            } else if (!data2.containsKey(key)) {
                result.append("- ").append(key).append(": ").append(value1).append("\n");
            } else if (!value1.equals(value2)) {
                result.append(("- ")).append(key).append((": ")).append(value1).append("\n")
                        .append("+ ").append(key).append((": ")).append((value2)).append("\n");
            } else {
                result.append("  ").append(key).append((": ")).append(value1).append(("\n"));
            }
        }
        result.append("}");
        return result.toString();
    }
}