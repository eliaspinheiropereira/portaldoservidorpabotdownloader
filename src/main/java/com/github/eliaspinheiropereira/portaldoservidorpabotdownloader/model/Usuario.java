package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.model;

import lombok.Data;

@Data
public class Usuario {

    private String url;
    private String username;
    private String senha;
    private int anoInicial;
    private int anoFinal;
    private String contrato;
}
