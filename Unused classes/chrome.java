import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

/**
 * Created by innoraft on 2/6/17.
 */
public class chrome {
    public static void main(String[] args) throws IOException {

        ChromeOptions options = new ChromeOptions();
        File file = new File("/usr/lib/chromium-browser/chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        options.addArguments("start-maximized");
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        caps.setBrowserName("chrome");
        caps.setPlatform(Platform.WINDOWS);
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(caps);
        driver.get("https://google.com");


    }
}
