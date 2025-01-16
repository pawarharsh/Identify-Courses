package Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class Courses extends Base {

    WebDriver driver;

    // Constructor to initialize WebDriver and PageFactory elements
    public Courses(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElement for English Language selection
    @FindBy(xpath = "//span[text()='English']/parent::*")
    WebElement EnglishLanguage;

    // WebElement for Beginner Level selection
    @FindBy(xpath = "//span[text()='Beginner']/parent::*")
    WebElement BegineerLevel;

    // List of WebElements for course headings
    @FindBy(xpath = "//*[@class='cds-ProductCard-content']//h3")
    List<WebElement> heading;

    // List of WebElements for courses
    @FindBy(xpath = "//*[@class='cds-ProductCard-content']")
    List<WebElement> courses;

    // List of WebElements for course ratings
    @FindBy(xpath = "//*[@class='cds-CommonCard-ratings']/div/div/span")
    List<WebElement> ratings;

    // List of WebElements for course descriptions
    @FindBy(xpath = "//*[@class='cds-CommonCard-metadata']/p")
    List<WebElement> description;

    // Method to select the English language option
    public WebElement languageSelection() throws InterruptedException {
        Thread.sleep(3000);
        return EnglishLanguage;
    }

    // Method to select the Beginner level option
    public WebElement levelSelection() throws InterruptedException {
        Thread.sleep(3000);
        return BegineerLevel;
    }

    // Method to check if the first course is displayed
    public boolean isFirstCourseDisplayed() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("------------- COURSE 1 ---------------");
        System.out.println();
        System.out.println(heading.get(0).getText());
        System.out.println(ratings.get(0).getText());
        System.out.println(description.get(0).getText());
        System.out.println();
        System.out.println("--------------------------------------");
        if (heading.get(0).getText() != null) {
            return true;
        } else {
            return false;
        }
    }

    // Method to check if the second course is displayed
    public boolean isSecondCourseDisplayed() throws InterruptedException {
        System.out.println("----------- COURSE 2 -----------------");
        System.out.println();
        System.out.println(heading.get(1).getText());
        System.out.println(ratings.get(1).getText());
        System.out.println(description.get(1).getText());
        System.out.println();
        System.out.println("--------------------------------------");
        if (heading.get(1).getText() != null) {
            return true;
        } else {
            return false;
        }
    }

    // Method to navigate back to the home page
    public void returnToHomePage() {
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
    }

    // Method to check if a course is opened in a new tab
    public boolean isCourseOpenedInNewTab() throws InterruptedException {
        courses.get(0).click();
        Set<String> windows = driver.getWindowHandles();
        List<String> windowsList = new ArrayList<String>(windows);
        String parent = windowsList.get(0);
        String child = windowsList.get(1);
        String beforeSwitchingTitle = driver.getTitle();
        driver.switchTo().window(child);
        Thread.sleep(3000);
        String afterSwitchingTitle = driver.getTitle();
        driver.close();
        driver.switchTo().window(parent);
        Thread.sleep(3000);
        if (beforeSwitchingTitle.equals(afterSwitchingTitle)) {
            return false;
        } else {
            return true;
        }
    }
}
