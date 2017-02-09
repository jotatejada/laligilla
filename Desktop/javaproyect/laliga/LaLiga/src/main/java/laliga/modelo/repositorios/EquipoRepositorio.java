package laliga.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import laliga.modelo.entidades.Equipo;
@Repository
public interface EquipoRepositorio extends CrudRepository<Equipo, Long> {

}
