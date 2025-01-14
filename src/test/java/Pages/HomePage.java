package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Base;

public class HomePage extends Base{
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="search-autocomplete-input")
	WebElement searchBox;
	
	@FindBy(id="react-autowhatever-1")
	List<WebElement>list;

	public void openURL() {
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
	public boolean isSearchBoxVisible() {
		return searchBox.isDisplayed();
	}
	
	public int autoSuggestionListSize() throws InterruptedException {
		searchBox.click();
		Thread.sleep(5000);
		searchBox.sendKeys("Web Development");
		Thread.sleep(5000);
        int size = list.size();
        
        return size;
		
	}
	
	public void click() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"react-autowhatever-1-section-1-item-2\"]/div/div/div[2]/span")));
		WebElement we=driver.findElement(By.xpath("//*[@id=\"react-autowhatever-1-section-1-item-2\"]/div/div/div[2]/span"));
		we.click();
		Thread.sleep(3000);
	}
	
	
}
