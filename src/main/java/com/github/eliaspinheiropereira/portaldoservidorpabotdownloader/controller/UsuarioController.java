package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller;

import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.dto.UsuarioDTO;
import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/automacao")
@RequiredArgsConstructor
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> login(
            @RequestBody UsuarioDTO usuarioDTO
    ){
        this.logger.info("Efetuando login no site: {}", usuarioDTO.url());
        this.usuarioService.login(usuarioDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
