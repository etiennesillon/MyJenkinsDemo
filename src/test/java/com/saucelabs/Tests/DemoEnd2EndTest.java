package com.saucelabs.Tests;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;

import com.saucelabs.Pages.DemoCartPage;
import com.saucelabs.Pages.DemoLoginPage;
import com.saucelabs.Pages.DemoShopPage;

public class DemoEnd2EndTest extends TestBase {
	
	/*********************************************************************/

    private static String USERNAME = "standard_user";
    private static String PASSWD = "secret_sauce";
    
	/*********************************************************************/

    @org.testng.annotations.Test(dataProvider = "hardCodedBrowsers")
    public void loginTest(String browser, String version, String os, Method method) throws MalformedURLException, InvalidElementStateException, UnexpectedException {
    	
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();
        
//        boolean isFail = IS_FAIL && browser.equals(IE) ? true : false;

        this.annotate("Visiting Demo Login page...");
        DemoLoginPage loginPage = DemoLoginPage.visitPage(driver, IS_FAIL);

        this.annotate(String.format("Login in with: \"%s\"", USERNAME));
        DemoShopPage shopPage = loginPage.login(USERNAME, PASSWD);

        this.annotate(String.format("Add item and go to cart", USERNAME));
        DemoCartPage cart = shopPage.selectItemAndGotToCart();
        
    }

}