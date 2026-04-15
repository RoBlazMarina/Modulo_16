package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private WebDriver driver;

    // Constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Localizadores
    private By cartBadge = By.className("shopping_cart_badge");

    // Método genérico para obtener el botón "Add to cart" de un producto
    private By addToCartButton(String productName) {
        return By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
    }

    // Método genérico para obtener el botón "Remove" de un producto
    private By removeButton(String productName) {
        return By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[contains(text(),'Remove')]");
    }

    // Añadir producto al carrito
    public void addProductToCart(String productName) {
        driver.findElement(addToCartButton(productName)).click();
    }

    // Obtener número de productos en el carrito
    public int getCartCount() {
        try {
            return Integer.parseInt(driver.findElement(cartBadge).getText());
        } catch (Exception e) {
            return 0; // Si no hay badge, el carrito está vacío
        }
    }

    // Comprobar si un texto o botón aparece en pantalla
    public boolean isTextVisible(String text) {
        return driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]")).size() > 0;
    }

    public boolean isRemoveButtonVisible(String productName) {
        return driver.findElements(removeButton(productName)).size() > 0;
    }
}

