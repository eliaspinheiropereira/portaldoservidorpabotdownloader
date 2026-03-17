package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class LoginService {

    private WebDriverWait wait;

    private final By campoMatricula = By.cssSelector("#login-matcon input");
    private final By campoSenha = By.cssSelector("#login-senha input");
    private final By botaoEntrar = By.cssSelector("button.cinbesa-tela-login-botao-entrar");

    private final EsperandoCarregarPaginasService esperandoCarregarPaginasService;

    public void login(String username, String senha, WebDriver driver) {
        realizandoLogin(username, senha, driver);
    }

    private void realizandoLogin(String username, String senha, WebDriver driver) {
        this.wait = this.esperandoCarregarPaginasService.esperandoCarregarPagina(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(campoMatricula)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(campoSenha)).sendKeys(senha);
        wait.until(ExpectedConditions.visibilityOfElementLocated(botaoEntrar)).click();
    }
}
