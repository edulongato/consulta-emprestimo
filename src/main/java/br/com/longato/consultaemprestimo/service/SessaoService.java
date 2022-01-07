package br.com.longato.consultaemprestimo.service;

import br.com.longato.consultaemprestimo.dto.Sessao;
import br.com.longato.consultaemprestimo.model.Usuario;
import br.com.longato.consultaemprestimo.security.JWTCreator;
import br.com.longato.consultaemprestimo.security.JWTObject;
import br.com.longato.consultaemprestimo.security.SecurityConfig;
import java.util.Date;

public class SessaoService {

  public void addUser(final Usuario usuario){
    final Sessao sessao = new Sessao();
    sessao.setLogin(usuario.getEmail());

    final JWTObject jwtObject = new JWTObject();
    jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
    jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
    jwtObject.setRoles(usuario.getRoles());
    sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
  }

}
