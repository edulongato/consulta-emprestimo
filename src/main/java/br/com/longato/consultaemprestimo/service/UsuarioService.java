package br.com.longato.consultaemprestimo.service;

import br.com.longato.consultaemprestimo.dto.Login;
import br.com.longato.consultaemprestimo.model.Usuario;
import br.com.longato.consultaemprestimo.repository.UsuarioRepository;
import br.com.longato.consultaemprestimo.service.exception.WrongCredentialException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsuarioService {

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public void createUser(Usuario user) {
    String pass = user.getSenha();
    //criptografando antes de salvar no banco
    user.setSenha(encoder.encode(pass));
    usuarioRepository.save(user);
  }

  public Usuario authenticate(final Login login) {
    final Usuario user = usuarioRepository.findByEmail(login.getEmail());
    final boolean passwordOk = encoder.matches(login.getSenha(), user.getSenha());
    if (user == null || !passwordOk) {
      throw new WrongCredentialException(login.getEmail());
    }
    return user;
  }


  public Usuario saveUsuario(Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  public List<Usuario> findAll() {
    return usuarioRepository.findAll();
  }

  public Optional<Usuario> getById(Long idUsuario) {
    return usuarioRepository.findById(idUsuario);
  }

  public Usuario updateUsuario(Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  public Long deleteUsuario(Long idUsuario) {
    try {
      usuarioRepository.deleteById(idUsuario);
      return idUsuario;
    } catch (Exception e) {
      log.error(e.toString());
      return null;
    }
  }


}
