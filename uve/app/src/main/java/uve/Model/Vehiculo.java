package uve.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

import uve.Model.Coche.Interfaz.InterfazCoche;
import uve.Model.Service.FacturacionService.LogCollector;
import uve.Model.Taller.Reparacion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class Vehiculo implements InterfazCoche {

    protected String marca;
    protected String modelo;
    protected String placa;
    protected ArrayList<Reparacion> listaReparaciones;
    protected ArrayList<Reparacion> listaHistoricoReparaciones;
    
    private static final Logger logger = LogManager.getLogger(Vehiculo.class.getName());

       //Constructor vacio
    public Vehiculo() {
        this.marca = "";
        this.modelo = "";
        this.placa = "";
        this.listaReparaciones = new ArrayList<>();
        this.listaHistoricoReparaciones = new ArrayList<>();
     
    }

    public Vehiculo(String marca, String modelo, String placa) throws Exception {
        try {
              if (marca == null || modelo == null || placa == null) {
            throw new IllegalArgumentException("Marca, modelo o placa no pueden ser nulos");
             
        }

        if (marca.isEmpty() || modelo.isEmpty() || placa.isEmpty()) {
            throw new IllegalArgumentException("Marca, modelo o placa no pueden estar vacíos");
        }

        if (placa.length() != 7) {
            throw new IllegalArgumentException("La placa debe tener exactamente 7 caracteres");
        }

        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.listaReparaciones = new ArrayList<>();
        this.listaHistoricoReparaciones = new ArrayList<>();
        
        } catch (Exception e) {
            throw new Exception("Error al crear el vehiculo: " + e.getMessage(), e);
        }
      
    }

    @Override
    public void encender() {
        System.out.println("El vehículo " + marca + " " + modelo + " ha sido encendido.");
    }

    @Override
    public void apagar() {
        System.out.println("El vehículo " + marca + " " + modelo + " ha sido apagado.");
    }

     public String getMarca() {
        return marca;
        }
    
    public String getModelo(){
        return modelo;
    }    

    public String getPlaca() {
        return placa;
    }

    public ArrayList<Reparacion> getReparacion() {
        return listaReparaciones;
        }

    public ArrayList<Reparacion> getHistoricoReparaciones() {
        return listaHistoricoReparaciones;
    }   

    //Historia de eparacion
    public void agregarReparacionHistoria(Reparacion reparacion) {
        if (reparacion == null) {
            throw new IllegalArgumentException("La reparación no puede ser nula");
        }
        listaReparaciones.add(reparacion);
    }

    //paso de reparaciones al histórico
    public void ReparacionesAHistorico() {
        if (listaReparaciones.isEmpty()) {
            logger.info("No hay reparaciones para pasar al histórico.");
            
            return;
        }
        listaHistoricoReparaciones.addAll(listaReparaciones);
        listaReparaciones.clear();
        logger.info("Todas las reparaciones han sido pasadas al histórico.");
        
    }

}
    