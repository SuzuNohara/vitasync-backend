package com.suzu.vitasync.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String correoElectronico;
    private String claveAcceso;
}