package practise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_CustomDropdownlist {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	// Explicit wait
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;  
		// Implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Fluent wait ( chưa dùng )

	}

	//@Test
	public void TC_01_Jquery() {

        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div","10");
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='10']")).isDisplayed());

		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div","15");
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());

		selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div","19");
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());

	}
	
  // @Test
	public void TC_02_Angular() throws InterruptedException {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
	
		selectItemInDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]",
				"//ul[@id='games_options']/li", 
				"Basketball");
        Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Basketball");
        
        selectItemInDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]",
				"//ul[@id='games_options']/li", 
				"Golf");
        Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Golf");
		
		// Lấy giá trị text từ DOM trong html
		// document.querySelector("").text    <--- topic 4- TIP TRICK
        
	}
    @Test
    public void TC_03_React() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        
        selectItemInDropdown("//i[@class='dropdown icon']",
				"//span[@class='text']", 
				"Jenny Hess");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']/div[@class='divider text' and text()='Jenny Hess']")).isDisplayed());
        	
        selectItemInDropdown("//i[@class='dropdown icon']",
				"//span[@class='text']", 
				"Stevie Feliciano");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']/div[@class='divider text' and text()='Stevie Feliciano']")).isDisplayed());
        	
    	}

   // @Test
	public void TC_04_Editable() throws InterruptedException {
    	driver.get("http://indrimuska.github.io/jquery-editable-select/");
    	sendkeyToEditDropdown("//div[@id='default-place']/input", "Audi");
    	Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "Audi");
    	
    	sendkeyToEditDropdown("//div[@id='default-place']/input", "Ford");
    	Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "Ford");
    	sendkeyToEditDropdown("//div[@id='slide-place']/input", "Kent");
    	
	}
    
    //@Test
	public void TC_05_Advanced() throws InterruptedException {
    	driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
    	driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
	}

    
    @AfterClass
	public void afterClass() {
		driver.quit();
	}

    public void sendkeyToEditDropdown(String locator , String value)  {
    	driver.findElement(By.xpath(locator)).clear();
    	driver.findElement(By.xpath(locator)).sendKeys(value);
    	SleepInSecond(1);
    	driver.findElement(By.xpath(locator)).sendKeys(Keys.TAB);
    	SleepInSecond(1);
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

		// Lấy tổng số lượng trong dropdown là bao nhiêu
		System.out.println("ItemSize = " + allItems.size());

		
		// 4- Duyệt qua từng cái item
		for (WebElement item : allItems) {

			// 5- Mỗi lần duyệt kiểm tra xem text của item đó có bằng với item mình cần
			// click hay không
			
			String actualItem = item.getText();
			System.out.println(actualItem);
			
			if (actualItem.equals(expectedItem)) {
				
				explicitWait.until(ExpectedConditions.elementToBeClickable(item));
		    // Trước khi click thì nên scroll đến element
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				SleepInSecond(2);
				
				item.click();
				SleepInSecond(2);

				// 6- Nếu bằng thì không duyệt nữa
				break;
			}
		}

	}

	public String getHiddenText (String cssLocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + cssLocator + "\").textContent");
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
