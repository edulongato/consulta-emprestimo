package br.com.longato.consultaemprestimo.repository;

import br.com.longato.consultaemprestimo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);

}
