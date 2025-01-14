package Base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	
	public WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public ChromeOptions options;
	public EdgeOptions e_options;
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
			MyChromeOptions mcp = new MyChromeOptions();
			options = mcp.setChromeOptions();
			driver = new ChromeDriver(options);
		}
		if(br.equalsIgnoreCase("edge")){
			MyEdgeOptions mep = new MyEdgeOptions();
			e_options = mep.setChromeOptions();
			driver = new EdgeDriver(e_options);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
}
