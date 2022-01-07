package br.com.longato.consultaemprestimo.controller;

import br.com.longato.consultaemprestimo.model.Usuario;
import br.com.longato.consultaemprestimo.service.UsuarioService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @PostMapping
  public void postUser(@RequestBody Usuario user) {
    usuarioService.createUser(user);
  }

  @PostMapping
  public Usuario createUsuario(@RequestBody Usuario usuario) {
    return usuarioService.saveUsuario(usuario);
  }

  @GetMapping
  public List<Usuario> getUsuarioList() {
    return usuarioService.findAll();
  }

  @GetMapping("/{idUsuario}")
  public ResponseEntity<Usuario> getUsuarioById(@PathVariable("idUsuario") Long idUsuario) {
    return ResponseEntity.ok(usuarioService.getById(idUsuario)
        .orElseThrow(() -> new NoSuchElementException("Usuario nao encontrado")));
  }

  @PutMapping
  public Usuario updateUsuario(@RequestBody Usuario usuario) {
    return usuarioService.updateUsuario(usuario);
  }

  @DeleteMapping("/{idUsuario}")
  @ResponseBody
  public ResponseEntity<Usuario> deleteById(@PathVariable("idUsuario") Long idUsuario) {
    final Long userId = usuarioService.deleteUsuario(idUsuario);
    if (userId == null) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    } else {
      return ResponseEntity.ok().build();
    }
  }
}
