package ui.controls;

import core.elements.Element;
import core.elements.WebElementCollection;

public class ViteUI {

    public Element pencilIcon = new Element("span[id='base-edit-icon']");
    public Element basePriceInput = new Element("input[id='base-value-input']");
    public Element acceptBasePrice = new Element("span[id='base-check-icon'] i");
    public Element basePriceContainer = new Element("div[id='BasePrice'] > div[class='w-1/3 flex flex-col'] div");
    public Element basePriceLabel = new Element("div[id='BasePrice'] > div[class='flex-grow flex flex-col'] > span");

    public Element componentName = new Element("input[id='ghost-label-input']");
    public Element componentPrice = new Element("input[id='ghost-value-input']");
    public Element componentIcon = new Element("span[id='ghost-check-icon'] > i");

    public WebElementCollection componentList = new WebElementCollection("div[class^='flex items-start']:not([id])");
    public Element componentToRemove = new Element("//div[contains(@class,'flex items-start')]/div[@class='flex-grow flex flex-col']/span[.='INTERNAL_SURCHARGE']");
    public Element componentToRemoveTrashIcon = new Element("//div[contains(@class,'flex items-start')]/div[@class='flex-grow flex flex-col']/span[.='INTERNAL_SURCHARGE']/../../div[@class='w-16']/span[contains(@id,'thrash-icon')]/i");
}