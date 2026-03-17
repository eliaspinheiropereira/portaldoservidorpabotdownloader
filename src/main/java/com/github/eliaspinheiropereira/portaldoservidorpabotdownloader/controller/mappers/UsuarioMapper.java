package com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.mappers;

import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.controller.dto.UsuarioDTO;
import com.github.eliaspinheiropereira.portaldoservidorpabotdownloader.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDTO usuarioDTO);
}
