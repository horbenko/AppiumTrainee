package config;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Resources {

    /**
     * @param resource The name of the resource
     * @return Path to resource
     */
    public String getResource(String resource) {
        try {
            return Paths.get(WebDriverFactory.class.getResource(resource).toURI()).toFile().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resource;
    }

}
