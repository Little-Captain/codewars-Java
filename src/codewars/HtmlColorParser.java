package codewars;

import java.util.Arrays;
import java.util.Map;

public class HtmlColorParser {

    private final Map<String, String> presetColors;

    public HtmlColorParser(Map<String, String> presetColors) {
        this.presetColors = presetColors;
    }

    public RGB parse(String color) {
        if (color.startsWith("#")) {
            color = color.replace("#", "");
        } else {
            color = presetColors.get(color.toLowerCase()).replace("#", "");
        }
        if (color.length() == 3) {
            color = color.replaceAll("(.)", "$1$1");
        }
        Integer[] rgb = Arrays
                .stream(color.replaceAll("(..)", "$1.").split("\\."))
                .map(s -> Integer.parseInt(s, 16))
                .toArray(Integer[]::new);
        if (rgb.length != 3) {
            return null;
        }
        return new RGB(rgb[0], rgb[1], rgb[2]);
    }
}
