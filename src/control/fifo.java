package control;

import java.util.ArrayList;

import model.proceso;

public class fifo {
    // Constructor
    public fifo() {
    };

    // Metodo del algoritmo.
    public ArrayList<String> FCFS(ArrayList<proceso> arregloProceso, int n) {
        ArrayList<String> arregloParaLUE = new ArrayList<String>();
        // Ordenar la lista de procesos por tiempo de llegada CON ALGORITMO BURBUJA
        int i, j;
        for ( i = 0; i < arregloProceso.size(); i++) {
            for ( j = 0; j < arregloProceso.size() - 1; j++) {
                if (arregloProceso.get(j).getTiempoLLegada() > arregloProceso.get(j + 1).getTiempoLLegada()) {
                    proceso aux = arregloProceso.get(j);
                    arregloProceso.set(j, arregloProceso.get(j + 1));
                    arregloProceso.set(j + 1, aux);
                }
            }
        }
        int empiezaArreglo = arregloProceso.get(0).getTiempoLLegada();
        int arrRafaga[] = new int[arregloProceso.size()];
        
        for (i = 0; i < empiezaArreglo; i++) {// Para manejar la rafaga y saber si aun hay rafaga o no;
            arregloParaLUE.add(" ");
        }
        for (i = 0; i < arregloProceso.size(); i++) {// Para manejar la rafaga y saber si aun hay rafaga o no;
            arrRafaga[i] = arregloProceso.get(i).getRafaga();
        }
        // Calculamos los tiempos de arranque y finalizacion
        int tiempo = 0;
        for (proceso proceso : arregloProceso) {
            if (tiempo < proceso.getTiempoLLegada()) {
                tiempo = proceso.getTiempoLLegada();
            }
            proceso.setTiempoArranque(tiempo);
            tiempo += proceso.getRafaga();
            proceso.setTiempoFinalizacion(tiempo);
            for (i = proceso.getTiempoArranque(); i < proceso.getTiempoFinalizacion(); i++) {
                arregloParaLUE.add(proceso.getId());
            }
        }
        // Calculamos los derivados del proceso
        for (proceso proceso : arregloProceso) {
            proceso.setParametros();
        }

        return arregloParaLUE;
    }
}