package hexlet.code.utils;

import org.junit.Test;
import java.util.HashMap;
import static org.junit.Assert.assertNotNull;

public class DifferTest {

    @Test
    public void generateTest() {
        var testData1 = new HashMap<String, Object>();
        testData1.put("key1", "value1");
        testData1.put("key2", "value2");

        var testData2 = new HashMap<String, Object>();
        testData2.put("key3", "value3");
        testData2.put("key1", "value4");

        var result = Differ.generate(testData1, testData2);

        assertNotNull(result);
    }
}
