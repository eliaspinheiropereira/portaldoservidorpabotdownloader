package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class DownloadContraChequeService {

    private final Path download_folder = Path.of("/home/deckard/Downloads/temp");
    private final By botaoGerar = By.xpath("//button[contains(.,'Gerar Contra-Cheque')]");

    public By clicandoDownload(){
        return botaoGerar;
    }

    public void esperandoDownload(String mes){
        esperando();
        moverDownload(download_folder, mes);
    }

    private void esperando(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void moverDownload(Path destino, String mes){
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(download_folder, "*.pdf");

            for (Path file: stream){
                Path novoNome = destino.resolve(mes+".pdf");
                Files.move(file, novoNome, StandardCopyOption.REPLACE_EXISTING);
                break;
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao mover o download: "+e.getMessage());
        }
    }

}
