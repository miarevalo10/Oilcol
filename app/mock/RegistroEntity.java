package mock;

import java.util.Calendar;

/**
 * Created by hj.calderon10 on 31/08/2016.
 */
public class RegistroEntity {

    private Calendar createdAt;
    private Calendar updatedAt;
    /**
     * Se define como la suma de barriles de crudo (petróleo) y fluido (principalmente agua) diarios que la bomba de
     * un equipo de producción extrae del pozo.
     * * El consumo diario de energía de la bomba dado en kWh (kilowatts-hora).
     * La temperatura en °C (grados centígrados () del generador principal encargado de energizar la bomba.
     */
    private double registro;

    public RegistroEntity(Calendar createdAt, Calendar updatedAt, double registro, int tipo)
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

}
