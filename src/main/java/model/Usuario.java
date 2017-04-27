package model;

import java.util.List;


public class Usuario {
	
	List<Empresa> empresas;
	
	private static Usuario instance = null;

	public static Usuario getInstance() {
		if (instance == null) {
			instance = new Usuario();
		}
		return instance;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

}
