package practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Part1_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 10);

		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Visible() {
		driver.get("https://www.facebook.com/r.php");

		// wait for email / ... visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password_step_input']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstname']")));

		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("Hung345@gmail.com");
		sleepInSecond(5);
	}

	//@Test
	public void TC_02_Invisible() {
		driver.navigate().refresh();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));

		driver.get("http://live.demoguru99.com/");
		
        // 2.1- Element không hiển thị trên UI + có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer']//a[text()='My Account']")));
		
		// 2.2- Element không hiển thị trên UI + không có trong DOM
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));

		
	}

	//@Test
	public void TC_03_Presence() {
		driver.get("https://www.facebook.com/r.php");
		
		// 3.1 Element có trong DOM và có hiển thị trên UI
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@name='reg_email__']")));
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@id='password_step_input']")));
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@name='firstname']")));
		
		// 3.1 Element có trong DOM và không có hiển thị trên UI
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
	}

	@Test
	public void TC_04_Staleness() {
		 driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		 driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
		 
		 // Page có trạng thái là A
		 explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='create_account_error']//li")));
		 WebElement emailErrorMessage = driver.findElement(By.xpath("//div[@id='create_account_error']//li"));
		 
		 driver.navigate().refresh();
		 
		// Page có trạng thái khác B lúc này emailErrorMessage có trạng thái Staleness 		 
		// 4 Element khong có trong DOM 
		 explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));
		 
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}