package hexlet.code.utils;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void testParseJson() throws Exception {
        String jsonContent = "{\"key1\": \"value1\", \"key2\": 2}";
        Map<String, Object> result = Parser.parse(jsonContent);
        assertEquals("value1", result.get("key1"));
        assertEquals(2, result.get("key2"));
    }

    @Test
    public void testParseYaml() throws Exception {
        String yamlContent = "key1: value1\nkey2: 2";
        Map<String, Object> result = Parser.parse(yamlContent);
        assertEquals("value1", result.get("key1"));
        assertEquals(2, result.get("key2"));
    }

    @Test
    public void testGetDataJsonFile() throws Exception {
        Map<String, Object> result = Parser.getData("src/test/resources/test.json");
        assertEquals("value1", result.get("key1"));
        assertEquals(2, result.get("key2"));
    }

    @Test
    public void testGetDataYamlFile() throws Exception {
        Map<String, Object> result = Parser.getData("src/test/resources/test.yml");
        assertEquals("value1", result.get("key1"));
        assertEquals(2, result.get("key2"));
    }

    @Test
    public void testFormatDifferentValues() {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("key1", "value1");
        data1.put("key2", 2);
        Map<String, Object> data2 = new HashMap<>();
        data2.put("key1", "value2");
        data2.put("key2", 2);
        String expected = "{\n" +
                "- key1: value1\n" +
                "+ key1: value2\n" +
                "  key2: 2\n" +
                "}";
        String result = Stylish.format(data1, data2);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatSameValues() {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("key1", "value1");
        data1.put("key2", 2);
        Map<String, Object> data2 = new HashMap<>();
        data2.put("key1", "value1");
        data2.put("key2", 2);
        String expected = "{\n" +
                "  key1: value1\n" +
                "  key2: 2\n" +
                "}";
        String result = Stylish.format(data1, data2);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatOnlyInFirstMap() {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("key1", "value1");
        Map<String, Object> data2 = new HashMap<>();
        String expected = "{\n" +
                "- key1: value1\n" +
                "}";
        String result = Stylish.format(data1, data2);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatOnlyInSecondMap() {
        Map<String, Object> data1 = new HashMap<>();
        Map<String, Object> data2 = new HashMap<>();
        data2.put("key2", "value2");
        String expected = "{\n" +
                "+ key2: value2\n" +
                "}";
        String result = Stylish.format(data1, data2);
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateStylish() {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("key1", "value1");
        data1.put("key2", 2);
        Map<String, Object> data2 = new HashMap<>();
        data2.put("key1", "value2");
        data2.put("key2", 2);
        String expected = "{\n" +
                "- key1: value1\n" +
                "+ key1: value2\n" +
                "  key2: 2\n" +
                "}";
        String result = Differ.generate(data1, data2, "stylish");
        assertEquals(expected, result);
    }

}

