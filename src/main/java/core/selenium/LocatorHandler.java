package core.selenium;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.By;

public class LocatorHandler
{

    public enum LocatorType
    {
        Xpath,
        CssSelector
    }

    private static LocatorType GetLocatorType(String locator)
    {
        if (locator.startsWith("//") || locator.startsWith(".//") || locator.startsWith("(//"))
            return LocatorType.Xpath;
        else
            return LocatorType.CssSelector;
    }

    public static By GetSeleniumBy(String locator) {
        String parsedLocator = locator;
        var splitter = "=";

        switch (GetLocatorType(parsedLocator)) {
            case Xpath:

                if (parsedLocator.startsWith("xpath="))
                    parsedLocator = parsedLocator.split(splitter, 2)[1];

                return By.xpath(parsedLocator);

            case CssSelector:

                if (parsedLocator.startsWith("css="))
                    parsedLocator = parsedLocator.split(splitter, 2)[1];

                return By.cssSelector(parsedLocator);
        }

        throw new NotImplementedException("Proper selenium By type cannot be found");
    }
}