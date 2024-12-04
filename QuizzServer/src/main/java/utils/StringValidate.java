package utils;

import java.util.regex.Pattern;

public class StringValidate {
    public static final String NORMAL_NAME_CHAR_VALID = "^[a-zA-Z\\s.-]+$";
    public static final String PHONE_CHAR_VALID = "^[0-9+\\s-]+$";
    public static final String NICE_DIGIT = "^\\d{9}$";
    public static final String NICE_TO_ELEVENT_DIGIT = "^\\d{9,11}$";
    public static final String EMAIL_VALID = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    
    // Hàm kiểm tra chuỗi không rỗng
    public static boolean CheckNotNull(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Hàm kiểm tra chuỗi nằm trong khoảng min và max ký tự
    public static boolean CheckLengthInRange(String input, int min, int max) {
        if (input.trim() == null) {
            return false;
        }
        int length = input.trim().length();
        return length >= min && length <= max;
    }

    // Hàm kiểm tra chuỗi có khớp với regex
    public static boolean CheckMatchRegex(String input, String regex) {
        if (input.trim() == null || regex == null) {
            return false;
        }
        return Pattern.matches(regex, input.trim());
    }

    // Hàm kiểm tra chuỗi có phải số thực không
    public static Float CheckIsFloat(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        try {
            float finput = Float.parseFloat(input.trim());
            return finput; 
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Hàm kiểm tra chuỗi có phải số nguyên không
    public static Integer CheckIsInteger(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        try {
            int intinput = Integer.parseInt(input.trim());
            return intinput; 
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean CheckEmailValid(String input) {
        return CheckMatchRegex(input.trim(), EMAIL_VALID);
    }

    public static boolean CheckPhoneValid(String input){
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        if (CheckMatchRegex(input, PHONE_CHAR_VALID)) {
            if(input.charAt(0) == '0' && CheckMatchRegex(input.substring(1), NICE_DIGIT)) return true;
            if(input.charAt(0) == '+' && CheckMatchRegex(input.substring(1), NICE_TO_ELEVENT_DIGIT)) return true;
        }
        return false;
    }
}
