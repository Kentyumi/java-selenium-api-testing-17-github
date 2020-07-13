package Exercise;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_06_Create_an_account() {

		// Open brower/Điền thông tin/Click đăng kí
		driver.get("http://live.demoguru99.com/index.php");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.findElement(By.name("firstname")).sendKeys("Nguyen Quoc");
		driver.findElement(By.id("lastname")).sendKeys("Hung");
		String email = "nguyenquochung" + randomNumber() + "@gmail.com";
		driver.findElement(By.className("validate-email")).sendKeys(email);
		driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		// Verify Firstname/Lastname/email hiển thị ở trang Dashboard
		String Successmessage = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();

		// Kiểm tra Text "Thank you for registering with Main Website Store."
		Assert.assertEquals(Successmessage, "Thank you for registering with Main Website Store.");

		// Kiếm tra First/Lastname
		driver.findElement(By.xpath("//div[@class='box']//p[contains(text(),'Nguyen Quoc')]")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='box']//p[contains(.,'" + email + "')]")).isDisplayed();

		// Click account then click logout
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

		// Chờ element(image) hiển thị trên trang
		driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed();

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
