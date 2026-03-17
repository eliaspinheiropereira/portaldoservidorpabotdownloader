package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BrowserService {

    private WebDriver driver;
    private Map<String, Object> prefs;

    public WebDriver abrindoBrowser(String url) {
        String usuario = System.getProperty("user.name");
        String so = System.getProperty("os.name");

        prefs = new HashMap<>();
        if(so.contains("Linux")){
            prefs.put("download.default_directory", "/home/" + usuario + "/Downloads/temp");
        }else {
            prefs.put("download.default_directory", "C:/Users/" + usuario + "/Downloads/temp");
        }
        prefs.put("download.prompt_for_download", false);
        prefs.put("plugins.always_open_pdf_externally", true);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        this.driver = new ChromeDriver(options);
        this.driver.get(url);
        return driver;
    }

    private void fechandoBrowser() {
        this.driver.quit();
    }
}
