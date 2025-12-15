package org.arin;

public class Util {

    /**
     * Converts a string into Title Case (each word starts with a capital letter)
     * @param str the input string (words separated by spaces)
     * @return title-cased version of the string, or null if str is null
     */
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
