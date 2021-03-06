package blogsScripts;

import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class SimpleDataDrivenFramework {
	WebDriver driver = null;

	@Test(dataProvider = "dp")
	public void f(String userName, String password) {
		WebDriver driver = null;

		driver.findElement(By.xpath(".//*[@name = 'userName']")).sendKeys(userName);
		driver.findElement(By.xpath(".//*[@name = 'password']")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@name='login']")).click();
		// Verify correct landing page is presented to user after successful
		// login.
		Assert.assertEquals(driver.getCurrentUrl(), "http://newtours.demoaut.com/mercuryreservation.php");
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.get("http://newtours.demoaut.com/");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@DataProvider
	public Object[][] dp() {
		Object[][] o = null;
		try {

			Workbook wb = Workbook.getWorkbook(new File("D:\\path\\to\\excel\\sheet\\DataDriver1.xls"));
			Sheet sh = wb.getSheet(0); // zero selects first worksheet from the
										// excelsheet.
			o = new Object[sh.getRows()][sh.getColumns()]; // initialized the
															// object with total
															// no. of rows and
															// columns present
															// in the selected
															// sheet (sheet
															// zero).
			for (int i = 0; i < sh.getRows(); i++) { // loop for rows.
				for (int j = 0; j < sh.getColumns(); j++) { // loop for cols.
					o[i][j] = sh.getCell(j, i).getContents(); // getcell
																// function
																// first
																// argument is
																// columns and
																// then rows.
					// In array we have first row and then column.that is why
					// reverse of j and i is present.
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return o;
	}
}
