package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import control.RounRobin;
import control.almacen;
import control.fifo;
import control.fileManager;
import model.planificador;
import model.proceso;
import lib.*;

class TablaNoEditable extends DefaultTableModel {
    public TablaNoEditable() {
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

public class PlanificadorProcesosGUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    // Tabs
    private TablaNoEditable lueTableModel;
    private TablaNoEditable resultadosTableModel;
    private TablaNoEditable comparacionesTable;
    private DefaultTableModel procesosTableModel;

    // Tables
    private JTable procesosTable;
    private JTable resultadosTable;
    private JTable lueTable;
    private JTable comparaciones;

    // TextFields
    private JTextField procesoTxt, tiempoLlegadaTxt, rafagaTxt, quantumTxt;

    // Botones
    private JComboBox<String> metodoCmb;

    // Almacen
    private almacen almacen = new almacen();

    public PlanificadorProcesosGUI() {
        setTitle("Planificador de Procesos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        procesosTableModel = new DefaultTableModel();
        procesosTableModel.addColumn("Proceso");
        procesosTableModel.addColumn("Tiempo de llegada");
        procesosTableModel.addColumn("Ráfaga");
        procesosTableModel.addColumn("Quantum");
        procesosTable = new JTable(procesosTableModel);

        resultadosTableModel = new TablaNoEditable();
        resultadosTableModel.addColumn("Proceso");
        resultadosTableModel.addColumn("Tiempo de llegada");
        resultadosTableModel.addColumn("Ráfaga");
        resultadosTableModel.addColumn("Tiempo de arranque");
        resultadosTableModel.addColumn("Tiempo finalización");
        resultadosTableModel.addColumn("Tiempo retorno");
        resultadosTableModel.addColumn("Tiempo respuesta");
        resultadosTableModel.addColumn("Tasa desperdicio");
        resultadosTableModel.addColumn("Tasa penalización");
        resultadosTableModel.addColumn("Tiempo de espera");
        resultadosTable = new JTable(resultadosTableModel);

        lueTableModel = new TablaNoEditable();
        lueTableModel.addColumn(" ");
        lueTableModel.addColumn(" ");
        lueTableModel.addColumn(" ");
        lueTableModel.addColumn(" ");
        lueTableModel.addColumn(" ");
        lueTableModel.addColumn(" ");
        lueTableModel.addColumn(" ");
        lueTableModel.addColumn(" ");
        lueTableModel.addColumn(" ");
        lueTable = new JTable(lueTableModel);

        comparacionesTable = new TablaNoEditable();
        comparacionesTable.addColumn("Proceso");
        comparacionesTable.addColumn("Tiempo de llegada");
        comparacionesTable.addColumn("Ráfaga");
        comparacionesTable.addColumn("Tiempo de arranque");
        comparacionesTable.addColumn("Tiempo finalización");
        comparacionesTable.addColumn("Tiempo retorno");
        comparacionesTable.addColumn("Tiempo respuesta");
        comparacionesTable.addColumn("Tasa desperdicio");
        comparacionesTable.addColumn("Tasa penalización");
        comparacionesTable.addColumn("Tiempo de espera");
        comparaciones = new JTable(comparacionesTable);

        // //Ejemplo de como se ve la tabla de procesos
        // for (int i = 0; i < 5; i++) {
        // Object[] fila = new Object[4];
        // fila[0] = "Proceso " + (i + 1);
        // fila[1] = i * 2;
        // fila[2] = i * 3;
        // fila[3] = i * 4;
        // procesosTableModel.addRow(fila);
        // }

        procesoTxt = new JTextField(10);
        tiempoLlegadaTxt = new JTextField(10);
        rafagaTxt = new JTextField(10);
        quantumTxt = new JTextField(10);

        JButton agregarBtn = new JButton("Agregar Proceso");
        agregarBtn.addActionListener(this);

        JButton eliminarBtn = new JButton("Eliminar Proceso");
        eliminarBtn.addActionListener(this);

        JButton calcularBtn = new JButton("Calcular");
        calcularBtn.addActionListener(this);

        JButton limpiarBtn = new JButton("Limpiar");
        limpiarBtn.addActionListener(this);

        JButton guardarBtn = new JButton("Guardar");
        guardarBtn.addActionListener(this);

        JButton exportBtn = new JButton("Exportar");
        exportBtn.addActionListener(this);

        JPanel datosPanel = new JPanel(new FlowLayout());
        datosPanel.add(procesoTxt);
        datosPanel.add(tiempoLlegadaTxt);
        datosPanel.add(rafagaTxt);
        datosPanel.add(quantumTxt);

        String[] metodos = { "FIFO", "Round Robin" };
        metodoCmb = new JComboBox<>(metodos);
        metodoCmb.setSelectedIndex(0);
        JPanel metodoPanel = new JPanel(new FlowLayout());
        metodoPanel.add(metodoCmb);

        JPanel botonesPanel = new JPanel(new FlowLayout());
        botonesPanel.add(agregarBtn);
        botonesPanel.add(eliminarBtn);
        botonesPanel.add(calcularBtn);
        botonesPanel.add(limpiarBtn);
        botonesPanel.add(guardarBtn);
        botonesPanel.add(exportBtn);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Procesos", new JScrollPane(procesosTable));
        tabbedPane.addTab("Resultados", new JScrollPane(resultadosTable));
        tabbedPane.addTab("LUE", new JScrollPane(lueTable));
        tabbedPane.addTab("Comparaciones", new JScrollPane(comparaciones));

        JPanel principalPanel = new JPanel(new BorderLayout());
        principalPanel.add(tabbedPane, BorderLayout.CENTER);
        principalPanel.add(datosPanel, BorderLayout.NORTH);
        principalPanel.add(metodoPanel, BorderLayout.WEST);
        principalPanel.add(botonesPanel, BorderLayout.SOUTH);

        setContentPane(principalPanel);
        TextPrompt placeholder = new TextPrompt("Nombre de Proceso", procesoTxt);
        TextPrompt placeholder2 = new TextPrompt("Tiempo de llegada", tiempoLlegadaTxt);
        TextPrompt placeholder3 = new TextPrompt("Ráfaga", rafagaTxt);
        TextPrompt placeholder4 = new TextPrompt("Quantum", quantumTxt);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        if (accion.equals("Agregar Proceso")) {
            agregarProceso();
        } else if (accion.equals("Eliminar Proceso")) {
            eliminarProceso();
        } else if (accion.equals("Calcular")) {
            if (almacen.getArregloProcesos().size() == 0) {
                JOptionPane.showMessageDialog(this, "No hay procesos para calcular");
            } else {
                if (((String) metodoCmb.getSelectedItem()).equals("Round Robin")) {
                    ArrayList<String> arregloParaLUE = new ArrayList<String>();
                    RounRobin RR = new RounRobin();
    
                    /*
                     * Falta refactorizar el código para que no se repita tanto, pero por ahora lo
                     * dejamos asi.
                     */
                    arregloParaLUE = RR.RR(almacen.getArregloProcesos(), almacen.getQuantum());
                    almacen.setTablaLue(arregloParaLUE);
                    almacen.setAlgoritmo("RR");
    
                    for (int i = 0; i < almacen.getArregloProcesos().size(); i++) {
                        Object[] fila = new Object[10];
                        fila[0] = almacen.getArregloProcesos().get(i).getId();
                        fila[1] = almacen.getArregloProcesos().get(i).getTiempoLLegada();
                        fila[2] = almacen.getArregloProcesos().get(i).getRafaga();
                        fila[3] = almacen.getArregloProcesos().get(i).getTiempoArranque();
                        fila[4] = almacen.getArregloProcesos().get(i).getTiempoFinalizacion();
                        fila[5] = almacen.getArregloProcesos().get(i).getTiempoRetorno();
                        fila[6] = almacen.getArregloProcesos().get(i).getTiempoRespuesta();
                        fila[7] = almacen.getArregloProcesos().get(i).getTasaDesperdicio();
                        fila[8] = almacen.getArregloProcesos().get(i).getTasaPenalizacion();
                        fila[9] = almacen.getArregloProcesos().get(i).getTiempoEspera();
                        resultadosTableModel.addRow(fila);
                    }
    
                    poblarTablaLUE(almacen.getArregloProcesos(), arregloParaLUE);
                } else {
                    fifo fifo = new fifo();
    
                    ArrayList<String> arregloParaLUE = new ArrayList<String>();
                    arregloParaLUE = fifo.FCFS(almacen.getArregloProcesos(), almacen.getArregloProcesos().size());
                    almacen.setTablaLue(arregloParaLUE);
                    almacen.setAlgoritmo("FIFO");
                    for (int i = 0; i < almacen.getArregloProcesos().size(); i++) {
                        Object[] fila = new Object[10];
                        fila[0] = almacen.getArregloProcesos().get(i).getId();
                        fila[1] = almacen.getArregloProcesos().get(i).getTiempoLLegada();
                        fila[2] = almacen.getArregloProcesos().get(i).getRafaga();
                        fila[3] = almacen.getArregloProcesos().get(i).getTiempoArranque();
                        fila[4] = almacen.getArregloProcesos().get(i).getTiempoFinalizacion();
                        fila[5] = almacen.getArregloProcesos().get(i).getTiempoRetorno();
                        fila[6] = almacen.getArregloProcesos().get(i).getTiempoRespuesta();
                        fila[7] = almacen.getArregloProcesos().get(i).getTasaDesperdicio();
                        fila[8] = almacen.getArregloProcesos().get(i).getTasaPenalizacion();
                        fila[9] = almacen.getArregloProcesos().get(i).getTiempoEspera();
                        resultadosTableModel.addRow(fila);
    
                    }
                    almacen.obtenerPromedios();
                    Object[] fila = new Object[10];
                    fila[0] = "Tiempo Total:";
                    fila[1] = "";
                    fila[2] = "";
                    fila[3] = "";
                    fila[4] = "";
                    fila[5] = almacen.getTRetTotal();
                    fila[6] = almacen.getTRespTotal();
                    fila[7] = almacen.getTWTotal();
                    fila[8] = almacen.getTPenTotal();
                    fila[9] = almacen.getTEsperaTotal();
                    resultadosTableModel.addRow(fila);
                    fila[0] = "Promedio:";
                    fila[1] = "";
                    fila[2] = "";
                    fila[3] = "";
                    fila[4] = "";
                    fila[5] = almacen.getTRetPromedio();
                    fila[6] = almacen.getTRespPromedio();
                    fila[7] = almacen.getTWPromedio();
                    fila[8] = almacen.getTPenPromedio();
                    fila[9] = almacen.getTEsperaPromedio();
                    resultadosTableModel.addRow(fila);
    
                    poblarTablaLUE(almacen.getArregloProcesos(), arregloParaLUE);
                    JOptionPane.showMessageDialog(this, "FIFO Aplicado. Revisa la tabla de resultados y LUE");
                }
            }
        } else if (accion.equals("Limpiar")) {
            if (almacen.getArregloProcesos().size() == 0) {
                JOptionPane.showMessageDialog(this, "No hay procesos para limpiar");
            } else {
                JOptionPane.showMessageDialog(this, "Limpiando");
                limpiar();
            }
        } else if (accion.equals("Guardar")) {
            if (almacen.getArregloProcesos().size() == 0) {
                JOptionPane.showMessageDialog(this, "No hay procesos para guardar");
            } else {
                guardarPlanificador();
            }

        } else if(accion.equals("Exportar")){
            if (almacen.getPlanificadores().size() == 0) {
                JOptionPane.showMessageDialog(this, "No hay procesos para exportar");
            } else {
                exportarPlanificador(almacen.getPlanificadores());
            }
        }

        

    }

    // METODOS PARA AGREGAR Y ELIMINAR
    // PROCESOS--------------------------------------
    private void agregarProceso() {
        String proceso = procesoTxt.getText();
        int tiempoLlegada = 0;
        int rafaga = 0;
        int quantum = 0;

        if (proceso.equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del proceso");
            return;
        }

        try {
            tiempoLlegada = Integer.parseInt(tiempoLlegadaTxt.getText());
            if (tiempoLlegada < 0) {
                JOptionPane.showMessageDialog(this, "El tiempo de llegada debe ser un número entero positivo");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El tiempo de llegada debe ser un número entero positivo");
            return;
        }

        try {
            rafaga = Integer.parseInt(rafagaTxt.getText());
            if (rafaga <= 0) {
                JOptionPane.showMessageDialog(this, "La ráfaga debe ser un número entero positivo");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La ráfaga debe ser un número entero positivo");
            return;
        }

        if (metodoCmb.getSelectedItem().equals("Round Robin")) {
            try {
                quantum = Integer.parseInt(quantumTxt.getText());
                almacen.setQuantum(quantum);
                if (quantum <= 0) {
                    JOptionPane.showMessageDialog(this, "El quantum debe ser un número entero positivo");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El quantum debe ser un número entero positivo");
                return;
            }
        }

        // Insertar proceso en el
        // almacén-------------------------------------------------------
        proceso procAux = new proceso(proceso, tiempoLlegada, rafaga);
        almacen.getArregloProcesos().add(procAux);

        // Insertar proceso en la
        // tabla---------------------------------------------------------

        Object[] fila = new Object[4];
        fila[0] = proceso;
        fila[1] = tiempoLlegada;
        fila[2] = rafaga;
        fila[3] = quantum;
        procesosTableModel.addRow(fila);

        procesoTxt.setText("");
        tiempoLlegadaTxt.setText("");
        rafagaTxt.setText("");
        quantumTxt.setText("");
    }

    // METODO PARA POBLAR LA TABLA LUE PARA
    // FIFO------------------------------------------
    private void poblarTablaLUE(ArrayList<proceso> arregloProcesos, ArrayList<String> arregloLUE) {
        int tiempoDeRafagasTotales = 0;
        int tablasLue = 0;

        // CALCULAMOS EL NUMERO DE TABLAS QUE NECESITAMOS
        for (int i = 0; i < arregloProcesos.size(); i++) {
            tiempoDeRafagasTotales += arregloProcesos.get(i).getRafaga();
        }

        if (tiempoDeRafagasTotales % 8 != 0) {
            tiempoDeRafagasTotales += tiempoDeRafagasTotales + (8 - (tiempoDeRafagasTotales % 8));
        }

        tablasLue = (tiempoDeRafagasTotales / 8) + 5;

        // CREAMOS LA TABLA
        Object fila[] = new Object[9];
        for (int i = 0; i < fila.length; i++) {
            fila[i] = " ";
        }

        for (int i = 0; i < (tablasLue * 3); i++) {
            lueTableModel.addRow(fila);
        }

        // POBLAMOS LA COLUMNA DE LUE

        for (int i = 0; i < lueTableModel.getRowCount(); i += 3) {
            lueTableModel.setValueAt("L", i, 0);
            lueTableModel.setValueAt("U", i + 1, 0);
            lueTableModel.setValueAt("E", i + 2, 0);
        }

        // POBLAMOS LA FILA DEL CENTRO - U
        int contador = 0;
        int contadorRow = 1;
        for (int i = 0; i < tablasLue; i++) {
            contador = poblarU(contadorRow, contador);
            contadorRow += 3;
        }

        // POBLAMOS LA FILA DE ARRIBA - L

        contador = 0;
        contadorRow = 0;

        for (int i = 0; i < tablasLue; i++) {
            poblarL(contadorRow, contador, arregloProcesos);
            contadorRow += 3;
        }

        // POBLAMOS LA FILA DE ABAJO - E
        int start = arregloProcesos.get(0).getTiempoLLegada();
        contador = 0;
        contadorRow = 2;
        boolean flag = false;
        for (int i = 0; i < tablasLue; i++) {
            flag = poblarE(contadorRow, contador, arregloLUE, arregloProcesos, start, flag);
            contadorRow += 3;
        }
    }

    private boolean poblarE(int row, int contador, ArrayList<String> arregloLue, ArrayList<proceso> arregloProcesos,
            int start, boolean flag) {

        int valor;
        String id;

        for (int i = 1; i < lueTableModel.getColumnCount(); i++) {
            if (i % 2 != 0) {
                valor = Integer.parseInt(lueTableModel.getValueAt(row - 1, i).toString());
                if (valor < arregloLue.size()) {

                    id = arregloLue.get(valor);
                    lueTableModel.setValueAt(id, row, i + 1);
                    contador += 1;

                }
            }
        }

        return flag;

    }

    private int poblarU(int row, int contador) {

        for (int i = 1; i < lueTableModel.getColumnCount(); i++) {
            if (i % 2 == 0) {
            } else {
                lueTableModel.setValueAt(contador, row, i);
                contador++;
            }
        }
        return contador;
    }

    private int poblarL(int row, int contador, ArrayList<proceso> arregloProcesos) {
        int aux = 0;
        int valor;
        for (int i = 1; i < lueTableModel.getColumnCount(); i++) {
            if (i % 2 != 0) {
                valor = Integer.parseInt(lueTableModel.getValueAt(row + 1, i).toString());
                for (int j = contador; j < arregloProcesos.size(); j++) {
                    if (arregloProcesos.get(j).getTiempoLLegada() == valor && aux == 0) {
                        lueTableModel.setValueAt(arregloProcesos.get(j).getId(), row, i);
                        contador += 1;
                        aux += 1;
                    } else if (aux == 1 && valor == arregloProcesos.get(j).getTiempoLLegada()) {
                        contador += 1;
                    }
                }
                aux = 0;
            }
        }
        return contador;
    }

    private void eliminarProceso() {
        int filaSeleccionada = procesosTable.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proceso para eliminar");
            return;
        }
        procesosTableModel.removeRow(filaSeleccionada);
        almacen.getArregloProcesos().remove(filaSeleccionada);

    }

    private void limpiar() {
        try {
            int numeroDeFilas = procesosTableModel.getRowCount();
            int filasLues = lueTableModel.getRowCount();

            for (int i = numeroDeFilas - 1; i >= 0; i--) {
                procesosTableModel.removeRow(i);
                resultadosTableModel.removeRow(i);
            }
            for (int i = filasLues - 1; i >= 0; i--) {
                lueTableModel.removeRow(i);
            }
            almacen.limpiar();

        } catch (Exception e) {
            System.out.println("Error al limpiar");

        }

    }

    private void guardarPlanificador() {
        almacen.guardarPlanificador();
        try {
            int numeroDeFilas = procesosTableModel.getRowCount();
            int filasLues = lueTableModel.getRowCount();
            int filasResultados = resultadosTableModel.getRowCount();
            int filasComparaciones = comparacionesTable.getRowCount();

            for (int i = numeroDeFilas - 1; i >= 0; i--) {
                procesosTableModel.removeRow(i);
            }
            for (int i = filasResultados - 1; i >= 0; i--) {
                resultadosTableModel.removeRow(i);
            }
            for (int i = filasLues - 1; i >= 0; i--) {
                lueTableModel.removeRow(i);
            }
            for (int i = filasComparaciones - 1; i >= 0; i--) {
                comparacionesTable.removeRow(i);
            }

        } catch (Exception e) {
            System.out.println("Error al limpiar");

        }
        poblarComparaciones();

    }

    private void poblarComparaciones() {

        for (int i = 0; i < almacen.getPlanificadores().size(); i++) {
            planificador elemento = almacen.getPlanificadores().get(i);
            Object[] fila = new Object[10];
            fila[0] = elemento.getAlgoritmo();
            for (int l = 1; l < 9; l++) {
                fila[l] = "";
            }
            comparacionesTable.addRow(fila);

            for (int k = 0; k < elemento.getProcesosOrdenados().size(); k++) {
                fila[0] = elemento.getProcesosOrdenados().get(k).getId();
                fila[1] = elemento.getProcesosOrdenados().get(k).getTiempoLLegada();
                fila[2] = elemento.getProcesosOrdenados().get(k).getRafaga();
                fila[3] = elemento.getProcesosOrdenados().get(k).getTiempoArranque();
                fila[4] = elemento.getProcesosOrdenados().get(k).getTiempoFinalizacion();
                fila[5] = elemento.getProcesosOrdenados().get(k).getTiempoRetorno();
                fila[6] = elemento.getProcesosOrdenados().get(k).getTiempoRespuesta();
                fila[7] = elemento.getProcesosOrdenados().get(k).getTasaDesperdicio();
                fila[8] = elemento.getProcesosOrdenados().get(k).getTasaPenalizacion();
                fila[9] = elemento.getProcesosOrdenados().get(k).getTiempoEspera();
                comparacionesTable.addRow(fila);
            }
            fila[0] = "Tiempo Total:";
            fila[1] = "";
            fila[2] = "";
            fila[3] = "";
            fila[4] = "";
            fila[5] = elemento.getTRetTotal();
            fila[6] = elemento.getTRespTotal();
            fila[7] = elemento.getTWTotal();
            fila[8] = elemento.getTPenTotal();
            fila[9] = elemento.getTEsperaTotal();
            comparacionesTable.addRow(fila);
            fila[0] = "Promedio:";
            fila[1] = "";
            fila[2] = "";
            fila[3] = "";
            fila[4] = "";
            fila[5] = elemento.getTRetPromedio();
            fila[6] = elemento.getTRespPromedio();
            fila[7] = elemento.getTWPromedio();
            fila[8] = elemento.getTPenPromedio();
            fila[9] = elemento.getTEsperaPromedio();
            comparacionesTable.addRow(fila);

            for (int j = 0; j < fila.length; j++) {
                fila[j] = "********";
            }
            comparacionesTable.addRow(fila);
        }

    }

    public void exportarPlanificador(ArrayList<planificador> arrayIn){
        fileManager fm = new fileManager();
        fm.escribirExcel(arrayIn);
    }

}