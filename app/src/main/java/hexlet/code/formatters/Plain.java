package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.Objects;

public class Plain {
    public static String format(Map<String, Object> data1, Map<String, Object> data2) {
        List<String> changes = new ArrayList<>();

        List<String> sortedData1Keys = sortKeys(data1);
        List<String> sortedData2Keys = sortKeys(data2);

        for (String key : sortedData1Keys) {
            if (data2.containsKey(key)) {
                Object value1 = data1.get(key);
                Object value2 = data2.get(key);

                if (!Objects.equals(value1, value2)) {
                    changes.add("Property '" + key + "' was updated. From '"
                            + formatValue(value1) + "' to '" + formatValue(value2) + "'");
                }
            } else {
                changes.add("Property '" + key + "' was removed");
            }
        }
        for (String key : sortedData2Keys) {
            if (!data1.containsKey(key)) {
                changes.add("Property '" + key + "' was added with value: '" + formatValue(data2.get(key)) + "'");
            }
        }

        return String.join("\n", changes);
    }

    private static List<String> sortKeys(Map<String, Object> data) {
        return data.keySet().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof Collection || value instanceof Map) {
            return "[complex value]";
        } else {
            return String.valueOf(value);
        }
    }
}
