package parser;

import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.Reader;
import java.util.Map;
import java.util.logging.Logger;

public class ConfigParser {
    private static final String CONFIG_FILE_NAME = "./config.yml";

    private ConfigParser() {}

    public static String getValue(String key) {
        Yaml yaml = new Yaml();
        try {
            Reader yamlFile = new FileReader(CONFIG_FILE_NAME);

            Map<String, Object> yamlMaps = yaml.load(yamlFile);
            return (String) yamlMaps.get(key);
        } catch (Exception e) {
            Logger.getAnonymousLogger().severe(e.getMessage());
        }
        return null;
    }
}
