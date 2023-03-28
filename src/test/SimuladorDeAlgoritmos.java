import control.*;
import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SimuladorDeAlgoritmos{
    /*
     * 
     * El código en este "main" es solo para probar el funcionamiento de los algoritmos.
     * SE DEBE DE QUITAR CUANDO SE INTEGRE CON LA INTERFAZ GRÁFICA.
     * 
     */
    public static void ClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) throws Exception {
        ArrayList<proceso> listaProcesos = new ArrayList<proceso>();
        int opc = 0, n = 0;
        Scanner input = new Scanner(System.in);
        while (opc != 6) {
            
            System.out.println("Escoge un alogirtmo de planificacion:");
            System.out.println("1. FIFO");
            System.out.println("2. Round Robin");
            System.out.println("3. SALIR");
            System.out.print("Elección -> ");
            opc = input.nextInt();
            switch (opc) {
                case 1:
                fifo fifo = new fifo();
                    ClearScreen();
                    System.out.println("FIFO------------------------");
                    System.out.print("Ingresa el numero de procesos: ");
                    n = input.nextInt();
                    
                    for (int i = 0; i < n; i++) {
                        ClearScreen();
                        System.out.println("Proceso " + (i + 1));
                        System.out.print("ID: ");
                        String id = input.next();
                        System.out.print("Tiempo de llegada: ");
                        int tiempoLLegada = input.nextInt();
                        System.out.print("Rafaga de CPU: ");
                        int rafaga = input.nextInt();
                        proceso p = new proceso(id, tiempoLLegada, rafaga);
                        listaProcesos.add(p);
                    }
                    listaProcesos = fifo.FCFS(listaProcesos, n);
                    
                    break;
                case 2:
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}

