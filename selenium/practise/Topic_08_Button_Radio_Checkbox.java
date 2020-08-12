package practise;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox {
	WebDriver driver;
    JavascriptExecutor jsExecutor;
	
    // Checkbox
    By firstCheckBox = By.xpath("//input[@value='Anemia']");
	By secondCheckBox = By.xpath("//input[@value='Asthma']");
	By thirthCheckBox = By.xpath("//input[@value='Arthritis']");
    By allCheckboxes = By.xpath("//input[@type='checkbox']");
	// Radio button
	By firstRadio= By.xpath("//input[@value='3-4 days']");
	By secondRadio = By.xpath("//input[@value='I have a strict diet']");
	
	String email = "quochung" + randomNumber() + "@gmail.com"; 
	
	@BeforeClass
	public void beforeClass() {
		
		// Absolute path
		//System.setProperty("webdriver.chrome.driver",
				//"E:\\Kent\\Automation\\02-Selenium API Github\\java-selenium-api-testing-17-github\\browserDriver\\chromedriver.exe");
		
		// Relative path ( Cach 1)
		System.setProperty("webdriver.chrome.driver",
				".\\browserDriver\\chromedriver.exe");
		
		// Relative path ( Cach 2)
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
		//		".\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Button() throws InterruptedException {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		
		// Navigate to login TAB
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		WebElement loginButton = driver.findElement(By.cssSelector(".fhs-btn-login")) ;
			
		// Verify login button is disable
		
		boolean status = loginButton.isEnabled();
		System.out.println("Login Status = " + status);
		Assert.assertFalse(status);
		
		// Input email password
		driver.findElement(By.cssSelector("#login_username")).sendKeys(email);
		driver.findElement(By.cssSelector("#login_password")).sendKeys("552912");
		SleepInSecond(2);
		
		// Verify login button is enable
        status = loginButton.isEnabled();
		System.out.println("Login Status = " + status);
		Assert.assertTrue(status);

		// Click login button
		loginButton.click();
		
	    String errorMessage = driver.findElement(By.cssSelector(".fhs-popup-msg.fhs-login-msg")).getText();
	    Assert.assertEquals(errorMessage, "Số điện thoại/Email hoặc Mật khẩu sai!");
		SleepInSecond(2);

		// Cách remove attribute - Hạn chế sử dụng TRICK này vì user không dùng cách này để thao tác với app
		
		driver.navigate().refresh();
		SleepInSecond(2);
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		SleepInSecond(2);
		
		loginButton = driver.findElement(By.cssSelector(".fhs-btn-login")) ;
		Assert.assertFalse(loginButton.isEnabled());
		
		removeDisableAttribute(loginButton);
		SleepInSecond(2);
		
		loginButton.click();
		SleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_username']//parent::div//following-sibling::div")).getText(),
				"Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_password']//parent::div//following-sibling::div")).getText(),
				"Thông tin này không thể để trống");
		
		
	}
	@Test
	public void TC_02_Default_Radio_Checkbox() throws InterruptedException {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// Kiểm tra 3 checkbox đầu tiên + 2 radio button deselected
		Assert.assertFalse(driver.findElement(firstCheckBox).isSelected());
		Assert.assertFalse(driver.findElement(secondCheckBox).isSelected());
		Assert.assertFalse(driver.findElement(thirthCheckBox).isSelected());
		
		Assert.assertFalse(driver.findElement(firstRadio).isSelected());
		Assert.assertFalse(driver.findElement(secondRadio).isSelected());

		// Click vao 3 checkbox đầu tiên + 2 radio button
		driver.findElement(firstCheckBox).click();
		driver.findElement(secondCheckBox).click();
		driver.findElement(thirthCheckBox).click();
		
		driver.findElement(firstRadio).click();
		driver.findElement(secondRadio).click();
		SleepInSecond(5);
	
		// Verify 3 checkbox đầu tiên + 2 radio button selected
		Assert.assertTrue(driver.findElement(firstCheckBox).isSelected());
		Assert.assertTrue(driver.findElement(secondCheckBox).isSelected());
		Assert.assertTrue(driver.findElement(thirthCheckBox).isSelected());
		
		Assert.assertTrue(driver.findElement(firstRadio).isSelected());
		Assert.assertTrue(driver.findElement(secondRadio).isSelected());
	    driver.navigate().refresh();

        // Click tất cả Checkbox
	    List<WebElement> checkBoxes = driver.findElements(allCheckboxes);
	    for (WebElement checkbox: checkBoxes) {
	    	checkbox.click();
	    	Thread.sleep(500);
	    }
	    for (WebElement checkbox: checkBoxes) {
	    Assert.assertTrue(checkbox.isSelected());
	    }
	    for (WebElement checkbox: checkBoxes) {
	    	checkbox.click();
	    	Thread.sleep(500);
	    }
	    for (WebElement checkbox: checkBoxes) {
		    Assert.assertFalse(checkbox.isSelected());
		    }
	
	}
	
	@Test
	public void TC_03_Custom_Radio_Checkbox() throws InterruptedException {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		By checkedCheckbox = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::div/input");
		
		// Click by input
		clickByJavascript(driver.findElement(checkedCheckbox));
	    SleepInSecond(5);
		// Verify 
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		
		
	}
	
	public void clickByJavascript(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();",element);
	}
	
	public void SleepInSecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeDisableAttribute(WebElement element) {
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",element);
	}
	
	public static int randomNumber() {
	Random rand = new Random();
	return rand.nextInt(99);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}