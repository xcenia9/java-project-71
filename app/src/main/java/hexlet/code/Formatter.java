package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

public class Formatter {
    public static Object getFormatter(String format) {
        switch (format) {
            case "plain":
                return new Plain();
            case "stylish":
            default:
                return new Stylish();
        }
    }
}
