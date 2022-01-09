package br.com.longato.consultaemprestimo.controller;

import br.com.longato.consultaemprestimo.model.User;
import br.com.longato.consultaemprestimo.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@NoArgsConstructor
@AllArgsConstructor
@Controller
@RequestMapping("/usuario")
public class UserController {

  @Autowired
  private UserService userService;


  @PostMapping
  public User createUsuario(@RequestBody User user) {
    return userService.saveUsuario(user);
  }

  @GetMapping
  public List<User> getUsuarioList() {
    return userService.findAll();
  }

  @GetMapping("/{idUsuario}")
  public ResponseEntity<User> getUsuarioById(@PathVariable("idUsuario") Long idUsuario) {
    return ResponseEntity.ok(userService.getById(idUsuario)
            .orElseThrow(() -> new NoSuchElementException("User nao encontrado")));
  }

  @PutMapping
  public User updateUsuario(@RequestBody User user) {
    return userService.updateUsuario(user);
  }

  @DeleteMapping("/{idUsuario}")
  @ResponseBody
  public ResponseEntity<User> deleteById(@PathVariable("idUsuario") Long idUsuario) {
    final Long userId = userService.deleteUsuario(idUsuario);
    if (userId == null) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    } else {
      return ResponseEntity.ok().build();
    }
  }
}
