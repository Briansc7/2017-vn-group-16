package model.metodologia.condiciones;

import java.math.BigDecimal;

public enum Comparador {
	MAYOR {
		public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
			return valorUno.compareTo(valorDos) > 0;
		}
	}, 
	MAYOROIGUAL {
		public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
			return valorUno.compareTo(valorDos) >= 0;
		}
	},
	MENOR {
		public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
			return valorUno.compareTo(valorDos) < 0;
		}
	},
	MENOROIGUAL {
		public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
			return valorUno.compareTo(valorDos) <= 0;
		}
	},IGUAL {
		public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
			return valorUno.compareTo(valorDos) == 0;
		}
	};
	
	abstract boolean comparar(BigDecimal valorUno, BigDecimal valorDos);
}
