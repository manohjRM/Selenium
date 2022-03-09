package cashWrapObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import reusables.EWaitsClass;

public class CashWrapObjectModel {
	WebDriver driver;
	String name = "guna";
	//EWaitsClass waitHere = new EWaitsClass(driver);
	
	public CashWrapObjectModel(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//*[@id='ddlTerminal']")
	public WebElement terminal;
	@FindBy(xpath="//*[@id='ddlTerminal']//following::option[@value='PS']")
	public WebElement terminalSelect;
	@FindBy(id="txtUserId")
	public WebElement username;
	@FindBy(id="txtPwd")
	public WebElement password;
	@FindBy(id="btnLogin")
	public WebElement loginButton;
	@FindBy(id="txtCMobileNo")
	public WebElement mobileNumberField;
	@FindBy(id="btnCusOK")
	public WebElement clickOk;
	@FindBy(id="btnTerminalResetOk")
	public WebElement terminalResetConfirm;
	@FindBy(id="btnTerminalResetCancel")
	public WebElement terminalResetDiscard;
	@FindBy(id="txtCustomerName")
	public WebElement enterCustomerName;
	@FindBy(xpath="(//*[@class='blobselect-button'])[2]")
	public WebElement customerGroup;
	@FindBy(id="txtEmail")
	public WebElement custEmail;
	@FindBy(xpath = "(//*[@class='blobselect-item-search'])[2]")
	public WebElement customerGroupSearch;
	@FindBy(id="btnCustomer")
	public WebElement saveBtn;
	@FindBy(xpath="//table[@id='tblVendorList']")
	public WebElement vendorSelection;
	@FindBy(xpath="//div[@class='card']//following::h6[@id='lblCustomerName']")
	public WebElement checkCustName;
	
	@FindBy(id="btnProductASearch")
	public WebElement prtSearch;
	@FindBy(xpath="//table[@id='tblAdvancedSearch']//thead//tr//th")
	public List<WebElement> itemTable;
	@FindBy(xpath="(//table[@id='tblAdvancedSearch']//tbody//tr//td)[1]")
	public WebElement firstItem;
	@FindBy(xpath="(//table[@id='tblBatchList']//tbody//tr[@id='BT01_IS11817']//td)[1]")
	public WebElement batchCode;
	@FindBy(id="txtBatchQty")
	public WebElement batchQty;
	@FindBy(id="btnBatchAdd")
	public WebElement addQty;
	@FindBy(id="btnBatchOK")
	public WebElement batchOk;
	
	@FindBy(id="btnPayment")
	public WebElement payItem;
	@FindBy(id="btnPaymentProcess")
	public WebElement addPayment;
	@FindBy(id="btnPOS")
	public WebElement finishPay;
	
	public void login(String UN,String Pwd) throws InterruptedException
	{
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait waitHere = new WebDriverWait(driver, 30);
		waitHere.until(ExpectedConditions.elementToBeClickable(terminal));
		terminal.click();
		waitHere.until(ExpectedConditions.elementToBeClickable(terminal));
		terminalSelect.click();
		
		username.sendKeys(UN);
		password.sendKeys(Pwd);
		loginButton.click();
	}
	@SuppressWarnings("deprecation")
	public void enterMobileNumber() {
		WebDriverWait waitHere = new WebDriverWait(driver, 15);
		waitHere.until(ExpectedConditions.elementToBeClickable(mobileNumberField));
		mobileNumberField.sendKeys("9874651222");
		clickOk.click();
	}
	@SuppressWarnings("deprecation")
	public void resetTerminal() throws InterruptedException {
		WebDriverWait waitHere = new WebDriverWait(driver, 15);
		waitHere.until(ExpectedConditions.elementToBeClickable(terminalResetConfirm));
		terminalResetConfirm.click();
		waitHere.until(ExpectedConditions.alertIsPresent());
		Alert ob = driver.switchTo().alert();
		ob.accept();
	}
	public void newCustomerInfo() {
		WebDriverWait waitHere = new WebDriverWait(driver, 15);
		waitHere.until(ExpectedConditions.visibilityOf(enterCustomerName));
		//name = "guna";
		enterCustomerName.sendKeys(name);
		custEmail.sendKeys("guna122@gmail.com");
		waitHere.until(ExpectedConditions.visibilityOf(customerGroup));
		customerGroup.click();
		Actions select = new Actions(driver);
		waitHere.until(ExpectedConditions.visibilityOf(customerGroupSearch));
		select.moveToElement(customerGroupSearch).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		saveBtn.click();
		Alert ob = driver.switchTo().alert();
		ob.accept();;
		
	}
	public void existingCustomer() {
		WebDriverWait waitHere = new WebDriverWait(driver, 15);
		waitHere.until(ExpectedConditions.visibilityOf(vendorSelection));
		try {
			vendorSelection.click();
		}catch(Exception e) {
			Actions select = new Actions(driver);
			select.moveToElement(vendorSelection).click().build().perform();
		}
		System.out.println(checkCustName.getText());
		Assert.assertEquals(name.toUpperCase(), checkCustName.getText(), "Verified Successfully");
		
	}
	public void addItems() {
		prtSearch.click();
		WebDriverWait waitHere = new WebDriverWait(driver, 15);
		waitHere.until(ExpectedConditions.elementToBeClickable(firstItem));
		firstItem.click();
		waitHere.until(ExpectedConditions.elementToBeClickable(batchCode));
		batchCode.click();
		batchQty.sendKeys("1");
		addQty.click();
		batchOk.click();
	}
	
	public void paymentProcess() throws InterruptedException {
		Thread.sleep(1000);
		//Alert ob = driver.switchTo().alert();
		//ob.accept();
		payItem.click();
		WebDriverWait waitHere = new WebDriverWait(driver, 50);
		waitHere.until(ExpectedConditions.visibilityOf(addPayment));
		addPayment.click();
		waitHere.until(ExpectedConditions.elementToBeClickable(finishPay));
		finishPay.click();
		//waitHere.until(ExpectedConditions.alertIsPresent());
		//Alert ob1 = driver.switchTo().alert();
		//ob1.accept();
		
	}
}
