package com.saucelabs.Tests;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;

import com.saucelabs.Pages.DemoLoginPage;

public class DemoLoginTest extends TestBase {
	
    private static String USERNAME = "standard_user";
    private static String PASSWD = "secret_sauce";

    @org.testng.annotations.Test(dataProvider = "hardCodedBrowsers")
    public void loginTest(String browser, String version, String os, Method method) throws MalformedURLException, InvalidElementStateException, UnexpectedException {
    	
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        this.annotate("Visiting Deno Login page...");
        DemoLoginPage page = DemoLoginPage.visitPage(driver);

        this.annotate(String.format("Login in with: \"%s\"", USERNAME));
        page.login(USERNAME, PASSWD);

    }

}