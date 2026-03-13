package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.service;

import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.dto.UsuarioDTO;
import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    public void login(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUrl(usuarioDTO.url());
        usuario.setUsername(usuarioDTO.username());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setAnoInicial(usuarioDTO.anoInicial());
        usuario.setAnoFinal(usuarioDTO.anoFinal());
        usuario.setContrato(usuarioDTO.contrato());
    }
}
