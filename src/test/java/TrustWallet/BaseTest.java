package TrustWallet;

import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    public AndroidDriver<AndroidElement> driver;

    @BeforeSuite
    public void init() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion", "13");
        //device ID should be replaced with your own device. The ID can be found in terminal with adb devices command
        capabilities.setCapability("deviceName","1C201FDF600B2W");
        //appPackage and appActivity can be found with adb logcat command in terminal
        capabilities.setCapability("appPackage","com.wallet.crypto.trustapp");
        capabilities.setCapability("appActivity","com.wallet.crypto.trustapp.ui.start.activity.RootHostActivity");
        //capabilities.setCapability("AndroidMobileCapabilityType.ADB_EXEC_TIMEOUT", "40000");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("automationName", "UiAutomator2");

        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); //App server default URL
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println("App is launched!");
    }

    @AfterSuite
    public void end() throws InterruptedException{

        driver.quit();
    }
}
