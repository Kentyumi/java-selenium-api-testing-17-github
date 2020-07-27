package practise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_default_Dropdownlist {
	WebDriver driver;
	Select select;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Single() {
		driver.get("https://www.facebook.com/");

		// Có cái dropdown xuất hiện thì mới khởi tạo
		// Khởi tạo 1 biến select : đi tìm element có id = day
		select = new Select(driver.findElement(By.id("month")));

		// Kiếm tra 1 element có hổ trợ chọn nhiều (multiple) hay không
		// Dropdown nào không hổ trợ
		boolean status = select.isMultiple();
		System.out.println(status);
		Assert.assertFalse(status);

		// <select aria-label="Tháng" name="birthday_month" id="month" title="Tháng"
		// class="_9407 _5dba _8esg">
		// <option value="0">Tháng</option> 0
		// <option value="1">Tháng 1 1
		// </option><option value="2">Tháng 2 2
		// </option><option value="3">Tháng 3 3
		// </option><option value="4">Tháng 4
		// </option><option value="5">Tháng 5
		// </option><option value="6">Tháng 6
		// </option><option value="7" selected="1">Tháng 7
		// </option><option value="8">Tháng 8
		// </option><option value="9">Tháng 9
		// </option><option value="10">Tháng 10
		// </option><option value="11">Tháng 11
		// </option><option value="12">Tháng 12</option></select>

		// Chọn tháng 5 ( Index(int) , Value ( string) )

		// ko nên dùng
		select.selectByIndex(2);
		sleepInSecond(3);

		// Chọn bằng value
		select.selectByValue("5");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 5");
		;

		// Kiểm tra đã chọn 1 item thành công
		// Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng ..");;

		// Visible text
		select.selectByVisibleText("Tháng 10");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 10");
		;

		// Kiểm tra xem 1 dropdown có bao nhiêu item và value của nó là gì

		// Get ra tất cả các thẻ option của dropdown item
		List<WebElement> monthOption = select.getOptions();
		// driver.findElement(By.xpath("//select[@id='month']/option[1]"));
		// driver.findElement(By.xpath("//select[@id='month']/option[2]"));
		// driver.findElement(By.xpath("//select[@id='month']/option[3]"));
		// driver.findElement(By.xpath("//select[@id='month']/option[4]"));
		// driver.findElement(By.xpath("//select[@id='month']/option[5]"));
		// ...
		// driver.findElement(By.xpath("//select[@id='month']/option[13]"));

		// Inra xem có bao nhiêu giá trị (13 giá trị)

		int monthOptionSize = monthOption.size();
		System.out.println("Month item number =" + monthOptionSize);
		Assert.assertEquals(monthOptionSize, 13);

		// In ra item value của nó
		// for-iterstor

		for (int i = 0; i < monthOption.size(); i++) {
			monthOption.get(i);

			// i = 0 , i < 12 , i + 1
			// i = 1 , i < 12 , i + 1
			// i = 12 , i < 12, i + 1
			// ...
			// 1 =13 , i > 12 ko chạy

			System.out.println(monthOption.get(i).getText());
			if (i == 5) {
				break;
			}
		}
		// for-each không dùng được break

		// Kiểm tra được dropdown hiển thị đúng giá trị ( Requirement cho trước )
		ArrayList<String> actualItem = new ArrayList<String>();
		actualItem.add("Tháng");
		actualItem.add("Tháng 1");
		actualItem.add("Tháng 2");
		actualItem.add("Tháng 3");
		actualItem.add("Tháng 4");
		actualItem.add("Tháng 5");
		actualItem.add("Tháng 6");
		actualItem.add("Tháng 7");
		actualItem.add("Tháng 8");
		actualItem.add("Tháng 9");
		actualItem.add("Tháng 10");
		actualItem.add("Tháng 11");
		actualItem.add("Tháng 12");

		ArrayList<String> expectedItem = new ArrayList<String>();
		for (WebElement option : monthOption) {
			expectedItem.add(option.getText());
		}
		Assert.assertEquals(actualItem, expectedItem);
	}

	@Test
	public void TC_02_Multiple() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Hổ trợ multiple select
		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		Assert.assertTrue(select.isMultiple());

		// Automation
		// Mobile
		// Performance

		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Mobile");

		int optionSelected = select.getAllSelectedOptions().size();
		System.out.println("Selected Item =" + optionSelected);

		Assert.assertEquals(optionSelected, 2);

		List<WebElement> optionSelectedElement = select.getAllSelectedOptions();
		for (WebElement option : optionSelectedElement) {
			System.out.println(option.getText());
		}

		// Bỏ chọn 2 thèn cùng lúc ( chỉ hổ trợ cho multiple )
		select.deselectAll();
		optionSelected = select.getAllSelectedOptions().size();
		System.out.println("Selected Item =" + optionSelected); // trả về giá trị 0
		Assert.assertEquals(optionSelected, 0);

	}

	public void TC_03_HTML_Dropdownlist() {
		// Step 1: Truy cập vào trang
		driver.get("https://demo.nopcommerce.com/register");

		// Step 2 : Click register
        driver.findElement(By.id("register-button"));
        
        // Step 3: Input các thông tin hợp lệ vào form
        
	}

	public void sleepInSecond(long time) {
		// 1000ms = 1s
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}