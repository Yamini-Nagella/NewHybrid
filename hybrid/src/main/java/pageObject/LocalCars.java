package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;

import exceptions.PageObjectException;
import exceptions.ReusableComponentException;
import reusableComponent.WebDriverSupport;
import uiStore.LocalCarsUi;
import utilities.ExtentLogUtilities;

public class LocalCars extends LocalCarsUi{
	WebDriver driver;
	Logger log;
	ExtentTest test;

	public LocalCars(WebDriver driver, Logger log, ExtentTest test) throws Exception {
		this.driver = driver;
		this.test = test;
		this.log = log;
	}

	public void select(String src, String month, String day, String stime, String vcity, String vdate) throws ReusableComponentException, Exception {
		try {
			WebDriverSupport.click(driver, localtab, "Home Page", "Select Local City Tab", log, test);
			WebDriverSupport.sendKeys(driver, city, "Home Page", "Select City", log, test, src);
			driver.findElement(By.id("ngb-typeahead-2-0")).click();
			
			WebDriverSupport.click(driver, pickup, "Local Car Booking", "Date Field", log, test);
			
			Thread.sleep(2000);
			while(true) {
				String Default_Month = driver.findElement(By.xpath(".//*[@class='p-datepicker-title ng-tns-c75-3']")).getText();
				if(month.equals(Default_Month) ) {
					break;
				}
				else {
					driver.findElement(By.xpath("//*[@class=\"p-datepicker-next-icon pi pi-chevron-right ng-tns-c75-3\"]")).click();
				}
			}
			day = day.substring(0,2);
			int count = driver.findElements(By.xpath(".//tbody[@class='ng-tns-c75-3']/tr/td")).size();
			for(int i=0;i<count;i++) {
				
				if(driver.findElements(By.xpath(".//tbody[@class='ng-tns-c75-3']/tr/td")).get(i).getText().contains(day)) {
					driver.findElements(By.xpath(".//tbody[@class='ng-tns-c75-3']/tr/td")).get(i).click();
					break;
				}
			}
			Select ttime = new Select(driver.findElement(pickupat));
			ttime.selectByVisibleText(stime);
			WebDriverSupport.click(driver, book_btn, "Local Taxi Booking", "click on select car", log, test);
			String head = driver.findElement(vheading).getText();
			if (head.contains(vcity) && head.contains(vdate) && head.contains(stime)) {
				ExtentLogUtilities.pass(driver, test, "Successfully opened Local Taxi Booking Page", log);
			} else {
				ExtentLogUtilities.fail(driver, test, "failed to open Airport Taxi Booking Page", log);
				throw new PageObjectException("Logged in failed");
			}
			
		} catch (Exception e) {
			throw new PageObjectException(e.getMessage());
		}
	}


}
