package uiStore;

import org.openqa.selenium.By;

public class OutStationTaxi_OneWay_Ui {
	public static By source = By.xpath(".//*[@formcontrolname=\"fromCityControl\"]");
	public static By destination = By.xpath(".//*[@class='form-control ng-untouched ng-pristine ng-invalid']");
	public static By date = By.xpath("//*[@class=\"ng-tns-c75-1 form-control p-inputtext p-component ng-star-inserted\"]");
	public static By time = By.xpath("//*[@id=\"pickUpTime\"]");
	public static By book_btn = By.xpath(".//*[contains(@class, 'book-button btn')]");
	public static By vheading = By.xpath(".//*[@class = 'headingHolder_grey_bar']");
}
