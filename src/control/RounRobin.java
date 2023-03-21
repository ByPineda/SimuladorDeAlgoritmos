package control;

import java.util.ArrayList;
import model.proceso;

public class RounRobin {
    ArrayList<proceso> arregloProceso;
    public RounRobin() {
    };// Constructor

    // Metodo para implementar el planificador
    public ArrayList<String> RR(ArrayList<proceso> arregloProceso) {
        this. arregloProceso = arregloProceso;
        ArrayList<String> arregloParaLUE = new ArrayList<String>(); //Arreglo a devolver para la tabla LUE
        ordenarProcesos(arregloProceso); //Para ordenar el arreglo
        int empiezaArreglo = arregloProceso.get(0).getTiempoLLegada();//Para ver si este no esta vacio al principio
        int arrRafaga[] = new int[arregloProceso.size()],i,j;
        for (i=0;i < arrRafaga.length ;i++){//Para poner en el arreglo a imprimir un espacio en blanco para saber que ahi no va nada.
            arregloParaLUE.set(i, " ");
        }
        for (i = 0; i < arregloProceso.size(); i++) {//Para manejar la rafaga y saber si aun hay rafaga o no;
            arrRafaga[i] = arregloProceso.get(i).getRafaga();
        }
        int aux=0,aux2=arregloProceso.size();
        do {
            if (aux==arregloProceso.size()){
                aux=0;
            }else{
                if (arrRafaga[aux]>0){
                    if (arrRafaga[aux]==arregloProceso.get(aux).getRafaga()){
                        arregloProceso.get(aux).setTiempoArranque(empiezaArreglo);
                    }
                    if (arrRafaga[aux] >= 4){
                        for (j=0;j<4;j++){
                            arregloParaLUE.add(arregloProceso.get(aux).getId());
                        }
                        empiezaArreglo+=4;
                        arrRafaga[aux]-=4;
                    }else{
                        for (j=0;j<arrRafaga[aux];j++){
                            arregloParaLUE.add(arregloProceso.get(aux).getId());
                        }
                        empiezaArreglo+=arrRafaga[aux];
                        arregloProceso.get(aux).setTiempoFinalizacion(empiezaArreglo);
                        arrRafaga[aux]=0;
                    }
                }
                aux+=1;
            }
        }while(aux!=0);
        return arregloParaLUE;
    }

    public void ordenarProcesos(ArrayList<proceso> arregloProceso) {
        proceso aux;
        for (int i = 0; i < arregloProceso.size(); i++) {
            for (int j = 0; j < arregloProceso.size(); j++) {
                if (arregloProceso.get(i).getTiempoLLegada() < arregloProceso.get(i).getTiempoLLegada()) {
                    aux = arregloProceso.get(i);
                    arregloProceso.set(i,arregloProceso.get(j));
                    arregloProceso.set(j,aux);
                }
            }
        }
    }

}