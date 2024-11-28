package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.ParseException;

public class StringNormalization {
    // Xóa bỏ khoảng trống trùng lặp (chỉ giữ 1 khoảng trống giữa các từ)
    public static String removeDuplicateSpaces(String input) {
        if (input == null) return null;
        return input.trim().replaceAll("\\s+", " ");
    }
    
    // Xóa bỏ tất cả khoảng trống
    public static String removeAllSpaces(String input) {
        if (input == null) return null;
        return input.replaceAll("\\s+", "");
    }

    // Xóa bỏ các ký tự không phải là số
    public static String removeNonDigits(String input) {
        if (input == null) return null;
        return input.replaceAll("\\D", ""); 
    }

    // Xóa tất cả các ký tự char có trong chuỗi input
    public static String removeCharFromString(String input, char charToRemove) {
        if (input == null) return null;        
        return input.replaceAll(String.valueOf(charToRemove), "");
    }

    // Chuyển chuỗi thành kiểu viết tên riêng (chữ cái đầu in hoa, các chữ cái còn lại in thường)
    public static String convertToTitleCase(String input) {
        if (input == null) return null;
        input = removeDuplicateSpaces(input);
        
        // Tách chuỗi thành các từ và chuyển đổi từng từ
        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(word.substring(0, 1).toUpperCase()) 
                      .append(word.substring(1).toLowerCase()) 
                      .append(" "); 
            }
        }
        return result.toString().trim();
    }

    // Chuyển chuỗi thành số điện thoại
    public static String convertToPhoneNumber(String input) {
        if (input == null) return null;
        return removeAllSpaces(removeCharFromString(input, '-'));
    }    

    // Chuyển chuỗi thành ngày
    public static Object convertStringToDateTime(String input, String pattern) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("DateTimeFormatterPattern cannot be null or empty.");
        }

        try {
            if (pattern.contains("HH") || pattern.contains("mm") || pattern.contains("ss")) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(input, dateTimeFormatter);  // Trả về kiểu LocalDateTime            
            } else if (pattern.contains("yyyy") && pattern.contains("MM") && pattern.contains("dd")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                return dateFormat.parse(input);  // Trả về kiểu Date
            } else {
                throw new IllegalArgumentException("Unsupported DateTimeFormatterPattern.");
            }
        } catch (ParseException | java.time.format.DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format with the provided pattern.");
        }
    }
}
