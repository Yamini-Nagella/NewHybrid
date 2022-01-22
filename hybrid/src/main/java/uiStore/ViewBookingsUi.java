package uiStore;

import org.openqa.selenium.By;

public class ViewBookingsUi {
	public static By profile = By.xpath(".//*[contains(@class,'d-block m-auto')]");
	public static By viewlist = By.xpath("//*[@id=\"approot\"]/mat-sidenav-container/mat-sidenav/div/app-menu/div/ul/li[1]/a");
	public static By vheading = By.xpath(".//*[@class = 'headingHolder bookingHeading']");
}
