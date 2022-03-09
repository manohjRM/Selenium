package cashWrapTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cashWrapMain.CashWrapMainClass;
import cashWrapObject.CashWrapObjectModel;

@Listeners(listenerCashWarp.ListenerCashWrap.class)

public class TestCashWrapLoginPage extends CashWrapMainClass{
	
	@Test(dataProvider = "LoginData", priority = 0)
	
	public static void loginCashWrap(String Username,String Password) throws InterruptedException
	{
	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	logger=extent.startTest("search- sample test");
	CashWrapObjectModel obj=new CashWrapObjectModel(driver);
	obj.login(Username,Password);
	try {
		obj.enterMobileNumber();
	}
	catch(Exception e) {
		obj.resetTerminal();
		obj.login(Username,Password);
		obj.enterMobileNumber();
	}
	try {
		obj.newCustomerInfo();
	}catch(Exception e) {
		obj.existingCustomer();
	}
	
	
	}
	@Test(priority = 1)
	void addItemToCart() {
		logger=extent.startTest("search- sample test");
		CashWrapObjectModel obj=new CashWrapObjectModel(driver);
		obj.addItems();
	}
	
	@Test(priority = 2)
	void payToItems() throws InterruptedException {
		logger=extent.startTest("search- sample test");
		CashWrapObjectModel obj=new CashWrapObjectModel(driver);
		obj.paymentProcess();
	}
	
	@DataProvider(name="LoginData")
	public Object[][] sampledata(){
		Object[][] data = getExcelData("DataSheet.xlsx", "Credentials");
		return data;
	}
}
