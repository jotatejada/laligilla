package laliga.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import laliga.modelo.entidades.Equipo;
import laliga.modelo.entidades.Federacion;
import laliga.modelo.enumeraciones.EquipoCategoria;
import laliga.modelo.repositorios.EquipoRepositorio;
import laliga.modelo.repositorios.FederacionRepositorio;

@Controller
	@RequestMapping ("/federaciones")
	public class FederacionController {


		@Autowired
		private FederacionRepositorio federacionRepo;
		@Autowired
		private EquipoRepositorio equipoRepo;

		
		@RequestMapping(method=RequestMethod.GET)
		public String listarFederaciones(Model model){
			
			Iterable<Federacion> lista =federacionRepo.findAll();
			model.addAttribute("titulo", "Lista de Federaciones");
			model.addAttribute("federaciones", lista);
			
			return "federaciones/listadoFederacion";
			
		}
		
		@RequestMapping(method=RequestMethod.POST)
		public String guardar(Model model, 
				@Valid @ModelAttribute Federacion federacion, BindingResult bindingResult){
			
			federacionRepo.save(federacion);
			Iterable<Federacion> lista = federacionRepo.findAll();
			model.addAttribute("titulo", "Listado de Federaciones");
			model.addAttribute("federaciones", lista);
			
			return "federaciones/listadoFederacion";
			
		}
		
		@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
		public ResponseEntity<String> borrarFederacion(@PathVariable Long id){

			try {
				federacionRepo.delete(id);
				return new ResponseEntity<String>(HttpStatus.OK);
				
			}catch(Exception e){
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
				}
			
					
			}
		
		
		@RequestMapping(method=RequestMethod.GET, value="/{id}")
		@ResponseBody
		public Federacion buscarFederacion(@PathVariable Long id){
			return federacionRepo.findOne(id);
			
		}

}
