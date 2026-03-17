package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class SistemaOperacionalService {

    public String obtendoSO(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String sistemaOperacional = (String) js.executeScript("return navigator.userAgent");

        return sistemaOperacional;
    }

    public String obtendoUsuarioSO(){
        return System.getProperty("user.name");
    }
}
