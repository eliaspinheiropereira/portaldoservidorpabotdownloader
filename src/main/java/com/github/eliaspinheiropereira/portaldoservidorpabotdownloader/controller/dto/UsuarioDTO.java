package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.dto;

public record UsuarioDTO(
        String url,
        String username,
        String senha,
        int anoInicial,
        int anoFinal,
        String contrato
) {
}
