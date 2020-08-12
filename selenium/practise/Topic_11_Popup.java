package practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_11_Popup {
	WebDriver driver;
    WebDriverWait explicitWait;
    boolean status;
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Popup_Fixed() {
		
		driver.get("https://www.zingpoll.com/");
		explicitWait.until(ExpectedConditions.elementToBeClickable((By.id("Loginform"))));
		driver.findElement(By.id("Loginform")).click();
		sleepInSecond(3);
			
		// Login Pop_up display
		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("LoginPopup status =" + status );
		Assert.assertTrue(status);
			
		// Close pop up and verify pop_up closed
		driver.findElement(By.cssSelector("#Login .close")).click();
		sleepInSecond(3);
		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("LoginPopup status =" + status );
		Assert.assertFalse(status);
		
		driver.findElement(By.id("Loginform")).click();
		sleepInSecond(3);
		driver.findElement(By.id("loginEmail")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("loginPassword")).sendKeys("automationfc");
		driver.findElement(By.id("button-login")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='username' and contains(text(),'Automation Testing')")).isDisplayed());
		
	}

	@Test
	public void TC_02_Popup_Random() {
		
		driver.get("https://blog.testproject.io/");
				

	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}