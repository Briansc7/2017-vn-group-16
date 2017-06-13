package model;

import componentesMatematicos.Expresion;

public abstract class Factor implements Expresion {
	
	public String nombre;
	
	public Factor(String unNombre){
		this.nombre = unNombre;
	}
}