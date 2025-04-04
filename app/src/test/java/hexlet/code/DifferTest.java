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

public class DifferTest {

    private String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path))).trim();
    }

    private static Map<String, Object> data1;
    private static Map<String, Object> data2;

    @BeforeAll
    public static void before() throws Exception {
        data1 = getData("src/test/resources/introductoryYaml.yml");
        data2 = getData("src/test/resources/introductoryJson.json");
    }

    @Test
    public void testGenerateStylishFormat() throws IOException {
        String expectedOutput = readFile("src/test/resources/testStylish.txt");
        String result = Differ.generate(data1, data2, "stylish");
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testGenerateJsonFormat() throws IOException {
        String expectedOutput = readFile("src/test/resources/testJson.json");
        String result = Differ.generate(data1, data2, "json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode expected = mapper.readTree(expectedOutput);
        JsonNode actualResult = mapper.readTree(result);

        assertEquals(expected, actualResult);
    }

    @Test
    public void testGeneratePlainFormat() throws IOException {
        String expectedOutput = readFile("src/test/resources/testPlain.txt");
        String result = Differ.generate(data1, data2, "plain");
        assertEquals(expectedOutput, result);
    }
}
