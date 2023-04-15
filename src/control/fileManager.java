package control;

import java.io.FileOutputStream;
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

        String[] fila = new String[] { "Proceso", "Tiempo de llegada", "Ráfaga", "Tiempo de arranque",
                "Tiempo de finalización", "Tiempo de retorno", "Tiempo de respuesta", "Tasa de desperdicio",
                "Tasa de penalización", "Tiempo de espera" };

        row = sheet.createRow(rowcounter);
        fillRow(fila, sheet, row);
        rowcounter++;

        for (int i = 0; i < planificadores.size(); i++) {
            planificador auxPlanificador = planificadores.get(i);

            fila = new String[] { auxPlanificador.getAlgoritmo(), "", "", "", "", "", "", "", "", "" };
            row = sheet.createRow(rowcounter);
            fillRow(fila, sheet, row);
            rowcounter++;

            for (int j = 0; j < auxPlanificador.getProcesosOrdenados().size(); j++) {

                proceso auxProceso = auxPlanificador.getProcesosOrdenados().get(j);

                fila = new String[] { (String)auxProceso.getId(), Integer.toString(auxProceso.getTiempoLLegada()), Integer.toString(auxProceso.getRafaga()),
                        Integer.toString(auxProceso.getTiempoArranque()), Integer.toString(auxProceso.getTiempoFinalizacion()),
                        Integer.toString(auxProceso.getTiempoRetorno()), Integer.toString(auxProceso.getTiempoRespuesta()), Integer.toString(auxProceso.getTasaDesperdicio()),
                        String.valueOf(auxProceso.getTasaPenalizacion()), Integer.toString(auxProceso.getTiempoEspera()) };
                row = sheet.createRow(rowcounter);
                fillRow(fila, sheet, row);
                rowcounter++;

            }
            fila = new String[] { "Tiempo total", "", "", "", "", Integer.toString(auxPlanificador.getTRetTotal()),
            Integer.toString(auxPlanificador.getTRespTotal()), Integer.toString(auxPlanificador.getTWTotal()), Integer.toString(auxPlanificador.getTPenTotal()),
            Integer.toString(auxPlanificador.getTEsperaTotal()) };
            row = sheet.createRow(rowcounter);
            fillRow(fila, sheet, row);
            rowcounter++;

            fila = new String[] { "Promedio", "", "", "", "", String.valueOf(auxPlanificador.getTRetPromedio()),
            String.valueOf(auxPlanificador.getTRespPromedio()), String.valueOf(auxPlanificador.getTWPromedio()),
            String.valueOf(auxPlanificador.getTPenPromedio()), String.valueOf(auxPlanificador.getTEsperaPromedio()) };
            row = sheet.createRow(rowcounter);
            fillRow(fila, sheet, row);
            rowcounter++;

            fila = new String[] { auxPlanificador.getAlgoritmo(), "", "", "", "", "", "", "", "", "" };
            row = sheet.createRow(rowcounter);
            fillRow(fila, sheet, row);
            rowcounter++;

        }

        try{
            FileOutputStream fileOut = new FileOutputStream("Planificadores.xlsx");
            workbook.write(fileOut);
            System.out.println("Archivo creado");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void fillRow(String[] aux, Sheet sheet, Row row) {

        for (int i = 0; i < aux.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue((String) aux[i]);
        }
    }

}
