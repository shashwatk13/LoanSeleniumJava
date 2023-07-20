package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {

    public static WebDriver driver;
    public static Properties prop = new Properties();

    public void initSetup() throws IOException
    {
        FileInputStream file = new FileInputStream("F:\\Projects\\LoanSeleniumJava\\src\\main\\resources\\Configs\\config.properties");
        prop.load(file);
    }

    public void initializeBrowser() throws IOException
    {
        initSetup();

            switch (prop.getProperty("browser")) {

                case "chrome":
                    driver = WebDriverManager.chromedriver().create();
                    break;
                case "firefox":
                    driver = WebDriverManager.firefoxdriver().create();
                    break;
                default:
                    break;
            }

        driver.manage().window().maximize();
        driver.get(prop.getProperty("applicationUrl"));
        validateBrowserOpening();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Log.info("Application home page");
    }

    public void validateBrowserOpening(){
        String expectedTitle = "Products | IDFC FIRST Bank";
        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle)){
            Log.info("Browser opened successfully.");
        }else{
            Assert.fail("Failed to open the browser or incorrect webpage.");
        }
    }

    public void pageScrollDown(int x,int y){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy("+x+","+y+")", "");
    }

    public void explicitWaitVisibilityOfElement(int duration,WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String takeScreenShot(String testMethodName,WebDriver driver) throws IOException
    {
       File sourceFilePath =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       File destinationFilePath = new File("F:\\Projects\\LoanSeleniumJava\\Screenshots\\"+testMethodName+".jpg");
       FileUtils.copyFile(sourceFilePath, destinationFilePath);

       return destinationFilePath.getAbsolutePath();
    }

    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }


}
