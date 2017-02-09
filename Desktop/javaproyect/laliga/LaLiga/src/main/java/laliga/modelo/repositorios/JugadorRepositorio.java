package laliga.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import laliga.modelo.entidades.Jugador;

@Repository
public interface JugadorRepositorio extends CrudRepository<Jugador, Long> {

}
