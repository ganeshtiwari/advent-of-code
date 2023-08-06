package Utils;

import java.net.URL;

public class FileUtils {
    public static URL readResource(String resourcePath) {
        return FileUtils.class.getClassLoader().getResource(resourcePath);
    }
}
