package control;

import model.*;
import java.util.ArrayList;

public class almacen {
    private ArrayList<proceso> ArregloProcesos;
    private int TRetTotal, TRetPromedio;
    private int TRespTotal, TRespPromedio;
    private int TWTotal, TWPromedio;
    private int TPenTotal, TPenPromedio;
    private int TEsperaTotal, TEsperaPromedio;
    private int quantum;

    //Arreglo donde se guardarán los planificadores
    private ArrayList<planificador> planificadores;

    public almacen() {
        ArregloProcesos = new ArrayList<proceso>();
    }

    // Setters y Getters
    public ArrayList<proceso> getArregloProcesos() {
        return ArregloProcesos;
    }

    public void setArregloProcesos(ArrayList<proceso> ArregloProcesos) {
        this.ArregloProcesos = ArregloProcesos;
    }

    public ArrayList<planificador> getPlanificadores() {
        return planificadores;
    }

    public void setPlanificadores(ArrayList<planificador> planificadores) {
        this.planificadores = planificadores;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getTRetTotal() {
        return TRetTotal;
    }

    public void setTRetTotal(int TRetTotal) {
        this.TRetTotal = TRetTotal;
    }

    public int getTRetPromedio() {
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

    public int getTRespPromedio() {
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

    public int getTWPromedio() {
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

    public int getTPenPromedio() {
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

    public int getTEsperaPromedio() {
        return TEsperaPromedio;
    }

    public void setTEsperaPromedio(int TEsperaPromedio) {
        this.TEsperaPromedio = TEsperaPromedio;
    }

    // Metodos

    public void obtenerPromedios(){
        for (proceso proceso : ArregloProcesos) {
            TRetTotal += proceso.getTiempoRetorno();
            TRespTotal += proceso.getTiempoRespuesta();
            TWTotal += proceso.getTasaDesperdicio();
            TPenTotal += proceso.getTasaPenalizacion();
            TEsperaTotal += proceso.getTiempoEspera();
        }
        TRetPromedio = TRetTotal / ArregloProcesos.size();
        TRespPromedio = TRespTotal / ArregloProcesos.size();
        TWPromedio = TWTotal / ArregloProcesos.size();
        TPenPromedio = TPenTotal / ArregloProcesos.size();
        TEsperaPromedio = TEsperaTotal / ArregloProcesos.size();
    }

    public void limpiar(){
        ArregloProcesos.clear();

        TRetTotal = 0;
        TRetPromedio = 0;
        TRespTotal = 0;
        TRespPromedio = 0;
        TWTotal = 0;
        TWPromedio = 0;
        TPenTotal = 0;
        TPenPromedio = 0;
        TEsperaTotal = 0;
        TEsperaPromedio = 0;

        quantum = 0;
    }

}
