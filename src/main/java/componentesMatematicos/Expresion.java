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

import model.Empresa;

@Entity
@Table(name = "expresiones")
@DiscriminatorColumn(name = "tipo_de_expresion", discriminatorType=DiscriminatorType.INTEGER)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Expresion {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	public Expresion(){}
	
	public abstract BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa);
}