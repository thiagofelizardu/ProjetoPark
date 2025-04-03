package com.estudos.demo_park_api.model.service;

import com.estudos.demo_park_api.model.entity.Usuario;
import com.estudos.demo_park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor //para fazer injecao de dependencia via construtor, variavel tem que ter o final para que o lombook funcione com o spring
@Service
public class UsuarioService implements UsuarioServiceImpl {


    private final UsuarioRepository usuarioRepository;

    @Transactional //estou dizendo que o spring vai tomar contado do metodo salvar
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)// Indica ao Spring que este é um método é apenas de consulta
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Usuario nao encontrado")
        );
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha)) {
            throw new RuntimeException("Nova Senha não confere com o confirmação da senha");
        }
        Usuario usuario = buscarPorId(id);
        if(!usuario.getPassword().equals(senhaAtual)) {
            throw new RuntimeException("Sua senha não confere");
        }
        usuario.setPassword(novaSenha);// Atualiza a senha do usuário
        /*
         Não utilizei uma instrução explícita de update porque, ao buscar o objeto,
         ele passa para o estado persistente. Isso significa que o Hibernate gerencia
         esse objeto até o final da transação. Ao chamar setPassword, o Hibernate
         detecta a alteração, a mantém em seu cache e, ao final do processo,
         sincroniza automaticamente com o banco de dados.
         */
        return usuario;
    }


    @Transactional(readOnly = true)
    public Page<Usuario> buscarTodosUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

}
