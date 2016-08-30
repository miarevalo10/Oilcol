package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "sensor")
public class SensorEntity extends Model
{
    public static Finder<Long,SensorEntity> FINDER = new Finder<>(SensorEntity.class);

    public enum TipoSensor
    {
        /**
         * El sensor de temperatura de la bomba registra la información cada segundo.
         */
        TEMPERATURA,
        /**
         * El sensor de consumo energético actualiza la información cada 1, 5, 15, 30 o 60 minutos según el encargado.
         */
        CONSUMO_ENERGETICO,
        /**
         * El sensor de la cantidad de barriles de crudo y fluido actualiza la información cada 1, 3, 5, 10, 15, 30 min, 1, 2, 4, 6, 12, 24 horas.
         */
        CAUDAL,
        /**
         * El sensor de la cantidad de barriles de crudo y fluido actualiza la información cada 1, 3, 5, 10, 15, 30 min, 1, 2, 4, 6, 12, 24 horas.
         */
        EMERGENCIA
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Sensor")
    private Long id;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.DATE)
    private Calendar createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Calendar updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    private PozoEntity pozo;

    /**
     * Se define como la suma de barriles de crudo (petróleo) y fluido (principalmente agua) diarios que la bomba de
     un equipo de producción extrae del pozo.
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

    public SensorEntity() {
        this.id=null;
    }

    public SensorEntity(Long id) {
        this();
        this.id = id;
    }

    public SensorEntity(Long id, double caudal,  double consumoEnergia, double temperatura ) {
        this.id = id;
        this.caudal = caudal;
        this.consumoEnergia = consumoEnergia;
        this.temperatura = temperatura;
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

    @PreUpdate
    private void updateTimestamp() {
        this.updatedAt = Calendar.getInstance();
    }

    @PrePersist
    private void creationTimestamp() {
        this.createdAt = this.updatedAt = Calendar.getInstance();
    }

}
