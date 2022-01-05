package br.com.longato.consultaemprestimo.controller;

import br.com.longato.consultaemprestimo.dto.Login;
import br.com.longato.consultaemprestimo.dto.Sessao;
import br.com.longato.consultaemprestimo.model.Usuario;
import br.com.longato.consultaemprestimo.repository.UsuarioRepository;
import br.com.longato.consultaemprestimo.security.JWTCreator;
import br.com.longato.consultaemprestimo.security.JWTObject;
import br.com.longato.consultaemprestimo.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UsuarioRepository usuariorepository;

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login){
        Usuario user = usuariorepository.findByEmail(login.getEmail());
        if(user!=null) {
            boolean passwordOk =  encoder.matches(login.getSenha(), user.getSenha());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getEmail());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            Sessao sessao = new Sessao();
            sessao.setLogin(user.getEmail());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}
