package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.common;

import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.dto.ErroCampoDTO;
import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.dto.ErroRespostaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaDTO> handlerMethodArgumentNotValid(MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<ErroCampoDTO> listaErros = fieldErrors.stream().map(erros -> new ErroCampoDTO(
                erros.getField(),
                erros.getDefaultMessage()
        )).toList();

        ErroRespostaDTO erro = new ErroRespostaDTO(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "erro de validação",
                listaErros
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }
}
