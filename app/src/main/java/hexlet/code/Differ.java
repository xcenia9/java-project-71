package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Differ {
    public static String generate(Map<String, Object> data1, Map<String, Object> data2, String format) {
        Object formatter = Formatter.getFormatter(format);
        if (formatter instanceof Stylish) {
            return ((Stylish) formatter).format(data1, data2);
        } else if (formatter instanceof Plain) {
            return ((Plain) formatter).format(data1, data2);
        }
        return "";
    }
}
