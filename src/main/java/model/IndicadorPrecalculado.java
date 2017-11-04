package model;

import java.math.BigDecimal;

public class IndicadorPrecalculado {
    private String nombre;
    private String formula;
    private String empresa;
    private Integer periodo;
    private BigDecimal valor;

    public IndicadorPrecalculado(String nombre, String formula, String empresa, Integer periodo, BigDecimal valor) {
        this.nombre = nombre;
        this.formula = formula;
        this.empresa = empresa;
        this.periodo = periodo;
        this.valor = valor;
    }

    private IndicadorPrecalculado(){ }
}
