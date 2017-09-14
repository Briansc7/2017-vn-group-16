package componentesMatematicos;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import model.BaseDeDatos;
import model.Empresa;
import scala.xml.dtd.impl.Base;

@Entity
@Table(name = "expresion")
@DiscriminatorColumn(name = "tipoDeExpresion", discriminatorType=DiscriminatorType.INTEGER)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Expresion {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	public Expresion(){}
	
	public abstract BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa);
}