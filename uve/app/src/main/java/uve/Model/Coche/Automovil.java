package uve.Model.Coche;

import uve.Model.Vehiculo;
import uve.Model.Taller.Reparacion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Automovil extends Vehiculo {


     private static final Logger logger = LogManager.getLogger(Automovil.class.getName());

    public Automovil() {
        super();

        logger.info("Creando un nuevo automóvil con valores por defecto");
        
    } 
    
    public Automovil(String marca, String modelo, String placa) throws Exception {
        super(marca, modelo, placa);
        
        logger.info("Automóvil, creando un nuevo automóvil con los siguientes valores: " +
                    "marca=" + marca + ", modelo=" + modelo +  ", placa=" + placa);


    }

    //Encender automovil
    @Override 
    public void encender() {
        System.out.println("El automovil " + marca + " " + modelo + " ha sido encendido.");
    
        
    }

    //Apagar automovil
    @Override
    public void apagar() {
        System.out.println("El automovil " + marca + " " + modelo + " ha sido apagado.");
    }

}
