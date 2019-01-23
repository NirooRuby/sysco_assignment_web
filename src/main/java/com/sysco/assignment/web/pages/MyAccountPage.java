package com.sysco.assignment.web.pages;

import com.syscolab.qe.core.ui.SyscoLabUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyAccountPage {
    protected static SyscoLabUI syscoLabUIOgm;
    private static By lnkMyAccount = By.cssSelector("[class='authorization-link -login']");
    private static By lblUserName = By.cssSelector("[class='greet welcome'] span");
    private static By lblCartCount = By.cssSelector("[class='counter qty']");
    private static By miniCartWindow = By.xpath("//div[@class='block block-minicart']");
    private static By miniCartWindowClose = By.xpath("//div[@class='close']");
    private static By miniCartDelete = By.xpath("//a[@class='action delete']");
    private static By cartIcon = By.cssSelector("[class='showcart-trigger header-sidebar-link -cart']");
    private static By minicartActive = By.xpath("//span[@class='showcart-trigger header-sidebar-link -cart -active']");
    private static By btnPopUpWindow = By.xpath("//div[@class='modal-content'  and contains(., \"Are you sure\")]");
    private static By btnPopUpWindow_Ok = By.xpath("//footer[@class='modal-footer']/button/span[contains(.,'OK')]/parent::button");
    private static By lblSubMenuOpen = By.xpath("//div[@class='sub-menu -level1 -open']");
    private static By lblItemPageTitle = By.xpath("//h1[@class='page-title  product']");
    private static By btnProceedTOCheckout = By.xpath("//button[@title='Proceed To Checkout']");
    private static By lblItemPriceTag = By.xpath("//div[@class='product-info-price']//span[@class='price']");
    private static By lblItemsInMiniCart = By.xpath("//div[@class='minicart-product-details']");
    private static By lblItemNameInMiniCart = By.xpath("//a[@class='alink -plain']");
    private static By lblItemPriceInMiniCart = By.xpath("//span[@class='minicart-price']");
    private static By BtnAddToCart = By.xpath("//button[@title='Add to Cart']");

    public MyAccountPage(){
        /*Constructor*/
    }

    public MyAccountPage(SyscoLabUI syscoLabUIOgm){
        this.syscoLabUIOgm = syscoLabUIOgm;
    }

    public static boolean verifyMyAccountLink() {
        return syscoLabUIOgm.isDisplayed(lnkMyAccount);
    }

    public static String verifyUserName() {
        return syscoLabUIOgm.getText(lblUserName);
    }

    public static void openMiniCart() {
        syscoLabUIOgm.waitTillElementLoaded(cartIcon);
        syscoLabUIOgm.click(cartIcon);
    }

    public static void removeExistingCartItems(){
        if(syscoLabUIOgm.findElements(lblCartCount).size()>0) {
            openMiniCart();
            if(syscoLabUIOgm.isDisplayed(miniCartWindow)) {
                List<WebElement> miniCartItems = syscoLabUIOgm.findElements(miniCartDelete);
                if (miniCartItems.size() > 0) {
                    for (int i = 0; i < miniCartItems.size(); i++) {
                        syscoLabUIOgm.waitTillElementLoaded(miniCartItems.get(i));
                        syscoLabUIOgm.clickWithJavascript(miniCartItems.get(i));
                        syscoLabUIOgm.waitTillElementLoaded(btnPopUpWindow);
                        syscoLabUIOgm.click(btnPopUpWindow_Ok);
                    }
                }
            }
            if(syscoLabUIOgm.isDisplayed(minicartActive)){
                syscoLabUIOgm.waitTillElementLoaded(miniCartWindowClose);
                syscoLabUIOgm.click(miniCartWindowClose);
            }
        }
    }

    public By getLnkCategoryElement(String category){return By.xpath("//a[@title='" + category + "']");}
    public By getLnkSubCategoryElement(String category){return By.xpath("//a[@title='" + category + "']");}
    public By getLnkItemNameElement(String itemName){return By.xpath("//div[@class='product-item-info sli_content']//a[@title='"+ itemName +"']");}
    public By getItemSizeElement(String itemSize){return By.xpath("//div[@id='swatch-row-227']/div[@option-label='" + itemSize + "']");}

    public void navigateToCategoryPage(String category) {
        syscoLabUIOgm.waitTillElementLoaded(getLnkCategoryElement(category));
        syscoLabUIOgm.isClickable(getLnkCategoryElement(category));
        syscoLabUIOgm.click(syscoLabUIOgm.findElement(getLnkCategoryElement(category)));
    }

    public void navigateToSubCategoryPage(String category, String subCategory) {
        syscoLabUIOgm.click(syscoLabUIOgm
                .findElement(getLnkCategoryElement(category))
                .findElement(getLnkSubCategoryElement(subCategory)));
    }

    public boolean verifyPageTitle(String title) {
        return syscoLabUIOgm.findElement(lblItemPageTitle).getText().contains(title);
    }


    public static boolean isSubMenuWindowOpen() {
        syscoLabUIOgm.waitTillElementLoaded(lblSubMenuOpen);
        return syscoLabUIOgm.isDisplayed(lblSubMenuOpen);
    }

    public void navigateToItemsPage(String itemName) {
        if(syscoLabUIOgm.isClickable(getLnkItemNameElement(itemName))){
            syscoLabUIOgm.click(getLnkItemNameElement(itemName));
        } else {
            syscoLabUIOgm.waitTillElementLoaded(getLnkItemNameElement(itemName), 20L);
            syscoLabUIOgm.click(getLnkItemNameElement(itemName));
        }
    }

    public void selectSize(String itemSize) {
        syscoLabUIOgm.click(getItemSizeElement(itemSize));
    }
    public boolean isSizeSelected(String itemSize) {
        String className = syscoLabUIOgm.getAttribute(getItemSizeElement(itemSize),"class");
        return className.contains("selected");
    }

    public CheckoutPage clickProceedToCheckout() {
        syscoLabUIOgm.click(btnProceedTOCheckout);
        return new CheckoutPage(syscoLabUIOgm);
    }

    public String getItemPrice() {
            return syscoLabUIOgm.getText(lblItemPriceTag);
    }

    public boolean isProductPageLoaded() {
        return syscoLabUIOgm.isDisplayed(lblItemPageTitle);
    }


    public boolean isItemNameAndPriceExistInMiniCart(String itemName, String itemPrice) {
        boolean isItemNameAndPriceDisplay=false;
        syscoLabUIOgm.waitTillElementLoaded(lblItemsInMiniCart);
        List<WebElement> elements = syscoLabUIOgm.findElements(lblItemsInMiniCart);
        if(elements.size()>0) {
            for (WebElement element: elements
                 ) {
                String name = syscoLabUIOgm.getText(element.findElement(lblItemNameInMiniCart)).trim();
                String price = syscoLabUIOgm.getText(element.findElement(lblItemPriceInMiniCart)).trim();
                isItemNameAndPriceDisplay = name.contains(itemName) && price.equals(itemPrice) ;
            }
        }
        return isItemNameAndPriceDisplay;
    }

    public void clickAddToCartButton() {
        syscoLabUIOgm.click(BtnAddToCart);
    }
}
