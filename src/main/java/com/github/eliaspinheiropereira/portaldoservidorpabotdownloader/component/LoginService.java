package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class LoginService {

    private WebDriverWait wait;

    private final By campoMatricula = By.cssSelector("#login-matcon input");
    private final By campoSenha = By.cssSelector("#login-senha input");
    private final By botaoEntrar = By.cssSelector("button.cinbesa-tela-login-botao-entrar");

    public void login(String username, String senha, WebDriver driver) {
        realizandoLogin(username, senha, driver);
    }

    private void realizandoLogin(String username, String senha, WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOfElementLocated(campoMatricula)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(campoSenha)).sendKeys(senha);
        wait.until(ExpectedConditions.visibilityOfElementLocated(botaoEntrar)).click();
    }
}
