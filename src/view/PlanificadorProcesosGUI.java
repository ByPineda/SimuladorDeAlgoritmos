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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import control.RounRobin;
import control.almacen;
import control.fifo;
import model.proceso;
import lib.*;

public class PlanificadorProcesosGUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private DefaultTableModel lueTableModel;
    private DefaultTableModel procesosTableModel;
    private JTable procesosTable;
    private DefaultTableModel resultadosTableModel;
    private JTable resultadosTable;
    private JTable lueTable;
    private JTextField procesoTxt, tiempoLlegadaTxt, rafagaTxt, quantumTxt;
    private JComboBox<String> metodoCmb;
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

        resultadosTableModel = new DefaultTableModel();
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

        lueTableModel = new DefaultTableModel();
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

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Procesos", new JScrollPane(procesosTable));
        tabbedPane.addTab("Resultados", new JScrollPane(resultadosTable));
        tabbedPane.addTab("LUE", new JScrollPane(lueTable));

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
            // Aquí puedes implementar la lógica de cálculo y llenar la tabla de resultados
            if (((String) metodoCmb.getSelectedItem()).equals("Round Robin")) {
                ArrayList<String> arregloParaLUE = new ArrayList<String>();
                RounRobin RR = new RounRobin();
                arregloParaLUE = RR.RR(almacen.getArregloProcesos(), almacen.getQuantum());
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
                poblarTablaLUE_FIFO(almacen.getArregloProcesos(), arregloParaLUE);
            } else {
                fifo fifo = new fifo();

                ArrayList<String> arregloParaLUE = new ArrayList<String>();
                arregloParaLUE = fifo.FCFS(almacen.getArregloProcesos(), almacen.getArregloProcesos().size());
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
                poblarTablaLUE_FIFO(almacen.getArregloProcesos(), arregloParaLUE);
                JOptionPane.showMessageDialog(this, "FIFO Aplicado. Revisa la tabla de resultados y LUE");
            }
        } else if (accion.equals("Limpiar")) {
            JOptionPane.showMessageDialog(this, "Limpiando");
            limpiar();
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
        // DEBUG CODE System.out.println("Proceso " +
        // almacen.getArregloProcesos().get(0).getId() + " agregado");

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
    private void poblarTablaLUE_FIFO(ArrayList<proceso> arregloProcesos, ArrayList<String> arregloLUE) {
        int tiempoDeRafagasTotales = 0;
        int tablasLue = 0;

        // CALCULAMOS EL NUMERO DE TABLAS QUE NECESITAMOS
        for (int i = 0; i < arregloProcesos.size(); i++) {
            tiempoDeRafagasTotales += arregloProcesos.get(i).getRafaga();
        }

        if (tiempoDeRafagasTotales % 8 != 0) {
            tiempoDeRafagasTotales += tiempoDeRafagasTotales + (8 - (tiempoDeRafagasTotales % 8));
        }

        tablasLue = (tiempoDeRafagasTotales / 8) + 3;

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
            contador = poblarUdeLue(contadorRow, contador);
            contadorRow += 3;
        }

        // POBLAMOS LA FILA DE ARRIBA - L

        contador = 0;
        contadorRow = 0;

        for (int i = 0; i < tablasLue; i++) {
            poblarLdeLue(contadorRow, contador, arregloProcesos);
            contadorRow += 3;
        }

        // POBLAMOS LA FILA DE ABAJO - E
        contador = 0;
        contadorRow = 2;

        for (int i = 0; i < tablasLue; i++) {
            tablaE(contadorRow, contador, arregloLUE);
            contadorRow += 3;
        }

    }

    private int tablaE(int row, int contador, ArrayList<String> arregloProcesos) {
        int valor;
        String id;

        for (int i = 1; i < lueTableModel.getColumnCount(); i++) {
            if (i % 2 != 0) {
                valor = Integer.parseInt(lueTableModel.getValueAt(row - 1, i).toString());
                if (valor < arregloProcesos.size()) {

                    id = arregloProcesos.get(valor);
                    lueTableModel.setValueAt(id, row, i + 1);
                    contador += 1;

                }
            }
        }
        return contador;

    }

    private int poblarUdeLue(int row, int contador) {

        for (int i = 1; i < lueTableModel.getColumnCount(); i++) {
            if (i % 2 == 0) {
            } else {
                lueTableModel.setValueAt(contador, row, i);
                contador++;
            }
        }
        return contador;
    }

    private int poblarLdeLue(int row, int contador, ArrayList<proceso> arregloProcesos) {
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
        // DEBUG CODE System.out.println("Proceso " +
        // almacen.getArregloProcesos().get(0).getId() + " agregado");
    }

    private void limpiar() {
        try {
            int numeroDeFilas = procesosTableModel.getRowCount();
            int filasLues = lueTableModel.getRowCount();

            for (int i = numeroDeFilas; i >= 0; i--) {
                procesosTableModel.removeRow(i);
                resultadosTableModel.removeRow(i);
            }
            for(int i = filasLues-1; i >= 0; i--){
                lueTableModel.removeRow(i);
            }

        } catch (Exception e) {
            System.out.println("Error al limpiar");

        }

    }

    class TransposedTableModel extends AbstractTableModel {
        private DefaultTableModel originalModel;

        public TransposedTableModel(DefaultTableModel originalModel) {
            this.originalModel = originalModel;
        }

        @Override
        public int getRowCount() {
            return originalModel.getColumnCount();
        }

        @Override
        public int getColumnCount() {
            return originalModel.getRowCount();
        }

        @Override
        public Object getValueAt(int row, int column) {
            return originalModel.getValueAt(column, row);
        }

        @Override
        public void setValueAt(Object aValue, int row, int column) {
            originalModel.setValueAt(aValue, column, row);
        }

        @Override
        public String getColumnName(int column) {
            return "LUE" + (column + 1);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return originalModel.getRowCount() > 0 ? originalModel.getValueAt(0, columnIndex).getClass() : Object.class;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return originalModel.isCellEditable(column, row);
        }

        @Override
        public void addTableModelListener(javax.swing.event.TableModelListener l) {
            originalModel.addTableModelListener(l);
        }

        @Override
        public void removeTableModelListener(javax.swing.event.TableModelListener l) {
            originalModel.removeTableModelListener(l);
        }
    }
}