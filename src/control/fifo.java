package control;

import java.util.ArrayList;

import model.proceso;

public class fifo {;
    //Constructor
    public fifo(){};

    // Metodo del algoritmo.
    public ArrayList<String> FCFS(ArrayList<proceso> listaProcesos, int n) {
         ArrayList<String> arregloParaLUE = new ArrayList<String>();
        // Ordenar la lista de procesos por tiempo de llegada CON ALGORITMO BURBUJA
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (listaProcesos.get(j).getTiempoLLegada() > listaProcesos.get(j + 1).getTiempoLLegada()) {
                    proceso aux = listaProcesos.get(j);
                    listaProcesos.set(j, listaProcesos.get(j + 1));
                    listaProcesos.set(j + 1, aux);
                }
            }
        }
        // Calculamos los tiempos de arranque y finalizacion
        int tiempo = 0;
        for (proceso proceso : listaProcesos) {
            if (tiempo < proceso.getTiempoLLegada()) {
                tiempo = proceso.getTiempoLLegada();
            }
            proceso.setTiempoArranque(tiempo);
            tiempo += proceso.getRafaga(); 
            proceso.setTiempoFinalizacion(tiempo);
            for(int i=proceso.getTiempoArranque();i<proceso.getTiempoFinalizacion();i++){
                arregloParaLUE.add(proceso.getId());
            }
        }
        // Calculamos los derivados del proceso
        for (proceso proceso : listaProcesos) {
            proceso.setParametros();
        }
        return arregloParaLUE;
    }
}