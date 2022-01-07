package br.com.longato.consultaemprestimo.service.exception;

import static java.lang.String.format;

public class WrongCredentialException extends RuntimeException {

  private static final String ERROR_MESSAGE = "Senha inválida para o login: %s";

  public WrongCredentialException(String email) {
    super(format(ERROR_MESSAGE, email));
  }
}
