package core.browser;

import core.TestRunner;
import org.openqa.selenium.WebDriver;

public class Browser
{
    private TestRunner runner = new TestRunner();

    public WebDriver launch()
    {
        return runner.getDriver();
    }

    public void Close()
    {
        runner.Quit();
    }

    public void NavigateToUrl(String url)
    {
        try
        {
            runner.getDriver().navigate().to(url);
        }
        catch (Exception ex)
        {
            Close();
        }
    }
}