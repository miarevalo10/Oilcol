package mock;

import models.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by hj.calderon10 on 30/08/2016.
 */
public class SensorMock
{
    public final static int CAUDAL = 1;
    public final static int CONSUMO_ENERGIA = 2;
    public final static int TEMPERATURA = 3;
    public final static int EMERGENCIA = 4;

    private int id;

    private int pozo;

    private ArrayList<Registro> registros;

    private ArrayList<SensorMock> listaSnsores;

    private String tipo;

    private class Registro {
        private Calendar createdAt;
        private Calendar updatedAt;
        /**
         * Se define como la suma de barriles de crudo (petróleo) y fluido (principalmente agua) diarios que la bomba de
         * un equipo de producción extrae del pozo.
         */
        private double caudal;

        /**
         * El consumo diario de energía de la bomba dado en kWh (kilowatts-hora).
         */
        private double consumoEnergia;

        /**
         * La temperatura en °C (grados centígrados) del generador principal encargado de energizar la bomba.
         */
        private double temperatura;

        private double emergencia;

        public Registro(Calendar createdAt, Calendar updatedAt, double registro, int tipo)
        {
            if(tipo==CAUDAL)caudal = registro;
            if(tipo==CONSUMO_ENERGIA)consumoEnergia = registro;
            if(tipo==TEMPERATURA)temperatura = registro;
            if(tipo==EMERGENCIA)emergencia = registro;

            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public double getCaudal() {
            return caudal;
        }

        public void setCaudal(double caudal) {
            this.caudal = caudal;
        }

        public double getConsumoEnergia() {
            return consumoEnergia;
        }

        public void setConsumoEnergia(double consumoEnergia) {
            this.consumoEnergia = consumoEnergia;
        }

        public double getTemperatura() {
            return temperatura;
        }

        public void setTemperatura(double temperatura) {
            this.temperatura = temperatura;
        }

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

        public double getEmergencia() {
            return emergencia;
        }

        public void setEmergencia(double emergencia) {
            this.emergencia = emergencia;
        }
    }

    public SensorMock(int id, int idPozo)
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
            listaSnsores.add(new SensorMock(i, ++j));
        }
    }

    public void agregarRegistro(Calendar createdAt, Calendar updatedAt, int tipo, double registro, int idSensor)
    {
        for (int i = 0; i < listaSnsores.size(); i++)
        {
            if(listaSnsores.get(i).getId() == idSensor)
            {
                listaSnsores.get(i).getRegistros().add(new Registro(createdAt, updatedAt, registro, tipo));
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

    public ArrayList<SensorMock> getListaSnsores() {
        return listaSnsores;
    }

    public void setListaSnsores(ArrayList<SensorMock> listaSnsores) {
        this.listaSnsores = listaSnsores;
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
}
