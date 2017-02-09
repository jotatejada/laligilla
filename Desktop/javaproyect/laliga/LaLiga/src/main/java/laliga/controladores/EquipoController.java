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
import laliga.modelo.entidades.Federacion;
import laliga.modelo.entidades.Jugador;
import laliga.modelo.enumeraciones.EquipoCategoria;
import laliga.modelo.repositorios.EquipoRepositorio;
import laliga.modelo.repositorios.FederacionRepositorio;
import laliga.propertyeditors.FederacionPropertyEditor;


@Controller
@RequestMapping ("/equipos")
public class EquipoController {

	@Autowired 
	private FederacionPropertyEditor federacionPropertyEditor;
	@Autowired
	private FederacionRepositorio federacionRepo;
	@Autowired
	private EquipoRepositorio equipoRepo;

	
	
	@RequestMapping(method=RequestMethod.GET)
	public String listarEquipos(Model model){
		
		Iterable<Equipo> lista = equipoRepo.findAll();
		model.addAttribute("titulo", "Lista de Equipos");
		model.addAttribute("equipos", lista);
		model.addAttribute("federacion", federacionRepo.findAll());
		model.addAttribute("divisiones", EquipoCategoria.values());
		
		return "equipos/listadoEquipo";
		
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String guardar(Model model, 
			@Valid @ModelAttribute Equipo equipo, BindingResult bindingResult){
		
		equipoRepo.save(equipo);
		Iterable<Equipo> lista = equipoRepo.findAll();
		model.addAttribute("titulo", "Listado de Equipos");
		model.addAttribute("equipos", lista);
		model.addAttribute("federacion", federacionRepo.findAll());
		model.addAttribute("divisiones", EquipoCategoria.values());
		
		return "equipos/listadoEquipo";
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<String> borrarEquipo(@PathVariable Long id){

		try {
			equipoRepo.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
		
				
		}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	@ResponseBody
	public Equipo buscarEquipo(@PathVariable Long id){
		return equipoRepo.findOne(id);
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.registerCustomEditor(Federacion.class, federacionPropertyEditor);
	}
}
