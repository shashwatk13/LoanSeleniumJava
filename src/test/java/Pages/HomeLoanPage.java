package Pages;

import Utils.Base;
import Utils.ExcelFileReader;
import Utils.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeLoanPage extends Base {

    WebDriver driver;

    @FindBy(xpath = "//h2[@class='hd7 os-animation animated fadeInUp']")
    WebElement homeLoanText;

    @FindBy(id = "investAmount")
    WebElement investAmount;

    @FindBy(id = "yearValue")
    WebElement period;

    @FindBy(id = "interestRate")
    WebElement interestRate;

    @FindBy(id = "interestValue")
    WebElement interestValue;

    public HomeLoanPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void validateHomeLoanPage() {
        //Scroll page to down
        pageScrollDown(0, 1800);
        Log.info("Scroll down page");

        //Explicit wait
        explicitWaitVisibilityOfElement(10,homeLoanText);

        //Get home loan text
        String actualHomeLoanText = homeLoanText.getText();

        if (actualHomeLoanText.contains("Home Loan EMI Calculator")) {
            Log.info("Home Loan EMI calculator displayed");
        } else {
            Assert.fail("Failed to load Home Loan page");
        }
    }

    public void validateHomeLoanEMICalculator ()  {

        ExcelFileReader.readExcelSheet();

        //Enter amount
        investAmount.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
        investAmount.sendKeys(ExcelFileReader.stringAmount);

        //Enter period
        period.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
        period.sendKeys(ExcelFileReader.stringPeriod);

        //Enter interest rate
        interestRate.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
        interestRate.sendKeys(ExcelFileReader.stringInterestRate);

        long actualInterestValue = amountSeparationWithRupeeSymbol();

        Assert.assertEquals(actualInterestValue,189057,"Interest Value matched");
    }

    public Long amountSeparationWithRupeeSymbol(){

        String amountWithRupeeSymbol = interestValue.getText();

        String amountWithoutRupeeSymbol =  amountWithRupeeSymbol.substring(1);

        String[] amountWithoutSlashSymbol = amountWithoutRupeeSymbol.split("/");

        String amountWithoutComma = amountWithoutSlashSymbol[0].replaceAll(",","");

        return Long.parseLong(amountWithoutComma);

    }
}
