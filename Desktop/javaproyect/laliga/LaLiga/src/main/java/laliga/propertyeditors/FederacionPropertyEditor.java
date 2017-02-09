package laliga.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import laliga.modelo.entidades.Federacion;
import laliga.modelo.repositorios.FederacionRepositorio;



@Component
public class FederacionPropertyEditor extends PropertyEditorSupport {

	@Autowired private FederacionRepositorio federacionRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long idFederacion = Long.parseLong(text);
		Federacion federacion = federacionRepositorio.findOne(idFederacion);
		setValue(federacion);
	}
	
}
