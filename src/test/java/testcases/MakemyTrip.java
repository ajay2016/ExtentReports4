package testcases;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;




public class MakemyTrip {

		static WebDriver driver;
		public static String screenshotName;
		
		@Test
		public void chooseDate() throws ParseException{
			//System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");

			 driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			driver.get("https://www.tripadvisor.com.au/Flights-o255060-From_Sydney-Cheap_Discount_Airfares.html");
			//driver.findElement(By.id("fromCity")).sendKeys("New Delhi (DEL)");
			driver.findElement(By.xpath("//div[@class='date-picker-label' and text()=\"Depart \"]")).click();
			//driver.findElement(By.xpath("//*[@id='start_date_sec']")).click();
			//driver.findElement(By.xpath("//*[@id='start_date_sec']/span[2]")).click();
			selectDate("1/11/2019");
		}
		
		
		public static void selectDate(String date) throws java.text.ParseException{
			SimpleDateFormat df =  new SimpleDateFormat("dd/MM/yyyy");
			Date dateToBeSelected = df.parse(date);
			System.out.println(dateToBeSelected);
			Date currentDate = new Date();
			
			//String yearDisplayed=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
			//System.out.println(yearDisplayed);
			//String monthYearDisplayed = monthDisplayed+yearDisplayed;
			
			String  month =  new SimpleDateFormat("MMMM").format(dateToBeSelected);//March
			String year = new SimpleDateFormat("yyyy").format(dateToBeSelected);//2018
			String day = new SimpleDateFormat("d").format(dateToBeSelected);//1
			System.out.println(day);
			/*without converting month to upper case it does not get the match*/
			String monthYearToBeSelected=month+" "+year;//MARCH 2018
			System.out.println("Month and Year to be selected:   "+monthYearToBeSelected);
			
			
			while(true){
				String monthyearDisplayed=driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
				System.out.println("Month year displayed on the calender:   "+monthyearDisplayed);
					if(monthYearToBeSelected.equalsIgnoreCase(monthyearDisplayed)){
						//select date
					driver.findElement(By.xpath("//a[text()="+day+"]")).click();;
						System.out.println("Found and selected   "+driver.findElement(By.xpath("//a[text()="+day+"]")).getText()+monthyearDisplayed);
						break;
						
					}else{ // navigate to correct month and year
						
						if(dateToBeSelected.after(currentDate)){
							driver.findElement(By.xpath("//span [@class='ui-icon ui-icon-circle-triangle-e']")).click();
						}else{
							//driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/a/span")).click();
						}
						
						
						
						
						
					}
					
				 monthyearDisplayed=driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			}
			
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


