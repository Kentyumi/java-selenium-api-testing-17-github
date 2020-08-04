package Exercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Ymese_WorldPress_Passster {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_FUNC_01() {
		// Input valid data  
		String validPassword = "kentyumi";
		
		// Precondition
		driver.get("https://www.24h.com.vn/bong-da/lich-thi-dau-bong-da-hom-nay-moi-nhat-c48a364371.html");
		
		// Input password and click submit button
		driver.findElement(By.xpath("//input[@placeholder='Enter your password..']")).sendKeys(validPassword);
		driver.findElement(By.name("submit")).click();
		sleepInSecond(10);
		
		// Verify text
		Assert.assertEquals(driver.findElement(By.className("passster-error")).getText(),"Wrong Password");
		
		sleepInSecond(10);
	}
	


	@Test
	public void TC_02_() {

	}


	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}