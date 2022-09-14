package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.controls.ViteUI;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class VitePage {

    public ViteUI ui = new ViteUI();

    public VitePage setBasePriceValue(int basePrice)
    {
        ui.basePriceLabel.hover();
        ui.pencilIcon.click();
        ui.basePriceInput.setValue(String.valueOf(basePrice));
        ui.acceptBasePrice.click();
        return this;
    }

    public VitePage verifyBasePriceEquals(int basePrice)
    {
        float convertedPrice = (float) basePrice;
        assertThat(String.valueOf(convertedPrice)).isEqualTo(ui.basePriceContainer.getValue());
        return this;
    }

    public VitePage addPriceComponent(Map<String, String> priceComponents)
    {
        for (Map.Entry<String, String> componentInfo : priceComponents.entrySet()) {
            ui.componentName.setValue(componentInfo.getKey());
            ui.componentPrice.setValue(componentInfo.getValue());
            ui.componentIcon.click();
        }

        return this;
    }

    public VitePage verifyComponentPrice(Map<String, String> expectedPriceComponents) {
        Map<String, String> actualPriceComponents = collectComponentPrices();

        for (Map.Entry<String, String> expectedComponent: expectedPriceComponents.entrySet()) {

            String actualPrice = actualPriceComponents.get(expectedComponent.getKey());
            String expectedPrice = expectedComponent.getValue();

            String actualDecimalPoints = actualPrice.substring(actualPrice.lastIndexOf("."));
            assertThat(actualDecimalPoints.length() > 0 && actualDecimalPoints.length() <= 2).isTrue();

            if (!expectedPrice.contains("."))
                assertThat(actualDecimalPoints.equals(String.format(expectedPrice + "%s", ".0"))).isTrue();
            else {
                String expectedDecimalPoints = expectedPrice.substring(expectedPrice.lastIndexOf("."));
                if (expectedDecimalPoints.length() > 2) {
                    BigDecimal convertedActualPrice = new BigDecimal(actualPrice).setScale(2, RoundingMode.HALF_UP);
                    BigDecimal convertedExpectedPrice = new BigDecimal(expectedPrice).setScale(2, RoundingMode.HALF_UP);
                    assertThat(convertedExpectedPrice).isEqualTo(convertedActualPrice);
                }
            }
        }

        return this;
    }

    public VitePage removeInternalSurcharge() {
        ui.componentToRemove.hover();
        ui.componentToRemoveTrashIcon.click();
        return this;
    }

    public VitePage verifyTotalPrice() {

        return this;
    }

    /*
    private WebElement getComponentByName(String componentName) throws Exception {
        for (WebElement component : ui.componentList.getElements()) {
            WebElement currentComponent = component.findElement(By.cssSelector("div[class='flex-grow flex flex-col'] span"));
            if (currentComponent.getText().equals(componentName))
                return currentComponent;
            else throw new Exception("Component " + componentName + " is not found");
        }
    }
     */

    private Map<String, String> collectComponentPrices()
    {
        Map<String, String> actualComponentPrices = new HashMap<>();

        for (WebElement component: ui.componentList.getElements()) {
            actualComponentPrices.put(
                    component.findElement(By.cssSelector("div[class='flex-grow flex flex-col'] span")).getText(),
                    component.findElement(By.cssSelector("div[class='w-1/3 flex flex-col'] div")).getText()
            );
        }

        return  actualComponentPrices;
    }
}