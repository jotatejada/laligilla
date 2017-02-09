package laliga.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import laliga.modelo.entidades.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

	UserDetails findOneByUsername(String username);

}
