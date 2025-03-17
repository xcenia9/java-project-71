package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.HashMap;
import java.util.Map;


public class Json {
    public static String format(Map<String, Object> data1, Map<String, Object> data2) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Map<String, Object> mergedData = new HashMap<>(data1);
        mergedData.putAll(data2);
        try {
            return mapper.writeValueAsString(mergedData);
        } catch (Exception e) {
            throw new RuntimeException("Error formatting to JSON", e);
        }
    }
}
