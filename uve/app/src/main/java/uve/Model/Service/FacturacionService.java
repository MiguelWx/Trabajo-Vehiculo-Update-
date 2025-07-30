package uve.Model.Service;

import uve.Model.Vehiculo;
import uve.Model.Taller.Reparacion;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class FacturacionService  {

      private static final String rutaArchivo = "logs/";  
      private static final Logger logger = LogManager.getLogger(FacturacionService.class.getName());

      

    public static void generarFacturaVehiculo(Vehiculo vehiculo) throws Exception {
    try {
        // Limpiar logs previos
        LogCollector.clear();

        // Logs y mensaje inicial
        StringBuilder registroLogs = new StringBuilder();

        String log1 = "Generando factura al vehiculo: " + vehiculo.getPlaca() +
                      " Cantidad de Reparaciones: " + vehiculo.getReparacion().size();
        logger.info(log1);
        LogCollector.add(log1);
        registroLogs.append(log1).append("\n");

        //  costos
        int costoTotalReparaciones = calcularCostoTotalReparaciones(vehiculo);

        // Mensaje de factura base
        String mensajeReparaciones = "Factura del Vehiculo: " + vehiculo.getPlaca() + "\n" +
                                     "Marca: " + vehiculo.getMarca() + "\n" +
                                     "Modelo: " + vehiculo.getModelo() + "\n" +
                                     "Reparaciones:\n";

        // Detalles de reparaciones
        for (Reparacion reparacion : vehiculo.getReparacion()) {
            mensajeReparaciones += "- Descripción: " + reparacion.getDescripcion() +
                                   ", Costo: " + reparacion.getCosto() +
                                   ", Fecha: " + reparacion.getFechaReparacion() +
                                   ", Pieza: " + reparacion.getPieza() + "\n";
        }

        // Log adicional
        String log2 = "Reparaciones traspasadas al historial del vehiculo: " + vehiculo.getPlaca();
        logger.info(log2);
        LogCollector.add(log2);
        registroLogs.append(log2).append("\n");

        String logFinal = "Factura generada exitosamente para el vehiculo: " + vehiculo.getPlaca();
        logger.info(logFinal);
        LogCollector.add(logFinal);

        // Obtener todos los logs acumulados
        String logs = LogCollector.getLogs();

        // Generar el archivo de factura
        generarArchivo(vehiculo, mensajeReparaciones, costoTotalReparaciones, logs);
        
    } catch (Exception e) {
        throw new Exception("Error al generar la factura del vehiculo: " + vehiculo.getPlaca() + "\n " + e.getMessage(), e);
    }
}

        
        
    

private static void generarArchivo(Vehiculo vehiculo, String mensajeReparaciones, int costoTotalReparaciones, String logs) throws Exception {
    try {
        logger.info("Generando archivo de factura para el vehiculo: " + vehiculo.getPlaca());

        Date fechaActual = new Date(System.currentTimeMillis());
        FileWriter fileWriter = new FileWriter(rutaArchivo + "factura_" + vehiculo.getPlaca() + "_" + fechaActual + ".txt");

        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(mensajeReparaciones + "\n\nTotal: " + costoTotalReparaciones);
            printWriter.println("\n--- Registro de logs del proceso ---\n");
            printWriter.println(logs);
        }

        logger.info("Archivo de factura generado exitosamente para el vehiculo: " + vehiculo.getPlaca());

    } catch (Exception e) {
        throw new Exception("Error al generar el archivo de la factura del vehiculo: " + vehiculo.getPlaca() + "\n " + e.getMessage(), e);
    }
}


   public class LogCollector {
    private static StringBuilder logs = new StringBuilder();

    private LogCollector() {

    }

    public static void add(String mensaje) {
        logs.append(mensaje).append("\n");
    }
    
    public static String getLogs() {
        if (logs.length() == 0) {
            return "No logs available.";
        }
        return logs.toString();
    }

    public static void clear() {
        logs.setLength(0); // limpia los logs acumulados
    }
}



    private static int calcularCostoTotalReparaciones(Vehiculo vehiculo) {
        int costoTotal = 0;
        for (Reparacion reparacion : vehiculo.getReparacion()) {
            costoTotal += reparacion.getCosto();
        }
        return costoTotal;
    }
}
