package uve.Model.Taller;

import java.util.ArrayList;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uve.Model.Vehiculo;

public class Taller {
    private String sede;
    private int numeroCeldasDisp;
    private ArrayList<Vehiculo> listaVehiculos;

    private static final Logger logger = LogManager.getLogger(Taller.class.getName());

    public Taller() throws Exception {
        try {
            this.sede = "Taller Default";
            this.numeroCeldasDisp = 50; // Inicializar con 50 celdas disponibles
            this.listaVehiculos = new ArrayList<>();


        } catch (Exception e) {
            throw new Exception("Error al crear el taller: " + e.getMessage(), e);
        }
    }

    public Taller(String sede, int numeroCeldasDisp) throws Exception {
       
        try {
            if (sede == null || sede.isEmpty()) {
                throw new IllegalArgumentException("El nombre del taller no puede ser nulo o vacío");
            }
            if (numeroCeldasDisp < 0) {
                throw new IllegalArgumentException("El número de celdas disponibles no puede ser negativo");
            }

        this.sede = sede;
        this.numeroCeldasDisp = numeroCeldasDisp;
        this.listaVehiculos = new ArrayList<>();

        } catch (Exception e) {
            throw new Exception("Error al crear el taller: " + e.getMessage(), e);
        }

    }

    public void OcuparTaller(Vehiculo vehiculo) throws Exception {
        try {
            if(this.numeroCeldasDisp > 0){
            listaVehiculos.add(vehiculo);
            
            this.numeroCeldasDisp--;
            logger.info("Vehículo " + vehiculo.getMarca() + " " + vehiculo.getModelo() + " ha sido agregado al taller.");
        }
            else{
                logger.info(" No hay celdas disponibles en el taller para agregar el vehículo: " + vehiculo.getMarca() + " " + vehiculo.getModelo());
                throw new IllegalArgumentException("No hay celdas disponibles en el taller");
            }
           
            

        } catch (Exception e) {
            logger.error("Error al agregar el vehículo al taller: " + e.getMessage(), e);
            throw new Exception("Error al agregar el vehículo al taller: " + e.getMessage(), e);
        }
       
    }

    public String getSede() {
        return sede;
    }

    public int getNumeroCeldasDisp() {
        return numeroCeldasDisp;
    }

    public void setNumeroCeldasDisp(int numeroCeldasDisp) {
        if (numeroCeldasDisp < 0) {
            throw new IllegalArgumentException("El número de celdas disponibles no puede ser negativo");
        }
        this.numeroCeldasDisp = numeroCeldasDisp;
    }    
    
}
