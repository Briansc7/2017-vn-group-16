package viewModel;

import java.io.IOException;

import org.uqbar.commons.utils.Observable;

import model.Indicador;
import model.repositories.RepositorioDeIndicadores;
import parser.ParseException;
import parser.TokenMgrError;

@Observable
public class AgregarIndicadorViewModel {

	private String indicador;
	
	public AgregarIndicadorViewModel(){
	}
	
	public void verificarIndicador() throws ParseException, TokenMgrError, IOException{
		this.verificarIndicador(this.indicador);
	}
	
	public String getIndicador() {
		return indicador;
	}
	
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public void verificarIndicador(String indicador) throws ParseException, TokenMgrError{
		String[] partes = indicador.split("=");
		
		if(verificarSintaxisIndicador(partes[0].trim(), partes[1])){
			Indicador miIndicador = new Indicador(partes[0].trim(), partes[1]);
			RepositorioDeIndicadores repositorioDeIndicadores = RepositorioDeIndicadores.getInstance();
			repositorioDeIndicadores.guardar(miIndicador);
		} else {
			throw new RuntimeException("No se puede usar un indicador en su propia definicion");
		}
	}
	
	// Verifica sintaxis = que el indicador no se llame a si mismo
		public boolean verificarSintaxisIndicador(String nombreIndicador, String contenidoFormula) throws ParseException, TokenMgrError{
			return !contenidoFormula.toLowerCase().contains(nombreIndicador.toLowerCase());
		}
		
	
}
