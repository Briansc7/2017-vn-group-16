package viewModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Dependencies;

import model.BaseDeDatos;
import model.Empresa;
import model.metodologia.CondicionNoTaxativa;
import model.metodologia.Metodologia;

public class ConsultarMetodologiasViewModel {
	
private BaseDeDatos baseDeDatos;
	
	private String nombreMetodologiaElegida = "";	
	private Metodologia metodologiaElegida;
	private CondicionNoTaxativa condNoTaxativaElegida;
	
	
	public ConsultarMetodologiasViewModel(String path) throws IOException{
		
	}
	
	@Dependencies("nombreEmpresaElegida")
	public List<Metodologia> getMetodologias() throws IOException {
			if (nombreMetodologiaElegida.equals("")) {
				//return baseDeDatos.buscarMetodologias("");
			} else {		
				//return baseDeDatos.buscarMetodologias(nombreMetodologiaElegida);
			}
			
			return null;
	}
	
	public Metodologia getMetodologiaElegida() {
		return metodologiaElegida;
	}

	public String getNombreMetodologiaElegida() {
		return nombreMetodologiaElegida;
	}
}
