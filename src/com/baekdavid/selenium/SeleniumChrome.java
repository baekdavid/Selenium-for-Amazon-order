package com.baekdavid.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SeleniumChrome {

	public static WebDriver driver = null;
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// open the web site
		driver.navigate().to("https://amazon.fr");
		driver.manage().window().maximize();
		String title = driver.getTitle();

		if (title.equalsIgnoreCase("Amazon.fr"))
			System.out.println("Title matches");
		else
			System.out.println(title);
	
		//locate a web element
		String tagname = "";
		tagname = driver.findElement(By.cssSelector("#nav-link-shopall > span.nav-line-2")).getText();
		System.out.println(tagname);

		// drop down
		WebElement category = driver.findElement(By.cssSelector("#nav-link-shopall > span.nav-line-2 > span"));
		
		Actions action = new Actions(driver);
		action.moveToElement(category).perform();
		Thread.sleep(2000);

		WebElement books = driver.findElement(By.cssSelector("#nav-flyout-shopAll > div.nav-? > div.nav-"));
		action.moveToElement(books);
		action.click();
		action.perform();
		Thread.sleep(3000);

		driver.findElement(By.linkText("Fiction Books")).click();
		Thread.sleep(2000);

		// Typing the text
		WebElement myElement = driver.findElement(By.id("twotabsearchtextbox"));
		myElement.sendKeys("John Grisham");

		driver.findElement(By.className("nav-input")).click();
		Thread.sleep(2000);
		
		//Select the book
		driver.findElement(By.partialLinkText("Firm")).click();
		Thread.sleep(2000);
		
		//Shift the tab control
		java.util.Set<String> handles = driver.getWindowHandles();
		String winHandle1 = driver.getWindowHandle();
		handles.remove(winHandle1);
		
		String winHandle = handles.iterator().next();
		String winHandle2 = " ";
		if (winHandle != winHandle1)
		{
			// to retrieve the handle of second window, extracting the handle which does 
			winHandle2 = winHandle; //Storing handle of second window handle
			//Switch control to new tab
			driver.switchTo().window(winHandle2);
			System.out.println(winHandle2);
		}
		Thread.sleep(2000);
		
		//add to cart
		driver.findElement(By.cssSelector("#add-to-cart-button")).click();
		Thread.sleep(5000);
		
		//scroll the web page till end.
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript ("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		
		//iframes
		driver.get("https://n3energie.netlify.app"); //baekdavid's practice site hosted by Netlify
		WebElement frame = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
			
		WebElement textbox = driver.findElement(By.xpath("//input[@type='text']"));
		textbox.sendKeys("hey");
		Thread.sleep(3000);	
		
			
		}
		

	}


