package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.dto;

import java.util.List;

public record ErroRespostaDTO(int status, String mensagem, List<ErroCampoDTO> erros) {
}
