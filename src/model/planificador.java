package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class planificador {
    private String algoritmo;
    private int quantum;
    ArrayList <proceso> procesosOrdenados;
    ArrayList <String> tablaLue;
    private int TRetTotal, TRetPromedio;
    private int TRespTotal, TRespPromedio;
    private int TWTotal, TWPromedio;
    private int TPenTotal, TPenPromedio;
    private int TEsperaTotal, TEsperaPromedio;

    // Constructor
    public planificador(String algoritmo, ArrayList<proceso> arregloIn, ArrayList<String> tablaLue, int quantum, int TRetTotal, int TRetPromedio, int TRespTotal, int TRespPromedio, int TWTotal,int  TWPromedio,int  TPenTotal,int  TPenPromedio,int  TEsperaTotal, int TEsperaPromedio) {
        this.algoritmo = algoritmo;
        this.procesosOrdenados = arregloIn;
        this.tablaLue = tablaLue;
        this.quantum = quantum;
        this.TRetTotal = TRetTotal;
        this.TRetPromedio = TRetPromedio;
        this.TRespTotal = TRespTotal;
        this.TRespPromedio = TRespPromedio;
        this.TWTotal = TWTotal;
        this.TWPromedio = TWPromedio;
        this.TPenTotal = TPenTotal;
        this.TPenPromedio = TPenPromedio;
        this.TEsperaTotal = TEsperaTotal;
        this.TEsperaPromedio = TEsperaPromedio;
    }
    
}
