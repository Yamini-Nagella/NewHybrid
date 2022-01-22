package uiStore;

import org.openqa.selenium.By;

public class AirportTaxiUi {
	public static By Airporttab = By.xpath(".//*[@class = 'bg-airport']");
	public static By city = By.id("fromCityList");
	public static By trip = By.xpath(".//*[@formcontrolname=\"frmLocalSubTripType\"]");
	public static By address = By.id("search_places");
	public static By pickup = By.xpath("//*[@class=\"ng-tns-c75-3 form-control p-inputtext p-component ng-star-inserted\"]");
	public static By pickupat = By.xpath("//*[@id=\"pickUpTime\"]");
	public static By book_btn = By.xpath(".//*[@class='book-button btn']");
	public static By vheading = By.xpath(".//*[@class = 'headingHolder_grey_bar']");
}
