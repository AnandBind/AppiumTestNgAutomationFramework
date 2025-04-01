package baseTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.driverManager;

public class baseTest extends driverManager {
    @BeforeMethod
    public void setup(){
        capabilitys();
    }

    @AfterMethod
    public void teardown(){
        quitDriver();
    }

    

}
