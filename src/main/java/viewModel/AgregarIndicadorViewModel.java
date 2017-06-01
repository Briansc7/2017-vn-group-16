package viewModel;

import java.io.IOException;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Planilla;

@Observable
public class AgregarIndicadorViewModel {

	private String indicador;
	
	public void verificarIndicador(){
		try {
			Planilla.instance.verificarIndicador(this.indicador);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("El archivo no existe");
		}
	}
	
	public String getIndicador() {
		return indicador;
	}
	
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	
}
