package kuby.web.testCases;

import kuby.web.objectRepositories.LoginPage;
import kuby.web.testBase.TestBase;
import kuby.web.utility.CommonUtilities;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KubyLoginTestCases extends TestBase
{
    public static final Logger logg=Logger.getLogger(KubyLoginTestCases.class.getName());
   public LoginPage loginpage;
    public KubyLoginTestCases()
    {
        super();
    }
    @BeforeClass
    public void setUp() {
        browserOpen();
        loginpage=new LoginPage();
    }

    //"======================Negative Scenarios============================="
    @Test(priority = 1)
//@Test(enabled = false)
    public void TC_01_Verify_that_the_User_is_not_able_to_log_in_with_a_blank_EmailId()
    {
        logg.info("========== TC_01: Verify User cannot login with blank EmailId ==========");
//accept all cookies
        CommonUtilities.threadSleep(1500);

       loginpage.clickacceptAllCookiesbtn();
        // Enter blank email

        loginpage.blankCreadencialEnterContinue();

        logg.info("Clicked on Login Button without enterning email");

        // Wait for error message to appear
        CommonUtilities.threadSleep(5000);

        // Get and verify error message
        String blankemailErrMsg = loginpage.useremailBlankErrorMsg();
        Assert.assertEquals(blankemailErrMsg, "Email is required");
        logg.info(" Verified: Error message displayed = " + blankemailErrMsg);
    }
    //"======================Negative Scenarios============================="
    @Test(priority = 2)
   // @Test(enabled = false)
    public void TC_02_Verify_that_the_User_is_not_able_to_Login_with_an_valid_Email_and_invalid_Password()
    {
        logg.info("========== TC_02: Verify User cannot login with valid Email and invalid Password ==========");

        loginpage.inValid_Credentials_login(prop.getProperty("loginUserEmail"), CommonUtilities.generateUniqueAlpahabeticalString(4)+"@"+CommonUtilities.generateRandomNumericValue(3));
        logg.info("Entered valid Email and Invalid password for login");

        loginpage.clickOnLoginInButton();
        logg.info("Click On Login Button");

        CommonUtilities.threadSleep(1500);

        String errormsg=loginpage.invalidPasswordErrorMsg();
        Assert.assertEquals(errormsg,"Incorrect password entered");
        logg.error("This error message should be displayed = "+errormsg);
        loginpage.refreshBrowser();
    }


    //"======================positive Scenarios============================="
    @Test (priority = 3)
    public void TC_03_Verify_that_the_User_is_able_to_Login_with_Valid_Username_and_Password() throws InterruptedException {
        logg.info("========== TC_03: Verify User successfully logs in and redirects to home page ==========");

        CommonUtilities.threadSleep(1000);
        loginpage.valid_Credentials_login(prop.getProperty("loginUserEmail"), prop.getProperty("loginPassword"));
        logg.info("Entered Valid Username and Valid password for Login");
        loginpage.clickOnLoginInButton();
        logg.info("Click on login In Button");

        // Wait for page to load and redirect
        CommonUtilities.threadSleep(3000);

        // Verify redirect to home page
        String currentURL = driver.getCurrentUrl();
        logg.info("Current URL after login = " + currentURL);

        String expectedHomePageURL = "https://kuby-private.vercel.app/en/home";

        // Check if redirect was successful
        if (currentURL.contains("/home")) {
            Assert.assertTrue(currentURL.contains("/home"), "User should be redirected to home page");
            logg.info("User successfully redirected to home page: " + currentURL);
        } else {
            // If not redirected, user might still be logging in
            logg.warn("User not yet redirected to home page. Current URL: " + currentURL);
            logg.info("This might indicate the credentials are invalid or login process is taking longer");
            Assert.fail("User was not redirected to home page. Expected URL to contain '/home' but got: " + currentURL);
        }
    }


    @AfterClass
    public void close_browser() {
        logg.info("Closing Browser");
        logg.info("*********** End kuby Login Functionality *************");
        driver.close();
    }
}

