package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Objects;

public class DiffBuilder {
    public static List<Map<String, Object>> buildDifference(Map<String,
            Object> map1, Map<String, Object> map2) {
        var result = new ArrayList<Map<String, Object>>();

        var allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (String key : allKeys) {
            var value1 = map1.get(key);
            var value2 = map2.get(key);
            var keyCompareResults = new HashMap<String, Object>();
            keyCompareResults.put("FIELD", key);

            if (!map2.containsKey(key)) {
                keyCompareResults.put("STATUS", "REMOVED");
                keyCompareResults.put("OLD_VALUE", value1);
            } else if (!map1.containsKey(key)) {
                keyCompareResults.put("STATUS", "ADDED");
                keyCompareResults.put("NEW_VALUE", value2);
            } else if (Objects.equals(value1, value2)) {
                keyCompareResults.put("STATUS", "SAME");
                keyCompareResults.put("OLD_VALUE", value1);
            } else {
                keyCompareResults.put("STATUS", "UPDATED");
                keyCompareResults.put("OLD_VALUE", value1);
                keyCompareResults.put("NEW_VALUE", value2);
            }
            result.add(keyCompareResults);
        }
        return result;
    }
}
