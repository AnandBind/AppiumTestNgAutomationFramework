package pages;

import java.util.HashMap;

import org.openqa.selenium.By;

import base.driverManager;

public class loginPage extends driverManager {

    private By CountryDropdown = By.id("com.androidsample.generalstore:id/spinnerCountry");
    private By CountryName=By.xpath("//android.widget.TextView[@text='Zambia']");
    private By Name = By.id("com.androidsample.generalstore:id/nameField");
    private By SubmintButton = By.id("com.androidsample.generalstore:id/btnLetsShop");
   

    public void clickOnCountyDropdown() {
        waitForExpectedElement(CountryDropdown, 10).click();
        System.out.println("Country dropdown clicked successfully!");
    }
    public void selectCounty(){
       
        scrollToElement();
       // waitForExpectedElement(CountryName, 10).click();
        System.out.println("Country selected successfully!");
    }

    public void enterName() {
        waitForExpectedElement(Name, 10).sendKeys("Anand");
        //getAndroidDriver().findElement(Name).sendKeys("Anand");
    }

    public void clickOnSubmitButton(){
        waitForExpectedElement(SubmintButton, 10).click();
    }

}
