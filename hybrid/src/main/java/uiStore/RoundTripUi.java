package uiStore;

import org.openqa.selenium.By;

public class RoundTripUi {
	public static By radio = By.xpath(".//*[@for = 'out_radio']");
	public static By source = By.xpath(".//*[@formcontrolname=\"fromCityControl\"]");
	public static By destination = By.xpath(".//*[@class='form-control ng-untouched ng-pristine ng-invalid']");
	public static By pickup = By.xpath("//*[@class=\"ng-tns-c75-1 form-control p-inputtext p-component ng-star-inserted\"]");
	public static By Return = By.xpath("//*[@class='ng-tns-c75-3 form-control p-inputtext p-component ng-star-inserted']");
	public static By pickupat = By.xpath("//*[@id=\"pickUpTime\"]");
	public static By book_btn = By.xpath(".//*[@class='book-button btn']");
	public static By vheading = By.xpath(".//*[@class = 'headingHolder_grey_bar']");
}
