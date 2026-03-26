package kuby.web.testCases;

import kuby.web.objectRepositories.BookAComapnionSlotPage;
import kuby.web.objectRepositories.CustomerCompanionPage;
import kuby.web.objectRepositories.LoginPage;
import kuby.web.testBase.TestBase;
import kuby.web.utility.CommonUtilities;
import kuby.web.utility.StringWords;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class CustomerBookingTestCases extends TestBase {

    public static final Logger logg = Logger.getLogger(CustomerBookingTestCases.class.getName());

    private LoginPage loginpage;
    private CustomerCompanionPage companionPage;
    private BookAComapnionSlotPage slotPage;

    public CustomerBookingTestCases() {
        super();
    }

    @BeforeClass
    public void setUp() {
        browserOpen();

        loginpage = new LoginPage();
        companionPage = new CustomerCompanionPage();
        slotPage = new BookAComapnionSlotPage();
    }

    /**
     * Scenario 1: Navigate to Companion section and verify user is on Companion page
     */
    @Test(priority = 1)
    public void TC_04_Verify_user_is_able_to_Redirect_Homepage_To_CompanionSection() {
        logg.info("========== TC 04: User able to redirect home page to companion section ==========");

        try {
            //Login with valid credentials and accept all cookies
            loginpage.loginWIthAcceptAllCookies();
            // Click via anchor containing '/companions/browse'
            CommonUtilities.JSClick(driver.findElement(By.xpath("//a[contains(@href,'/companions/browse') or contains(.,'KUBYcompanions')]")));
            logg.info("Clicked on Companions link");

            // Wait for page to load
            CommonUtilities.threadSleep(2000);

            // Verify user is on Companions page
            String currentUrl = driver.getCurrentUrl();
            logg.info("Current URL: " + currentUrl);

            Assert.assertTrue(currentUrl.contains("/companions/browse"),
                    "User should be on companions/browse page");

            logg.info("User successfully navigated to Companion page");


        } catch (Exception e) {
            logg.error("redirection is passed: " + e.getMessage());
            throw new RuntimeException("Failed to navigate to companion page", e);
        }
    }

    /**
     * Scenario 2: Click on companion search box and enter companion name
     */
    @Test(priority = 2)
    public void TC_05_Verify_user_is_able_to_Search_the_Valid_Companion_name_and_results_should_be_displayed() {
        logg.info("========== TC 05:User is able to search the valid companion name in the serach box ==========");

        try {
            String searchName = StringWords.enterTestData("CompanionSearchName");
            logg.info("Searching for companion: " + searchName);

            companionPage.searchCompanionByName(searchName);
            CommonUtilities.threadSleep(2000);

            // wait until results refresh
            companionPage.waitForSearchResults();

            // Verify search result contains expected name
            CommonUtilities.threadSleep(2000);

            List<String> allNames = companionPage.getAllCompanionNames();
            logg.info("Total cards found: " + allNames.size());
            logg.info("Card names: " + allNames);

            boolean found = false;
            for (String name : allNames) {
                if (name != null && searchName != null && name.toLowerCase().contains(searchName.toLowerCase())) {
                    found = true;
                    logg.info("Match found: " + name);
                    break;
                }
            }

            Assert.assertTrue(found, "At least one companion with " + searchName + " should be in search results");
            logg.info("✓ Search results verified successfully");

        } catch (Exception e) {
            logg.error("Unable to enter data in search box: " + e.getMessage());
            throw new RuntimeException("Failed to search companion", e);
        }
    }


    /**
     * Scenario 3: Validate date and slot is available and book the slot
     */
    @Test(priority = 3)
    public void TC_06_Verify_user_is_able_to_Book_the_Available_slot_once_data_and_Slots_Available() {
        logg.info("========== TC 06: User should be able to view the dates and select the slot and click continue button ==========");

        try {
            // Click on book your session button
            logg.info("Clicking on Book your session button...");
            slotPage.clickOnBookYourSessionbtn();
            logg.info("Book your session button clicked");

            logg.info("Checking for available slots...");
            boolean anyAvailable = slotPage.isAnySlotAvailable();
            Assert.assertTrue(anyAvailable, "There should be available slots for booking");
            logg.info("Slots are available");

            logg.info("Attempting to select the first available slot...");
            boolean selected = slotPage.selectFirstAvailableSlot();
            Assert.assertTrue(selected, "Should be able to select a slot");
            logg.info("Slot selected successfully");

            // Wait for page to update after slot selection
            CommonUtilities.threadSleep(2000);

            // Click continue button
            logg.info("Clicking Continue button to proceed with booking...");
            slotPage.clickContinueButtonOnBookYourSessionPopUp();

            // Wait for next page to load
            CommonUtilities.threadSleep(2000);

            logg.info("✓ Successfully opened reason/details screen");

        } catch (Exception e) {
            logg.error("Failed to book slot: " + e.getMessage());
            throw new RuntimeException("Failed to validate and book slot", e);
        }
    }
@Test(priority = 4)
    public void TC_07_Verify_user_should_not_be_able_to_continue_without_adding_response_text()
    {
        logg.info("========== TC 07: User should Receive error message when continue without adding response text ==========");

        try {

            logg.info("Clicking Continue button without entering response...");
            slotPage.clickContinueButtonOnDescribeYourRequestSection();

            logg.info("Verify that an error message is displayed");

            String emptyresponseErrormessage=slotPage.getErrorMessageWithoutDescribeYourRequest();

            Assert.assertEquals(emptyresponseErrormessage,"Please describe your request");

            logg.info(" Error message verified successfully when trying to continue without response");

        } catch (Exception e) {
            logg.error("Failed to verify error message: " + e.getMessage());
            throw new RuntimeException("Failed to verify error message for empty response", e);
        }
    }
    @Test(priority = 5)
    public void TC_08_Verify_user_should_be_able_to_continue_after_adding_response_text()
    {
        logg.info("========== TC 08: User should be able to continue after adding response text ==========");

        try {

            String responseText=StringWords.enterTestData("DescribeYourRequestResponse");
            logg.info("Entering response text: " + responseText);
            slotPage.enterDescribeYourRequestResponse(responseText);

            logg.info("Clicking Continue button after entering response...");
            slotPage.clickContinueButtonOnDescribeYourRequestSection();

            logg.info("Successfully continued after entering response");

        } catch (Exception e) {
            logg.error("Failed to continue after entering response: " + e.getMessage());
            throw new RuntimeException("Failed to continue after entering response", e);
        }
    }


    @AfterClass
    public void close_browser() {
        logg.info("Closing Browser");
        logg.info("*********** End kuby Companion book Functionality *************");
        driver.close();
    }
}