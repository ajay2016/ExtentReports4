package base;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	public  static WebDriver driver;
	// private Map<String, Object> vars;
	public JavascriptExecutor js;
	public static String screenshotName;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		// vars = new HashMap<String, Object>();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	  public static String screenshotPath;
			//public static String screenshotName;
			
			public static void captureScreenshot() {

				//File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				Date d = new Date();
				screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

				try {
					FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
			}

}
