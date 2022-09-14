import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;

public class PriceCalculationTest extends BaseTest {

    private final int NEW_BASE_PRICE = 5;

    //Test Data for Price Components
    private final HashMap priceComponents = new HashMap() {{
        put("ALLOY_SURCHARGE", "2.15");
        put("SCRAP_SURCHARGE", "3.14");
        put("INTERNAL_SURCHARGE", "0.7658");
        put("EXTERNAL_SURCHARGE", "1");
        put("STORAGE_SURCHARGE", "0.3");
    }};

    @BeforeTest
    private PriceCalculationTest NavigateToBaseUrl()
    {
        getBrowser().NavigateToUrl("http://localhost:3000/");
        return this;
    }

    @Test
    public void ViteAppTest() {
        app().vitePage.
                setBasePriceValue(NEW_BASE_PRICE).
                verifyBasePriceEquals(NEW_BASE_PRICE).
                addPriceComponent(priceComponents).
                verifyComponentPrice(priceComponents).
                removeInternalSurcharge().
                verifyTotalPrice();
    }
}