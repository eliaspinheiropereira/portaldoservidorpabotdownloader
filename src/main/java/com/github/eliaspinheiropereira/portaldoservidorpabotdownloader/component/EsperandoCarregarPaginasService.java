package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class EsperandoCarregarPaginasService {

    private final By loadingOverlay = By.cssSelector(".cinbesa-loading-container");

    public WebDriverWait esperandoCarregarPagina(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingOverlay));
        return wait;
    }
}
