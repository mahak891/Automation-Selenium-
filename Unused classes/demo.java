import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by innoraft on 22/5/17.
 */
public class demo {

    public static void main(String[] args) throws IOException {



        System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.MINUTES);
        driver.get("http://hclstg.sites.innoraft.com/contact-us/customer");

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        String winHandleBefore = driver.getWindowHandle();
        driver.switchTo().frame(0);
        driver.findElement(By.tagName("input")).click();


        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandle.equals(winHandleBefore))
                driver.switchTo().window(winHandle);
        }


        System.out.println("new Window title:"+driver.getTitle());
        //driver.switchTo().window(winHandleBefore);
        //System.out.println("old Window title:"+driver.getTitle());
    }
}
