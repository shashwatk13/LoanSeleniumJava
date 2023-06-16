package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base {

    public static WebDriver driver;
    Properties prop = new Properties();

    public void initSetup() throws IOException
    {
        FileInputStream file = new FileInputStream("F:\\Projects\\LoanSeleniumJava\\src\\main\\resources\\config.properties");
        prop.load(file);
    }


    @Test
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
        driver.quit();
    }

}
