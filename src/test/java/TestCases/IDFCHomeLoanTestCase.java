package TestCases;

import Pages.HomeLoanPage;
import Pages.IDFCHomePage;
import Utils.Base;
import Utils.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(Utils.Listeners.class)
public class IDFCHomeLoanTestCase extends Base {

    IDFCHomePage idfcHomePage;
    HomeLoanPage homeLoanPage;

    @BeforeClass
    public void initializeDriver() throws IOException {
        initializeBrowser();
        idfcHomePage = new IDFCHomePage(driver);
        homeLoanPage = new HomeLoanPage(driver);
    }

    @Test(description = "Validate home loan calculator")
    public void validate_home_loan_calculator() throws Exception {
        Log.startTestCase("Validate_home_loan_calculator");
        idfcHomePage.clickOnHomeLoan();
        homeLoanPage.validateHomeLoanPage();
        homeLoanPage.validateHomeLoanEMICalculator();
        Log.endTestCase("Validate_home_loan_calculator");
        Thread.sleep(3000);
    }

}
