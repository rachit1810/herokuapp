package New_Selenium.Project;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class StandAloneTest 
{
	
		/*WebDriver driver = new ChromeDriver();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		driver.findElement(By.cssSelector("a[id='btn-make-appointment']")).click();
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.cssSelector("#btn-login")).click();*/
		//System.out.println(loc);
		//driver.findElement(By.id("txt-username")).sendKeys(loc);
	
		public static String url = "https://katalon-demo-cura.herokuapp.com/";
		public static WebDriver driver;
		
		@BeforeMethod
		public void setup()
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		
		public void waiting(String wait_loc)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(wait_loc)));
		}
		
		@Test (priority=0)
		public void login()
		{
			//open login page
			driver.get(url);
			
			//go to login page
			WebElement toggle_menu = driver.findElement(By.id("menu-toggle"));
			WebElement toggle_menu_login = driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[3]/a"));
			toggle_menu.click();
			toggle_menu_login.click();
			
			try 
			{
				Assert.assertEquals("https://katalon-demo-cura.herokuapp.com/profile.php#login", driver.getCurrentUrl());
			}
			catch(AssertionError e) 
			{
				System.out.println("Login Failed");
			}
			
			//Access elements of login page
			WebElement UserName = driver.findElement(By.id("txt-username"));
			WebElement Password = driver.findElement(By.id("txt-password"));
			WebElement login = driver.findElement(By.id("btn-login"));
			UserName.sendKeys("John Doe");
			Password.sendKeys("ThisIsNotAPassword");
			login.click();
			
			try
			{
				Assert.assertEquals("https://katalon-demo-cura.herokuapp.com/#appointment", driver.getCurrentUrl());
			}
			
			catch(AssertionError e)
			{
				System.out.println("Login Failed");
			}
			
			driver.close();
			
		}
		
		@Test (priority=1)
		public void makeAppointment()
		{
			//Test 1, checking combo facility, selecting Hongkong CURA Healthcare Center loc, selecting Medicare, Date: For selecting 25th July 2025, 
			
			driver.get(url);
			//login to make appointment
			driver.findElement(By.xpath("//*[@id=\"btn-make-appointment\"]")).click();
			
			StandAloneTest obj = new StandAloneTest();
			
			//login
			WebElement UserName = driver.findElement(By.id("txt-username"));
			WebElement Password = driver.findElement(By.id("txt-password"));
			WebElement login = driver.findElement(By.id("btn-login"));
			UserName.sendKeys("John Doe");
			Password.sendKeys("ThisIsNotAPassword");
			login.click();
			
			//make appointment
			Select dd = new Select(driver.findElement(By.id("combo_facility")));
			dd.selectByContainsVisibleText("Hongkong CURA Healthcare Center");
			driver.findElement(By.id("chk_hospotal_readmission")).click();
			driver.findElement(By.xpath("//*[@id=\"radio_program_medicaid\"]")).click();
			driver.findElement(By.id("txt_visit_date")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			obj.waiting("datepicker-days");
			
			
			//For selecting 25th July 2026
			driver.findElement(By.className("datepicker-switch")).click(); //for going to month section
			obj.waiting("datepicker-months");
			
			driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr/td/span[7]")).click();//selecting month
			obj.waiting("datepicker-days");
			
			driver.findElement(By.className("datepicker-switch")).click();
			obj.waiting("datepicker-months");
			
			driver.findElement(By.xpath("html/body/div/div[2]/table/thead/tr[2]/th[2]")).click();
			obj.waiting("datepicker-years");
			
			driver.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr/td/span[8]")).click();
			obj.waiting("datepicker-months");
			
			driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr/td/span[7]")).click();//selecting month
			obj.waiting("datepicker-days");
			
			driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr[4]/td[7]")).click();
			
			//for adding comment
			driver.findElement(By.id("txt_comment")).sendKeys("Please connect with me: [My number] ");
			//for clicking book
			driver.findElement(By.id("btn-book-appointment")).click();
			String expectedUrl = "https://katalon-demo-cura.herokuapp.com/appointment.php#summary";
			
			try 
			{
				Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
			}
			catch(AssertionError e)
			{
				System.out.println("Booking failed!");
			}
			driver.close();	
		}
		@Test (priority=2)
		public void makeAppointment2()
		{
			//Test 2, selecting Seoul CURA Healthcare Center, none health program, date: 10th of current month
			
			driver.get(url);
			//login to make appointment
			driver.findElement(By.xpath("//*[@id=\"btn-make-appointment\"]")).click();
			
			//login
			WebElement UserName = driver.findElement(By.id("txt-username"));
			WebElement Password = driver.findElement(By.id("txt-password"));
			WebElement login = driver.findElement(By.id("btn-login"));
			UserName.sendKeys("John Doe");
			Password.sendKeys("ThisIsNotAPassword");
			login.click();
			StandAloneTest obj = new StandAloneTest();
			
			
			//make appointment
			Select dd = new Select(driver.findElement(By.id("combo_facility")));
			dd.selectByContainsVisibleText("Seoul CURA Healthcare Center");
			driver.findElement(By.id("chk_hospotal_readmission")).click();
			driver.findElement(By.xpath("//*[@id=\"radio_program_medicaid\"]")).click();
			driver.findElement(By.id("txt_visit_date")).click();
			
			obj.waiting("datepicker-days");
			driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr[2]/td[5]")).click();// Selecting 10th of the current month
			
			//for adding comment
			driver.findElement(By.id("txt_comment")).sendKeys("Please dont connect with me: [My number] ");
			//for clicking book
			driver.findElement(By.id("btn-book-appointment")).click();
			
			String expectedUrl = "https://katalon-demo-cura.herokuapp.com/appointment.php#summary";
			
			//Validation
			
			try 
			{
				Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
			}
			catch(AssertionError e)
			{
				System.out.println("Booking failed!");
			}
			driver.close();	
			
		}
		
		@Test (priority = 3)
		public void makeAppointment3()
		{
			//Test 3, selecting Tokyo CURA Healthcare Center, medicare health program, date: by visible text
			
			driver.get(url);
			//login to make appointment
			driver.findElement(By.xpath("//*[@id=\"btn-make-appointment\"]")).click();
			
			//login
			WebElement UserName = driver.findElement(By.id("txt-username"));
			WebElement Password = driver.findElement(By.id("txt-password"));
			WebElement login = driver.findElement(By.id("btn-login"));
			UserName.sendKeys("John Doe");
			Password.sendKeys("ThisIsNotAPassword");
			login.click();
			StandAloneTest obj = new StandAloneTest();
			
			
			//make appointment
			Select dd = new Select(driver.findElement(By.id("combo_facility")));
			dd.selectByContainsVisibleText("Seoul CURA Healthcare Center");
			driver.findElement(By.id("chk_hospotal_readmission")).click();
			driver.findElement(By.xpath("//*[@id=\"radio_program_medicare\"]")).click();
			driver.findElement(By.id("txt_visit_date")).sendKeys("22/03/2026");
			
			//for clicking book
			driver.findElement(By.id("btn-book-appointment")).click();
			
			String expectedUrl = "https://katalon-demo-cura.herokuapp.com/appointment.php#summary";
			
			//Validation
			try 
			{
				Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
			}
			catch(AssertionError e)
			{
				System.out.println("Booking failed!");
			}
			driver.close();	
			
		}
		
		@Test (priority = 4)
		public void history()
		{
			//Test 4, Validating History page
			
			driver.get(url);
			//login to make appointment
			driver.findElement(By.xpath("//*[@id=\"btn-make-appointment\"]")).click();
			
			//login
			WebElement UserName = driver.findElement(By.id("txt-username"));
			WebElement Password = driver.findElement(By.id("txt-password"));
			WebElement login = driver.findElement(By.id("btn-login"));
			UserName.sendKeys("John Doe");
			Password.sendKeys("ThisIsNotAPassword");
			login.click();
			StandAloneTest obj = new StandAloneTest();
			
			//driver.findElement(By.id("menu-toggle"));
			obj.waiting("fa-bars");
			driver.findElement(By.xpath("//*[@id=\"menu-toggle\"]/i")).click();
			obj.waiting("sidebar-nav");
			driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[3]/a")).click();
			
			String expectedUrl = "https://katalon-demo-cura.herokuapp.com/history.php#history";		
			
			try 
			{
				Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
			}
			catch(AssertionError e)
			{
				System.out.println("Verification Failed");
			}
			
			driver.close();
		}
		
		@Test (priority = 5)
		public void profile()
		{
			//Test 5, verifying profile page
			
			driver.get(url);
			//login to make appointment
			driver.findElement(By.xpath("//*[@id=\"btn-make-appointment\"]")).click();
			
			//login
			WebElement UserName = driver.findElement(By.id("txt-username"));
			WebElement Password = driver.findElement(By.id("txt-password"));
			WebElement login = driver.findElement(By.id("btn-login"));
			UserName.sendKeys("John Doe");
			Password.sendKeys("ThisIsNotAPassword");
			login.click();
			
			StandAloneTest obj = new StandAloneTest();
			
			obj.waiting("fa-bars");
			driver.findElement(By.className("fa-bars")).click();
			obj.waiting("sidebar-nav");
			
			driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[4]/a")).click();
			

			String expectedUrl = "https://katalon-demo-cura.herokuapp.com/profile.php#profile";		
			
			try 
			{
				Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
			}
			catch(AssertionError e)
			{
				System.out.println("Verification Failed");
			}
			
			driver.close();
		}
		
		@Test
		public void menu()
		{
			driver.get(url);
			
			driver.findElement(By.xpath("//a[@id=\"menu-toggle\"]")).click();
			
		}
		
	}

