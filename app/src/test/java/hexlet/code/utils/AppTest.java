package hexlet.code.utils;


import hexlet.code.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AppTest {
    private Map<String, Object> testData1;
    private Map<String, Object> testData2;
    private String resultTest;

    @BeforeEach
    public void beforeEach() {
        testData1 = new HashMap<>();
        testData1.put("key1", "value1");
        testData1.put("key2", "value2");
        testData1.put("key", "value");

        testData2 = new HashMap<>();
        testData2.put("key3", "value3");
        testData2.put("key1", "value4");
        testData2.put("key", "value");

        resultTest = "{\n  key: value\n- key1: value1\n+ key1: value4\n- key2: value2\n+ key3: value3\n}";
    }
    @Test
    public void generateTestNotNul() {
        String result = Differ.generate(testData1, testData2);
        assertNotNull(result);
    }
    @Test
    public void generateTestEqualsResult() {
        String result = Differ.generate(testData1, testData2);
        assertEquals(resultTest, result);
    }

    @Test
    public void generateEmptyData() {
        Map<String, Object> notData1 = new HashMap<>();
        Map<String, Object> notData2 = new HashMap<>();
        String emptyResultTest = "{\n}";
        var emptyResult = Differ.generate(notData1, notData2);
        assertEquals(emptyResultTest, emptyResult);
    }
    @Test
    public void testGetDataNotNull() throws Exception {
        assertNotNull(App.getData("filepath1.yml"));
    }
    @Test
    public void testFullPath() throws Exception {
        assertEquals(App.getData("src/main/resources/files/filepath2.yml"), App.getData("filepath2.yml"));
    }
    @Test
    public void testParseJson() throws Exception {
        String jsonContent = "{\"key\": \"value\"}";
        Map<String, Object> result = App.parse(jsonContent);
        assertEquals("value", result.get("key"));
    }
    @Test
    public void testParseYaml() throws Exception {
        String yamlContent = "key: value";
        Map<String, Object> result = App.parse(yamlContent);
        assertEquals("value", result.get("key"));
    }
}
