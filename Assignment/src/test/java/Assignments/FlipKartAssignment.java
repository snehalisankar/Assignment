package Assignments;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlipKartAssignment {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
  

	public static void main(String[] args) {
		
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("initialise browser");
		  try {
	        	driver.get("https://www.flipkart.com");
	            driver.manage().window().maximize();
	            
	  		  List<WebElement> links  = driver.findElements(By.tagName("a")); 
			  
			  
			  // using stream and foreach and lamda 
			  links.stream().forEach((link)->{System.out.println(link.getText());});
			  //
			  
			  System.out.println("===================================================");
			  // using parallel stream and foreach and lamda 
			  links.parallelStream().forEach((link)->{System.out.println(link.getText());});
			  //
			  
	        } catch (Exception e) {e.printStackTrace();}
		  
            finally {driver.close(); }
		  

		    
    }

	}

