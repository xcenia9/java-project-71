package hexlet.code.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content) throws Exception {
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        try {
            return jsonObjectMapper.readValue(content, Map.class);
        } catch (JsonParseException e) {
            ObjectMapper yamlObjectMapper = new ObjectMapper(new YAMLFactory());
            return yamlObjectMapper.readValue(content, Map.class);
        }
    }
}
