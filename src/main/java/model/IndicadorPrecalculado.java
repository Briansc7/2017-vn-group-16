package model;

public class IndicadorPrecalculado {
    private String nombre;
    private String formula;
    private long idUsuario;
    private String nombreEmpresa;
    private Integer periodo;
    private String valor;

    public IndicadorPrecalculado(String nombre, String formula, long idUsuario, String nombreEmpresa, Integer periodo, String valor) {
        this.nombre = nombre.toLowerCase();
        this.formula = formula;
        this.idUsuario = idUsuario;
        this.nombreEmpresa = nombreEmpresa.toLowerCase();
        this.periodo = periodo;
        this.valor = valor;
    }

    private IndicadorPrecalculado(){ }

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }
}
