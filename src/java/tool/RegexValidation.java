package tool;

public class RegexValidation {
    
    public static boolean onlyLetters(String text) {
        return text.matches("[a-zA-Z ]+");
    }
    
    public static boolean onlyNumbers(String text) {
        return text.matches("\\d+");
    }
    
    public static boolean isDecimalNumber(String text) {
        return text.matches("[0-9.]+");
    }
}
