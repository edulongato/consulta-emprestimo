package br.com.longato.consultaemprestimo.controller;

import br.com.longato.consultaemprestimo.dto.Login;
import br.com.longato.consultaemprestimo.model.Usuario;
import br.com.longato.consultaemprestimo.security.SecurityConfig;
import br.com.longato.consultaemprestimo.service.SessaoService;
import br.com.longato.consultaemprestimo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UsuarioService usuarioService;

    private SessaoService sessaoService;

    @PostMapping("/login")
    public void login(@RequestBody Login login){
        final Usuario user = usuarioService.authenticate(login);
        sessaoService.addUser(user);
    }
}
