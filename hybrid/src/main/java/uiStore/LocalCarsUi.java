package uiStore;

import org.openqa.selenium.By;

public class LocalCarsUi {
	public static By localtab = By.xpath(".//*[@class = 'bg-local']");
	public static By city = By.id("fromCityList");
	public static By pickup = By.xpath("//*[@class=\"ng-tns-c75-3 form-control p-inputtext p-component ng-star-inserted\"]");
	public static By pickupat = By.xpath("//*[@id=\"pickUpTime\"]");
	public static By book_btn = By.xpath(".//*[@class='book-button btn']");
	public static By vheading = By.xpath(".//*[@class = 'headingHolder_grey_bar']");
}
