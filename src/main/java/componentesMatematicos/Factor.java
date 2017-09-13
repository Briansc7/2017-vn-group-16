package componentesMatematicos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public abstract class Factor extends Expresion {
	public Factor(){}
}