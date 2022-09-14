package core.browser;

import lombok.Getter;
import java.util.Arrays;
import org.apache.commons.lang.StringUtils;

@Getter
public enum BrowserType {

    CHROME("chrome");

    private String browserType;

    BrowserType(String browserType) {
        this.browserType = browserType;
    }

    public static BrowserType GetBrowserType(String browserName)
    {
        BrowserType browserType = null;
        if (StringUtils.isNotBlank(browserName)) {
            browserType = Arrays.asList(values()).stream()
                    .filter(s -> s.getBrowserType().equalsIgnoreCase(browserName))
                    .findFirst().orElse(null);
        }

        return browserType;
    }
}