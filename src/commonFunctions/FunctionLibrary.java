package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil {
public static boolean VerifyLogin(String username,String password) {
	driver.findElement(By.xpath(config.getProperty("Objuser"))).sendKeys(username);
	driver.findElement(By.xpath(config.getProperty("Objpass"))).sendKeys(password);
	driver.findElement(By.xpath(config.getProperty("ObjLogin"))).click();
	String expected ="adminflow";
	String actual =driver.getCurrentUrl();
	if(actual.toLowerCase().contains(expected.toLowerCase())) {
		Reporter.log("login success::"+expected+"   "+actual,true);
		return true;
		
	}
	else
	{
		Reporter.log("login fail::"+expected+"   "+actual,true);
		return false;
	}
}
//methods for click branches
public static void clickbranches() throws Throwable {
	driver.findElement(By.xpath(config.getProperty("ObjBranches"))).click();
	Thread.sleep(3000);
}
//methods for branch creation
public static boolean verifyBranch(String branchname,String Address1,String Address2,String Address3,String Area,String Zipcode,String Country,String State,String City) throws Throwable {
driver.findElement(By.xpath(config.getProperty("ObjBranches"))).click();

Thread.sleep(3000);
driver.findElement(By.xpath(config.getProperty("ObjNewBranch"))).click();
Thread.sleep(2000);
driver.findElement(By.xpath(config.getProperty("ObjBranch"))).sendKeys(branchname);
driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(Address1);
driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys(Address2);
driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(Address3);
driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys(Area);
driver.findElement(By.xpath(config.getProperty("ObjZipcode"))).sendKeys(Zipcode);
new Select(driver.findElement(By.xpath(config.getProperty("ObjCountry")))).selectByVisibleText(Country);
Thread.sleep(3000);
new Select(driver.findElement(By.xpath(config.getProperty("ObjState")))).selectByVisibleText(State);
Thread.sleep(3000);
new Select(driver.findElement(By.xpath(config.getProperty("ObjCity")))).selectByVisibleText(City);
Thread.sleep(3000);
driver.findElement(By.xpath(config.getProperty("ObjSubmit"))).click();
Thread.sleep(3000);
String expectedAlertbranch= driver.switchTo().alert().getText();
Thread.sleep(3000);
driver.switchTo().alert().accept();
String actualalertbranch="New Branch With Id ";
if(expectedAlertbranch.toLowerCase().contains(actualalertbranch.toLowerCase())) {
	Reporter.log(expectedAlertbranch,true);
	return true;
}
else
{
Reporter.log("unable to create new branch",true);
  return false;
}
}
//methods for branch updation
public static boolean verifyBranchUpdate(String branch,String Address,String Areaname,String Zip) throws Throwable {
	driver.findElement(By.xpath(config.getProperty("ObjEdit"))).click();
	Thread.sleep(2000);
	WebElement element1 =driver.findElement(By.xpath(config.getProperty("ObjBranchName")));
	element1.clear();
	element1.sendKeys(branch);
	WebElement element2 =driver.findElement(By.xpath(config.getProperty("ObjAddress")));
	element2.clear();
	element2.sendKeys(Address);
	WebElement element3 =driver.findElement(By.xpath(config.getProperty("ObjAreaName")));
	element3.clear();
	element3.sendKeys(Areaname);
	WebElement element4=driver.findElement(By.xpath(config.getProperty("ObjZip")));
	element4.clear();
	element4.sendKeys(Zip);
	driver.findElement(By.xpath(config.getProperty("ObjUpdate"))).click();
	Thread.sleep(3000);
	String expectedAlertUpdate=driver.switchTo().alert().getText();
	Thread.sleep(3000);
	driver.switchTo().alert().accept();
	String actualalertupdate ="Branch Updated";
	if(expectedAlertUpdate.toLowerCase().contains(actualalertupdate.toLowerCase())) {
		Reporter.log(expectedAlertUpdate,true);
		return true;
	}
	else
	{
		Reporter.log("unable to update branch",true);
		return false;
	}

}
public static boolean verifyLogout() throws Throwable {
	driver.findElement(By.xpath(config.getProperty("ObjLogout"))).click();
	Thread.sleep(3000);
	if(driver.findElement(By.xpath(config.getProperty("ObjLogin"))).isDisplayed()) {
		Reporter.log("Admin Logout success",true);
		return true;
	}
	else
	{
		Reporter.log("Admin Logout fail",true);
		return false;
	}
}
}


