package ru.vsu.csf.sapegin;

public class Utils {
    public static String getSubstringBeforePoint(String path) {
        int indexOfPointForExtension = path.lastIndexOf(".");
        return path.substring(0, indexOfPointForExtension);
    }

    public static String getSubstringAfterPoint(String path) {
        int indexOfPointForExtension = path.lastIndexOf(".");
        return path.substring(indexOfPointForExtension + 1);
    }
}
