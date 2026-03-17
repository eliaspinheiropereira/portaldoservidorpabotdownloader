package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.service;

import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component.*;
import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.dto.UsuarioDTO;
import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.mappers.UsuarioMapper;
import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final BrowserService browserService;
    private final LoginService loginService;
    private final ContraChequeService contraChequeService;
    private final SelecionarElementosService selecionarElementosService;
    private final UsuarioMapper usuarioMapper;

    public void login(UsuarioDTO usuarioDTO) {
        Usuario usuario = this.usuarioMapper.toEntity(usuarioDTO);

        WebDriver driver = abrindoSite(usuario.getUrl());
        fazendoLogin(usuario.getUsername(), usuario.getSenha(), driver);
        acessandoContracheque(driver);
        fazendoDownload(driver, usuario.getAnoInicial(), usuario.getAnoFinal(), usuario.getContrato());
    }

    private WebDriver abrindoSite(String url){
        return this.browserService.abrindoBrowser(url);
    }

    private void fazendoLogin(String username, String senha, WebDriver diver) {
        this.loginService.login(username, senha, diver);
    }

    private void acessandoContracheque(WebDriver driver) {
        this.contraChequeService.acessandoContraCheque(driver);
    }

    private void fazendoDownload(WebDriver driver, int anoInicial, int anoFinal, String contrato) {
        this.selecionarElementosService.selecionarElemento(driver, anoInicial, anoFinal, contrato);
    }
}
