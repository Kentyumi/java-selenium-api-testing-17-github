package practise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_CustomDropdownlist {
	WebDriver driver;
	// Explicit wait
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);

		// Implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Fluent wait ( chưa dùng )

	}

	@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div","5");
		
		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div","10");

		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div","15");

		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div","19");

	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Hàm này được dùng lại nhiều lần ( chỉ cần truyền giá trị vào )
	public void selectItemInDropdown(String parentLocator, String itemLocator, String expectedItem) {

		// 1- Click vào 1 thẻ bất kì show ra hết tất cả các item trong dropdown list
		driver.findElement(By.xpath(parentLocator)).click();
		SleepInSecond(1);

		// 2- Chờ cho đến khi tất cả các item xuất hiện / có trong HTML DOM
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));

		// 3- Lấy hết tất cả các item này đưa vào 1 list WebElement
		List<WebElement> allItems = driver.findElements(By.xpath(itemLocator));

		// 4- Duyệt qua từng cái item
		for (WebElement item : allItems) {

			// 5- Mỗi lần duyệt kiểm tra xem text của item đó có bằng với item mình cần
			// click hay không
        if (item.getText().equals(expectedItem)) {
        item.click();
        SleepInSecond(2);
        
        // 6- Nếu bằng thì không duyệt nữa
        break;
        }
        }

	}

	public void SleepInSecond(long time) {
		try {
			// Hard | Dead wait
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
