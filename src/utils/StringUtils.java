package utils;

import java.util.List;

public class StringUtils {
    public static String splitByDash(List<String> documents) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (String document: documents){
            stringBuilder.append(document).append(";");
        }
        if(!documents.isEmpty()) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
