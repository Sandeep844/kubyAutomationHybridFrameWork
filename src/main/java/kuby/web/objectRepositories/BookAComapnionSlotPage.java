package kuby.web.objectRepositories;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kuby.web.testBase.TestBase;
import kuby.web.utility.CommonUtilities;
import org.testng.Assert;

/**
 * Page object for the booking/slot selection panel that opens after clicking "Book your session".
 * Implemented defensively using multiple locator fallbacks because the booking modal HTML may vary.
 */
public class BookAComapnionSlotPage extends TestBase {

    public static final Logger log = Logger.getLogger(BookAComapnionSlotPage.class);

    // ============ Locators using @FindBy ============

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    private WebElement continueBtnonBookyourSession;


    @FindBy(xpath = "//button[text()='Book your session']")
    private WebElement bookyoursessionbtn;

    // ============ Describe your request locators ============

    @FindBy(xpath = "//textarea[contains(@placeholder, 'Share additional information')]")
    private WebElement describeYourRequestTextArea;

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    private WebElement getDescribeYourRequestContinueBtn;

 @FindBy(xpath = "//span[text()='Please describe your request']")
 private WebElement emptyrequestdescribeErrorMessage;

    //   @FindBy(xpath = "//div[contains(@class,'time-slots')]//button")
    //   private List<WebElement> timeSlotsPrimary;

    @FindBy(xpath = "//div[@class='grid grid-cols-3 sm:flex gap-2.5 sm:gap-2 flex-wrap justify-start']")
    private List<WebElement> timeSlotsList;

    public BookAComapnionSlotPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait for booking panel/modal to appear. We try a few common indicators.
     */
    public void clickOnBookYourSessionbtn() {

        // Try primary continue button

        CommonUtilities.elementToBeVisible(bookyoursessionbtn, 10).click();

    }

    /**
     * Click Continue button in booking flow
     */
    public void clickContinueButtonOnBookYourSessionPopUp() {
        try {
            CommonUtilities.elementToBeClickable(continueBtnonBookyourSession, 10).click();
            log.info("Clicked Continue button on book your session window");
        } catch (Exception ex) {
            throw new RuntimeException("Unable to click Continue button: " + ex.getMessage());


        }
    }

    /**
     * Checks for available slots, asserts if empty, and selects the first slot.
     */
    public boolean selectFirstAvailableSlot() {
        try {
            WebElement firstSlot = CommonUtilities.elementToBeVisible(timeSlotsList.get(0), 10);
            System.out.println("Selected slot: " + firstSlot.getText());
            firstSlot.click();
            return true;
        } catch (Exception e) {
            log.error("No time slots available: " + e.getMessage());
            Assert.fail("time slots are not available for booking");
            return false;
        }

    }

    public boolean isAnySlotAvailable() {
        try {
            return timeSlotsList != null && !timeSlotsList.isEmpty();
        } catch (Exception e) {
            log.error("Error checking slot availability: " + e.getMessage());
            return false;
        }
    }

    public void clickContinueButtonOnDescribeYourRequestSection() {
        try {
            CommonUtilities.elementToBeClickable(getDescribeYourRequestContinueBtn, 10).click();
            log.info("Clicked Continue button on Describe Your Request screen");
        } catch (Exception ex) {
            throw new RuntimeException("Unable to click Continue button on Describe Your Request: " + ex.getMessage());
        }
    }

    public void enterDescribeYourRequestResponse(String text) {
        try {
            CommonUtilities.elementToBeVisible(describeYourRequestTextArea, 10).sendKeys(text);
            log.info("Entered text in Describe Your Request: " + text);
        } catch (Exception ex) {
            throw new RuntimeException("Unable to enter text in Describe Your Request: " + ex.getMessage());
        }
    }
    public String getErrorMessageWithoutDescribeYourRequest()
    {
        return emptyrequestdescribeErrorMessage.getText();
    }
}
