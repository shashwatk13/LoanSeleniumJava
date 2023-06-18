package Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IDFCHomePage {

    public WebDriver driver;

    @FindBy(xpath = "//a[text()='Loans']")
    WebElement loansHoverElement;

    @FindBy(xpath = "//a[text()='Loans']/../div/div[2]/div[3]/a")
    WebElement homeLoan;

    public IDFCHomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void clickOnHomeLoan()
    {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(loansHoverElement);
            action.perform();

            //Click on home loan
            homeLoan.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
}
