package TestCases;

import Pages.HomeLoanPage;
import Pages.IDFCHomePage;
import Utils.Base;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

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
    public void Validate_home_loan_calculator() throws Exception {
        idfcHomePage.clickOnHomeLoan();
        homeLoanPage.validateHomeLoanPage();
        homeLoanPage.validateHomeLoanEMICalculator();
        Thread.sleep(3000);
    }

}
