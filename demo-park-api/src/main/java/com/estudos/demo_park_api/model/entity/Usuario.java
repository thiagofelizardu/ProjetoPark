package com.estudos.demo_park_api.model.entity;

import com.estudos.demo_park_api.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "db_usuarios")
public class Usuario implements Serializable {// implementei a Serializable pois é uma boa pratica para qunado se trabalha com JPA e Hibernate

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username",nullable = false, unique = true, length = 255)
    private String username;

    @Column(name = "password",nullable = false,length = 255)
    private String password;

    @Enumerated(EnumType.STRING) //transforma a constante em uma String
    @Column(name = "role", nullable = false, length = 25)
    private Role role = Role.ROLE_CLIENT;

    @Column(name = "data_Criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_Modificacao")
    private LocalDateTime dataModificacao;

    @Column(name = "criado_Por")
    private String criadoPor;

    @Column(name = "modificado_Por")
    private String modificadoPor;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                '}';
    }
}
