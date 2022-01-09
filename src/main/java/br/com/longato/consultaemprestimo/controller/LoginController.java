package br.com.longato.consultaemprestimo.controller;

import br.com.longato.consultaemprestimo.dto.Login;
import br.com.longato.consultaemprestimo.model.User;
import br.com.longato.consultaemprestimo.security.SecurityConfig;
import br.com.longato.consultaemprestimo.service.SessaoService;
import br.com.longato.consultaemprestimo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@NoArgsConstructor
@AllArgsConstructor
@Controller
public class LoginController {
    @Autowired
     PasswordEncoder encoder;
    @Autowired
     SecurityConfig securityConfig;
    @Autowired
     UserService userService;

    private SessaoService sessaoService;

    @PostMapping("/login")
    public void login(@RequestBody Login login){
        final User user = userService.authenticate(login);
        sessaoService.addUser(user);
    }
}
