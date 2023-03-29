package control;
import model.*;
import java.util.ArrayList;

public class almacen {
    private ArrayList<proceso> ArregloProcesos;
    private int quantum;
    
    public almacen() {
        ArregloProcesos = new ArrayList<proceso>();
    }

    //Setters y Getters
    public ArrayList<proceso> getArregloProcesos() {
        return ArregloProcesos;
    }

    public void setArregloProcesos(ArrayList<proceso> ArregloProcesos) {
        this.ArregloProcesos = ArregloProcesos;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

}
