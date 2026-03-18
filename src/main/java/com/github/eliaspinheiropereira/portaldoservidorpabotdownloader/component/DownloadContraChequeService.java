package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
@RequiredArgsConstructor
public class DownloadContraChequeService {

    private final By botaoGerar = By.xpath("//button[contains(.,'Gerar Contra-Cheque')]");
    private final SistemaOperacionalService sistemaOperacionalService;
    private final InformacaoUsuarioLogadoService informacaoUsuarioLogadoService;

    public By clicandoDownload(){
        return botaoGerar;
    }

    public void esperandoDownload(WebDriver driver, String mes, String ano){
        esperando();
        moverDownload(driver, mes, ano);
    }

    private void esperando(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void moverDownload(WebDriver driver, String mes, String ano){
        String so = this.sistemaOperacionalService.obtendoSO(driver);
        String usuarioSO = this.sistemaOperacionalService.obtendoUsuarioSO();
        String nome = this.informacaoUsuarioLogadoService.obtendoNome(driver);
        String matricula = this.informacaoUsuarioLogadoService.obtendoMatricula(driver);

        if(so.contains("Linux")){
            Path pastaTemp = Paths.get("/home/"+usuarioSO+"/Downloads/temp");
            Path pastaUsuarioLinux = Paths.get("/home/"+usuarioSO+"/Área de trabalho/BotPortalServidorPA/pastas_servidoresd/"+nome+" "+matricula+"/"+ano);

            try {
                    Path arquivo = Files.list(pastaTemp)
                            .filter(f -> f.toString().endsWith(".pdf"))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("nenhum pdf encontrado"));

                    Path novoArquivo = pastaUsuarioLinux.resolve(mes+".pdf");
                    Files.move(arquivo, novoArquivo, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Path pastaTemp = Paths.get("C:\\Users\\" + usuarioSO + "\\Downloads\\temp");
            Path pastaUsuarioWindows = Paths.get("C:\\Users\\" + usuarioSO + "\\Desktop\\BotPortalServidorPA\\pastas_servidores\\" + nome + " " + matricula + "\\" + ano);

            try {
                Path arquivo = Files.list(pastaTemp)
                        .filter(f -> f.toString().endsWith(".pdf"))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("nenhum pdf encontrado"));

                Path novoArquivo = pastaUsuarioWindows.resolve(mes+".pdf");
                Files.move(arquivo, novoArquivo, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
