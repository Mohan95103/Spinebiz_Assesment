package utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Utilities {
	public static WebDriver driver;

	public static WebDriver openBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {

			System.err.println("Invalid Browser name");
			throw new WebDriverException();
		}

		return driver;
	}

	public static void openUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	public static void implicitWait(long sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);

	}

	public static void sendkeys(WebElement e, String value) {
		try {
			e.sendKeys(value);
		} catch (ElementNotInteractableException m) {
			jsSendKeys(e, value);
		}
	}

	public static void click(WebElement e) {
		e.click();
	}

	public static void closeBrowser() {
		driver.quit();

	}

	public static String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		return url;

	}

	public static String getTitle() {
		return driver.getTitle();

	}

	public static String getText(WebElement e) {
		return e.getText();

	}

	public static String getAttribute(WebElement e) {
		return e.getAttribute("value");

	}

	public static void selectByValue(WebElement element, String val) {
		Select s = new Select(element);
		s.selectByValue(val);
	}

	public static void screenShot(String imagename) {
		TakesScreenshot tk = (TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File des = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\ScreenShots\\" + imagename + ".png");
		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void jsSendKeys(WebElement e, String data) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + data + "')", e);

	}

	public static String getPropertyValue(String key) {
		Properties p = new Properties();
		try {
			p.load(new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\TestDatas\\TestInputs.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String st = p.get(key).toString();
		return st;

	}

}
