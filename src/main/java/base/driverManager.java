package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class driverManager {

    /*
     * ===========================
     * Setup Appium Capabilities:
     * ==========================
     * UiAutomator2Options,Android and IOs Driver,property file.
     * ThreadLocal using for parallel run,
     */

    // -----------------------------------------------------------------------
    /*
     * UiAutomator2Options:- is used to define desired capability for launching an
     * android app.it tells to appium how to start and configure the session.
     */
    private static final ThreadLocal<UiAutomator2Options> androidOptions = new ThreadLocal<>();

    /*
     * Android/IOS Driver:-are responsible for sending commands to mobile
     * device/amulator via Appium.
     * They act as a bridge between your test script and the actual mobile device.
     */
    private static final ThreadLocal<AndroidDriver> androidDriver = new ThreadLocal<>();
    private static final ThreadLocal<IOSDriver> iosdriver = new ThreadLocal<>();

    /*
     * Property configuration:- get data from property file
     */

    private static final ThreadLocal<Properties> prop = ThreadLocal.withInitial(() -> {
        Properties properties = new Properties();
        String configPath = System.getProperty("config.path", "config.properties"); // Default if not set
        try (FileInputStream fileInputStream = new FileInputStream(configPath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    });

    // Encapsulation-Getter method to access "prop" object variables
    public static Properties getProperties() {
        return prop.get();
    }

    public static void capabilitys() {

        String platform = System.getProperty("platform", "android").toLowerCase();
        /*
         * gegetProperty() is a built-in method in Java. It belongs to the
         * java.lang.System class.
         */
        if (platform.equals("android")) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("AnandEmulator");
            options.setAutomationName("UiAutomator2");
            options.setApp("E:\\General-Store.apk");
            options.setCapability("appium:logLevel", "debug");
            try {
                androidDriver.set(new AndroidDriver(new URL("http://127.0.0.1:4723/"), options));
                System.out.println("App Launched Successfully!");
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Appium URL", e);
            }

        } else if (platform.equals("ios")) {
            XCUITestOptions options = new XCUITestOptions();
            options.setDeviceName("iPhoen 12");
            options.setPlatformVersion("platformVersion");
            options.setApp("apk path in Phone");
            options.setAutomationName("XCUITest");
            try {
                iosdriver.set(new IOSDriver(new URL("http://127.0.0.1:4723/"), options));
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Appium URL", e);
            }
        } else {
            throw new IllegalArgumentException("Invalid platform name! Use 'android' or 'ios'.");
        }

    }

    /*
     * Encapulation-This is getter method getObject (Becouse "private" static final
     * ThreadLocal<AndroidDriver> androidDriver)
     */
    public static AndroidDriver getAndroidDriver() {
        return androidDriver.get();
    }

    /*
     * Encapulation-This is getter method getObject (Becouse "private" static final
     * ThreadLocal<IOSDriver> iosdriver)
     */
    public static IOSDriver getIOSDriver() {
        return iosdriver.get();
    }

    // Explicit wait for each element-wait until visiblity
    public static WebElement waitForExpectedElement(final By by, int timeout) {
        WebDriverWait wait = new WebDriverWait(getAndroidDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // this is for quit the driver
    public static void quitDriver() {
        getAndroidDriver().quit();
    }

    public static void scrollToElement() {
        Map<String, Object> params = new HashMap<>();
        params.put("selector", "//*[@text='Zambia']");
        params.put("trackScrollEvents", false); // Hypothetical, Appium does NOT support this directly
        getAndroidDriver().executeScript("mobile: scroll", params);
    }

}
