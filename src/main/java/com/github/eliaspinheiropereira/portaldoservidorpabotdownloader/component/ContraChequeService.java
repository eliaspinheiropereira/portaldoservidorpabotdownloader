package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class ContraChequeService {

    private final EsperandoCarregarPaginasService esperandoCarregarPaginasService;

    public void acessandoContraCheque(WebDriver driver){
        WebDriverWait wait = this.esperandoCarregarPaginasService.esperandoCarregarPagina(driver);

        WebElement botao = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='#/autenticado/contracheque']")));
        botao.click();
    }

}
