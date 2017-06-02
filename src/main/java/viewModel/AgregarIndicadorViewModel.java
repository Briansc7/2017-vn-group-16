package viewModel;

import java.io.IOException;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import model.Planilla;
import parser.ParseException;
import parser.TokenMgrError;

@Observable
public class AgregarIndicadorViewModel {

	private String indicador;
	
	public void verificarIndicador() throws ParseException, TokenMgrError, IOException{
		Planilla.instance.verificarIndicador(this.indicador);
	}
	
	public String getIndicador() {
		return indicador;
	}
	
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	
}
