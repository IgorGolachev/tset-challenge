import ui.Application;
import core.browser.Browser;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    private Browser browser = new Browser();

    public Browser getBrowser()
    {
        return browser;
    }

    @BeforeTest
    protected WebDriver StartUp()
    {
        return getBrowser().launch();
    }

    @AfterTest
    protected void TearDown()
    {
        getBrowser().Close();
    }

    protected Application app()
    {
        return new Application();
    }

}
