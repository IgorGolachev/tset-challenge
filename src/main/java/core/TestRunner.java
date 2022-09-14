package core;

import core.browser.*;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang.NotImplementedException;

public class TestRunner
{
    private static BrowserType browserType = BrowserType.GetBrowserType("chrome");

    private static WebDriver driver;

    public static WebDriver getDriver()
    {
        if (driver == null)
            driver = initializeDriver(browserType);

        return driver;
    }

    private static WebDriverWait wait;

    public static WebDriverWait getWait()
    {
        return wait;
    }

    public static Actions getActions()
    {
        return new Actions(getDriver());
    }

    public static void Quit()
    {
        try
        {
            getDriver().quit();
        }
        finally
        {
            DisposeResources();
        }
    }

    private static void DisposeResources()
    {
        driver = null;
        wait = null;
    }

    private static WebDriver initializeDriver(BrowserType browserType)
    {
        WebDriver driver;

        switch (browserType)
        {
            case CHROME:
            {
                var options = new ChromeOptions();

                options.addArguments("--start-maximized");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-features=NetworkService", "--dns-prefetch-disable", "--disable-extensions");

                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
            }

            default:
            {
                throw new NotImplementedException(
                        String.format("Launch for '{0}' is not implemented yet", browserType));
            }
        }

        wait = new WebDriverWait(driver, Duration.ofMillis(20000));
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(InvalidElementStateException.class);
        wait.ignoring(ElementNotInteractableException.class);
        wait.pollingEvery(Duration.ofMillis(100));

        return driver;
    }
}