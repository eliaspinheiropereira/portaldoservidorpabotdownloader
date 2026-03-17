package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InformacaoUsuarioLogadoService {

    private final EsperandoCarregarPaginasService esperandoCarregarPaginasService;

    public String obtendoNome(WebDriver driver) {
        WebDriverWait wait = this.esperandoCarregarPaginasService.esperandoCarregarPagina(driver);

        WebElement informacoesUsuario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.texto-nome")));

        String texto = informacoesUsuario.getText();
        String nome = nomeUsuario(texto);
        return nome;
    }

    public String obtendoMatricula(WebDriver driver) {
        WebDriverWait wait = this.esperandoCarregarPaginasService.esperandoCarregarPagina(driver);

        WebElement informacoesUsuario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.texto-nome")));

        String texto = informacoesUsuario.getText();
        String matricula = matriculaUsuario(texto);
        return matricula;
    }

    private String nomeUsuario(String dados){
        String[] partes = dados.split("- Matrícula:");
        String nome = partes[0].replace("Usuário:", "").trim();
        return nome;
    }

    private String matriculaUsuario(String dados){
        String[] partes = dados.split("- Matrícula:");
        String matricula = partes[1].replace("-", "").trim();
        return matricula;
    }
}
