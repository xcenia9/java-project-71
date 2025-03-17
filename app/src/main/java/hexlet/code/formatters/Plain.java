package hexlet.code.formatters;

import java.util.Map;
import java.util.Set;
import java.util.Optional;
import java.util.TreeSet;
import java.util.Objects;

public class Plain {
    public static String format(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder();

        for (var key : allKeys) {
            appendPlainFormat(result, key, Optional.ofNullable(data1.get(key)), Optional.ofNullable(data2.get(key)));
        }

        return result.toString();
    }

    private static void appendPlainFormat(
            StringBuilder result,
            String key,
            Optional<Object> value1,
            Optional<Object> value2
    ) {
        if (value1.isPresent() && value2.isPresent()) {
            appendBothValues(result, key, value1.get(), value2.get());
        } else if (value1.isPresent()) {
            appendFirstValue(result, key, value1.get());
        } else {
            appendSecondValue(result, key, value2.get());
        }
    }

    private static void appendBothValues(StringBuilder result, String key, Object value1, Object value2) {
        if (!Objects.equals(value1, value2)) {
            result.append("Property '").append(key).append("' was updated. From ")
                    .append(formatValue(value1)).append(" to ")
                    .append(formatValue(value2)).append("\n");
        }
    }

    private static void appendFirstValue(StringBuilder result, String key, Object value1) {
        result.append("Property '").append(key).append("' was removed\n");
    }

    private static void appendSecondValue(StringBuilder result, String key, Object value2) {
        result.append("Property '").append(key).append("' was added with value: ")
                .append(formatValue(value2)).append("\n");
    }

    private static String formatValue(Object value) {
        if (value instanceof Map || value instanceof Iterable) {
            return "[complex value]";
        }
        return value == null ? "null" : value.toString();
    }
}
