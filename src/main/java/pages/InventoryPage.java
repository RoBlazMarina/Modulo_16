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
    private By encontrarCarrito = By.className("shopping_cart_badge"); //La clase de nombre no se puede cambiar pues depende del CSS de la página

    // Método genérico para obtener el botón "Add to cart" de un producto
    private By botonAnadir(String nombreProducto) {
        return By.xpath("//div[text()='" + nombreProducto + "']/ancestor::div[@class='inventory_item']//button");
    }

    // Método genérico para obtener el botón "Remove" de un producto
    private By botonQuitar(String nombreProducto) {
        return By.xpath("//div[text()='" + nombreProducto + "']/ancestor::div[@class='inventory_item']//button[contains(text(),'Remove')]");
    }

    // Añadir producto al carrito
    public void anadirCarrito(String nombreProducto) {
        driver.findElement(botonAnadir(nombreProducto)).click();
    }

    // Obtener número de productos en el carrito
    public int getNumeroCarrito() {
        try {
            return Integer.parseInt(driver.findElement(encontrarCarrito).getText());
        } catch (Exception e) {
            return 0; // Si no hay badge, el carrito está vacío
        }
    }

    // Comprobar si un texto o botón aparece en pantalla
    public boolean textoVisible(String text) {
        return driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]")).size() > 0;
    }

    public boolean botonQuitarVisible(String nombreProducto) {
        return driver.findElements(botonQuitar(nombreProducto)).size() > 0;
    }
}

