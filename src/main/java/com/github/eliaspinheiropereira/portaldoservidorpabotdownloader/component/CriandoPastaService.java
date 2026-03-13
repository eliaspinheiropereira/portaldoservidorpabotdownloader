package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

@Component
public class CriandoPastaService {

//    private final By informacoesUsuario = By.cssSelector("p.texto-nome");

    public void criandoPastaBase(WebDriver driver) {
        String so = obtendoSistemaOperadional(driver);

        if (so.contains("Linux")){
            criandoPastaLinux();
        } else{
            criandoPastaWindows();
        }
    }

    public void criandoPastaUsuario(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement informacoesUsuario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.texto-nome")));
        String so = obtendoSistemaOperadional(driver);
        String usuarioSO = obtendoUsuarioSistemaOperacional();

        String texto = informacoesUsuario.getText();
        String nome = nomeUsuario(texto);
        String matricula = matriculausuario(texto);

        if(so.contains("Linux")){
            Path pastaUsuarioLinux = Paths.get("/home/"+usuarioSO+"/Área de trabalho/BotPortalServidorPA/pastas_servidoresd/"+nome+" "+matricula);
            try {
                Files.createDirectories(pastaUsuarioLinux);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            Path pastaUsuarioWindows = Paths.get("C:/Users/" + usuarioSO + "/Desktop/BotPortalServidorPA/pastas_servidores/"+nome+" "+matricula);
            try {
                Files.createDirectories(pastaUsuarioWindows);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void criandoPastaWindows(){
        String usuarioSO = System.getProperty("user.name");

        Path pastaTemp = Paths.get("C:/Users/" + usuarioSO + "/Downloads/temp");
        Path pastaPortalServidor = Paths.get("C:/Users/" + usuarioSO + "/Desktop/BotPortalServidorPA/pastas_servidores");

        try {
            Files.createDirectories(pastaTemp);
            Files.createDirectories(pastaPortalServidor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void criandoPastaLinux(){
        String usuarioSO = obtendoUsuarioSistemaOperacional();

        Path pastaTemp = Paths.get("/home/"+usuarioSO+"/Downloads/temp");
        Path pastaPortalServidor = Paths.get("/home/"+usuarioSO+"/Área de trabalho/BotPortalServidorPA/pastas_servidoresd");

        try {
            Files.createDirectories(pastaTemp);
            Files.createDirectories(pastaPortalServidor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String obtendoSistemaOperadional(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String sistemaOperacional = (String) js.executeScript("return navigator.userAgent");

        return sistemaOperacional;
    }

    private String nomeUsuario(String dados){
        String[] partes = dados.split("- Matrícula:");
        String nome = partes[0].replace("Usuário:", "").trim();
        return nome;
    }

    private String matriculausuario(String dados){
        String[] partes = dados.split("- Matrícula:");
        String matricula = partes[1].replace("-", "").trim();
        return matricula;
    }

    private String obtendoUsuarioSistemaOperacional(){
        return System.getProperty("user.name");
    }
}
