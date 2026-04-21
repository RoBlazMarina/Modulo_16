package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

class LoginTest {

        private WebDriver driver;
        private LoginPage loginPage;

        @BeforeEach
        void setUp() throws InterruptedException {
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                // PERFIL NUEVO DE VERDAD
                options.addArguments("--user-data-dir=C:/selenium-profile-clean");
                options.addArguments("--no-default-browser-check");
                options.addArguments("--no-first-run");

                // DESACTIVAR TODO LO DE CONTRASEÑAS
                options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-extensions");

                // IMPORTANTE: DESACTIVAR GOOGLE SYNC
                options.addArguments("--disable-sync");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
                driver.get("https://www.saucedemo.com/");

                loginPage = new LoginPage(driver);

                loginPage.login("standard_user", "secret_sauce");
                Thread.sleep(2000);
        }

        @AfterEach
        void tearDown() {
                if (driver != null) {
                        driver.quit();
                }
        }

        @Test
        void loginCorrecto() throws InterruptedException {

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
