package config;

public enum BrowserName {
    FIREFOX("Firefox"),
    IE("IE"),
    CHROME("Chrome");

    private final String browserName;

    BrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

}
