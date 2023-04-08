package control;

import java.util.ArrayList;
import model.proceso;

public class RounRobin {
    public RounRobin() {
    };// Constructor
      // Metodo para implementar el planificador

    public ArrayList<String> RR(ArrayList<proceso> arregloProceso, int quantum) {

        ArrayList<String> arregloParaLUE = new ArrayList<String>(); // Arreglo a devolver para la tabla LUE// Para ver
                                                                    // si este no esta vacio al principio
        for (int i = 0; i < arregloProceso.size(); i++) {
            for (int j = 0; j < arregloProceso.size() - 1; j++) {
                if (arregloProceso.get(j).getTiempoLLegada() > arregloProceso.get(j + 1).getTiempoLLegada()) {
                    proceso aux = arregloProceso.get(j);
                    arregloProceso.set(j, arregloProceso.get(j + 1));
                    arregloProceso.set(j + 1, aux);
                }
            }
        }
        int empiezaArreglo = arregloProceso.get(0).getTiempoLLegada();
        int arrRafaga[] = new int[arregloProceso.size()];
        int i, j;
        for (i = 0; i < empiezaArreglo; i++) {// Para manejar la rafaga y saber si aun hay rafaga o no;
            arregloParaLUE.add(" ");
        }
        for (i = 0; i < arregloProceso.size(); i++) {// Para manejar la rafaga y saber si aun hay rafaga o no;
            arrRafaga[i] = arregloProceso.get(i).getRafaga();
        }
        int aux = 0, aux2 = 0;
        do {
            if (aux == arregloProceso.size()) {
                aux = 0;
            } else {
                if (arrRafaga[aux] > 0) {
                    if (arrRafaga[aux] == arregloProceso.get(aux).getRafaga()) {
                        arregloProceso.get(aux).setTiempoArranque(empiezaArreglo);
                    }
                    if (arrRafaga[aux] >= quantum) {
                        for (j = 0; j < quantum; j++) {
                            arregloParaLUE.add(arregloProceso.get(aux).getId());
                        }
                        empiezaArreglo += quantum;
                        if (arrRafaga[aux] == quantum) {
                            arregloProceso.get(aux).setTiempoFinalizacion(empiezaArreglo);
                            aux2 += 1;
                        }
                        arrRafaga[aux] -= quantum;
                    } else if (arrRafaga[aux] > 0 && arrRafaga[aux] < quantum) {
                        for (j = 0; j < arrRafaga[aux]; j++) {
                            arregloParaLUE.add(arregloProceso.get(aux).getId());
                        }
                        empiezaArreglo += arrRafaga[aux];
                        arregloProceso.get(aux).setTiempoFinalizacion(empiezaArreglo);
                        arrRafaga[aux] = 0;
                        aux2 += 1;
                    }
                }
                aux += 1;
            }
            System.out.println(aux2);
        } while (aux2 != arregloProceso.size());
        for (i = 0; i < arregloProceso.size(); i++) {
            arregloProceso.get(i).setParametros();
        }
        return arregloParaLUE;
    }

    public void ordenarProcesos(ArrayList<proceso> arregloProceso) {
        proceso aux;
        for (int i = 0; i < arregloProceso.size(); i++) {
            for (int j = 0; j < arregloProceso.size(); j++) {
                if (arregloProceso.get(i).getTiempoLLegada() < arregloProceso.get(i).getTiempoLLegada()) {
                    aux = arregloProceso.get(i);
                    arregloProceso.set(i, arregloProceso.get(j));
                    arregloProceso.set(j, aux);
                }
            }
        }
    }

}