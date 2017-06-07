import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Dim;
import org.apache.poi.hssf.record.formula.functions.Days360;
import org.apache.xerces.impl.dv.xs.DayDV;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;



/**
 * Created by innoraft on 17/5/17.
 */
public class contact_us {

    ExtentReports report;
    ExtentTest test;
    WebDriver driver;
    private String report_num = "4";
//    private String page_link = "http://hcl.lc/contact-us/customer";
//    private String submitted_url = "http://hcl.lc/contact-us/customer/thank-you";
    private String page_link = "https://www.hcltech.com/contact-us/customer";
    private String submitted_url = "https://www.hcltech.com/contact-us/customer/thank-you";


    @BeforeClass

        public void setup() {

        System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        report = new ExtentReports("/home/innoraft/IdeaProjects/Contact us form testing/reports/contact_us_page/automation_report" + report_num + ".html",true);
        test = report.startTest("Hcl contact us page form testing");
    }

    /*
     *  CASE 1
     */

    @Test(priority = 0,enabled = false)
        public void test_page1() throws InterruptedException {

        test.log(LogStatus.INFO,"TEST CASE 1");
        driver.get(page_link);
        test.log(LogStatus.PASS,"Given link opens sucessfully.");
        test.log(LogStatus.INFO,"CASE 1: NO field has been filled.");

        WebDriverWait myWait = new WebDriverWait(driver, 30);
        myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("edit-submitted-full-name"))));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.document.getElementById('edit-submit').click()");
        test.log(LogStatus.INFO,"Submit button is clicked");

        TimeUnit.SECONDS.sleep(5);
        String current_url = driver.getCurrentUrl();
        function checkUrl = new function();
        boolean value = checkUrl.check_url(current_url,submitted_url);
        if (value){
            test.log(LogStatus.PASS,"Test is passed successfully. Form is submitted and page directs to thank you page.");
        }
        else {
            test.log(LogStatus.FAIL,"Test is failed. Form is not submitted.");
        }
        TimeUnit.SECONDS.sleep(5);
    }

    /*
     *  CASE 2
     */

    @Test(priority = 8, enabled = true)
        public void test_page2() throws InterruptedException {

        test.log(LogStatus.INFO,"TEST CASE 2");
        driver.get(page_link);
        test.log(LogStatus.PASS,"Given link opens successfully.");
        test.log(LogStatus.INFO,"CASE 2: All the fields have been filled except required fields");

        driver.findElement(By.id("edit-submitted-organization")).sendKeys ("hcl");
        test.log(LogStatus.INFO,"Organisation is filled with value 'hcl'");

        WebElement country = driver.findElement(By.id("edit-submitted-country"));
        Select country_obj = new Select(country);
        country_obj.selectByVisibleText("India");
        test.log(LogStatus.INFO,"Country India is selected");

        driver.findElement(By.id("edit-upload-multifile")).sendKeys(
                "/home/innoraft/Downloads/hcl_mit_ese_iot_paper (3).pdf");
        test.log(LogStatus.INFO,"File is uploaded");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.document.getElementById('edit-submit').click()");
        test.log(LogStatus.INFO,"Submit button is clicked");

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

        String current_url = driver.getCurrentUrl();
        function checkUrl = new function();
        boolean value = checkUrl.check_url(current_url,submitted_url);
        if (value){
            test.log(LogStatus.PASS,"Test is passed successfully. Form is submitted and page directs to thank you page.");
        }
        else {
            test.log(LogStatus.FAIL,"Test is failed. Form is not submitted.");
        }

        TimeUnit.SECONDS.sleep(5);
    }

    /*
     *  CASE 3
     */

    @Test(priority = 2, enabled = false)
        public void test_page3() throws InterruptedException {
        test.log(LogStatus.INFO,"TEST CASE 3");
        driver.get(page_link);
        test.log(LogStatus.PASS,"Given link opens successfully.");
        test.log(LogStatus.INFO,"CASE 3: Only the required fields have been filled. ");

        WebDriverWait myWait = new WebDriverWait(driver, 30);
        myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("edit-submitted-full-name"))));

        driver.findElement(By.id("edit-submitted-full-name")).sendKeys ("Mahak Jain");
        test.log(LogStatus.INFO,"Name is filled with value 'Mahak Jain'");

        driver.findElement(By.id("edit-submitted-email-address")).sendKeys ("mahak@aa.com");
        test.log(LogStatus.INFO,"Email is filled with value 'mahak@aa.com' ");

        driver.findElement(By.id("edit-submitted-phone")).sendKeys ("098765");
        test.log(LogStatus.INFO,"PHone number is filled with value 098765");

        WebElement query_type = driver.findElement(By.id("edit-submitted-query-type"));
        Select query_type_obj = new Select(query_type);
        query_type_obj.selectByVisibleText("Investors");
        test.log(LogStatus.INFO,"Investors query type is selected.");

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id("edit-submitted-message-comments")));
        driver.findElement(By.id("edit-submitted-message-comments")).sendKeys ("query query query query query query ");
        test.log(LogStatus.INFO,"Query commment is filled.");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.document.getElementById('edit-submit').click()");
        test.log(LogStatus.INFO,"Submit button is clicked");

        driver.switchTo().alert().dismiss();
        String current_url = driver.getCurrentUrl();
        function checkUrl = new function();
        boolean value = checkUrl.check_url(current_url,submitted_url);
        if (value){
            test.log(LogStatus.PASS,"Test is passed successfully. Form is submitted and page directs to thank you page.");
        }
        else {
            test.log(LogStatus.FAIL,"Test is failed. Form is not submitted.");
        }

        TimeUnit.SECONDS.sleep(5);
    }


    /*
     *  CASE 4
     */

    @Test(priority = 3,enabled = false)
        public void test_page4() throws InterruptedException {
        test.log(LogStatus.INFO,"TEST CASE 4");
        driver.get(page_link);
        test.log(LogStatus.PASS,"Given link opens successfully.");
        test.log(LogStatus.INFO,"CASE 4: Selecting different options from Query type. Here two cases will be run. Analysts (Industry and Financial) and Staffing Partners will be selected in this case.");

        String arr[][] = {{"lucky","lucky@aa.com","jdhgg","565465465","Haiti","Analysts (Industry and Financial)","Manager","Energy","dghevfehfvgh","hjg jgg gyygyg ghh"
                ,"geg jhbfh hhjh ","query query query","/home/innoraft/Downloads/hcl_mit_ese_iot_paper (3).pdf"},
                {"Mahak Jain","mahak@aa.com","hcl","56565656","India","Staffing Partners","MD","Logistics","Innoraft","Test Test Test Test Test Test Test "
                ,"Test Test Test Test Test Test Test ","test test",""}};

        for(int i = 0; i < 2; i++) {

            driver.get(page_link);
            i++;
            test.log(LogStatus.PASS,"Given link opens for " + i + " th time");
            i--;

            WebDriverWait myWait = new WebDriverWait(driver, 30);
            myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("edit-submitted-full-name"))));

            driver.findElement(By.id("edit-submitted-full-name")).sendKeys(arr[i][0]);
            test.log(LogStatus.INFO,"Full name is filled");

            driver.findElement(By.id("edit-submitted-email-address")).sendKeys(arr[i][1]);
            test.log(LogStatus.INFO,"Email Address is filled");

            driver.findElement(By.id("edit-submitted-organization")).sendKeys(arr[i][2]);
            test.log(LogStatus.INFO,"Oraganisation is filled");

            driver.findElement(By.id("edit-submitted-phone")).sendKeys(arr[i][3]);
            test.log(LogStatus.INFO,"Phone number is filled");

            WebElement country = driver.findElement(By.id("edit-submitted-country"));
            // Select value from dropdown
            Select country_obj = new Select(country);
            country_obj.selectByVisibleText(arr[i][4]);
            test.log(LogStatus.INFO,"Country is selected");

            WebElement query_type = driver.findElement(By.id("edit-submitted-query-type"));
            Select query_type_obj = new Select(query_type);
            query_type_obj.selectByVisibleText(arr[i][5]);
            test.log(LogStatus.INFO,"Query type is selected");

            WebElement designation = driver.findElement(By.id("edit-submitted-designation"));
            WebElement industry = driver.findElement(By.id("edit-submitted-your-industry"));
            WebElement company_name = driver.findElement(By.id("edit-submitted-company-name"));
            WebElement partnership_int = driver.findElement(By.id("edit-submitted-partnership-interest"));
            WebElement brief_overview = driver.findElement(By.id("edit-submitted-brief-overview-of-your-company"));

            if (designation.isDisplayed()) {
                Select designation_obj = new Select(designation);
                designation_obj.selectByVisibleText(arr[i][6]);
                test.log(LogStatus.INFO,"Designation is selected");
            }

            else if (industry.isDisplayed()) {
                Select industry_obj = new Select(industry);
                industry_obj.selectByVisibleText(arr[i][7]);
                test.log(LogStatus.INFO,"Industry is selected");
            }
            else if (company_name.isDisplayed()) {
                company_name.sendKeys(arr[i][8]);
                test.log(LogStatus.INFO,"Company name is filled");

                partnership_int.sendKeys(arr[i][9]);
                test.log(LogStatus.INFO,"What interests you to partner with HCL Technologies? is filled");

                Actions focus = new Actions(driver);
                focus.moveToElement(brief_overview);
                brief_overview.sendKeys(arr[i][10]);
                test.log(LogStatus.INFO,"Brief overview of your company is filled");
            }

            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(By.id("edit-submitted-message-comments")));
            driver.findElement(By.id("edit-submitted-message-comments")).sendKeys(arr[i][11]);
            test.log(LogStatus.INFO,"Query is filled");

            // upload file
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("document.getElementById('edit-upload-multifile').focus();");
            driver.findElement(By.id("edit-upload-multifile")).sendKeys(arr[i][12]);
            test.log(LogStatus.INFO,"File is uploaded");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.document.getElementById('edit-submit').click()");
            test.log(LogStatus.INFO,"Submit button is clicked");

            String current_url = driver.getCurrentUrl();
            function checkUrl = new function();
            boolean value = checkUrl.check_url(current_url,submitted_url);
            i++;
            if (value){
                test.log(LogStatus.PASS,"Test is passed successfully for " + i + " th time . Form is submitted and page directs to thank you page.");
            }
            else {
                test.log(LogStatus.FAIL,"Test is failed for " + i + " th time. Form is not submitted.");
            }
            i--;
            TimeUnit.SECONDS.sleep(5);
        }
    }


    /*
     *  CASE 5
     */

    @Test(priority = 4,enabled = false)
        public void test_page5() throws InterruptedException {
        test.log(LogStatus.INFO,"TEST CASE 5");
        driver.get(page_link);
        test.log(LogStatus.PASS,"Given link opens successfully.");
        test.log(LogStatus.INFO,"CASE 5: In this case query type 'Request for Services' will be selected and under that job title's other text box will be filled. ");

        WebDriverWait myWait = new WebDriverWait(driver, 30);
        myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("edit-submitted-full-name"))));

        driver.findElement(By.id("edit-submitted-full-name")).sendKeys ("qwertyu");
        test.log(LogStatus.INFO,"Name is filled.");

        driver.findElement(By.id("edit-submitted-email-address")).sendKeys ("asdfg@sdfgbh.com");
        test.log(LogStatus.INFO,"Email address is filled.");

        driver.findElement(By.id("edit-submitted-organization")).sendKeys ("dvbjbvfbv");
        test.log(LogStatus.INFO,"Organisation is filled.");

        driver.findElement(By.id("edit-submitted-phone")).sendKeys ("6546546511");
        test.log(LogStatus.INFO,"Phone number is filled.");

        WebElement query_type = driver.findElement(By.id("edit-submitted-query-type"));
        Select query_type_obj = new Select(query_type);
        query_type_obj.selectByVisibleText("Request for Services");
        test.log(LogStatus.INFO,"Query type is selected");

        WebElement designation = driver.findElement(By.id("edit-submitted-designation"));

        if (designation.isDisplayed()) {
            Select designation_obj = new Select(designation);
            designation_obj.selectByVisibleText("Other");
            test.log(LogStatus.INFO,"JOb title is selected");
            WebElement other_job = driver.findElement(By.id("edit-submitted-other-job"));
            if (other_job.isDisplayed()) {other_job.sendKeys("Student");test.log(LogStatus.INFO,"Other job textarea is filled");}
        }
        driver.findElement(By.id("edit-submitted-message-comments")).sendKeys ("demo demo demo demo demo demo demo demo demo demo demo demo demo demo demo ");
        test.log(LogStatus.INFO,"Query comment is filled");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.document.getElementById('edit-submit').click()");
        test.log(LogStatus.INFO,"Submit button is clicked");

        String current_url = driver.getCurrentUrl();
        function checkUrl = new function();
        boolean value = checkUrl.check_url(current_url,submitted_url);
        if (value){
            test.log(LogStatus.PASS,"Test is passed successfully. Form is submitted and page directs to thank you page.");
        }
        else {
            test.log(LogStatus.FAIL,"Test is failed . Form is not submitted.");
        }
        TimeUnit.SECONDS.sleep(5);
    }


    /*
     *  CASE 6
     */

    @Test(priority = 5,enabled = false)
        public void test_page6() throws InterruptedException {
        test.log(LogStatus.INFO,"TEST CASE 6");
        driver.get(page_link);
        test.log(LogStatus.PASS,"Given link opens successfully.");
        test.log(LogStatus.INFO,"CASE 6: In this case multiple files will be uploaded");

        WebDriverWait myWait = new WebDriverWait(driver, 30);
        myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("edit-submitted-full-name"))));

        driver.findElement(By.id("edit-submitted-full-name")).sendKeys ("lkjhgf");
        test.log(LogStatus.INFO,"Name is filled. ");

        driver.findElement(By.id("edit-submitted-email-address")).sendKeys ("lkjhgdfg@fghj.com");
        test.log(LogStatus.INFO,"Email address is filled.");

        driver.findElement(By.id("edit-submitted-organization")).sendKeys ("dfghjkl");
        test.log(LogStatus.INFO,"Organisation is filled.");

        driver.findElement(By.id("edit-submitted-phone")).sendKeys ("09876534");
        test.log(LogStatus.INFO,"Phone number is filled.");

        WebElement query_type = driver.findElement(By.id("edit-submitted-query-type"));
        Select query_type_obj = new Select(query_type);
        query_type_obj.selectByVisibleText("Media and Journalists");
        test.log(LogStatus.INFO,"Query type is selected.");

        driver.findElement(By.id("edit-submitted-message-comments")).sendKeys ("Form is filled for testing purpose.");
        test.log(LogStatus.INFO,"Query comment is filled.");

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('edit-upload-multifile').focus();");
        driver.findElement(By.id("edit-upload-multifile")).sendKeys("/home/innoraft/Downloads/hcl_mit_ese_iot_paper (3).pdf");
        driver.findElement(By.id("edit-upload-multifile")).sendKeys("/home/innoraft/Downloads/test.xls");
        test.log(LogStatus.INFO,"Multifile file is uploaded.");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.document.getElementById('edit-submit').click()");
        test.log(LogStatus.INFO,"Submit button is clicked");

        String current_url = driver.getCurrentUrl();
        function checkUrl = new function();
        boolean value = checkUrl.check_url(current_url,submitted_url);
        if (value){
            test.log(LogStatus.PASS,"Test is passed successfully. Form is submitted and page directs to thank you page.");
        }
        else {
            test.log(LogStatus.FAIL,"Test is failed . Form is not submitted.");
        }
        TimeUnit.SECONDS.sleep(5);
    }


    /*
     *  CASE 7
     */

    @Test(priority = 6,enabled = true)
        public void test_page7() throws InterruptedException {
        test.log(LogStatus.INFO,"TEST CASE 7");
        driver.get(page_link);
        test.log(LogStatus.PASS,"Given link opens successfully.");
        test.log(LogStatus.INFO,"CASE 7: In this case extension which is not allowed is uploaded.");

        WebDriverWait myWait = new WebDriverWait(driver, 30);
        myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("edit-submitted-full-name"))));

        driver.findElement(By.id("edit-submitted-full-name")).sendKeys ("lkjhgf");
        test.log(LogStatus.INFO,"Name is filled. ");

        driver.findElement(By.id("edit-submitted-email-address")).sendKeys ("lkjhgdfg@fghj.com");
        test.log(LogStatus.INFO,"Email address is filled.");

        driver.findElement(By.id("edit-submitted-organization")).sendKeys ("dfghjkl");
        test.log(LogStatus.INFO,"Organisation is filled.");

        driver.findElement(By.id("edit-submitted-phone")).sendKeys ("09876534");
        test.log(LogStatus.INFO,"Phone number is filled.");

        WebElement query_type = driver.findElement(By.id("edit-submitted-query-type"));
        Select query_type_obj = new Select(query_type);
        query_type_obj.selectByVisibleText("Media and Journalists");
        test.log(LogStatus.INFO,"Query type is selected.");

        driver.findElement(By.id("edit-submitted-message-comments")).sendKeys ("Form is filled for testing purpose.");
        test.log(LogStatus.INFO,"Query comment is filled.");

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('edit-upload-multifile').focus();");
        driver.findElement(By.id("edit-upload-multifile")).sendKeys("/home/innoraft/Downloads/prescription.jpg");
        test.log(LogStatus.INFO,"File is uploaded.");

        driver.switchTo().alert().accept();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.document.getElementById('edit-submit').click()");
        test.log(LogStatus.INFO,"Submit button is clicked");

        String current_url = driver.getCurrentUrl();
        function checkUrl = new function();
        boolean value = checkUrl.check_url(current_url,submitted_url);
        if (value){
            test.log(LogStatus.PASS,"Test is passed successfully. Form is submitted and page directs to thank you page.");
        }
        else {
            test.log(LogStatus.FAIL,"Test is failed . Form is not submitted.");
        }
        TimeUnit.SECONDS.sleep(5);
    }

    /*
     *  CASE 8
     */

    @Test(priority = 7,enabled = true)
        public void test_page8() throws InterruptedException {

        function randomemail = new function();
        final String Email = randomemail.randomEmail();

        test.log(LogStatus.INFO,"TEST CASE 8");
        driver.get(page_link);
        test.log(LogStatus.PASS,"Given link opens successfully.");
        test.log(LogStatus.INFO,"CASE 8: Random email is used.");
        driver.findElement(By.id("edit-submitted-full-name")).sendKeys ("Test");
        test.log(LogStatus.INFO,"Name is filled with value 'Test'");

        driver.findElement(By.id("edit-submitted-email-address")).sendKeys (Email);
        test.log(LogStatus.INFO,"Email is filled with value "+ Email);

        driver.findElement(By.id("edit-submitted-phone")).sendKeys ("456789654");
        test.log(LogStatus.INFO,"PHone number is filled with value 456789654");

        WebElement query_type = driver.findElement(By.id("edit-submitted-query-type"));
        Select query_type_obj = new Select(query_type);
        query_type_obj.selectByVisibleText("Partners and Alliances");
        test.log(LogStatus.INFO,"Partners and Alliances query type is selected.");
        WebElement company_name = driver.findElement(By.id("edit-submitted-company-name"));
        WebElement partnership_int = driver.findElement(By.id("edit-submitted-partnership-interest"));
        WebElement brief_overview = driver.findElement(By.id("edit-submitted-brief-overview-of-your-company"));

        if (company_name.isDisplayed()) {
            company_name.sendKeys("Innoraft");
            test.log(LogStatus.INFO,"Company name is filled");

            partnership_int.sendKeys("Test Test Test Test Test Test Test ");
            test.log(LogStatus.INFO,"What interests you to partner with HCL Technologies? is filled");

            brief_overview.sendKeys("Test Test Test Test Test Test Test ");
            test.log(LogStatus.INFO,"Brief overview of your company is filled");
        }

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id("edit-submitted-message-comments")));
        driver.findElement(By.id("edit-submitted-message-comments")).sendKeys ("query query query query query query ");
        test.log(LogStatus.INFO,"Query commment is filled.");

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('edit-upload-multifile').focus();");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.document.getElementById('edit-submit').click()");
        test.log(LogStatus.INFO,"Submit button is clicked");

        String current_url = driver.getCurrentUrl();
        function checkUrl = new function();
        boolean value = checkUrl.check_url(current_url,submitted_url);
        if (value){
            test.log(LogStatus.PASS,"Test is passed successfully. Form is submitted and page directs to thank you page.");
        }
        else {
            test.log(LogStatus.FAIL,"Test is failed. Form is not submitted.");
        }

        TimeUnit.SECONDS.sleep(5);
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
        String subject = "HCL contact us page form testing";
        String filepath = "/home/innoraft/IdeaProjects/Contact us form testing/reports/contact_us_page/automation_report" + report_num + ".html";
        String reportname = "Test report contact us form ";
        String mailbody = "Hi All" + "\n\n" + "Please find the automation test results" + "\n\n" + "Regards,"
                + "\n" + "Testing team";

        function SendMail = new function();
        SendMail.sendmail(username, password, from_email, to_email, cc, bcc, subject, filepath, reportname, mailbody);
        driver.close();
    }
}
