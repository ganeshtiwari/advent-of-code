package Utils;

public class CommonUtils {
    public static boolean isDigit(String token) {
        for (char c : token.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
