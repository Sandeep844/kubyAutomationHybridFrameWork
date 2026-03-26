package kuby.web.objectRepositories;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import kuby.web.testBase.TestBase;
import kuby.web.utility.CommonUtilities;

public class LoginPage extends TestBase {
	 public static final Logger logg=Logger.getLogger(LoginPage.class.getName());
	/*All Object Repository*/
	//Sandeep all Object repository for login page
	
	@FindBy(name="email")
	private WebElement useremail;
	
	@FindBy(xpath="(//span[text()='Full name']/..)/div/input")
	private WebElement userFullName;

	@FindBy(xpath="(//span[text()='Password']/..)/div/input")
	private WebElement password;

	@FindBy(css="span.items-center")
	private WebElement checkBox;

	@FindBy(xpath="//button[text()='Sign up']")
	private WebElement SinUpBtn;

	@FindBy(xpath="//h3[text()='Log in or register']")
	private WebElement LoginorregisterText;

	@FindBy(xpath="//span[text()='Email is required']")
	private WebElement blankUserEmailErrorMessage;

	@FindBy(xpath="//span[text()='Name is required']")
    private WebElement blankUsernameErrorMessage;

	@FindBy(xpath="//span[text()='Password is required']")
	private WebElement blankUserPasswordErrorMessage;

	@FindBy(xpath="//span[text()='You must agree to the terms to continue']")
	private WebElement blankCheckBoxErrorMessage;

    @FindBy(xpath="//span[contains(text(),'User does not exist. Please sign up first.')]")
	private WebElement invalidEmailErrorMessage;

	@FindBy(xpath="//span[contains(text(),'Incorrect password entered')]")
	private WebElement invalidpassword;
	
	@FindBy(xpath="//a[contains(text(),'Forgot password?')]")
	private WebElement forgotPasswordLink;
	
	@FindBy(xpath="//button[text()='Log in']")
	private WebElement logeInBtn;

	@FindBy(xpath="//button[text()='Continue']")
	private WebElement ContinueBtn;

	@FindBy(xpath="//button[text()='Accept all']")
	private WebElement acceptAllCookies;
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}


	public void inValid_Credentials_login(String invalidemail, String invalidpassw)
	{
		CommonUtilities.sendkeys(useremail, invalidemail);
		logg.info("Invalid Email = "+invalidemail);
		CommonUtilities.threadSleep(2000);
		CommonUtilities.sendkeys(password, invalidpassw);
		logg.info("Invalid Password = "+invalidpassw);	
	}
	public void valid_Credentials_login(String vlaidemail, String validpassw) {
		CommonUtilities.sendkeys(useremail,vlaidemail);
		logg.info("valid Username = "+vlaidemail);
		CommonUtilities.threadSleep(2000);
		CommonUtilities.sendkeys(password, validpassw);
		logg.info("valid Password = "+validpassw);
	}
	
	/*
	 * public void valid_Credentials_login(String validuser,String validpass ) {
	 * username.clear(); username.sendKeys(validuser);
	 * logg.info("Valid UserName = "+validuser); password.clear();
	 * password.sendKeys(validpass); logg.info("Valid Password = "+validpass); try {
	 * Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
	 * SinInBtn.click(); return valid_Credentials_login(); }
	 */
	 
	
	public String useremailBlankErrorMsg()
	{
		 return blankUserEmailErrorMessage.getText();
	}
	public String userFullNameBlankErrorMsg()
	{
		return blankUsernameErrorMessage.getText();
	}
	public String userPasswordBlankErrorMsg()
	{
		return blankUserPasswordErrorMessage.getText();
	}
	public String emptyCheckBoxErrorMsg() {
		return blankCheckBoxErrorMessage.getText();
	}


public String invalidemailErrorMsg()
{
	return invalidEmailErrorMessage.getText();
}

	public String invalidPasswordErrorMsg() {
		return invalidpassword.getText();
	}


	public void clickOnLoginInButton() {
		CommonUtilities.elementToBeClickable(logeInBtn,10);
		logeInBtn.click();
	}

	public void clickoncheckbox()
	{
		checkBox.click();
	}
	public void blankCreadencialEnterContinue()
	{
	CommonUtilities.elementToBeClickable(ContinueBtn,10);
		ContinueBtn.click();


	}
	public void clickacceptAllCookiesbtn()
	{
		acceptAllCookies.click();
	}
	public void refreshBrowser() {
		driver.navigate().refresh();
	}
public void isOnHomePage()
{

}
	
	public boolean isOnLoginPage() {
		try {
			WebElement Loginwelcomeheadleine=CommonUtilities.elementToBeVisible(LoginorregisterText, 5);
			Loginwelcomeheadleine.getText().equals("Log in or register");
	        return true;
		}catch(Exception e ) {
	        return false;	
		}
	}
public void loginWIthAcceptAllCookies()
{
	clickacceptAllCookiesbtn();
	valid_Credentials_login(prop.getProperty("loginUserEmail"), prop.getProperty("loginPassword"));
	clickOnLoginInButton();
	CommonUtilities.threadSleep(3000);
}
	
}

