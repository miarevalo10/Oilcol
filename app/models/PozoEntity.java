package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "pozo")
public class PozoEntity extends Model {

    public static Finder<Long,PozoEntity> FINDER = new Finder<>(PozoEntity.class);

    public enum EstadoPozo
    {
        /**
         * Abierto: pozo perforado que no tiene equipo de extracción asignado.
         * Producción: pozo perforado del cual se extrae petróleo.
         * Parado: pozo que no produce crudo debido a una falla.
         * Clausurado: pozo que cumplió su ciclo de producción.
         */
        ABIERTO, PRODUCCION, PARADO, CLAUSURADO;
    }

    public enum EmergenciaPozo
    {
        INCENDIO, BLOQUEO_POZO, DANIO_ELECTRICO;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Pozo")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<SensorEntity> sensores;


//    /**
//     * Se define como la suma de barriles de crudo (petróleo) y fluido (principalmente agua) diarios que la bomba de
//     un equipo de producción extrae del pozo.
//     */
//    private double caudal;
//
//    /**
//     * El consumo diario de energía de la bomba dado en kWh (kilowatts-hora).
//     */
//    private double consumoEnergia;
//
//    /**
//     * La temperatura en °C (grados centígrados) del generador principal encargado de energizar la bomba.
//     */
//    private double temperatura;
//
//
    private EstadoPozo estado;

//    /**
//     * Ocurrencia de una emergencia en pozo, que pueden ser: incendio, bloqueo en pozo y daño eléctrico.
//     */
//    private EmergenciaPozo emergencia;


    public PozoEntity() {
        this.id=null;
    }

    public PozoEntity(Long id) {
        this();
        this.id = id;
    }

    public PozoEntity(Long id, EstadoPozo estado ) {
        this.id = id;
        this.estado = estado;
        sensores = new ArrayList<SensorEntity>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  //  public double getCaudal() { return sensor.getCaudal(); }

//    public void setCaudal(double caudal) { this.caudal = caudal; }

    //public double getConsumoEnergia() { return sensor.getConsumoEnergia(); }

//    public void setConsumoEnergia(double consumoEnergia) { this.consumoEnergia = consumoEnergia; }

   // public double getTemperatura() { return sensor.getTemperatura(); }

//    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }

    public EstadoPozo getEstado() { return estado; }

    public void setEstado(EstadoPozo estado)
    {
        if(estado != EstadoPozo.CLAUSURADO)this.estado = estado;
    }

//    public SensorEntity getSensor(){return sensor;}

//    public EmergenciaPozo getEmergencia() { return emergencia; }
//
//    public void setEmergencia(EmergenciaPozo emergencia) { this.emergencia = emergencia; }

    //@Override
   // public String toString() {
     //   return "PozoEntity{" +
       //         "id=" + id +
         //       ", caudal ='" + getCaudal() +
           //     ", consumo de energía=" + getConsumoEnergia() +
             //   ", temperatura =" + getTemperatura() +
               // ", estado=" + estado +
                //'}';
    //}
}
