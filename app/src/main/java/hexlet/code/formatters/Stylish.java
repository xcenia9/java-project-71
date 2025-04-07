package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stylish {
    public static String format(Map<String, Object> data1, Map<String, Object> data2) {
        List<String> outputList = new ArrayList<>();
        Map<String, Object> tempData1 = new HashMap<>(data1);

        for (String key : data2.keySet()) {
            if (!tempData1.containsKey(key)) {
                String parameter = "  + " + key + ": " + formatValue(data2.get(key)) + "\n";
                outputList.add(parameter);
            } else {
                Object value1 = tempData1.get(key);
                Object value2 = data2.get(key);

                if (!equals(value1, value2)) {
                    String parameter = "  - " + key + ": " + formatValue(value1) + "\n" +
                            "  + " + key + ": " + formatValue(value2) + "\n";
                    outputList.add(parameter);
                }
                tempData1.remove(key);
            }
        }

        for (String key : tempData1.keySet()) {
            String parameter = "  - " + key + ": " + formatValue(tempData1.get(key)) + "\n";
            outputList.add(parameter);
        }

        for (String key : data1.keySet()) {
            if (data2.containsKey(key) && equals(data1.get(key), data2.get(key))) {
                String parameter = "    " + key + ": " + formatValue(data1.get(key)) + "\n";
                outputList.add(parameter);
            }
        }

        outputList.sort((v1, v2) -> CharSequence.compare(v1.replaceAll("[^a-zA-Z0-9]", ""), v2.replaceAll("[^a-zA-Z0-9]", "")));

        return outputList.stream().collect(Collectors.joining("", "{\n", "}"));
    }

    private static boolean equals(Object value1, Object value2) {
        if (value1 == null && value2 == null) return true;
        if (value1 == null || value2 == null) return false;

        return value1.equals(value2);
    }

    private static String formatValue(Object value) {
        if (value instanceof Map<?, ?>) {
            return "{" + ((Map<?, ?>) value).entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining(", ")) + "}";
        } else if (value instanceof Iterable<?>) {
            return value.toString();
        } else {
            return String.valueOf(value);
        }
    }
}