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
    
    // Method to set up the WebDriver based on the browser type
    public WebDriver driverSetup(String br) {
        prop = new Properties();
        try {
            // Load properties from the configuration file
            prop.load(new FileInputStream("src//test//java//config//config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Set up ChromeDriver if the browser is Chrome
        if (br.equalsIgnoreCase("chrome")) {
            MyChromeOptions mcp = new MyChromeOptions();
            options = mcp.setChromeOptions();
            driver = new ChromeDriver(options);
        }
        
        // Set up EdgeDriver if the browser is Edge
        if (br.equalsIgnoreCase("edge")) {
            MyEdgeOptions mep = new MyEdgeOptions();
            e_options = mep.setChromeOptions();
            driver = new EdgeDriver(e_options);
        }
        
        // Set implicit wait for the WebDriver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}