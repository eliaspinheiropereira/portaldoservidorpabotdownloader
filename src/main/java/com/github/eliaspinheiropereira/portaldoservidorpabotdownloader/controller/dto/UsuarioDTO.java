package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        @NotBlank(message = "Este campo é obrigatório, preencha com o site correto.")
        String url,

        @NotBlank(message = "Este campo é obrigatório, preencha com a matricula correta.")
        String username,

        @NotBlank(message = "Este campo é obrigatório, preencha com a senha correta.")
        String senha,

        @NotNull(message = "Este campo é obrigatório.")
        int anoInicial,

        @NotNull(message = "Este campo é obrigatório.")
        int anoFinal,

        @NotBlank(message = "Este campo é obrigatório, o contrato deve ser preenchido como o exemplo ao lado -> SEMEC - 18")
        String contrato
) {
}
