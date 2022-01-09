package br.com.longato.consultaemprestimo.service;

import br.com.longato.consultaemprestimo.dto.Login;
import br.com.longato.consultaemprestimo.model.User;
import br.com.longato.consultaemprestimo.repository.UserRepository;
import br.com.longato.consultaemprestimo.service.exception.WrongCredentialException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        String pass = user.getSenha();
        //criptografando antes de salvar no banco
        user.setSenha(encoder.encode(pass));
        userRepository.save(user);
    }

    public User authenticate(final Login login) {
        final User user = userRepository.findOneByEmail(login.getEmail());
        final boolean passwordOk = encoder.matches(login.getSenha(), user.getSenha());
        if (user == null || !passwordOk) {
            throw new WrongCredentialException(login.getEmail());
        }
        return user;
    }


    public User saveUsuario(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long idUsuario) {
        return userRepository.findById(idUsuario);
    }

    public User updateUsuario(User user) {
        return userRepository.save(user);
    }

    public Long deleteUsuario(Long idUsuario) {
        try {
            userRepository.deleteById(idUsuario);
            return idUsuario;
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }


}
