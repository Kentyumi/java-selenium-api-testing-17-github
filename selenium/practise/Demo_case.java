package practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Demo_case {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_Xpath() throws InterruptedException {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//a[text()='About Us']")).click();
		Thread.sleep(2000);
	}

	@AfterClass // Post-codition
	public void afterClass() {
		driver.quit();
	}
}
