package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.service;

import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.component.BrowserService;
import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.dto.UsuarioDTO;
import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final BrowserService browserService;

    public void login(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUrl(usuarioDTO.url());
        usuario.setUsername(usuarioDTO.username());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setAnoInicial(usuarioDTO.anoInicial());
        usuario.setAnoFinal(usuarioDTO.anoFinal());
        usuario.setContrato(usuarioDTO.contrato());

        // entrando no site
        abrindoSite(usuario.getUrl());
    }

    private void abrindoSite(String url){
        this.browserService.abrindoBrowser(url);
    }
}
