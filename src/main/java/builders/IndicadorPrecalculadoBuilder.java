package builders;

import model.IndicadorPrecalculado;

public class IndicadorPrecalculadoBuilder {
    private String nombre;
    private String formula;
    private long idUsuario = -1;
    private String nombreEmpresa;
    private Integer periodo;
    private String valor;

    public IndicadorPrecalculado build(){
        if(nombre == null)
            throw new RuntimeException("falta el nombre del indicador");
        else if(formula == null)
            throw new RuntimeException("falta la formula");
        else if(idUsuario == -1)
            throw new RuntimeException("falta el id de usuario");
        else if(nombreEmpresa == null)
            throw new RuntimeException("falta el nombre de la empresa");
        else if(periodo == null)
            throw new RuntimeException("falta el periodo de evaluacion");
        else if(valor == null)
            throw new RuntimeException("falta el valor del indicador");

        return new IndicadorPrecalculado(nombre, formula, idUsuario, nombreEmpresa, periodo, valor);
    }

    public IndicadorPrecalculadoBuilder nombreIndicador(String nombre){
        this.nombre = nombre;
        return this;
    }

    public IndicadorPrecalculadoBuilder formulaIndicador(String formula){
        this.formula = formula;
        return this;
    }

    public IndicadorPrecalculadoBuilder idUsuario(long idUsuario){
        this.idUsuario = idUsuario;
        return this;
    }

    public IndicadorPrecalculadoBuilder nombreEmpresa(String nombreEmpresa){
        this.nombreEmpresa = nombreEmpresa;
        return this;
    }

    public IndicadorPrecalculadoBuilder periodoDeEvaluacion(Integer periodo){
        this.periodo = periodo;
        return this;
    }

    public IndicadorPrecalculadoBuilder valorString(String valor){
        this.valor = valor;
        return this;
    }
}
