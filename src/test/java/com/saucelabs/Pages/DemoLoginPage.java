package com.saucelabs.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DemoLoginPage {
	
    /***************************************************************/
    
    @FindBy(how = How.XPATH, using = "//*[@id=\"user-name\"]")
    private WebElement userName;
    
    @FindBy(how = How.XPATH, using = "//*[@id=\"password\"]")
    private WebElement password;
    
    @FindBy(how = How.XPATH, using = "//*[@id=\"login-button\"]")
    private WebElement submitButton;
        
    /***************************************************************/
    
    public WebDriver driver;
    public static String url = "http://www.saucedemo.com";

    /***************************************************************/
    
    public static DemoLoginPage visitPage(WebDriver driver) {
    	DemoLoginPage page = new DemoLoginPage(driver);
        page.visitPage();
        return page;
    }

    /***************************************************************/
    
    public DemoLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /***************************************************************/
    
    public void visitPage() {
        this.driver.get(url);
    }

    /***************************************************************/
    
    public void login(String userName, String password) {
    	this.userName.sendKeys(userName);
    	this.password.sendKeys(password);
    	submitButton.click();
    }
    
}