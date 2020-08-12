package practise;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_13_Javascript_executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;
	String name, dateOfBirth, address, city, passWord, state, pin, mobileNumber, email, gender;
	
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
		System.setProperty("webdriver.chrome.driver" , ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver(); 
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// Tạo bộ user data - New Customer
				name = "Quoc Hung";
				gender = "male";
				dateOfBirth = "1988-12-29";
				address = "156 Hung Vuong\n Da Nang";
				city = "Da Nang";
				state = "VietNam";
				pin = "123456";
				mobileNumber = "0799459836";
				email = "quochung" + randomNumber() + "@gmail.com";
				passWord = "552912";

	}

	//@Test
	public void TC_01_Click_hidden_Element() throws InterruptedException {

		driver.get("https://www.myntra.com/");
		
		WebElement homeAndPathLink = driver.findElement(By.xpath("//a[text()='Home & Bath']"));
		jsExecutor.executeScript("arguments[0].click();",homeAndPathLink);
		Thread.sleep(3000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
	}

	@Test
	public void TC_02_Live_Guru() {
		
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		// down casting Object to String 
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
		
		String liveGuruUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(liveGuruUrl, "http://live.demoguru99.com/");
		
		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		highlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

		String liveGuruInnerValue = getPageInnerText();
		Assert.assertTrue(liveGuruInnerValue.contains("Samsung Galaxy was added to your shopping cart."));
		Assert.assertTrue(verifyTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		
		String customerServiceTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		scrollToElement("//input[@id='newsletter']");
		highlightElement("//input[@id='newsletter']	");

		scrollToElement("//input[@id='newsletter']");
		Assert.assertTrue(verifyTextInInnerText("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String liveGuruDomainv4 = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomainv4, "demo.guru99.com");
	}
	
	//@Test
	public void TC_03_Remove_Attribute() {
		
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.name("uid")).sendKeys("mngr274425");
		driver.findElement(By.name("password")).sendKeys("tYbetys");
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
		
		// Click New customer button
				driver.findElement(By.xpath("//a[text()='New Customer']")).click();

				// Điền bộ data ở trên
				driver.findElement(CustomerNameTextBox).sendKeys(name);
				
				// Remove Attribute TYPE = "date" trong DOM của day time picker
				removeAttributeInDOM("//input[@name='dob']", "type");
				
				// Notes: Use chrome or fire fox mới nhất để hiện date time picker 
				/* ------------------chú ý CODE đoạn này  ----------------------*/
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

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	// Browser
		public Object executeForBrowser(String javaSript) {
			return jsExecutor.executeScript(javaSript);
		}

		public String getPageInnerText() {
			return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
			
		}
		
		public boolean verifyTextInInnerText(String textExpected) {
			String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			System.out.println("Text actual = " + textActual);
			return textActual.equals(textExpected);
		}

		public void scrollToBottomPage() {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		public void navigateToUrlByJS(String url) {
			jsExecutor.executeScript("window.location = '" + url + "'");
		}

		// Element
		public void highlightElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
			sleepInSecond(2);
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

		}	

		public void clickToElementByJS(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].click();", element);
		}

		public void scrollToElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		public void sendkeyToElementByJS(String locator, String value) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		}

		public void removeAttributeInDOM(String locator, String attributeRemove) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		}

		public void sleepInSecond(long time) {
			try {
				Thread.sleep(time * 1000);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		
		}
		
		public int randomNumber() {
			Random rand = new Random();
			return rand.nextInt(99);
		}
		
}