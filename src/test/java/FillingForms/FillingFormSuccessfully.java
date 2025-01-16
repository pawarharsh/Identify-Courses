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
	public FillingFormSuccessfully(WebDriver driver) {
		this.driver=driver;
	}

	public String getMsg() throws InterruptedException {

		String title = driver.getCurrentUrl();
		
		Thread.sleep(1000);
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		String sc="Screenshots\\SuccessMsg.png";
		try {
			FileUtils.copyFile(screenshot, new File(sc));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println("--------Success Message----------");
		System.out.println();
		if(title.contains("https://www.coursera.org/campus/thank-you")) {
			System.out.println("Success");
			System.out.println();
			System.out.println("---------------------------------------");
			return "Success";
		}
		else {
			System.out.println("Fail");
			System.out.println();
			System.out.println("---------------------------------------");
			return "Fail";
		}
	}
	
}
