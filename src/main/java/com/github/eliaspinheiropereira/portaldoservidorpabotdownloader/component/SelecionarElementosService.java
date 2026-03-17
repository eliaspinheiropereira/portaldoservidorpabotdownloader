package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SelecionarElementosService {

    private final By dropdownAno = By.id("ano");
    private final By dropdownMes = By.id("mes");
    private final By dropdownContrato = By.id("contrato");

    private final DownloadContraChequeService downloadContraChequeService;
    private final EsperandoCarregarPaginasService esperandoCarregarPaginasService;
    private final CriandoPastaService criandoPastaService;

    public void selecionarElemento(WebDriver driver, int anoInicial, int anoFinal, String contrato){
        String anoParaTexto;

        for (int i = anoInicial; i >= anoFinal; i--){
            anoParaTexto = String.valueOf(i);
            this.criandoPastaService.criandoPastaAno(driver, i);
            selecionandoOpcoes(dropdownAno, anoParaTexto, driver);
            List<String> meses = listarMesesDisponiveis(driver);

            for(String mes: meses){
                selecionandoOpcoes(dropdownMes, mes, driver);
                selecionandoOpcoes(dropdownContrato, contrato, driver);
                WebDriverWait wait = this.esperandoCarregarPaginasService.esperandoCarregarPagina(driver);
                wait.until(ExpectedConditions.elementToBeClickable(this.downloadContraChequeService.clicandoDownload())).click();
                this.downloadContraChequeService.esperandoDownload(driver, mes, String.valueOf(i));
            }
        }
    }

    private void selecionandoOpcoes(By dropdown, String texto, WebDriver driver){
        WebDriverWait wait = this.esperandoCarregarPaginasService.esperandoCarregarPagina(driver);

        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        By painel = By.cssSelector(".p-dropdown-panel");
        wait.until(ExpectedConditions.visibilityOfElementLocated(painel));

        By opcao = By.xpath("//div[contains(@class,'p-dropdown-panel')]//li[@role='option' and normalize-space()='" + texto + "']");
        wait.until(ExpectedConditions.elementToBeClickable(opcao)).click();
    }

    private List<String> listarMesesDisponiveis(WebDriver driver){
        WebDriverWait wait = this.esperandoCarregarPaginasService.esperandoCarregarPagina(driver);

        wait.until(ExpectedConditions.elementToBeClickable(dropdownMes)).click();
        By opcoes = By.cssSelector("li[role='option']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(opcoes));

        List<String> lista = new ArrayList<>();
        List<WebElement> elemntos = driver.findElements(opcoes);

        for (int i = 0; i < elemntos.size(); i++) {
            elemntos = driver.findElements(opcoes);
            lista.add(elemntos.get(i).getText());
        }

        driver.findElement(By.tagName("body")).click();
        return lista;
    }

}
