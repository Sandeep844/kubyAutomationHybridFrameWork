package kuby.web.utility;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import kuby.web.extentReport.MyReportListner;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import kuby.web.testBase.TestBase;


/**
 * @author
 *         <h1>Common Functions</h1>
 *         <p>
 *         purpose: This class is for common Functionality
 *         </p>
 *         It is used to handle all the functionalities which can be used in
 *         other classes
 */
public class CommonUtilities extends TestBase{


	public static final Logger logg=Logger.getLogger(CommonUtilities.class.getName());
	public static WebDriverWait wait;
	public static long PAGE_LOAD_TIME_OUT = 15;
	public static long IMPLICIT_WAIT = 15;

	/**************************************************
	 * Select Environment
	 **************************************************/
	public static String getEnvironment()
	{
		return System.getProperty("env", "dev");
	}

	/**************************************************
	 * Select Browser
	 **************************************************/
	/*public static String selectBrowser() {
		return System.getProperty("browser", "headless");
	}
	*/
	public static String selectBrowser()
	{
		return System.getProperty("browser", "chrome");
	}

	/**************************************************
	 * Sendkeys method Code (Enterprise tracking added)
	 **************************************************/
	public static void sendkeys(WebElement element, String text)
	{
		try {

			// 🔥 Track last interacted element (IMPORTANT)
			trackElement(element);

			// Select all text
			String selectstr = Keys.chord(Keys.CONTROL, "a");
			element.sendKeys(selectstr);

			// Delete existing value
			element.sendKeys(Keys.DELETE);

			// Send new value
			element.sendKeys(text);

		} catch(Exception e) {
			throw e;
		}
	}

	/**********************************************************
	 * Explicit Wait
	 *********************************************************/
	public static void setExplicitWait(Duration second)
	{
		wait = new WebDriverWait(driver, second);
	}

	/****************************************************************
	 * Click On Element With Explicit wait (element to Be click able)
	 ****************************************************************/
	public static WebElement elementToBeClickable(WebElement ele, int time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	/******************************************************************************
	 * An expectation for checking that an element, known to be present on the DOM of a page, is
     * visible. Visibility means that the element is not only displayed but also has a height and
     * width that is greater than 0.
	 * Click On Element When the  VisibilityOf()
	 ******************************************************************************/
	public static WebElement elementToBeVisible(WebElement element, int time )
			{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/******************************************************
	 * Check The Text To Be Present In Element or Wait for sometime element to Be
	 * present
	 ******************************************************/
	public static Boolean waitTextToBePresentInElement(WebElement element, int time,String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time)); 
		return wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, text)));
	}
	/********************************************************************
	 * // Fluent Wait for Visibility of Element
	 ********************************************************************/
//	public static WebElement fluentWait(WebElement element, int timeOut,int duration) {
//		Wait<WebDriver> wait = null;
//			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(timeOut))
//					.pollingEvery(Duration.ofSeconds(duration)).ignoring(Exception.class);
//			return	wait.until(ExpectedConditions.elementToBeClickable(element));
//	}

	/**************************************************************
	 * // Click The Element Using JavaScript
	 **************************************************************/
	public static boolean JSClick(WebElement element) {
		boolean flag = false;
		try {

			// 🔥 Track last interacted element
			trackElement(element);

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);

			flag = true;

		} catch (Exception e) {
			throw e;

		} finally {

			if (flag) {
				logg.info("JS Click Action is performed");
			} else {
				logg.info("JS Click Action is not performed");
			}
		}
		return flag;
	}
	/*************************************************************
	 * Generate The Random Alphabetical String
	 ***********************************************************/
	public static String generateUniqueAlpahabeticalString(int len) {
		String uniqueName = RandomStringUtils.randomAlphabetic(len);
		return uniqueName;
	}

	/************************************************************
	 * Generate The Random Alphabetical Capital Letter String
	 ***********************************************************/
	public static String generateUniqueAlphabeticalCapitalLetterString(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		Set<Character> usedCharacters = new HashSet<>();
		while (sb.length() < length) {
			char randomChar = (char) (random.nextInt(26) + 'A'); // Generate a random uppercase letter
			if (!usedCharacters.contains(randomChar)) {
				sb.append(randomChar);
				usedCharacters.add(randomChar);
			}
		}
		return sb.toString();
	}

	/**************************************************************
	 * Generate The Random Numeric Values
	 **************************************************************/
	public static String generateUniqueNumericString() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
		return now.format(formatter);
	}

	/**************************************************************
	 * Generate The Random Numeric Values basic upon length
	 **************************************************************/
	public static String generateRandomNumericValue(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int digit = random.nextInt(10); // Generates a random digit between 0 and 9
			sb.append(digit);
		}
		return sb.toString();
	}

	/**************************************************************
	 * Generate The Random Alphabetical Small Letter String
	 **************************************************************/
	public static String generateUniqueAlphabeticalSmallLetterString(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		Set<Character> usedCharacters = new HashSet<>();

		while (sb.length() < length) {
			char randomChar = (char) (random.nextInt(26) + 'a'); // Generate a random uppercase letter

			if (!usedCharacters.contains(randomChar)) {
				sb.append(randomChar);
				usedCharacters.add(randomChar);
			}
		}
		return sb.toString();
	}

	/**************************************************************
	 * Generate The Random Alphabetical First Name String
	 **************************************************************/

	public static String generateFirstName() {
		Faker faker = new Faker();
		String lname = faker.name().lastName();
		faker.idNumber().ssnValid();
		return lname.replaceAll("[^a-zA-Z0-9]", "");
	}

	/**************************************************************
	 * Generate The Random Alphabetical Last Name String
	 **************************************************************/

	public static String generateLastName() {
		Faker faker = new Faker();
		String lname = faker.name().lastName();
		faker.idNumber().ssnValid();
		return lname.replaceAll("[^a-zA-Z0-9]", "");
	}

	/**************************************************************
	 * Generate The Current Date As String
	 **************************************************************/
	public static String currentDateStringFormat() {
		// Define the desired date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMM");
		// Get the current date
		LocalDate currentDate = LocalDate.now();
		// Format the date using the formatter
		String formattedDate = currentDate.format(formatter);
		logg.info(formattedDate);
		return formattedDate;
	}

	/**************************************************************
	 * Select the Value From DropDown List
	 ***************************************************************/
	public static void selectValueFromDropDownList(WebElement element, String selBillingProfileName) {
		// 🔥 Track last interacted element
		trackElement(element);
		CommonUtilities.sendkeys(element, selBillingProfileName);
		element.sendKeys(Keys.DOWN);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		element.sendKeys(Keys.ENTER);
	}
	/**************************************************************
	 * Select the value from Given Option List
	 **************************************************************/
	public static void selectOptionFromList(List<WebElement> element, String value) {
		List<WebElement> allText = element;
		for (WebElement text : allText) {
			if (text.getText().equals(value)) {
				// 🔥 Track last interacted element
				trackElement(text);
				WebElement selectLagNo=	CommonUtilities.elementToBeClickable(text, 30);
			     selectLagNo.click();
				break;
			}
		}
	}
	
	/**************************************************************
	 * scroll horizontally on a web page to a specific web element
	 **************************************************************/
	public static void scrollByVisibilityOfElements(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + pixels + ");");
    }
	/**************************************************************
	 *  Move to Element
	 **************************************************************/
	public static boolean moveToElement(WebElement element) {
		boolean flag = false;
		try {
			// 🔥 Track last interacted element
			trackElement(element);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**************************************************************
	 *  Thread Sleep Utility
	 **************************************************************/
	public static void threadSleep(long milliseconds) {
		try {
			synchronized(CommonUtilities.class) {
				Thread.sleep(milliseconds);
				logg.info("waiting "+milliseconds+" milliseconds");
			}
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
			logg.info("Thread sleep Interrupted: "+e.getMessage());
		}	
	}

	public static void clickOnWebElement(WebElement backButton) {
		// TODO Auto-generated method stub
		
	}
	/**************************************************************
	 * Select Checkbox if Not Already Selected
	 **************************************************************/
	public static void selectCheckBox(WebElement checkbox) {
	    try {
	    	// 🔥 Track last interacted element
	    	trackElement(checkbox);
	        if (!checkbox.isSelected()) {
	            checkbox.click();
	            logg.info("Checkbox selected successfully.");
	        } else {
	            logg.info("Checkbox is already selected.");
	        }
	    } catch (Exception e) {
	        logg.error("Unable to select checkbox: " + e.getMessage());
	    }
	}

	/**************************************************************
	 * Deselect Checkbox if Already Selected
	 **************************************************************/
	public static void deselectCheckBox(WebElement checkbox) {
	    try {
	    	// 🔥 Track last interacted element
	    	trackElement(checkbox);
	        if (checkbox.isSelected()) {
	            checkbox.click();
	            logg.info("Checkbox deselected successfully.");
	        } else {
	            logg.info("Checkbox is already deselected.");
	        }
	    } catch (Exception e) {
	        logg.error("Unable to deselect checkbox: " + e.getMessage());
	    }
	}

	/**************************************************************
	 * Verify Checkbox Selection Status
	 **************************************************************/
	public static boolean isCheckBoxSelected(WebElement checkbox) {
	    boolean isSelected = false;
	    try {
	    	// 🔥 Track last interacted element
	    	trackElement(checkbox);
	        isSelected = checkbox.isSelected();
	        logg.info("Checkbox selected status: " + isSelected);
	    } catch (Exception e) {
	        logg.error("Unable to verify checkbox status: " + e.getMessage());
	    }
	    return isSelected;
	}

	/**************************************************************
	 * Click Checkbox Using JavaScript (useful for hidden elements)
	 **************************************************************/
	public static void clickCheckBoxUsingJS(WebElement checkbox) {
	    try {
			// 🔥 Track last interacted element (IMPORTANT)
			trackElement(checkbox);
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", checkbox);
	        logg.info("Checkbox clicked using JavaScript Executor.");
	    } catch (Exception e) {
	        logg.error("Unable to click checkbox using JS: " + e.getMessage());
			throw e;
	    }
	}

	/**************************************************************
	 * Select Checkbox by Label Text (if label is used instead of input)
	 **************************************************************/
	public static void selectCheckBoxByLabel(List<WebElement> checkboxes, String labelText) {
	    try {
	        for (WebElement checkbox : checkboxes) {
	            String label = checkbox.getText().trim();
	            if (label.equalsIgnoreCase(labelText)) {
	            	// 🔥 Track last interacted element
	            	trackElement(checkbox);
	                if (!checkbox.isSelected()) {
	                    checkbox.click();
	                    logg.info("Checkbox with label '" + labelText + "' selected.");
	                } else {
	                    logg.info("Checkbox with label '" + labelText + "' is already selected.");
	                }
	                break;
	            }
	        }
	    } catch (Exception e) {
	        logg.error("Unable to select checkbox by label: " + e.getMessage());
	    }
	}

	/**************************************************************
	 * Take Screenshot and Save to File
	 **************************************************************/
//	public static String takeScreenshot(String testName) {
//	    try {
//	        // Get file separator
//	        String fs = java.nio.file.FileSystems.getDefault().getSeparator();
//
//	        // Create screenshot directory if it doesn't exist
//	        String screenshotDir = TestBase.downloadPath;
//	        java.io.File dir = new java.io.File(screenshotDir);
//	        if (!dir.exists()) {
//	            dir.mkdirs();
//	            logg.info("Screenshot directory created: " + screenshotDir);
//	        }
//
//	        // Take screenshot
//	        org.openqa.selenium.TakesScreenshot ts = (org.openqa.selenium.TakesScreenshot) driver;
//	        java.io.File src = ts.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
//
//	        // Create filename with timestamp
//	        java.time.LocalDateTime now = java.time.LocalDateTime.now();
//	        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
//	        String timestamp = now.format(formatter);
//	        String filename = testName + "_" + timestamp + ".png";
//	        String screenshotPath = screenshotDir + fs + filename;
//
//	        // Copy file to destination
//	        org.openqa.selenium.io.FileHandler.copy(src, new java.io.File(screenshotPath));
//	        logg.info("Screenshot captured successfully: " + screenshotPath);
//
//	        return screenshotPath;
//	    } catch (Exception e) {
//	        logg.error("Unable to take screenshot: " + e.getMessage());
//	        return null;
//	    }
//	}

	/**************************************************************
	 * Highlight failed element based on exception message
	 **************************************************************/
	public static boolean forceHighlightFromException(Throwable throwable) {

		try {

			if (throwable == null) return false;

			String errorMessage = throwable.getMessage();
			if (errorMessage == null) return false;

			String xpath = null;

			// Extract xpath safely (works for different selenium versions)
			if (errorMessage.contains("selector\":\"")) {
				xpath = errorMessage.split("selector\":\"")[1].split("\"")[0];
			}
			else if (errorMessage.contains("value\":\"")) {
				xpath = errorMessage.split("value\":\"")[1].split("\"")[0];
			}
			else {
				return false;
			}

			JavascriptExecutor js = (JavascriptExecutor) driver;

			String script =
					"var xpath = arguments[0];" +
							"var element = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
							"if(element){" +
							"   element.scrollIntoView({block:'center'});" +
							"   element.style.border='4px solid red';" +
							"   element.style.position='relative';" +

							"   var arrow=document.createElement('div');" +
							"   arrow.innerHTML='⬇';" +
							"   arrow.style.position='absolute';" +
							"   arrow.style.top='-40px';" +
							"   arrow.style.left='50%';" +
							"   arrow.style.transform='translateX(-50%)';" +
							"   arrow.style.color='red';" +
							"   arrow.style.fontSize='40px';" +
							"   element.appendChild(arrow);" +

							"   var badge=document.createElement('div');" +
							"   badge.innerHTML='❌ FAIL';" +
							"   badge.style.position='absolute';" +
							"   badge.style.top='-70px';" +
							"   badge.style.left='50%';" +
							"   badge.style.transform='translateX(-50%)';" +
							"   badge.style.background='red';" +
							"   badge.style.color='white';" +
							"   badge.style.padding='5px 10px';" +
							"   badge.style.fontWeight='bold';" +
							"   badge.style.borderRadius='5px';" +
							"   element.appendChild(badge);" +
							"   return true;" +
							"}else{" +
							"   var div=document.createElement('div');" +
							"   div.innerHTML='❌ ELEMENT NOT FOUND : '+xpath;" +
							"   div.style.position='fixed';" +
							"   div.style.top='10px';" +
							"   div.style.left='10px';" +
							"   div.style.zIndex='9999';" +
							"   div.style.background='red';" +
							"   div.style.color='white';" +
							"   div.style.padding='10px';" +
							"   document.body.appendChild(div);" +
							"   return true;" +
							"}";

			Object result = js.executeScript(script, xpath);

			return result != null && (Boolean) result;

		} catch (Exception e) {
			System.out.println("Force highlight failed");
			return false;
		}
	}
	/**************************************************************
	 * Auto Highlight Element Based on Exception Message
	 **************************************************************/
	public static boolean autoHighlightFromException(Throwable throwable) {

		try {

			if (throwable == null) return false;

			String errorMessage = throwable.getMessage();
			if (errorMessage == null) return false;

			String xpath = null;

			// Extract xpath safely
			if (errorMessage.contains("selector\":\"")) {
				xpath = errorMessage.split("selector\":\"")[1].split("\"")[0];
			}
			else if (errorMessage.contains("value\":\"")) {
				xpath = errorMessage.split("value\":\"")[1].split("\"")[0];
			}
			else {
				return false;
			}

			WebElement element = driver.findElement(By.xpath(xpath));

			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript(
					"arguments[0].scrollIntoView({block:'center'});" +
							"arguments[0].style.border='4px solid orange';" +
							"arguments[0].style.backgroundColor='rgba(255,165,0,0.3)';",
					element);

			return true;

		} catch (Exception e) {
			System.out.println("Auto highlight skipped");
			return false;
		}
	}
	/**************************************************************
	 * Highlight the last interacted element (if stored in a variable) - useful for debugging test flow in screenshots
	 **************************************************************/
	public static boolean highlightLastAction() {

		try {

			if (lastInteractedElement != null) {

				JavascriptExecutor js = (JavascriptExecutor) driver;

				js.executeScript(
						"arguments[0].scrollIntoView({block:'center'});" +
								"arguments[0].style.border='4px solid red';" +
								"arguments[0].style.backgroundColor='rgba(255,0,0,0.2)';",
						lastInteractedElement);

				return true;
			}

		} catch (Exception e) {}

		return false;
	}
	public static WebElement lastInteractedElement = null;

	public static void trackElement(WebElement element) {
		lastInteractedElement = element;
	}
	/**************************************************************
	 * Highlight success element (Green style)
	 **************************************************************/
	public static void highlightSuccessElement() {

		try {

			if (lastInteractedElement != null) {

				JavascriptExecutor js = (JavascriptExecutor) driver;

				js.executeScript(
						"arguments[0].scrollIntoView({block:'center'});" +
								"arguments[0].style.border='4px solid green';" +
								"arguments[0].style.backgroundColor='rgba(0,255,0,0.2)';" +
								"arguments[0].style.position='relative';" +

								"var badge=document.createElement('div');" +
								"badge.innerHTML='✔ PASS';" +
								"badge.style.position='absolute';" +
								"badge.style.top='-40px';" +
								"badge.style.left='50%';" +
								"badge.style.transform='translateX(-50%)';" +
								"badge.style.background='green';" +
								"badge.style.color='white';" +
								"badge.style.padding='5px 10px';" +
								"badge.style.fontWeight='bold';" +
								"badge.style.borderRadius='5px';" +
								"arguments[0].appendChild(badge);",
						lastInteractedElement
				);
			}

		} catch (Exception e) {
			System.out.println("Success highlight failed");
		}
	}

}

