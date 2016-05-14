package blogsScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class RandomDropdwnSelection {

	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://the-internet.herokuapp.com/dropdown");
		Select s = new Select(driver.findElement(By.xpath(".//*[@id='dropdown']")));
		System.out.println(s.getFirstSelectedOption().getText());
		s.selectByIndex(1);
		if(s.getFirstSelectedOption().getText().equals("Please select an option")){
			s.selectByVisibleText("Option 1");
		}
		else if(s.getFirstSelectedOption().getText().equals("Option 1")){
			s.selectByVisibleText("Option 2");
		}
		else{
			s.selectByVisibleText("Option 3");
		}
		//driver.quit();

	}

}
