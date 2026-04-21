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
    private By EncontrarCarrito = By.className("shopping_cart_badge"); //La clase de nombre no se puede cambiar pues depende del CSS de la página

    // Método genérico para obtener el botón "Add to cart" de un producto
    private By BotonAnadir(String NombreProducto) {
        return By.xpath("//div[text()='" + NombreProducto + "']/ancestor::div[@class='inventory_item']//button");
    }

    // Método genérico para obtener el botón "Remove" de un producto
    private By removeButton(String NombreProducto) {
        return By.xpath("//div[text()='" + NombreProducto + "']/ancestor::div[@class='inventory_item']//button[contains(text(),'Remove')]");
    }

    // Añadir producto al carrito
    public void addProductToCart(String NombreProducto) {
        driver.findElement(BotonAnadir(NombreProducto)).click();
    }

    // Obtener número de productos en el carrito
    public int getCartCount() {
        try {
            return Integer.parseInt(driver.findElement(EncontrarCarrito).getText());
        } catch (Exception e) {
            return 0; // Si no hay badge, el carrito está vacío
        }
    }

    // Comprobar si un texto o botón aparece en pantalla
    public boolean isTextVisible(String text) {
        return driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]")).size() > 0;
    }

    public boolean isRemoveButtonVisible(String NombreProducto) {
        return driver.findElements(removeButton(NombreProducto)).size() > 0;
    }
}

