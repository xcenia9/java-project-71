package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path))).trim();
    }

    private static String firstYaml;
    private static String secondYaml;
    private static String firstJson;
    private static String secondJson;

    @BeforeAll
    public static void beforeAll() {
        firstYaml = "src/test/resources/files/firstYaml.yml";
        secondYaml = "src/test/resources/files/secondYaml.yml";
        firstJson = "src/test/resources/files/firstJson.json";
        secondJson = "src/test/resources/files/secondJson.json";
    }
    @Test
    public void testGenerateDefaultFormat() throws Exception {
        String expectedStylish = readFile("src/test/resources/resultFiles/testStylish.txt");
        String result = Differ.generate(firstYaml, secondYaml);
        assertEquals(expectedStylish, result);
    }

    @Test
    public void testGenerateStylishFormat() throws Exception {
        String expectedStylish = readFile("src/test/resources/resultFiles/testStylish.txt");
        String result = Differ.generate(firstYaml, secondYaml, "stylish");
        assertEquals(expectedStylish, result);
    }

    @Test
    public void testGenerateJsonFormat() throws Exception {
        String expectedJson = readFile("src/test/resources/resultFiles/testJson.json");
        String result = Differ.generate(firstJson, secondJson, "json");

        assertEquals(expectedJson, result);
    }

    @Test
    public void testGeneratePlainFormat() throws Exception {
        String expected = readFile("src/test/resources/resultFiles/testPlain.txt");
        String result = Differ.generate(firstYaml, secondYaml, "plain");
        assertThat(expected).isEqualTo(result);
    }
}
