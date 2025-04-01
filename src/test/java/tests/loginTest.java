package tests;

import java.time.Duration;
import org.testng.annotations.Test;
import baseTest.baseTest;
import pages.loginPage;

public class loginTest extends baseTest{

    @Test
    public void verifySuccessfullyLogin() throws InterruptedException {
        loginPage loginpage = new loginPage();
        loginpage.clickOnCountyDropdown();
        Thread.sleep(5000);
        loginpage.selectCounty();
        loginpage.enterName();
        loginpage.clickOnSubmitButton();
        System.out.println("Login Test Passed!");
    }

}
