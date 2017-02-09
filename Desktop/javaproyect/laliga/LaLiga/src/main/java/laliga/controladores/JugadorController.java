package laliga.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import laliga.modelo.entidades.Equipo;
import laliga.modelo.entidades.Jugador;
import laliga.modelo.enumeraciones.JugadoresCategoria;
import laliga.modelo.repositorios.EquipoRepositorio;
import laliga.modelo.repositorios.JugadorRepositorio;
import laliga.propertyeditors.EquipoPropertyEditor;




@Controller
@RequestMapping ("/jugadores")
public class JugadorController {

	@Autowired 
	private EquipoPropertyEditor equipoPropertyEditor;
	@Autowired
	private EquipoRepositorio equipoRepo;
	@Autowired
	private JugadorRepositorio jugadorRepo;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String listarJugadores(Model model){
		
		Iterable<Jugador> lista = jugadorRepo.findAll();
		model.addAttribute("titulo", "Lista de Jugadores");
		model.addAttribute("jugadores", lista);
		model.addAttribute("equipo", equipoRepo.findAll());
		model.addAttribute("posiciones", JugadoresCategoria.values());
		
		return "jugadores/listadoJugador";
		
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String guardar(Model model, 
			@Valid @ModelAttribute Jugador jugador, BindingResult bindingResult){
		
		jugadorRepo.save(jugador);
		Iterable<Jugador> lista = jugadorRepo.findAll();
		model.addAttribute("titulo", "Listado de Jugadores");
		model.addAttribute("jugadores", lista);
		model.addAttribute("equipo", equipoRepo.findAll());
		model.addAttribute("posiciones", JugadoresCategoria.values());
		
		return "jugadores/listadoJugador";
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<String> borrarJugador(@PathVariable Long id){

		try {
			jugadorRepo.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
		
				
		}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	@ResponseBody
	public Jugador buscarJugador(@PathVariable Long id){
		return jugadorRepo.findOne(id);
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.registerCustomEditor(Equipo.class, equipoPropertyEditor);
	}
}
