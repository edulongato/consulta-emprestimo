package br.com.longato.consultaemprestimo.service;

import br.com.longato.consultaemprestimo.dto.Sessao;
import br.com.longato.consultaemprestimo.model.User;
import br.com.longato.consultaemprestimo.security.JWTCreator;
import br.com.longato.consultaemprestimo.security.JWTObject;
import br.com.longato.consultaemprestimo.security.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SessaoService {

  public void addUser(final User user){
    final Sessao sessao = new Sessao();
    sessao.setLogin(user.getEmail());

    final JWTObject jwtObject = new JWTObject();
    jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
    jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
    jwtObject.setRoles(user.getRoles());
    sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
  }

}
