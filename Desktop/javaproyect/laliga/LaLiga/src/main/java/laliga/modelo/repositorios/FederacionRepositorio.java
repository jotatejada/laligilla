package laliga.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import laliga.modelo.entidades.Federacion;

@Repository
public interface FederacionRepositorio extends CrudRepository<Federacion, Long> {

}
