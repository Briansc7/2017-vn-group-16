package model;

import java.math.BigDecimal;

public class IndicadorPrecalculado {
    private String nombre;
    private String formula;
    private long idUsuario;
    private String nombreEmpresa;
    private Integer periodo;
    private String valor;

    public IndicadorPrecalculado(String nombre, String formula, long idUsuario, String nombreEmpresa, Integer periodo, String valor) {
        this.nombre = nombre;
        this.formula = formula;
        this.idUsuario = idUsuario;
        this.nombreEmpresa = nombreEmpresa;
        this.periodo = periodo;
        this.valor = valor;
    }

    private IndicadorPrecalculado(){ }
}
