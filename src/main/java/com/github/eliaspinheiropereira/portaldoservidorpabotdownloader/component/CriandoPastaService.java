package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class CriandoPastaService {

    private final EsperandoCarregarPaginasService esperandoCarregarPaginasService;
    private final SistemaOperacionalService sistemaOperacionalService;
    private final InformacaoUsuarioLogadoService informacaoUsuarioLogadoService;

    private void criandoPastaBase(WebDriver driver, String pastaAno) {
        String so = this.sistemaOperacionalService.obtendoSO(driver);

        if (so.contains("Linux")){
            criandoPastaLinux();
            criandoPastaUsuario(driver, pastaAno);
        } else{
            criandoPastaWindows();
            criandoPastaUsuario(driver, pastaAno);
        }
    }

    private void criandoPastaUsuario(WebDriver driver, String ano){
        WebDriverWait wait = this.esperandoCarregarPaginasService.esperandoCarregarPagina(driver);
        String so = this.sistemaOperacionalService.obtendoSO(driver);
        String usuarioSO = this.sistemaOperacionalService.obtendoUsuarioSO();

        String nome = this.informacaoUsuarioLogadoService.obtendoNome(driver);
        String matricula = this.informacaoUsuarioLogadoService.obtendoMatricula(driver);

        if(so.contains("Linux")){
            Path pastaUsuarioLinux = Paths.get("/home/"+usuarioSO+"/Área de trabalho/BotPortalServidorPA/pastas_servidoresd/"+nome+" "+matricula+"/"+ano);
            try {
                Files.createDirectories(pastaUsuarioLinux);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            Path pastaUsuarioWindows = Paths.get("C:/Users/" + usuarioSO + "/Desktop/BotPortalServidorPA/pastas_servidores/"+nome+" "+matricula+"/"+ano);
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
        String usuarioSO = this.sistemaOperacionalService.obtendoUsuarioSO();

        Path pastaTemp = Paths.get("/home/"+usuarioSO+"/Downloads/temp");
        Path pastaPortalServidor = Paths.get("/home/"+usuarioSO+"/Área de trabalho/BotPortalServidorPA/pastas_servidoresd/");

        try {
            Files.createDirectories(pastaTemp);
            Files.createDirectories(pastaPortalServidor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void criandoPastaAno(WebDriver driver, int ano){
        criandoPastaBase(driver, String.valueOf(ano));
    }
}
