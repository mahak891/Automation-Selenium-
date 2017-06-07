import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;




/**
 * Created by innoraft on 16/5/17.
 */
public class form   {

    ExtentReports report;
    ExtentTest test;
//    WebDriver driver;
    ChromeDriver driver;
    private String base_url = "http://hcl.lc";
    private String filenumber = "10";
    private String url = base_url + "/contact-us/customer/thank-you";

    @BeforeClass

        public void setup() throws IOException{

//        System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();

        ChromeOptions options = new ChromeOptions();
        File file = new File("/usr/lib/chromium-browser/chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        options.addArguments("start-maximized");
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        caps.setBrowserName("chrome");
//        caps.setPlatform(Platform.WINDOWS);
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(caps);

        report = new ExtentReports("/home/innoraft/IdeaProjects/Contact us form testing/reports/automation_report" + filenumber + ".html", true);
        test = report.startTest("Hcl contact us sticky form testing");
        test.log(LogStatus.PASS,"Sucessfully launches chrome driver");


    }

    @Test(priority = 0)

        public void test1() throws InterruptedException {

        test.log(LogStatus.INFO,"TEST 1");
        driver.get(base_url);
        test.log(LogStatus.PASS,"Given link opens sucessfully");

        test.log(LogStatus.INFO,"Case 1 : NO Values has been passed to the form");
        // Click on sticky button
        driver.findElement(By.className("ctools-collapsible-handle")).click();
        test.log(LogStatus.INFO, "Contact us form opens ");

        //click on submit button
        driver.findElement(By.id("edit-submit")).click();
        test.log(LogStatus.INFO, "Submit button clicked");

        TimeUnit.SECONDS.sleep(5);
        String current_url = driver.getCurrentUrl();
        function checkUrl = new function();
        boolean value = checkUrl.check_url(current_url,url);
        if (value){
        test.log(LogStatus.PASS,"Test is passed successfully. Form is submitted and page directs to thank you page.");
        }
        else {
        test.log(LogStatus.FAIL,"Test is failed. Form is not submitted.");
        }
        TimeUnit.SECONDS.sleep(5);
    }

    @Test(priority = 1)

        public void test2() throws InterruptedException {

        test.log(LogStatus.INFO,"TEST 2");
        driver.get(base_url);
        test.log(LogStatus.PASS,"Given link opens sucessfully");

        test.log(LogStatus.INFO,"Case 2 : Required values have been omitted");

        // Click on sticky button
        driver.findElement(By.className("ctools-collapsible-handle")).click();
        test.log(LogStatus.INFO, "Contact us form opens ");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("edit-submitted-organization")).sendKeys("Innoraft");
        test.log(LogStatus.INFO, "Organisation filled");

        WebElement select = driver.findElement(By.id("edit-submitted-country"));
        Select sel = new Select(select);
        sel.selectByVisibleText("Peru");
        test.log(LogStatus.INFO, "Value selected from dropdown country");

        driver.findElement(By.id("edit-submit")).click();
        test.log(LogStatus.INFO, "Submit button clicked");


        String current_url = driver.getCurrentUrl();
        function checkUrl = new function();
        boolean value = checkUrl.check_url(current_url,url);
        if (value){
            test.log(LogStatus.PASS,"Test is passed successfully. Form is submitted and page directs to thank you page.");
        }
        else {
            test.log(LogStatus.FAIL,"Test is failed. Form is not submitted.");
        }

        TimeUnit.SECONDS.sleep(5);
    }

    @Test(priority = 2)

        public void test3() throws InterruptedException {

        test.log(LogStatus.INFO,"TEST 3");
        driver.get(base_url);
        test.log(LogStatus.PASS,"Given link opens sucessfully");
        test.log(LogStatus.INFO,"Case 3 : ONLY Required values have been filled");

        // Click on sticky button
        driver.findElement(By.className("ctools-collapsible-handle")).click();
        test.log(LogStatus.INFO, "Contact us form opens ");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebDriverWait myWait = new WebDriverWait(driver, 10);
        myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("edit-submitted-full-name"))));

        driver.findElement(By.id("edit-submitted-full-name")).sendKeys ("Mahak Jain");
        test.log(LogStatus.INFO, "Full name filled");

        driver.findElement(By.id("edit-submitted-email-address")).sendKeys ("mahak@aajghg.com");
        test.log(LogStatus.INFO,"Email is filled");

        driver.findElement(By.id("edit-submitted-phone")).sendKeys ("46565465465");
        test.log(LogStatus.INFO,"Phone number is filled");

        driver.findElement(By.id("edit-submitted-message-comments")).sendKeys ("demo demo demo demo demo demo demo demo demo demo demo demo demo demo demo ");
        test.log(LogStatus.INFO,"Query message is filled");

        driver.findElement(By.id("edit-submit")).click();
        test.log(LogStatus.INFO, "Submit button clicked");

        String current_url = driver.getCurrentUrl();
        function checkUrl = new function();
        boolean value = checkUrl.check_url(current_url,url);
        if (value){
            test.log(LogStatus.PASS,"Test is passed successfully. Form is submitted and page directs to thank you page.");
        }
        else {
            test.log(LogStatus.FAIL,"Test is failed. Form is not submitted.");
        }
        TimeUnit.SECONDS.sleep(5);

    }

    @Test(enabled = false)

        public void test() throws InterruptedException {

//            report = new ExtentReports("/home/innoraft/IdeaProjects/Contact us form testing/reports/automation_report" + filenumber + ".html", true);
//            test = report.startTest("Hcl contact us sticky form testing");

            String arr[][] = {{"Mahak Jain", "mahak@dgecdg.com", "hcl", "56565656", "Peru", "demo demo demo demo demo ", "/home/innoraft/Downloads/hcl_mit_ese_iot_paper (3).pdf"},
                    {"Abcd jain", "abcd@aa.com", "abcd", "9999999", "India", "demo2 demo2 demo2 demo2 demo2 ", "/home/innoraft/Downloads/hcl_mit_ese_iot_paper (3).pdf"}};

            for (int i = 0; i < 2; i++) {
                i++;
                driver.get(base_url);
                test.log(LogStatus.INFO, "Given link opens for " + i + " th time");
                i--;

                String title = driver.getTitle();
                if (title.equals("HCL Technologies: Software, Technical & Global Core IT Solution Providers")) {
                    test.log(LogStatus.PASS, "Verified title of the page");
                } else {
                    test.log(LogStatus.FAIL, "Title is not verified");
                }

                driver.findElement(By.className("ctools-collapsible-handle")).click();
                test.log(LogStatus.INFO, "Contact us form opens ");

                WebDriverWait myWait = new WebDriverWait(driver, 30);
                myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("edit-submitted-full-name"))));

                // Enter value into textbox
                driver.findElement(By.id("edit-submitted-full-name")).sendKeys(arr[i][0]);
                test.log(LogStatus.INFO, "Full name filled");

                // Enter value into textbox
                driver.findElement(By.id("edit-submitted-email-address")).sendKeys(arr[i][1]);
                test.log(LogStatus.INFO, "Email address filled");

                // Enter value into textbox
                driver.findElement(By.id("edit-submitted-organization")).sendKeys(arr[i][2]);
                test.log(LogStatus.INFO, "Organisation filled");

                // Enter value into textbox
                driver.findElement(By.id("edit-submitted-phone")).sendKeys(arr[i][3]);
                test.log(LogStatus.INFO, "Phone Number filled");

                // Select value from dropdown
                WebElement select = driver.findElement(By.id("edit-submitted-country"));
                Select sel = new Select(select);
                sel.selectByVisibleText(arr[i][4]);
                test.log(LogStatus.INFO, "Value selected from dropdown country");

                // Enter value into textbox
                driver.findElement(By.id("edit-submitted-message-comments")).sendKeys(arr[i][5]);
                test.log(LogStatus.INFO, "Query filled");

                // upload file
                driver.findElement(By.id("edit-upload-multifile")).sendKeys(arr[i][6]);
                test.log(LogStatus.INFO, "File uploaded");

                //click on submit button
                driver.findElement(By.id("edit-submit")).click();
                test.log(LogStatus.INFO, "Submit button clicked");

                TimeUnit.SECONDS.sleep(5);
            }
    }


    @AfterClass
        public void teardown() {

        report.endTest(test);
        report.flush();

        // Send mail
        String username = "mahak.jain@innoraft.com";
        String password = "";
        String from_email = "mahak.jain@innoraft.com";
        String to_email = "mahakjain1010@gmail.com";
        String cc = "";
        String bcc = "nishant.singhal@innoraft.com";
        String subject = "HCL contact us sticky form testing";
        String filepath = "/home/innoraft/IdeaProjects/Contact us form testing/reports/automation_report" + filenumber + ".html";
        String reportname = "Test report sticky form ";
        String mailbody = "Hi All" + "\n\n" + "Please find the automation test results" + "\n\n" + "Regards,"
                + "\n" + "Testing team";

        function sendMail = new function();
        sendMail.sendmail(username, password, from_email, to_email, cc, bcc, subject, filepath, reportname, mailbody);
        driver.close();
    }
}
