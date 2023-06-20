package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {

    public static WebDriver driver;
    Properties prop = new Properties();

    public void initSetup() throws IOException
    {
        FileInputStream file = new FileInputStream("F:\\Projects\\LoanSeleniumJava\\src\\main\\resources\\Configs\\config.properties");
        prop.load(file);
    }

    public void initializeBrowser() throws IOException
    {

        initSetup();

        switch (prop.getProperty("browser")){

            case "chrome":
                driver = WebDriverManager.chromedriver().create();
                break;
            case "firefox":
                driver = WebDriverManager.firefoxdriver().create();
                break;
            default:
                System.out.println("-----Select browser-----");
                break;
        }

        driver.manage().window().maximize();
        driver.get(prop.getProperty("applicationUrl"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void scrollDown(int x,int y){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy("+x+","+y+")", "");
    }

    public void explicitWaitVisibilityOfElement(int duration,WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void takeScreenShot(String testMethodName) throws IOException {
       File file =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       FileUtils.copyFile(file, new File("F:\\Projects\\LoanSeleniumJava\\Screenshots\\"+testMethodName+".jpg"));
    }

    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }

}
