package core.elements;

import core.TestRunner;
import core.selenium.LocatorHandler;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Element {

    protected static final LocatorHandler lh = new LocatorHandler();
    private WebDriverWait wait = TestRunner.getWait();
    private Actions actions = TestRunner.getActions();
    private String locator;

    private WebElement getElement()
    {
        return TestRunner.getDriver().findElement(lh.GetSeleniumBy(locator));
    };

    public Element(String locator) {
        this.locator = locator;
    }

    public Element waitUntilEnabled()
    {
        wait.until(ExpectedConditions.elementToBeClickable(getElement()));
        return this;
    }

    public Element waitUntilDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(getElement()));
        return this;
    }

    public Element click()
    {
        waitUntilEnabled();
        getElement().click();
        return this;
    }

    public String getValue()
    {
        waitUntilDisplayed();
        return getElement().getText();
    }

    public Element hover()
    {
        actions.moveToElement(getElement()).release().perform();
        return this;
    }

    public Element setValue(String value)
    {
        waitUntilEnabled().click();
        getElement().clear();
        getElement().sendKeys(value);
        return this;
    }

    public Element waitUntilNotDisplayed()
    {
        wait.until(ExpectedConditions.invisibilityOf(getElement()));
        return this;
    }
}