package model.metodologia.condiciones;

import java.math.BigDecimal;

public enum Comparador {
	MAYOR {
		public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
			return valorUno.compareTo(valorDos) > 0;
		}
	}, 
	MAYOR_O_IGUAL {
		public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
			return valorUno.compareTo(valorDos) >= 0;
		}
	},
	MENOR {
		public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
			return valorUno.compareTo(valorDos) < 0;
		}
	},
	MENOR_O_IGUAL {
		public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
			return valorUno.compareTo(valorDos) <= 0;
		}
	},IGUAL {
		public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
			return valorUno.compareTo(valorDos) == 0;
		}
	};
	
	public abstract boolean comparar(BigDecimal valorUno, BigDecimal valorDos);
}
