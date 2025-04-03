package com.estudos.demo_park_api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioSenhaDto {

    @NotBlank
    @Size(min = 6, max = 10,message = "a senha deve conter apenas 6 caracteres")
    private String senhaAtual;
    @NotBlank
    @Size(min = 6, max = 10,message = "a senha deve conter apenas 6 caracteres")
    private String novaSenha;
    @NotBlank
    @Size(min = 6, max = 10,message = "a senha deve conter apenas 6 caracteres")
    private String confirmaSenha;

}
