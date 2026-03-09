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

        // search by name 'Sandeep Test'
        companionPage.searchCompanionByName(StringWords.enterTestData("CompanionSearchName"));
        CommonUtilities.threadSleep(2000);

        // verify search result contains 'sandeep'
        boolean found = false;
        for (String name : companionPage.getAllCompanionNames()) {
            if (name != null && name.toLowerCase().contains(StringWords.enterTestData("CompanionSearchName"))) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "At least one companion with "+StringWords.enterTestData("CompanionSearchName")+" should be in search results");

        // click Book your session for the first companion that matches
        companionPage.clickFirstCompanionBook();
        CommonUtilities.threadSleep(2000);

        // wait for booking panel
        Assert.assertTrue(slotPage.waitForBookingPanel(10), "Booking panel should appear");

        // check availability and select first slot
        boolean anyAvailable = slotPage.isAnySlotAvailable();
        if (!anyAvailable) {
            logg.info("No slots available at this time");
            Assert.fail("No slots available for the selected companion");
        }

        boolean selected = slotPage.selectFirstAvailableSlot();
        Assert.assertTrue(selected, "Should be able to select a slot");

        // click Continue
        slotPage.clickContinue();
        CommonUtilities.threadSleep(2000);

        logg.info("End: Companion booking end-to-end - select slot");
    }
}
