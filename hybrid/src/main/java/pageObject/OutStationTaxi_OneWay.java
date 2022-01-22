package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;

import exceptions.PageObjectException;
import exceptions.ReusableComponentException;
import reusableComponent.WebDriverSupport;
import uiStore.OutStationTaxi_OneWay_Ui;
import utilities.ExtentLogUtilities;

public class OutStationTaxi_OneWay extends OutStationTaxi_OneWay_Ui{
	WebDriver driver;
	Logger log;
	ExtentTest test;

	public OutStationTaxi_OneWay(WebDriver driver, Logger log, ExtentTest test) throws Exception {
		this.driver = driver;
		this.test = test;
		this.log = log;
	}

	public void onewayBooking(String src, String destin, String month, String day, String stime, String vcity, String vdate) throws ReusableComponentException, Exception {
		try {
			WebDriverSupport.sendKeys(driver, source, "Home Page", "Source city", log, test, src);
			WebDriverSupport.sendKeys(driver, destination, "Home Page", "Destination City", log, test, destin);
			driver.findElement(By.id("ngb-typeahead-1-0")).click();
			WebDriverSupport.click(driver, date, "Onw way Booking", "Date Field", log, test);
			
			while(true) {
				String Default_Month = driver.findElement(By.xpath(".//*[@class='p-datepicker-title ng-tns-c75-1']")).getText();
				
				if(month.equals(Default_Month) ) {
					break;
				}
				else {
					driver.findElement(By.xpath("//*[@class=\"p-datepicker-next-icon pi pi-chevron-right ng-tns-c75-12")).click();
				}
			}
			day = day.substring(0,2);
			int count = driver.findElements(By.xpath(".//tbody[@class='ng-tns-c75-1']/tr/td")).size();
			for( int i=0;i<count;i++) {
				
				if(driver.findElements(By.xpath(".//tbody[@class='ng-tns-c75-1']/tr/td")).get(i).getText().contains(day)) {
					driver.findElements(By.xpath(".//tbody[@class='ng-tns-c75-1']/tr/td")).get(i).click();
					break;
				}
			}
			Select ttime = new Select(driver.findElement(time));
			ttime.selectByVisibleText(stime);
			WebDriverSupport.click(driver, book_btn, "One way Booking", "click on select car", log, test);
			String head = driver.findElement(vheading).getText();
			if (head.contains(vcity) && head.contains(vdate) && head.contains(stime)) {
				ExtentLogUtilities.pass(driver, test, "Successfully opened one way booking page", log);
			} else {
				ExtentLogUtilities.fail(driver, test, "failed to open one way booking page", log);
				throw new PageObjectException("Logged in failed");
			}
			
		} catch (Exception e) {
			throw new PageObjectException(e.getMessage());
		}
	}

}
