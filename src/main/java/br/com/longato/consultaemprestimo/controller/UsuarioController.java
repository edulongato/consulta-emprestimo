package br.com.longato.consultaemprestimo.controller;

import br.com.longato.consultaemprestimo.model.Usuario;
import br.com.longato.consultaemprestimo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public void postUser(@RequestBody Usuario user){
        usuarioService.createUser(user);
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> getUsuarioList(){
        return usuarioService.findAll();
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("idUsuario") Long idUsuario) throws Exception{
        return ResponseEntity.ok(usuarioService.getById(idUsuario).orElseThrow(() -> new NoSuchElementException("Usuario nao encontrado")));
    }

    @PutMapping
    public Usuario updateUsuario(@RequestBody Usuario usuario){
        return usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping("/{idUsuario}")
    @ResponseBody
    public ResponseEntity<Usuario> deleteById(@PathVariable("idUsuario") Long idUsuario) throws Exception{
        try{
            usuarioService.deleteUsuario(idUsuario);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return(ResponseEntity<Usuario>) ResponseEntity.ok();

    }


}
