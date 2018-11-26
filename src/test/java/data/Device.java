package data;

public enum Device {

    NEXUS5X("Android",
            "8.0",
            "Nexus_5X_API_26"
            );

    private String platformName;
    private String platformVersion;
    private String deviceName;

    Device(String platformName, String platformVersion, String deviceName) {
        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.deviceName = deviceName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }
}
