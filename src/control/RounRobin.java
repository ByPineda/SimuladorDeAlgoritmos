package control;
import java.util.ArrayList;
import model.proceso;
public class RounRobin {

    public RounRobin();//Constructor

    //Metodo para implementar el planificador
    public ArrayList<String> RR(proceso[] arregloProceso) {
        String arregloParaLUE = new ArrayList<String>(); //Arreglo a devolver para la tabla LUE
        ordenarProcesos(arregloProceso); //Para ordenar el arreglo
        int empiezaArreglo = arregloProceso[0].getTiempoLlegada();//Para ver si este no esta vacio al principio
        int arrRafaga[arregloProceso.size()],i,j;
        for (i=0;i<arrRafaga;i++){//Para poner en el arreglo a imprimir un espacio en blanco para saber que ahi no va nada.
            arregloParaLUE[i]=" ";
        }
        for (i = 0; i < arregloProceso.size(); i++) {//Para manejar la rafaga y saber si aun hay rafaga o no;
            arrRafaga[i] = arregloProceso[i].getRafaga;
        }
        int aux=0,aux2=arregloProceso.size();
        do {
            if (aux==arregloProceso.size()){
                aux=0;
            }else{
                if (arrRafaga[aux]>0){
                    if (arrRafaga[aux]==arregloProceso[aux].getRafaga()){
                        arregloProceso[aux].setTiempoLLegada(empiezaArreglo);
                    }
                    if (arrRafaga>=4){
                        for (j=0;j<4;j++){
                            arregloParaLUE.add(arregloProceso[aux].getId());
                        }
                        empiezaArreglo+=4;
                        arrRafaga[aux]-=4;
                    }else{
                        for (j=0;j<arrRafaga[aux];j++){
                            arregloParaLUE.add(arregloProceso[aux].getId());
                        }
                        empiezaArreglo+=arrRafaga[aux]:
                        arregloProceso[aux].setTiempoFinalizacion(empiezaArreglo);
                        arrRafaga[aux]=0;
                    }
                }
                aux+=1;
            }
        }while(aux!=0);
        return arregloParaLUE;
    }

    public void ordenarProcesos(proceso[] arregloProceso) {
        proceso aux = new proceso();
        for (int i = 0; i < arregloProceso.size(); i++) {
            for (int j = 0; j < arregloProceso.size(); j++) {
                if (arregloProceso[i].getTiempoLlegada() < arregloProceso[j].getTiempoLlegada()) {
                    aux = arregloProceso[i];
                    arregloProceso[i] = arregloProceso[j];
                    arregloProceso[j] = aux;
                }
            }
        }
    }


}