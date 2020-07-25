package Exercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class registerFunctionMailchimp {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void mailChimp() throws InterruptedException {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("nguyenquochung291288@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("Kentyumi");
		
		WebElement passWord = driver.findElement(By.id("new_password"));
		passWord.sendKeys("123");
		Assert.assertFalse(driver.findElement(By.className("number-char")).isEnabled());
		passWord.clear();
		Thread.sleep(2000);
		
		passWord.sendKeys("hung");
		Assert.assertFalse(driver.findElement(By.className("lowercase-char completed")).isEnabled());
		passWord.clear();
		Thread.sleep(2000);
		
		passWord.sendKeys("HUNG");
		//Assert.assertFalse(driver.findElement(By.className("uppercase-char completed")).isEnabled());
		passWord.clear();
		Thread.sleep(2000);
		
		passWord.sendKeys("^%&");
		//Assert.assertFalse(driver.findElement(By.className("special-char")).isEnabled());
		passWord.clear();
		Thread.sleep(2000);
		
		passWord.sendKeys("123456789");
		//Assert.assertFalse(driver.findElement(By.className("8-char")).isEnabled());
		passWord.clear();
		Thread.sleep(2000);
	}
	
	public boolean isElementEnable(String xpathValue) {
		WebElement element = driver.findElement(By.xpath(xpathValue));
		if (element.isEnabled()) {
			System.out.println("Element with xpath =" + xpathValue + "is enable!");
			return true;
		} else {
			System.out.println("Element with xpath =" + xpathValue + "is disable!");
			return false;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}