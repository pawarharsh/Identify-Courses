package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class Levels extends Base {
    
    WebDriver driver;

    // Constructor to initialize WebDriver and PageFactory elements
    public Levels(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // List of WebElements for level checkboxes
    @FindBy(xpath = "/html/body/div[2]/div/div/main/div[1]/div/div/div/div/div[1]/div/div/div/div/div/div/div/div[4]/div/div[2]/div")
    List<WebElement> levelChecks;

    // Method to get the size of the list of levels and print them
    public int sizeOfListOfLevel() {
        System.out.println("------------Levels List---------------");
        System.out.println();
        // Iterate through the list of levels and print each one
        for (int i = 0; i < levelChecks.size(); i++) {
            System.out.println(levelChecks.get(i).getText());
        }
        System.out.println();
        System.out.println("--------------------------------------");
        return levelChecks.size(); // Return the size of the list
    }
}