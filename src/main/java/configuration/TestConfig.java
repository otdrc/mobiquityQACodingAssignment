package configuration;

public final class TestConfig {
    private static TestConfig instance;
    private String baseUrl;

    private TestConfig() {
    }

    public static TestConfig getInstance() {
        if (instance == null) {
            instance = new TestConfig();
            instance.setBaseUrl(ConfigParser.getValue("BaseUrl"));
        }
        return instance;
    }

    private void setBaseUrl(String baseUrl) {
        this.instance.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return this.instance.baseUrl;
    }
}
