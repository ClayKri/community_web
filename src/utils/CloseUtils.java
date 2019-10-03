package utils;

import java.io.Closeable;
import java.io.IOException;

public final class CloseUtils {

    private CloseUtils() {}

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
