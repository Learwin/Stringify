package learwin.stringify.client;

public class LocalDelimiter {

    private static String delimiter = "";
    private static boolean ignoreDuplicates = false;

    public static void setDelimiter(String delimiter) {
        LocalDelimiter.delimiter = delimiter;
    }

    public static void setIgnoreDuplicates(boolean ignoreDuplicates) {
        LocalDelimiter.ignoreDuplicates = ignoreDuplicates;
    }

    public static String getDelimiter() {
        return delimiter;
    }

    public static boolean shouldIgnoreDuplicates() {
        return ignoreDuplicates;
    }
}
