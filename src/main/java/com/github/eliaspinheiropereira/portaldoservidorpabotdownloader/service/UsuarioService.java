package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.service;

import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component.*;
import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.dto.UsuarioDTO;
import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final BrowserService browserService;
    private final LoginService loginService;
    private final CriandoPastaService criandoPastaService;
    private final ContraChequeService contraChequeService;
    private final SelecionarElementosService selecionarElementosService;

    public void login(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUrl(usuarioDTO.url());
        usuario.setUsername(usuarioDTO.username());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setAnoInicial(usuarioDTO.anoInicial());
        usuario.setAnoFinal(usuarioDTO.anoFinal());
        usuario.setContrato(usuarioDTO.contrato());

        WebDriver driver = abrindoSite(usuario.getUrl());
        fazendoLogin(usuario.getUsername(), usuario.getSenha(), driver);
//        criandoPastaBase(driver);
//        criandoPastaUsuario(driver);
        acessandoContracheque(driver);
        this.selecionarElementosService.selecionarElemento(driver, usuario.getAnoInicial(), usuario.getAnoFinal(), usuario.getContrato());
    }

    private WebDriver abrindoSite(String url){
        return this.browserService.abrindoBrowser(url);
    }

    private void fazendoLogin(String username, String senha, WebDriver diver) {
        this.loginService.login(username, senha, diver);
    }

//    private void criandoPastaBase(WebDriver driver) {
//        this.criandoPastaService.criandoPastaBase(driver);
//    }

//    private void criandoPastaUsuario(WebDriver driver){
//        this.criandoPastaService.criandoPastaUsuario(driver);
//    }

    private void acessandoContracheque(WebDriver driver) {
        this.contraChequeService.acessandoContraCheque(driver);
    }
}
