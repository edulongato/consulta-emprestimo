package br.com.longato.consultaemprestimo.repository;

import br.com.longato.consultaemprestimo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.email= (:email)")
    public Usuario findByEmail(@Param("email") String email);

    boolean existsByUsername(String email);
}
