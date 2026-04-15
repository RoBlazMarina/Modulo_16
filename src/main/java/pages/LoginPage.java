package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    //Atributos de clase

        //Ingreso a web
    private WebDriver driver;

    //Localizadores
    private By userField = By.id("user-name");
    private By passField =By.id("password");
    private By loginBtn =By.id("login-button");
    private By errorMenssage = org.openqa.selenium.By.cssSelector("[data-test='error']");

        //Constructor
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    //Acciones
        //Ingreso de usuario
    public void ingresarUsuario(String user){
        driver.findElement(userField).clear();
        driver.findElement(userField).sendKeys(user);
    }

    public void ingresarContrasena(String pass){
        driver.findElement(passField).clear();
        driver.findElement(passField).sendKeys(pass);
    }

    public void clickLogin (){
        driver.findElement(loginBtn).click();
    }

    //Metodo login general

    public void login(String user, String pass){
        ingresarUsuario(user);
        ingresarContrasena(pass);
        clickLogin();
    }



}
