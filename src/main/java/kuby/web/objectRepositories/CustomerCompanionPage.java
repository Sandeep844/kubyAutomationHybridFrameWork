package kuby.web.objectRepositories;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kuby.web.testBase.TestBase;
import kuby.web.utility.CommonUtilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerCompanionPage extends TestBase {

    // logger
    public static final Logger log = Logger.getLogger(CustomerCompanionPage.class);

    // Search / filters
    @FindBy(xpath = "//input[@placeholder='Search by name or keyword']")
    private WebElement searchByNameInput;

    @FindBy(xpath = "//button[normalize-space()='Filter']")
    private WebElement filterButton;

    // Companion card list (each card uses data-slot='card')
    @FindBy(xpath = "//div[@data-slot='card']")
    private List<WebElement> companionCards;

    // page heading
    @FindBy(xpath = "//h4[contains(.,'Find a companion')]")
    private WebElement pageHeading;

    public CustomerCompanionPage() {
        PageFactory.initElements(driver, this);
    }

    // Waits for the companion page to be visible
    public boolean isCompanionPageLoaded() {
        try {
            CommonUtilities.elementToBeVisible(pageHeading, 10);
            return true;
        } catch (Exception e) {
            log.info("Companion page heading not visible: " + e.getMessage());
            return false;
        }
    }

    // Search helpers
    public void searchCompanionByName(String name) {
        try {
            WebElement inp = CommonUtilities.elementToBeVisible(searchByNameInput, 10);
            CommonUtilities.sendkeys(inp, name);

            CommonUtilities.threadSleep(2000);
            inp.sendKeys(Keys.BACK_SPACE);
            CommonUtilities.threadSleep(5000);
            inp.sendKeys(Keys.ENTER);


        } catch (Exception e) {
            log.error("Failed to enter search text: " + e.getMessage());
            throw e;
        }
    }

    public void clickFilter() {
        try {
            CommonUtilities.elementToBeClickable(filterButton, 10).click();
        } catch (Exception e) {
            log.error("Failed to click Filter button: " + e.getMessage());
            throw e;
        }
    }

    // Companion list helpers
    public int getCompanionCount() {
        try {
            return (companionCards == null) ? 0 : companionCards.size();
        } catch (Exception e) {
            log.error("Error getting companion count: " + e.getMessage());
            return 0;
        }
    }


    /**
     * Wait for search results to load - IMPROVED
     */
    public void waitForSearchResults() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Wait for cards to be present
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[@data-slot='card']")
            ));

            log.info("Cards element found, waiting for visibility...");

            // Additional wait - ensure cards are visible
            Thread.sleep(1000);

            // Wait for at least one h3 element (companion name) to be visible
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//div[@data-slot='card']//h3")
            ));

            // Extra safety wait for animations to complete
            Thread.sleep(1000);

            log.info("Search results loaded successfully - all cards visible");

        } catch (TimeoutException e) {
            log.error("Search results did not load within timeout");
            throw e;
        } catch (InterruptedException e) {
            log.error("Wait interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }




    public String getPriceForCompanion(String name) {
        try {
            for (WebElement card : companionCards) {
                try {
                    WebElement nameEl = card.findElement(By.xpath(".//h3"));
                    if (nameEl.getText().trim().equalsIgnoreCase(name.trim())) {
                        WebElement priceEl = card.findElement(By.xpath(".//span[contains(text(),'€') or contains(text()," + "'€')]") );
                        return priceEl.getText().trim();
                    }
                } catch (Exception e) {
                    // continue
                }
            }
        } catch (Exception e) {
            log.error("Error fetching price for companion: " + e.getMessage());
        }
        return "";
    }

    public void clickFirstCompanionBook() {
        try {
            if (companionCards != null && !companionCards.isEmpty()) {
                WebElement firstCard = companionCards.get(0);
                WebElement bookBtn = firstCard.findElement(By.xpath(".//button[contains(normalize-space(.),'Book your session') or contains(normalize-space(.),'Book session')]") );
                CommonUtilities.elementToBeClickable(bookBtn, 10).click();
            } else {
                throw new NoSuchElementException("No companion cards available to click Book");
            }
        } catch (Exception e) {
            log.error("Error clicking Book for first companion: " + e.getMessage());
            throw e;
        }
    }


    /**
     * Get all companion names with their indices
     */
    public List<String> getAllCompanionNames() {
        List<String> names = new ArrayList<>();
        waitForSearchResults();

        for (WebElement card : companionCards) {
            try {
                names.add(card.findElement(By.xpath(".//h3")).getText().trim());
            } catch (Exception e) {
                log.info("Name not found in card");
            }
        }

        return names;
    }


    /**
     * Find and click companion card by name - MOST STABLE
     */
    public boolean clickCompanionCardByName(String searchName) {
        waitForSearchResults();

        for (int i = 0; i < companionCards.size(); i++) {
            try {
                WebElement card = companionCards.get(i);
                String cardName = card.findElement(By.xpath(".//h3")).getText().trim();

                log.info("Checking card: " + cardName + " against search: " + searchName);

                if (cardName.toLowerCase().contains(searchName.toLowerCase())) {
                    log.info("Found matching card: " + cardName + " at index: " + i);

                    // Scroll into view
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
                    CommonUtilities.threadSleep(500);

                    // Click book button
                    try {
                        WebElement bookButton = card.findElement(
                                By.xpath(".//button[contains(normalize-space(.),'Book your session') or contains(normalize-space(.),'Book session')]")
                        );
                        bookButton.click();
                        log.info("Successfully clicked book button for: " + cardName);
                        return true;
                    } catch (NoSuchElementException e) {
                        // Fallback: JS click
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", card);
                        log.info("Used JS click for: " + cardName);
                        return true;
                    }
                }
            } catch (Exception e) {
                log.error("Error processing card at index " + i + ": " + e.getMessage());
                continue;
            }
        }

        log.error("No companion card found with name containing: " + searchName);
        return false;
    }

}
