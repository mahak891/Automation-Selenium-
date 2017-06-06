import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by innoraft on 17/5/17.
 */
public class contact_us {

    ExtentReports report;
    ExtentTest test;
    WebDriver driver;

    @BeforeClass

        public void setup() {

        System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
        public void test() throws InterruptedException {

        report = new ExtentReports("/home/innoraft/IdeaProjects/Contact us form testing/reports/contact_us_page/automation_report1.html",true);
        test = report.startTest("Hcl contact us page form testing");

        String arr[][] = {{"lucky","lucky@aa.com","jdhgg","565465465","Haiti","Analysts (Industry and Financial)","Manager","Energy","dghevfehfvgh","hjg jgg gyygyg ghh"
                ,"geg jhbfh hhjh ","query query query","/home/innoraft/Downloads/hcl_mit_ese_iot_paper (3).pdf"},{"Mahak Jain","mahak@aa.com","hcl","56565656","India","Partners and Alliances","Director","Logistics","Innoraft","Test Test Test Test Test Test Test "
                ,"Test Test Test Test Test Test Test ","demo demo demo demo demo demo demo demo demo demo ","/home/innoraft/Downloads/hcl_mit_ese_iot_paper (3).pdf"}};

        for(int i = 0; i < 2; i++) {

            driver.get("http://hcl.lc/contact-us/customer");
            test.log(LogStatus.INFO,"Given link opens for " + i+1 + " th time");

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
//
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
            driver.findElement(By.id("edit-upload-multifile")).sendKeys(arr[i][12]);
            test.log(LogStatus.INFO,"File is uploaded");

            //click on submit button
//            WebElement element = driver.findElement(By.id("edit-submit"));
//            Actions actions = new Actions(driver);
//            actions.moveToElement(element).click().build().perform();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.document.getElementById('edit-submit').click()");
            test.log(LogStatus.INFO,"Submit button is clicked");


            String thank_you_page = driver.getCurrentUrl();
            String link = "http://hcl.lc/contact-us/customer/thank-you";
            if (thank_you_page.contains(link)) {
                System.out.print("Test Passed");
                test.log(LogStatus.PASS,"TEST PASSED for " + i + " th time. It successfully went to thank you page.");

            }
            else {
                System.out.print("Test Failed");
                test.log(LogStatus.FAIL, "TEST FAILED for " + i + " th time. Form is not submitted.");
            }
            TimeUnit.SECONDS.sleep(5);

//            String thanku_page_text = driver.findElement(By.xpath("//div[@class='webform-confirmation']/p")).getText();
//
//            String thank = "Thank you for your interest. We have received your query. Someone from HCL Technologies will get in touch with you shortly.";
//
//            if (thanku_page_text.equals(thank)) {
//                System.out.print("Test Passed");
//            }
//            else {
//                System.out.print("Test Failed");
//            }
        }
        report.endTest(test);
        report.flush();

        final String username = "mahak.jain@innoraft.com";
        final String password = "mercury234";

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.setTLS",true);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mahak.jain@innoraft.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("mahakjain1010@gmail.com"));
//            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(""));
            message.setSubject("HCL contact us page form testing");
//            message.setContent("<p>There is HTML report attached containing output of testcase</p>","text/html");

            MimeBodyPart attachementBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            String file = "/home/innoraft/IdeaProjects/Contact us form testing/reports/contact_us_page/automation_report1.html";
            String fileName = "report";
            DataSource source = new FileDataSource(file);
            attachementBodyPart.setDataHandler(new DataHandler(source));
            attachementBodyPart.setFileName(fileName);

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Hi All" + "\n\n" + "Please find the automation test results" + "\n\n" + "Regards,"
                    + "\n" + "Testing team");
            multipart.addBodyPart(textBodyPart);
            multipart.addBodyPart(attachementBodyPart);

            message.setContent(multipart);

            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
        public void teardown() {
        driver.close();
    }
}
