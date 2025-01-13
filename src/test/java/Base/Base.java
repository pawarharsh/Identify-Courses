package Base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	
	public WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public String browser;
	
	public WebDriver driverSetup(String br) {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("src//test//java//config//config.properties"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(br.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
		}
		if(br.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}
		return driver;
	}
	
	public void applyImplicitWait() {
		
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
}
