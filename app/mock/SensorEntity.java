package mock;

import models.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by hj.calderon10 on 30/08/2016.
 */
public class SensorEntity
{
    public final static int CAUDAL = 1;
    public final static int CONSUMO_ENERGIA = 2;
    public final static int TEMPERATURA = 3;
    public final static int EMERGENCIA = 4;

    private int id;

    private int pozo;

    private ArrayList<Registro> registros;

    private ArrayList<SensorEntity> listaSensores;

    private String tipo;

    private class Registro {
        private Calendar createdAt;
        private Calendar updatedAt;
        /**
         * Se define como la suma de barriles de crudo (petróleo) y fluido (principalmente agua) diarios que la bomba de
         * un equipo de producción extrae del pozo.
         * * El consumo diario de energía de la bomba dado en kWh (kilowatts-hora).
         * La temperatura en °C (grados centígrados () del generador principal encargado de energizar la bomba.
         */
        private double registro;

        public Registro(Calendar createdAt, Calendar updatedAt, double registro, int tipo)
        {
            this.registro = registro;

            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

       public double get(){return registro;}
       public void set(double n){registro= n;}

        public Calendar getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Calendar createdAt) {
            this.createdAt = createdAt;
        }

        public Calendar getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Calendar updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

    public SensorEntity(int id, int idPozo)
    {
        registros = new ArrayList<>();
        this.id = id;
        this.pozo = idPozo;
    }

    public void llenarSensores()
    {
        for (int i = 1; i <= 4; i++)
        {
            int j = 0;
            listaSensores.add(new SensorEntity(i, ++j));
        }
    }

    public void agregarRegistro(Calendar createdAt, Calendar updatedAt, int tipo, double registro, int idSensor)
    {
        for (int i = 0; i < listaSensores.size(); i++)
        {
            if(listaSensores.get(i).getId() == idSensor)
            {
                listaSensores.get(i).getRegistros().add(new Registro(createdAt, updatedAt, registro, tipo));
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPozo() {
        return pozo;
    }

    public void setPozo(int pozo) {
        this.pozo = pozo;
    }

    public ArrayList<SensorEntity> getListaSensores() {
        return listaSensores;
    }

    public void setListaSensores(ArrayList<SensorEntity> listaSensores) {
        this.listaSensores = listaSensores;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }

    public double getUltimo(){return (registros.get((registros.size()-1))).get();}
}
