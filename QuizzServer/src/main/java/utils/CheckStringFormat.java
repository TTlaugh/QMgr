package utils;

import java.util.regex.Pattern;

public class CheckStringFormat {
    public static boolean isCorrectFormat(String str) {
        String regex = "^ST\\d{3}$";
        return Pattern.matches(regex, str);
    }

}
