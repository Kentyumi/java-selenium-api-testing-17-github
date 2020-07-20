package Exercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise_Web_Browser_Element {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_VerifyUrl() {

		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	public void TC_02_VerifyTitle() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	public void TC_03_NavigateFunction() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	public void TC_04_GetSourceCode() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		String RegisterPageSource = driver.getPageSource();
		Assert.assertTrue(RegisterPageSource.contains("Create an Account"));

	}

	@Test
	public void TC_05_Web_Element() throws InterruptedException {

		driver.get("https://www.facebook.com/");
		// Tương tác với 1 element
		// Click vào 1 button/radiobutton/link/checkbox
		// Send key vào 1 textbox/text area
		// Builtin Type:String/int/float/double/boolean/date time...
		// User Type:Do người dùng tự định nghĩa

		WebElement element = driver.findElement(By.xpath("//input[@name='lastname']"));

		// 1 Xóa dữ liệu
		element.clear();

		// 2
		element.sendKeys("Automation FC");
		Thread.sleep(3000);

		// Dùng trực tiếp
		// driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Automation
		// Testing");
		// driver.findElement(By.xpath("//input[@name='lastname']")).clear();

		// Tương tác với nhiều element
		// Lấy ra hết tất cả đường link ở trang hiện tại
		// Đếm được bao nhiêu element thỏa mãn điều kiện
		// Click hết vào các radio button/checkbox đang có ở trang hiện tại
		// Thao tác với nhiều element ở trên tablet/grid

		// List<WebElement> sexRadioButton = driver.findElements(By.name("sex"));
		// 3
		// System.out.println("radioButton size = " + sexRadioButton.size());
		// for (WebElement radio : sexRadioButton)
		// radio.click();
		// Thread.sleep(2000);

		// Xóa dữ liệu trong 1 textbox/text area / dropdown (edited)
		// element.clear();

		// Nhập liệu vào các field có thể nhập được 1 textbox/text area / dropdown
		// (edited)
		// element.sendKeys("");

		// Lấy ra giá trị của Attribute thuộc về element đang thao tác
		String lastNamePlaceHoderText = element.getAttribute("aria-label");
		// Lấy ra
		// chữ "Họ" //Place hoder // Trả về
		// string nên cần biến để lưu
		System.out.println(lastNamePlaceHoderText);
		Thread.sleep(3000);

		element = driver.findElement(By.xpath("//label[@id='loginbutton'"));

		// Click vào 1 button/radio/checkbox/link/image/..
		element.click();
		Thread.sleep(3000);

		// Font/Size/color/Location/Position/Responsive(Resolution)(GUI)
		element = driver.findElement(By.id("loginbutton"));
		String backGroundColorValue = element.getCssValue("Background-color");
		String fontSize = element.getCssValue("font-size");
		// Mã màu của background-color = #4267b2(hexa)| RGB(black/yelow/pink/...)
		System.out.println("Mã màu = " + backGroundColorValue);
		System.out.println("Font size = " + fontSize);

		// Lấy ra kích thước của broswer so với độ phân giải của màn hình(kích thước bên
		// ngoài)
		driver.manage().window().getPosition();
		// Kích thước bên trong
		driver.manage().window().getSize();

		// Tính từ mép ngoài ( Kích thước bên ngoài)
		element.getLocation();
		// Kích thước bên trong
		element.getSize();

		element = driver.findElement(By.cssSelector("#email"));
		System.out.println("Tagname = " + element.getTagName()); // Tagname = input
		Thread.sleep(3000);

		// Get text đăng nhập facebook
		element = driver.findElement(By.xpath("//div[@id='header-block']/span"));
		System.out.println("Header text = " + element.getText());
		Thread.sleep(3000);

		// Kiểm tra element có hiển thị trên UI hay ko <--- trả ra true/false
		element = driver.findElement(By.xpath("div[@data-ownerid='email']//div[@role='alert']"));
		Assert.assertTrue(element.isDisplayed()); // liên quan hàm wait sẽ học sau

		// Kiểm tra element có bị disable| có thao tác được hay không // field read only
		// True <--- enable | False <--- disable
		Assert.assertTrue(element.isEnabled());

		// Kiểm tra element đã được chọn hay chưa radio button/dropdown list/ checkbox (
		// chọn được nhiều đáp án)
		// Trua là được chọn rồi và false là chưa được chọn
		Assert.assertTrue(element.isSelected());

		// Tagname = form
		element = driver.findElement(By.xpath("//input[@id='email']"));
		element.submit();   // chạy ra error
		Thread.sleep(3000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}