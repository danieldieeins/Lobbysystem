package live.nerotv.utils;

public class StringUtils {

    public static String formatString(String string) {
        return string.replace("&&","%and%").replace("&","§").replace("%and%","&");
    }
}