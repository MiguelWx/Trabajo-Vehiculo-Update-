package uve.Model.Coche;

import uve.Model.Vehiculo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Camioneta extends Vehiculo {

     private static final Logger logger = LogManager.getLogger(Camioneta.class.getName());

    public Camioneta() {
        super();

        logger.info("Creando una nueva camioneta con valores por defecto");
    } 

    public Camioneta(String marca, String modelo, String placa) throws Exception {
        super(marca, modelo, placa);
       
        logger.info("Camioneta, creando una nueva camioneta con los siguientes valores: " +
                    "marca=" + marca + ", modelo=" + modelo +  ", placa=" + placa);

    }



}
