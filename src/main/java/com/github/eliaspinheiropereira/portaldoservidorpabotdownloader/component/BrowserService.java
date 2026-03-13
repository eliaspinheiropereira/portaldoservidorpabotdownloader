package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

@Component
public class BrowserService {

    WebDriver driver;

    public WebDriver abrindoBrowser(String url){
        this.driver = new ChromeDriver();
        this.driver.get(url);

        return driver;
    }

    private void fechandoBrowser(){
        this.driver.quit();
    }
}
