package model;

import model.repositories.RepositorioDeEmpresas;
import model.repositories.RepositorioIndicadoresPrecalculados;
import utils.DatosCsv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class BatchEjecutable {

    private static String PATH_BATCH_EMPRESAS = "./Archivos del sistema/batch empresas.csv";

    public static void main(String[] args) throws FileNotFoundException {

        DatosCsv lector = new DatosCsv(PATH_BATCH_EMPRESAS);

        try {
            lector.leerEmpresas();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        RepositorioDeEmpresas.getInstance().guardarTodos(lector.getEmpresas());
        RepositorioIndicadoresPrecalculados.getInstance().precalcularIndicadores(/*lector.getEmpresas()*/);
        limpiarArchivoBatch();

        System.exit(0);
    }

    private static void limpiarArchivoBatch() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(PATH_BATCH_EMPRESAS);
        pw.close();
    }
}
