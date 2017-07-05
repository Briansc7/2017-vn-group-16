package viewModel;

import java.io.IOException;

import org.uqbar.commons.utils.Observable;

import model.BaseDeDatos;
import parser.ParseException;
import parser.TokenMgrError;

@Observable
public class AgregarIndicadorViewModel {

	private String indicador;
	private BaseDeDatos baseDeDatos;
	
	public AgregarIndicadorViewModel(){
	}
	
	public void verificarIndicador() throws ParseException, TokenMgrError, IOException{
		baseDeDatos.verificarIndicador(this.indicador);
	}
	
	public String getIndicador() {
		return indicador;
	}
	
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	
}
