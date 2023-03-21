import java.util.ArrayList;
import model.proceso;
public class RounRobin

    public RounRobin();//Constructor

    //Metodo para implementar el planificador
    public void RR(proceso[] arregloProceso){
        ordenarProcesos(arregloProceso);

    }

    public void ordenarProcesos(proceso[] arregloProceso)
        {
            proceso aux= new proceso();
            for (int i = 0; i < arregloProceso.size(); i++)
            {
                for (int j = 0; j < arregloProceso.size(); j++)
                {
                    if (arregloProceso[i].tiempollegada < arregloProceso[j].rafaga)
                    {
                        aux = arregloProceso[i];
                        arregloProceso[i] = arregloProceso[j];
                        arregloProceso[j] = aux;
                    }
                }
            }
        }
}