package tests.mobile;

import tests.mobile.AppiumTestListener;
import data.Device;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import static config.DriverInstanciator.*;

@Listeners(AppiumTestListener.class)
public class BaseMobileTest {

    @BeforeClass
    @Parameters("Device")
    public void setUp(String testDevice){
       Device castedTestDevice = Device.valueOf(testDevice.toUpperCase());
       setDriver(castedTestDevice);
    }

    @AfterClass
    public void tearDown() throws Exception{
        getAppiumDriver().quit();
    }

}
