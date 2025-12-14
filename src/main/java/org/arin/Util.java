package org.arin;

public class Util {

    public static String toTitleCase(String str) {
        if (str == null)
            return null;

        String[] words = str.trim().split(" ");
        String result = "";

        for (String word : words) {
            if (word.isEmpty())
                continue;

            result += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
        }

        return result.trim();
    }
}
