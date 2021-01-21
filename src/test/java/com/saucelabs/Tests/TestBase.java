package com.saucelabs.Tests;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class TestBase  {
	
	/*********************************************************************/

    public String buildTag = System.getenv("BUILD_TAG");

    public String username = System.getenv("SAUCE_USERNAME");

    public String accesskey = System.getenv("SAUCE_ACCESS_KEY");
    
	public String SAUCE_URL = "http://" + username + ":" + accesskey + "@ondemand.saucelabs.com:80/wd/hub";

	/*********************************************************************/

	private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

	/*********************************************************************/

    @DataProvider(name = "hardCodedBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
    	
        return new Object[][]{
        	
                new Object[]{"internet explorer", "latest", "Windows 7"},
                new Object[]{"firefox", "latest", "Windows 7"},
                new Object[]{"chrome", "latest", "Windows 7"},

                new Object[]{"internet explorer", "latest", "Windows 8"},
                new Object[]{"firefox", "latest", "Windows 8"},
                new Object[]{"chrome", "latest", "Windows 8"},

                new Object[]{"MicrosoftEdge", "latest", "Windows 10"},
                new Object[]{"internet explorer", "latest", "Windows 10"},
                new Object[]{"firefox", "latest", "Windows 10"},
                new Object[]{"chrome", "latest", "Windows 10"},

                new Object[]{"MicrosoftEdge", "latest", "macOS 10.15"},
                new Object[]{"firefox", "latest", "macOS 10.15"},
                new Object[]{"safari", "latest", "macOS 10.15"},
                new Object[]{"chrome", "latest", "macOS 10.15"},

                new Object[]{"perf", "latest", "macOS 10.15"},
                new Object[]{"perf", "latest", "Windows 7"},
                new Object[]{"perf", "latest", "Windows 10"}

        };
        
    }

	/*********************************************************************/

    public WebDriver getWebDriver() {
        return webDriver.get();
    }

	/*********************************************************************/
    
    public String getSessionId() {
        return sessionId.get();
    }

	/*********************************************************************/

    protected void createDriver(String browser, String version, String os, String methodName) throws MalformedURLException, UnexpectedException {
    	
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browser.equals("perf")) {
            capabilities.setCapability("capturePerformance", true);
            browser = "chrome";
            methodName = "Performance Test";
        } 

        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", methodName);
        
        capabilities.setCapability("extendedDebugging", true);
        
        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }

        webDriver.set(new RemoteWebDriver(new URL(SAUCE_URL),capabilities));

        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);
        
    }

	/*********************************************************************/

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        webDriver.get().quit();
    }

	/*********************************************************************/

    protected void annotate(String text) {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
    }
	
}
