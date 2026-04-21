package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

public class LoginTest {

        private WebDriver driver;
        private LoginPage loginPage;

        // Abre el navegador

        @BeforeEach
        void setUp() {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();

                driver.get("https://www.saucedemo.com/");

                loginPage = new LoginPage(driver);

        }

        // Cierra el navegador
        @AfterEach
        void tearDown() {
                if (driver != null) {
                        driver.quit();
                }
        }

        @Test
        void loginCorrecto() throws InterruptedException {

                // Usamos el método simplificado para el login
                loginPage.login("standard_user", "secret_sauce");
                Thread.sleep(2000);

                String urlActual = driver.getCurrentUrl();
                assertNotNull(urlActual);
                assertTrue(urlActual.contains("inventory"));
        }

        @Test
        void loginIncorrecto() throws InterruptedException {

                loginPage.login("standard_user", "malcontrasena");
                Thread.sleep(2000);

                assertTrue(loginPage.errorVisible());
                assertTrue(loginPage.obtenerTextoError().contains("Username and password do not match"));
        }
}
