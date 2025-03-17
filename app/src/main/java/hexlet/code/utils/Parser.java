package hexlet.code.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public static Map<String, Object> getData(String filepath) throws Exception {
        Path fullPath = Paths.get(filepath);
        if (Files.notExists(fullPath)) {
            Path currentDirectory = Paths.get(System.getProperty("user.dir"));
            Path relativePath = currentDirectory.resolve("src/main/resources/files").resolve(filepath);
            if (Files.exists(relativePath)) {
                fullPath = relativePath;
            }
        }
        String content = Files.readString(fullPath);
        return parse(content);
    }
}
