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

public class Exercise_Textbox_textearea_demoguru {
	WebDriver driver;
	String userIDValue, loginPageUrl, customerID;
	String name, dateOfBirth, address, city, passWord, state, pin, mobileNumber, email, gender;

	// Tạo bộ dữ liệu cho Edit Customer field
	String editAddress, editCity, editState, editPin, editMobileNumber, editEmail;

	// Tạo bộ user data - NewCustomer
	By CustomerNameTextBox = By.name("name");
	By genderTextbox = By.name("gender");
	By genderRadioButton = By.xpath("//input[@value='m']");
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
		
		// Tạo bộ user data - New Customer
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

		
		// Tạo bộ user data - Edit Customer
		editAddress= "156 Minh Khai\n Da Nang";
	    editCity= "Sai Gon";
	    editState= "America";
	    editPin= "654321";
	    editMobileNumber= "0799459889";
	    editEmail= "myhanh" + randomNumber() + "@hotmail.com";
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

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	@Test
	public void TC_04_EditCustomer() {
		// Click Edit customer button
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		// Input customerID vao textbox
		driver.findElement(By.name("cusid")).sendKeys(customerID);

		// Click submit button
		driver.findElement(By.name("AccSubmit")).click();

		// Verify Edit Customer form info matching with New customer info
		Assert.assertEquals(driver.findElement(CustomerNameTextBox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(genderTextbox).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(dateOfBirthTextBox).getAttribute("value"), dateOfBirth);
		Assert.assertEquals(driver.findElement(addressTextBox).getText(), address);
		Assert.assertEquals(driver.findElement(cityTextbox).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextBox).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextBox).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(mobileNumberTextBox).getAttribute("value"), mobileNumber);
		Assert.assertEquals(driver.findElement(emailTextBox).getAttribute("value"), email);

		// Verify name/gender/dob are disable fields | Read-only fields
		Assert.assertFalse(driver.findElement(CustomerNameTextBox).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(dateOfBirthTextBox).isEnabled());

		// Edit data tại edit customer form
		driver.findElement(addressTextBox).clear();
		driver.findElement(addressTextBox).sendKeys(editAddress);
		
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(editCity);
		
		driver.findElement(stateTextBox).clear();
		driver.findElement(stateTextBox).sendKeys(editState);
		
		driver.findElement(pinTextBox).clear();
		driver.findElement(pinTextBox).sendKeys(editPin);
		
		driver.findElement(mobileNumberTextBox).clear();
		driver.findElement(mobileNumberTextBox).sendKeys(editMobileNumber);
		
		driver.findElement(emailTextBox).clear();
		driver.findElement(emailTextBox).sendKeys(editEmail);
		
		// Submit 
		driver.findElement(By.name("sub")).click();
		
		// Verify edit customer successfully
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(),
				"Customer details updated Successfully!!!");
		
		// Verify Update Customer form info matching with Customer info
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dateOfBirth);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				editAddress.replace("\n", ""));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				editMobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				editEmail);
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
