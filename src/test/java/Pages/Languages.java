package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class Languages extends Base {

    WebDriver driver;

    // Constructor to initialize WebDriver and PageFactory elements
    public Languages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElement for the "Show More" button
    @FindBy(xpath = "/html/body/div[2]/div/div/main/div[1]/div/div/div/div/div[1]/div/div/div/div/div/div/div/div[2]/div[2]/button/span")
    WebElement showMore;

    // List of WebElements for language checkboxes
    @FindBy(xpath = "/html/body/div[2]/div/div/main/div[1]/div/div/div/div/div[1]/div/div/div/div/div/div/div/div[2]/div[1]/div[2]/div")
    List<WebElement> languageCheckboxes;

    // Method to get the size of the list of languages and print them
    public int sizeOfListOfLanguages() throws InterruptedException {
        showMore.click(); // Click the "Show More" button
        Thread.sleep(5000); // Wait for the languages to load
        System.out.println("-------------Languages List-----------");
        System.out.println();
        // Iterate through the list of languages and print each one
        for (int i = 0; i < languageCheckboxes.size(); i++) {
            System.out.println(languageCheckboxes.get(i).getText());
        }
        System.out.println();
        System.out.println("--------------------------------------");
        return languageCheckboxes.size(); // Return the size of the list
    }
}