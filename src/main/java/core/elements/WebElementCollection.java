package core.elements;

import core.TestRunner;
import core.selenium.LocatorHandler;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebElementCollection {

    private String locator;
    private static final LocatorHandler lh = new LocatorHandler();

    public WebElementCollection(String locator)
    {
        this.locator = locator;
    }

    public List<WebElement> getElements() {
        return TestRunner.getDriver().findElements(lh.GetSeleniumBy(locator));
    }
}
