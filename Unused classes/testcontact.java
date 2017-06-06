import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.text.normalizer.Utility;

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

/**
 * Created by innoraft on 29/5/17.
 */
public class testcontact {
    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://hcl.lc/contact-us/customer");
//        driver.manage().window().maximize();

//        WebDriverWait myWait = new WebDriverWait(driver, 30);
//        myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("edit-submitted-full-name"))));

        driver.findElement(By.id("edit-submitted-full-name")).sendKeys ("Mahak Jain");
        driver.findElement(By.id("edit-submitted-email-address")).sendKeys ("mahak@aa.com");
        driver.findElement(By.id("edit-submitted-organization")).sendKeys ("hcl");
        driver.findElement(By.id("edit-submitted-phone")).sendKeys ("56565656");

        WebElement country = driver.findElement(By.id("edit-submitted-country"));
        // Select value from dropdown
        Select country_obj = new Select(country);
        country_obj.selectByVisibleText("India");

        WebElement query_type = driver.findElement(By.id("edit-submitted-query-type"));
        Select query_type_obj = new Select(query_type);
        query_type_obj.selectByVisibleText("Analysts (Industry and Financial)");

        WebElement designation = driver.findElement(By.id("edit-submitted-designation"));
        WebElement industry = driver.findElement(By.id("edit-submitted-your-industry"));
        WebElement company_name = driver.findElement(By.id("edit-submitted-company-name"));
        WebElement partnership_int = driver.findElement(By.id("edit-submitted-partnership-interest"));
        WebElement brief_overview = driver.findElement(By.id("edit-submitted-brief-overview-of-your-company"));

        if (designation.isDisplayed()) {
            Select designation_obj = new Select(designation);
            designation_obj.selectByVisibleText("Manager");
        }

        else if (industry.isDisplayed()) {
            Select industry_obj = new Select(industry);
            industry_obj.selectByVisibleText("Energy");
        }

        else if (company_name.isDisplayed()) {
            company_name.sendKeys("hvhvhjv");
        }

        else if (partnership_int.isDisplayed()) {
            partnership_int.sendKeys("bvbfv bfdjf");
        }

        else if (brief_overview.isDisplayed()) {
            brief_overview.sendKeys("ndbfdnvgn bgrjryur");
        }

        driver.findElement(By.id("edit-submitted-message-comments")).sendKeys ("demo demo demo demo demo demo demo demo demo demo demo demo demo demo demo ");

        // upload file
        driver.findElement(By.id("edit-upload-multifile")).sendKeys(
                "/home/innoraft/Downloads/hcl_mit_ese_iot_paper (3).pdf");

        //click on submit button
        driver.findElement(By.id("edit-submit")).click();

        String thanku_page_text = driver.findElement(By.xpath("//div[@class='webform-confirmation']/p")).getText();

        String thank = "Thank you for your interest. We have received your query. Someone from HCL Technologies will get in touch with you shortly.";

        if(thanku_page_text.equals(thank)){
            System.out.print("Test Passed");
        }
        else
            System.out.print("Test Failed");


        final String username = "mahak.jain@innoraft.com";
        final String password = "mercury234";

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mahak.jain@innoraft.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("mahakjain1010@gmail.com, nishant.singhal@innoraft.com"));
            message.setSubject("HCL contact us page form testing");

            BodyPart messageBodyPart = new MimeBodyPart();
            BodyPart attachmentPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            // ATTACHMENT
            String file = "/home/innoraft/IdeaProjects/Contact us form testing/reports/contact_us_page/automation_report1.html";
            String filename = "AutomationTestReport";
            DataSource source = new FileDataSource(file);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(filename);
            multipart.addBodyPart(attachmentPart);

            // EMAIL BODY
            messageBodyPart.setText("Hi All" + "\n\n" + "Please find the automation test results" + "\n\n" + "Regards,"
                    + "\n" + "Testing team");
            multipart.addBodyPart(messageBodyPart);

            // SET CONTENT
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email report sent successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Email report sent failed...." + e.getMessage());

        }
                driver.close();
    }


}
