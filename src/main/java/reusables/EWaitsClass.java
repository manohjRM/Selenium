package reusables;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

public class EWaitsClass {
	WebDriver driver;
	
	public EWaitsClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	public void clickableElement(WebElement element) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
}
