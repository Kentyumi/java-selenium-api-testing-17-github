package Exercise;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise_Textbox_textearea_demoguru {
	WebDriver driver;
	String userIDValue, loginPageUrl;
	String name, dateOfBirth, address, city, passWord, state, pin, mobileNumber, email, gender;

	// Tạo bộ user data
	By CustomerNameTextBox = By.name("name");
	By dateOfBirthTextBox = By.name("dob");
	By addressTextBox = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextBox = By.name("state");
	By pinTextBox = By.name("pinno");
	By mobileNumberTextBox = By.name("telephoneno");
	By emailTextBox = By.name("emailid");
	By passWordTextBox = By.name("password");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		loginPageUrl = driver.getCurrentUrl();

		name = "Quoc Hung";
		gender = "male";
		dateOfBirth = "1988-12-29";
		address = "156 Hung Vuong\n Da Nang";
		city = "Da Nang";
		passWord = "552912";
		state = "VietNam";
		pin = "123456";
		mobileNumber = "0799459836";
		email = "quochung" + randomNumber() + "@gmail.com";

	}

	@Test
	public void TC_01_Register() {
		// Generate Access
		driver.findElement(By.xpath("//a[text()='here']")).click();

		// Input email
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		// Lấy ra user/ password
		userIDValue = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passWord = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		// Mở trang login page
		driver.get(loginPageUrl);

		// Input userID/Password đã lấy ở trên
		driver.findElement(By.name("uid")).sendKeys(userIDValue);
		driver.findElement(By.name("password")).sendKeys(passWord);

		// Click login vs Verify Login successfully page
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");

	}

	@Test
	public void TC_03_NewCustomer() {
		// Click Newcustomer button
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Điền bộ data ở trên
		driver.findElement(CustomerNameTextBox).sendKeys(name);
		driver.findElement(dateOfBirthTextBox).sendKeys(dateOfBirth);
		driver.findElement(addressTextBox).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextBox).sendKeys(state);
		driver.findElement(pinTextBox).sendKeys(pin);
		driver.findElement(mobileNumberTextBox).sendKeys(mobileNumber);
		driver.findElement(emailTextBox).sendKeys(email);
		driver.findElement(passWordTextBox).sendKeys(passWord);

		// Click to submit button
		driver.findElement(By.name("sub")).click();

		// Verify Newcustomer success
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(),
				"Customer Registered Successfully!!!");

		// Verify data ouput sever trả vể matching với data user
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address.replace("\n", ""));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				email);
	}

	@Test
	public void TC_04_Login() {

	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(99);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
