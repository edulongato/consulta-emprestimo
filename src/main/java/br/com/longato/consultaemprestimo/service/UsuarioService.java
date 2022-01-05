package br.com.longato.consultaemprestimo.service;

import br.com.longato.consultaemprestimo.model.Usuario;
import br.com.longato.consultaemprestimo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }


    @Autowired
    private PasswordEncoder encoder;
    public void createUser(Usuario user){
        String pass = user.getSenha();
        //criptografando antes de salvar no banco
        user.setSenha(encoder.encode(pass));
        usuarioRepository.save(user);
    }


    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getById(Long idUsuario){
        return usuarioRepository.findById(idUsuario);
    }

    public Usuario updateUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }



}
