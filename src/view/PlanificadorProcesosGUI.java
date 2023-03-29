package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        resultadosTableModel.addColumn("Tiempo de arranque");
        resultadosTableModel.addColumn("Tiempo finalización");
        resultadosTableModel.addColumn("Tiempo retorno");
        resultadosTableModel.addColumn("Tiempo respuesta");
        resultadosTableModel.addColumn("Tasa desperdicio");
        resultadosTableModel.addColumn("Tasa penalización");
        resultadosTableModel.addColumn("Tiempo de espera");
        resultadosTable = new JTable(resultadosTableModel);

        lueTable = new JTable(new TransposedTableModel(resultadosTableModel));

        //Ejemplo de como se ve la tabla de procesos
        for (int i = 0; i < 5; i++) {
            Object[] fila = new Object[4];
            fila[0] = "Proceso " + (i + 1);
            fila[1] = i * 2;
            fila[2] = i * 3;
            fila[3] = i * 4;
            procesosTableModel.addRow(fila);
        }

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

        JPanel datosPanel = new JPanel(new FlowLayout());
        datosPanel.add(procesoTxt);
        datosPanel.add(tiempoLlegadaTxt);
        datosPanel.add(rafagaTxt);
        datosPanel.add(quantumTxt);

        String[] metodos = {"FIFO", "Round Robin"};
        metodoCmb = new JComboBox<>(metodos);
        metodoCmb.setSelectedIndex(0);
        JPanel metodoPanel = new JPanel(new FlowLayout());
        metodoPanel.add(metodoCmb);

        JPanel botonesPanel = new JPanel(new FlowLayout());
        botonesPanel.add(agregarBtn);
        botonesPanel.add(eliminarBtn);
        botonesPanel.add(calcularBtn);

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
        }
    }

    //METODOS PARA AGREGAR Y ELIMINAR PROCESOS--------------------------------------
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
                if (quantum <= 0) {
                    JOptionPane.showMessageDialog(this, "El quantum debe ser un número entero positivo");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El quantum debe ser un número entero positivo");
                return;
            }
        }

        

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

    private void eliminarProceso() {
        int filaSeleccionada = procesosTable.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proceso para eliminar");
            return;
        }
        procesosTableModel.removeRow(filaSeleccionada);
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