package control;
import model.*;
import java.util.ArrayList;

public class almacen {
    private ArrayList<proceso> ArregloProcesos;
    private ArrayList<String> ArregloLUE;
    private int quantum;
    
    public almacen() {
        ArregloProcesos = new ArrayList<proceso>();
        ArregloLUE = new ArrayList<String>();
    }

    //Setters y Getters
    public ArrayList<proceso> getArregloProcesos() {
        return ArregloProcesos;
    }

    public ArrayList<String> getArregloLUE() {
        return ArregloLUE;
    }

    public void setArregloProcesos(ArrayList<proceso> ArregloProcesos) {
        this.ArregloProcesos = ArregloProcesos;
    }

    public void setArregloLUE(ArrayList<String> ArregloLUE) {
        this.ArregloLUE = ArregloLUE;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

}
