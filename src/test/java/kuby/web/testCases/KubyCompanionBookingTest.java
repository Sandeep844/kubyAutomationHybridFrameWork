package kuby.web.testCases;

import kuby.web.utility.StringWords;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import kuby.web.objectRepositories.BookAComapnionSlotPage;
import kuby.web.objectRepositories.CustomerCompanionPage;
import kuby.web.objectRepositories.LoginPage;
import kuby.web.testBase.TestBase;
import kuby.web.utility.CommonUtilities;

import java.util.List;

public class KubyCompanionBookingTest extends TestBase {

    public static final Logger logg = Logger.getLogger(KubyCompanionBookingTest.class.getName());
    private LoginPage loginpage;
    private CustomerCompanionPage companionPage;
    private BookAComapnionSlotPage slotPage;

    @BeforeClass
    public void setUp() {
        browserOpen();
        loginpage = new LoginPage();
        companionPage = new CustomerCompanionPage();
        slotPage = new BookAComapnionSlotPage();
    }

    @Test(priority = 1, description = "End-to-end test for companion booking - select slot")
    public void TC_Companion_EndToEnd_SelectSlot() {
        logg.info("Start: Companion booking end-to-end - select slot");

        // login
        loginpage.clickacceptAllCookiesbtn();
       loginpage.valid_Credentials_login(prop.getProperty("loginUserEmail"), prop.getProperty("loginPassword"));
       loginpage.clickOnLoginInButton();
       CommonUtilities.threadSleep(3000);

        // navigate to companions (header / link)
        // click via anchor containing '/companions/browse'
        CommonUtilities.JSClick(driver.findElement(By.xpath("//a[contains(@href,'/companions/browse') or contains(.,'KUBYcompanions')]")));
        CommonUtilities.threadSleep(2000);

        // verify url contains expected
        String current = driver.getCurrentUrl();
        logg.info("Current URL after navigation: " + current);
        Assert.assertTrue(current.contains("/companions/browse"), "Should be on companions browse page");
//---------------------------
        // search by name
        String searchName = StringWords.enterTestData("CompanionSearchName");
        logg.info("Searching for companion: " + searchName);

        companionPage.searchCompanionByName(searchName);
        CommonUtilities.threadSleep(2000);

// wait until results refresh
        companionPage.waitForSearchResults();

// Method 1: Verify search result contains expected name
        List<String> allNames = companionPage.getAllCompanionNames();
        logg.info("Total cards found: " + allNames.size());
        logg.info("Card names: " + allNames);

        boolean found = false;
        for (String name : allNames) {
            if (name != null && name.toLowerCase().contains(searchName.toLowerCase())) {
                found = true;
                logg.info("Match found: " + name);
                break;
            }
        }

        Assert.assertTrue(found, "At least one companion with " + searchName + " should be in search results");

// Method 2: Click the matching card by name (RECOMMENDED - Most Stable)
        boolean cardClicked = companionPage.clickCompanionCardByName(searchName);
        Assert.assertTrue(cardClicked, "able to click companion card with name: " + searchName);

// Wait for booking page to load
        CommonUtilities.threadSleep(3000);


        // check availability and select first slot
        boolean anyAvailable = slotPage.isAnySlotAvailable();
        if (!anyAvailable) {
            logg.info("No slots available at this time");
            Assert.fail("No slots available for the selected companion");
        }

        boolean selected = slotPage.selectFirstAvailableSlot();
        Assert.assertTrue(selected, "Should be able to select a slot");

        // click Continue
        slotPage.clickContinueButtonOnBookYourSessionPopUp();
        CommonUtilities.threadSleep(2000);

        logg.info("End: Companion booking end-to-end - select slot");
    }
}
