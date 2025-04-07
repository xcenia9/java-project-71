package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;

import static hexlet.code.utils.FileReader.getData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path))).trim();
    }

    private static Map<String, Object> data1yml;
    private static Map<String, Object> data2yml;
    private static Map<String, Object> data1Json;
    private static Map<String, Object> data2Json;

    @BeforeAll
    public static void before() throws Exception {
        data1yml = getData("src/test/resources/files/firstYaml.yml");
        data2yml = getData("src/test/resources/files/secondYaml.yml");
        data1Json = getData("src/test/resources/files/firstJson.json");
        data2Json = getData("src/test/resources/files/secondJson.json");
    }
    @Test
    public void testGenerateDefaultFormat() throws IOException {
        String expected = readFile("src/test/resources/resultFiles/testStylish.txt");
        String result = Differ.generate(data1yml, data2yml);
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateStylishFormat() throws IOException {
        String expected = readFile("src/test/resources/resultFiles/testStylish.txt");
        String result = Differ.generate(data1yml, data2yml, "stylish");
        assertEquals(expected, result.trim());
    }

    @Test
    public void testGenerateJsonFormat() throws IOException {
        String expected = readFile("src/test/resources/resultFiles/testJson.json");
        String result = Differ.generate(data1Json, data2Json, "json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedJson = mapper.readTree(expected);
        JsonNode actualResult = mapper.readTree(result);

        assertEquals(expectedJson, actualResult);
    }

    @Test
    public void testGeneratePlainFormat() throws IOException {
        String expected = readFile("src/test/resources/resultFiles/testPlain.txt");
        String result = Differ.generate(data1yml, data2yml, "plain");
        assertEquals(expected.replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }
}
