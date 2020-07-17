package practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Brower_Element {
	// Khai báo biến đại diện cho Webdriver = instance
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Khởi tạo driver cho web
		driver = new FirefoxDriver(); // **
		driver.manage().window().maximize(); // **
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // **

		// Tìm element

		// driver = new ChromeDriver();

		// driver = new SafariDriver();

		// driver = new InternetExplorerDriver();

		// driver = new EdgeDriver();

		// 4.x:2 broswer này ko support fix lỗi/khai tử/defrecated
		// Headless browser: chạy background/ngầm/không bật giao diện
		// ưu tiên Chrome/firefox Headless
		// driver = new PhantomJSDriver();

		// driver = new OperaDriver();

	}

	@Test
	public void TC_00_Web_Browser() {

		driver.get("https://www.facebook.com/");
		driver.manage().window().setSize(new Dimension(1920, 1080));

	}

	@Test
	public void TC_01_Web_Browser() {
		// mở app cần test(AUT;Application under testing/SUT:System under testing)
		driver.get("https://www.facebook.com/");

		// đóng duy nhất 1 TAB đang active
		// driver.close();

		// đóng tất cả các TAB
		// driver.quit(); //**
		// getTitle/getCurrentUrl<-- hàm GET trả về dữ liệu

		// Lấy ra url của page hiện tại đang active
		String loginPageUrl = driver.getCurrentUrl(); // **
		// Kiểm tra Url

		// Đúng
		Assert.assertTrue(loginPageUrl.equals("https://www.facebook.com/"));
		// Sai
		Assert.assertFalse(loginPageUrl.equals("https://www.facebook.com/login"));
		// Bằng
		Assert.assertEquals(loginPageUrl, "https://www.facebook.com/");

		System.out.println(loginPageUrl);

		String loginPageTitle = driver.getTitle(); // **
		System.out.println(loginPageTitle);

		// Các hàm để verify dữ liệu là đúng hay sai có bằng với các mình muốn hay không
		// TestNG/Junit/Hamcrest/AssertJ...

		// Lấy ra html/css/Jquery/Javascript của page lưu vào 1 biến nào đó
		driver.getPageSource();

		// Lấy ID window/Tab hiện tại
		driver.getWindowHandle(); // **
		driver.getWindowHandle(); // **
		// dùng để build framework
		// driver.manage().logs().get(BROWSER);

		// Chờ cho element được tìm thấy trong khoảng time được set(10s)
		// Webdriver wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Chờ cho page được loading xong
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		// Chờ script được loading sau (JS)
		// Java Script Excutor ( Nhúng đoạn JS vào browser/element
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

		driver.manage().window().fullscreen();
		driver.manage().window().maximize();

		// Test GUI graphic user interface
		// Font/Size/Color/Position/Location/Reponsive(resolution)
		// Set/get Position

		// Set/get Size
		// Back về trang trước
		driver.navigate().back();
		// Forward trang sau
		driver.navigate().forward();
		// F5 lại trang
		driver.navigate().refresh();
		// Mở ra 1 trang url mới (tracking history)
		driver.navigate().to("https://www.facebook.com/");

		// 3 loại
		// window / tab
		// alert
		// frame/iframe
		driver.switchTo().alert();  // **
		driver.switchTo().window("");   // **
		driver.switchTo().frame("");   // **	
	}

	@Test
	public void TC_02_Web_Element() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}