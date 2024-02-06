package games.enchanted.fallingBlockParticles.util;

public class Conversions {
    public static String boolToString(boolean bool, String trueString, String falseString){
        return bool ? trueString : falseString;
    }
    public static String boolString(boolean bool) {
        return boolToString(bool, "true", "false");
    }

    public static Boolean stringToBool(String str, String trueString, String falseString, boolean caseSensitive){
        if(caseSensitive) {
            return str.equals(trueString) ? true : false;
        }
        return str.toLowerCase().equals(trueString.toLowerCase()) ? true : false;
    }
    public static Boolean stringToBool(String str, boolean caseSensitive){
        return stringToBool(str,"true","false",caseSensitive);
    }
    public static Boolean stringToBool(String str){
        return stringToBool(str,"true","false", false);
    }

    public static int defaultedStringToInt(String str, int defaultVal) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defaultVal;
        }
    }
}
