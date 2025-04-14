package hexlet.code.formatters;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.stream.Collectors;

public class Plain {
    public static String format(Map<String, Object> data1, Map<String, Object> data2) {
        List<String> changes = new ArrayList<>();

        List<String> sortedDataKeys = sortKeys(data1, data2);

        for (String key : sortedDataKeys) {
            boolean inData1 = data1.containsKey(key);
            boolean inData2 = data2.containsKey(key);

            if (inData1 && inData2) {
                Object value1 = data1.get(key);
                Object value2 = data2.get(key);

                if (!Objects.equals(value1, value2)) {
                    changes.add("Property '" + key + "' was updated. From "
                            + formatValue(value1) + " to " + formatValue(value2));
                }
            } else if (inData1 && !inData2) {
                changes.add("Property '" + key + "' was removed");
            } else if (!inData1 && inData2) {
                changes.add("Property '" + key + "' was added with value: " + formatValue(data2.get(key)));
            }
        }

        return String.join("\n", changes);
    }

    private static List<String> sortKeys(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> mergedKeys = new HashSet<>(data1.keySet());
        mergedKeys.addAll(data2.keySet());

        return mergedKeys.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof Collection || value instanceof Map) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return String.valueOf(value);
        }
    }
}
