package practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_UserInteraction_Part1 {
	WebDriver driver;
    Actions action ;
		
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Hover_Mouse() {
		driver.get("https://www.myntra.com/");
		WebElement element = driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"));
		
		// Hover to KID Menu
		action.moveToElement(element).perform();
		
	}

	@Test
	public void TC_02_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}