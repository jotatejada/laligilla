package laliga.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.stereotype.Component;

import laliga.modelo.entidades.Equipo;
import laliga.modelo.repositorios.EquipoRepositorio;

@Component
public class EquipoPropertyEditor extends PropertyEditorSupport {

	@Autowired private EquipoRepositorio equipoRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long idEquipo = Long.parseLong(text);
		Equipo equipo = equipoRepositorio.findOne(idEquipo);
		setValue(equipo);
	}
	
}
