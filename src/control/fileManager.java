package control;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.planificador;
import model.proceso;

public class fileManager {
    public fileManager() {
    }

    public void escribirExcel(ArrayList<planificador> planificadores) {
        Workbook workbook = new XSSFWorkbook(); // Crea un nuevo archivo de Excel en formato XLSX
        Sheet sheet = workbook.createSheet("Planificadores");
        int rowcounter = 0;
        Row row;

        Object[] fila = new Object[] { "Proceso", "Tiempo de llegada", "Ráfaga", "Tiempo de arranque",
                "Tiempo de finalización", "Tiempo de retorno", "Tiempo de respuesta", "Tasa de desperdicio",
                "Tasa de penalización", "Tiempo de espera" };

        row = sheet.createRow(rowcounter);
        fillRow(fila, sheet, row);
        rowcounter++;

        for (int i = 0; i < planificadores.size(); i++) {
            planificador auxPlanificador = planificadores.get(i);

            fila = new Object[] { auxPlanificador.getAlgoritmo(), "", "", "", "", "", "", "", "", "" };
            row = sheet.createRow(rowcounter);
            fillRow(fila, sheet, row);
            rowcounter++;

            for (int j = 0; j < auxPlanificador.getProcesosOrdenados().size(); j++) {

                proceso auxProceso = auxPlanificador.getProcesosOrdenados().get(j);

                fila = new Object[] { auxProceso.getId(), auxProceso.getTiempoLLegada(), auxProceso.getRafaga(),
                        auxProceso.getTiempoArranque(), auxProceso.getTiempoFinalizacion(),
                        auxProceso.getTiempoRetorno(), auxProceso.getTiempoRespuesta(), auxProceso.getTasaDesperdicio(),
                        auxProceso.getTasaPenalizacion(), auxProceso.getTiempoEspera() };
                row = sheet.createRow(rowcounter);
                fillRow(fila, sheet, row);
                rowcounter++;

            }
            fila = new Object[] { "Tiempo total", "", "", "", "", auxPlanificador.getTRetTotal(),
                    auxPlanificador.getTRespTotal(), auxPlanificador.getTWTotal(), auxPlanificador.getTPenTotal(),
                    auxPlanificador.getTEsperaTotal() };
            row = sheet.createRow(rowcounter);
            fillRow(fila, sheet, row);
            rowcounter++;

            fila = new Object[] { "Promedio", "", "", "", "", auxPlanificador.getTRetPromedio(),
                    auxPlanificador.getTRespPromedio(), auxPlanificador.getTWPromedio(),
                    auxPlanificador.getTPenPromedio(), auxPlanificador.getTEsperaPromedio() };
            row = sheet.createRow(rowcounter);
            fillRow(fila, sheet, row);
            rowcounter++;

            fila = new Object[] { auxPlanificador.getAlgoritmo(), "", "", "", "", "", "", "", "", "" };
            row = sheet.createRow(rowcounter);
            fillRow(fila, sheet, row);
            rowcounter++;

        }

    }

    public void fillRow(Object[] aux, Sheet sheet, Row row) {

        for (int i = 0; i < aux.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue((String) aux[i]);
        }
    }

    /*
     * 
     * 
     * Workbook workbook = new XSSFWorkbook(); // Crea un nuevo archivo de Excel en
     * formato XLSX
     * Sheet sheet = workbook.createSheet("Planificadores");
     * int rowcounter = 0;
     * 
     * Object[] fila = new Object[]{"Proceso", "Tiempo de llegada", "Ráfaga",
     * "Tiempo de arranque","Tiempo de finalización","Tiempo de retorno"
     * ,"Tiempo de respuesta","Tasa de desperdicio",
     * "Tasa de penalización","Tiempo de espera"};
     * Row row = sheet.createRow(rowcounter);
     * for (int i = 0; i < fila.length; i++) {
     * Cell cell = row.createCell(i);
     * cell.setCellValue((String) fila[i]);
     * }
     * rowcounter++;
     * 
     * for(int i = 0; i < planificadores.size(); i++ ){
     * planificador auxPlanificador = planificadores.get(i);
     * row = sheet.createRow(rowcounter);
     * fila = new Object[]{auxPlanificador.getAlgoritmo(), "", "", "","","","","",
     * "",""};
     * for (int j = 0; j < fila.length; j++) {
     * Cell cell = row.createCell(j);
     * cell.setCellValue((String) fila[j]);
     * }
     * rowcounter++;
     * for(int j = 0; j < auxPlanificador.getProcesosOrdenados().size(); j++){
     * proceso auxProceso = auxPlanificador.getProcesosOrdenados().get(j);
     * row = sheet.createRow(rowcounter);
     * fila = new Object[]{auxProceso.getId(), auxProceso.getTiempoLLegada(),
     * auxProceso.getRafaga(),
     * auxProceso.getTiempoArranque(),auxProceso.getTiempoFinalizacion(),auxProceso.
     * getTiempoRetorno(),auxProceso.getTiempoRespuesta(),auxProceso.
     * getTasaDesperdicio(),
     * auxProceso.getTasaPenalizacion(),auxProceso.getTiempoEspera()};
     * for (int k = 0; k < fila.length; k++) {
     * Cell cell = row.createCell(k);
     * cell.setCellValue((String) fila[k]);
     * }
     * rowcounter++;
     * 
     * fila = new Object[]{"Tiempo total", "", "",
     * "","",auxPlanificador.getTRetTotal(),auxPlanificador.getTRespTotal(),
     * auxPlanificador.getTWTotal(),
     * auxPlanificador.getTPenTotal(),auxPlanificador.getTEsperaTotal()};
     * 
     * }
     * 
     * }
     * 
     * 
     */
}
