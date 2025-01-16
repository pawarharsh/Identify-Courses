package FillingForms;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Base.Base;

public class FillingFormSuccessfully extends Base {

    WebDriver driver;

    // Constructor to initialize WebDriver
    public FillingFormSuccessfully(WebDriver driver) {
        this.driver = driver;
    }

    // Method to get the success message after form submission
    public String getMsg() throws InterruptedException {

        // Get the current URL
        String url = driver.getCurrentUrl();
        
        Thread.sleep(1000);

        // Take a screenshot of the success message
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String sc = "Screenshots\\SuccessMsg.png";
        try {
            FileUtils.copyFile(screenshot, new File(sc));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the success message to the console
        System.out.println("--------Success Message----------");
        System.out.println();
        if (url.contains("https://www.coursera.org/campus/thank-you")) {
            System.out.println("Success");
            System.out.println();
            System.out.println("---------------------------------------");
            return "Success";
        } else {
            System.out.println("Fail");
            System.out.println();
            System.out.println("---------------------------------------");
            return "Fail";
        }
    }
}