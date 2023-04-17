package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class planificador {
    private String algoritmo;
    private int quantum;
    ArrayList<proceso> procesosOrdenados;
    ArrayList<String> tablaLue;
    private int TRetTotal ;
    private int TRespTotal ;
    private int TWTotal;
    private int TPenTotal ;
    private int TEsperaTotal;

    private float TRetPromedio, TRespPromedio, TWPromedio, TPenPromedio,TEsperaPromedio;

    // Constructor
    public planificador(String algoritmo, ArrayList<proceso> arregloIn, ArrayList<String> tablaLue, int quantum,
            int TRetTotal, int TRetPromedio, int TRespTotal, int TRespPromedio, int TWTotal, int TWPromedio,
            int TPenTotal, int TPenPromedio, int TEsperaTotal, int TEsperaPromedio) {
        this.algoritmo = algoritmo;
        this.procesosOrdenados = (ArrayList<proceso>) arregloIn.clone();
        this.tablaLue = (ArrayList<String>) tablaLue.clone();
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

    // Setters y Getters
    public String getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    public ArrayList<proceso> getProcesosOrdenados() {
        return procesosOrdenados;
    }

    public void setProcesosOrdenados(ArrayList<proceso> procesosOrdenados) {
        this.procesosOrdenados = procesosOrdenados;
    }

    public ArrayList<String> getTablaLue() {
        return tablaLue;
    }

    public void setTablaLue(ArrayList<String> tablaLue) {
        this.tablaLue = tablaLue;
    }

    public int getTRetTotal() {
        return TRetTotal;
    }

    public void setTRetTotal(int TRetTotal) {
        this.TRetTotal = TRetTotal;
    }

    public float getTRetPromedio() {
        return TRetPromedio;
    }

    public void setTRetPromedio(int TRetPromedio) {
        this.TRetPromedio = TRetPromedio;
    }

    public int getTRespTotal() {
        return TRespTotal;
    }

    public void setTRespTotal(int TRespTotal) {
        this.TRespTotal = TRespTotal;
    }

    public float getTRespPromedio() {
        return TRespPromedio;
    }

    public void setTRespPromedio(int TRespPromedio) {
        this.TRespPromedio = TRespPromedio;
    }

    public int getTWTotal() {
        return TWTotal;
    }

    public void setTWTotal(int TWTotal) {
        this.TWTotal = TWTotal;
    }

    public float getTWPromedio() {
        return TWPromedio;
    }

    public void setTWPromedio(int TWPromedio) {
        this.TWPromedio = TWPromedio;
    }

    public int getTPenTotal() {
        return TPenTotal;
    }

    public void setTPenTotal(int TPenTotal) {
        this.TPenTotal = TPenTotal;
    }

    public float getTPenPromedio() {
        return TPenPromedio;
    }

    public void setTPenPromedio(int TPenPromedio) {
        this.TPenPromedio = TPenPromedio;
    }

    public int getTEsperaTotal() {
        return TEsperaTotal;
    }

    public void setTEsperaTotal(int TEsperaTotal) {
        this.TEsperaTotal = TEsperaTotal;
    }

    public float getTEsperaPromedio() {
        return TEsperaPromedio;
    }

    public void setTEsperaPromedio(int TEsperaPromedio) {
        this.TEsperaPromedio = TEsperaPromedio;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

}
