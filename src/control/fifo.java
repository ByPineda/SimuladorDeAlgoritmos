package control;

import java.util.ArrayList;

import model.proceso;

public class fifo {;
    //Constructor
    public fifo(){};

    // Metodo del algoritmo.
    public ArrayList<proceso> FCFS(ArrayList<proceso> listaProcesos, int n) {
        ArrayList<proceso> listaProcesosFinal = listaProcesos;
        // Ordenar la lista de procesos por tiempo de llegada CON ALGORITMO BURBUJA
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (listaProcesosFinal.get(j).getTiempoLLegada() > listaProcesosFinal.get(j + 1).getTiempoLLegada()) {
                    proceso aux = listaProcesosFinal.get(j);
                    listaProcesosFinal.set(j, listaProcesosFinal.get(j + 1));
                    listaProcesosFinal.set(j + 1, aux);
                }
            }
        }
        // Calculamos los tiempos de arranque y finalizacion
        int tiempo = 0;
        for (proceso proceso : listaProcesosFinal) {
            if (tiempo < proceso.getTiempoLLegada()) {
                tiempo = proceso.getTiempoLLegada();
            }
            proceso.setTiempoArranque(tiempo);
            tiempo += proceso.getRafaga();
            proceso.setTiempoFinalizacion(tiempo);
        }
        // Calculamos los derivados del proceso
        for (proceso proceso : listaProcesosFinal) {
            proceso.setParametros();
        }
        imprimirListaProcesos(listaProcesosFinal);
        return listaProcesosFinal;
    }

    //Imprimir la lista de procesos
    public void imprimirListaProcesos(ArrayList<proceso> listaProcesos) {
        for (proceso proceso : listaProcesos) {
            System.out.println(proceso.getId());
        }
    }
}