package dominio;

import java.util.List;

public class Usuario {
	
	List<Empresa> empresas;

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

}
