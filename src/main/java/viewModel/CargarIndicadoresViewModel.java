package viewModel;

import org.uqbar.commons.utils.Observable;

@Observable
public class CargarIndicadoresViewModel {
	
	private String pathIndicadores;
	
	public String getPathIndicadores() {
		return pathIndicadores;
	}

	public void setPathIndicadores(String pathIndicadores) {
		this.pathIndicadores = pathIndicadores;
	}

	public CargarIndicadoresViewModel(){
		
	}

}
