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
	private Planilla planilla;
	
	public AgregarIndicadorViewModel(Planilla unaPlanilla){
		this.planilla = unaPlanilla;
	}
	
	public void verificarIndicador() throws ParseException, TokenMgrError, IOException{
		planilla.verificarIndicador(this.indicador);
	}
	
	public String getIndicador() {
		return indicador;
	}
	
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	
}
