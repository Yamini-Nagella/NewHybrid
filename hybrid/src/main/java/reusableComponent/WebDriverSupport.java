package reusableComponent;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;

import exceptions.ReusableComponentException;
import utilities.ExtentLogUtilities;

public class WebDriverSupport {
	public static void click(WebDriver driver, By by, String page, String element, Logger log, ExtentTest test)
			throws ReusableComponentException, Exception {
		try {
			new Actions(driver).moveToElement(driver.findElement(by)).click(driver.findElement(by)).perform();
			ExtentLogUtilities.pass(driver, test, element + " is clicked in page " + page, log);
		} catch (Exception e) {

			ExtentLogUtilities.fail(driver, test, element + " is not able clicked in page " + page, log);
			throw new ReusableComponentException(element + " is not able clicked in page " + page);
		}
	}

	public static void clickByWebElement(WebDriver driver, WebElement ele, String page, String element, Logger log,
			ExtentTest test) throws ReusableComponentException, Exception {
		try {
			new Actions(driver).moveToElement(ele).click(ele).perform();
			ExtentLogUtilities.pass(driver, test, element + " is clicked in page " + page, log);
		} catch (Exception e) {

			ExtentLogUtilities.fail(driver, test, element + " is not able clicked in page " + page, log);
			throw new ReusableComponentException(element + " is not able clicked in page " + page);
		}
	}

	public static void sendKeys(WebDriver driver, By by, String page, String element, Logger log, ExtentTest test,
			String src) throws ReusableComponentException, Exception {
		try {
			driver.findElement(by).sendKeys(src);
			;
			ExtentLogUtilities.pass(driver, test, element + " is send in page " + page, log);
		} catch (Exception e) {

			ExtentLogUtilities.fail(driver, test, element + " can't send in page " + page, log);
			throw new ReusableComponentException(element + " can't send in page ");
		}
	}

	public static void switchTo(WebDriver driver, String name, String elementName, Logger log, ExtentTest test)
			throws ReusableComponentException, Exception {
		if (elementName.equalsIgnoreCase("frame")) {
			try {
				driver.switchTo().frame(name);
				ExtentLogUtilities.pass(driver, test, "Switch to frame element ", log);
			} catch (Exception e) {

				ExtentLogUtilities.fail(driver, test, "can't Switch to frame element ", log);
				throw new ReusableComponentException("can't Switch to frame element ");
			}
		} else if (elementName.equalsIgnoreCase("window")) {
			try {
				driver.switchTo().window(name);
				ExtentLogUtilities.pass(driver, test, "Switch to window element ", log);
			} catch (Exception e) {

				ExtentLogUtilities.fail(driver, test, "can't Switch to window element ", log);
				throw new ReusableComponentException("can't Switch to window element ");

			}
		}
	}

	
}
