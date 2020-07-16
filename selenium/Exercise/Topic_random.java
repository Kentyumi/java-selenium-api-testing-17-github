package Exercise;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_random {

	public static void main(String[] args) {
		System.out.println(randomNumber());
		System.out.println(randomNumber());
		System.out.println(randomNumber());
		System.out.println(randomNumber());
		System.out.println(randomNumber());
		System.out.println(randomNumber());
	}

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void TC_05_Login_with_valid_Emailpassword() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.className("validate-email")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		// Verify các field cần được verify
		driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed();
		driver.findElement(By.xpath("//strong[text()='Hello, Automation Testing!']")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Automation')]")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'@gmail.com')]")).isDisplayed();

		// Click account then click log out
		driver.findElement(By.xpath("//div[@class='skip-links']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//li[last()]/a")).click();
		
	}

	
	// cố định 1 số 0-99
	// cố định 2 số 0-99
	public static int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(99);
	}

	@AfterClass // Post-codition
	public void afterClass() {
		driver.quit();
	}
}
