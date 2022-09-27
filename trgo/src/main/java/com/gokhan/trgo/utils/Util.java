package com.gokhan.trgo.utils;

import java.util.Locale;

public class Util {
    public static String capitalizedWords(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder sbCapitalizedWords = new StringBuilder(sentence.length());

        for (String word : words) {

            if (word.length() > 1)
                sbCapitalizedWords
                        .append(word.substring(0, 1).toUpperCase(Locale.ROOT))
                        .append(word.substring(1).toLowerCase(Locale.ROOT));
            else
                sbCapitalizedWords.append(word.toUpperCase());

            sbCapitalizedWords.append(" ");
        }
        return sbCapitalizedWords.toString().trim();
    }
}
