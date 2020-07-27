package practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Xpath_Css_locater {

	// Khai báo biến driver(instance: thể hiện/đại diện)
	WebDriver driver;

	// Firefox driver
	// Chrome driver
	// IE driver
	// Opera driver
	// PhantomJSDriver
	// Edge driver
	@BeforeClass // Pre-condition
	public void beforeClass() {

		// Khởi tạo trình duyệt Firefox
		// Tương tác với Browser
		driver = new FirefoxDriver();
		// Phóng to broswer
		driver.manage().window().maximize();
		// Chờ element được stable trước khi thao tác trong xx seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Đi đến trang demo guru(mở app url)
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	// HTML của email Textbox
	// <input id="email" class="input-text required-entry validate-email"
	// type="email"
	// title="Email Address" value="" name="login[username]"
	// spellcheck="false" autocorrect="off" autocapitalize="off">
	// Trong Selenium có 3 dạng locator là 3 dạng attribute của HTML(ID/Class/Name)
	// Hay được development team set là duy nhất(unique)

	@Test // Testcase:Action/Verify
	public void TC_01_ID() throws InterruptedException {
		// Tương tác với element email thông qua locater ID
		driver.findElement(By.id("email")).sendKeys("id@gmail.com");
		Thread.sleep(2000);
		// Xóa dữ liệu cho các element có thể nhập được(Textbox/dropdown/Textarea/..)
		driver.findElement(By.id("email")).clear();
	}

	@Test

	public void TC_02_Class() throws InterruptedException {
		driver.findElement(By.className("validate-password")).sendKeys("552912");
		Thread.sleep(2000);
		driver.findElement(By.className("validate-password")).clear();

	}

	@Test
	public void TC_03_Name() throws InterruptedException {
		driver.findElement(By.name("login[username]")).sendKeys("kentyumi");
		Thread.sleep(2000);
		driver.findElement(By.name("login[username]")).clear();
	}

	@Test
	public void TC_04_Tagname() throws InterruptedException {
		// Tim ra tất cả những đường link (thẻ a)
		int Linknumber = driver.findElements(By.tagName("a")).size();
		System.out.println("Sum link = " + Linknumber);
		Thread.sleep(2000);
	}

	@Test // Nó chỉ work với link: Link cố định tuyệt đối
	public void TC_05_Link_Text() throws InterruptedException {
		// Click vào site map link
		driver.findElement(By.linkText("SITE MAP")).click();
		Thread.sleep(2000);
	}

	@Test // Nó chỉ work với link: Text tương đối
	public void TC_06_Partial_Link_Text() throws InterruptedException {
		driver.findElement(By.partialLinkText("ADVANCE")).click();
		Thread.sleep(2000);
	}

	@Test
	public void TC_07_Css() throws InterruptedException {
		// ID
		driver.findElement(By.cssSelector("#name")).sendKeys("Css");
		Thread.sleep(2000);
		// Class
		driver.findElement(By.cssSelector(".fieldset")).isDisplayed();
		Thread.sleep(2000);
		// Name
		driver.findElement(By.cssSelector("input[name='description']")).sendKeys("NameCss");
		Thread.sleep(2000);
		// Link Text
		driver.findElement(By.cssSelector("a[href='http://live.demoguru99.com/index.php/mobile.html']")).click();
		Thread.sleep(2000);
		// partial Link Text
		driver.findElement(By.cssSelector("a[href*='/mobile.html']")).click();
		Thread.sleep(2000);
		// Tag name
		int Linksize = driver.findElements(By.cssSelector("a")).size();
		System.out.println("Css Tagname = " + Linksize);
		Thread.sleep(2000);
	}

	@Test
	public void TC_08_Xpath() throws InterruptedException {
		// mở lại trang my account
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		Thread.sleep(2000);
		// ID
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xpath_id@gmail.com");
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		Thread.sleep(2000);
		// Class
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']"))
				.sendKeys("Xpath_class@gmail.com");
		Thread.sleep(2000);
		// name
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("xpath_name@gmail.com");
		Thread.sleep(2000);
		// Link Text
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//a[text()='About Us']")).click();
		Thread.sleep(2000);
		// Partial Link Text
		driver.findElement(By.xpath("//a[contains(text(),'Customer')]")).click();
		Thread.sleep(2000);
		// Tag name
		System.out.println(driver.findElements(By.xpath("//a")).size());
		Thread.sleep(2000);
		// Css
		driver.findElement(By.xpath("//input[@title='Sign up for our newsletter']")).sendKeys("xpath_css@gmail.com");
		Thread.sleep(2000);
	}

	@AfterClass // Post-codition
	public void afterClass() {
		driver.quit();
		System.out.println("DONE_Testcase_topic_05");
	}
}
