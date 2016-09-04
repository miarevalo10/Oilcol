package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "sensores")
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

    @ManyToOne(cascade = CascadeType.ALL)
    private PozoEntity pozo;

    @OneToMany(mappedBy = "sensor",cascade = CascadeType.ALL)
    private ArrayList<RegistroEntity> registros;

    private TipoSensor tipo;

    public SensorEntity() {
        this.id=null;
    }

    public SensorEntity(Long id) {
        this();
        this.id = id;
    }

    public SensorEntity(Long id,TipoSensor x ) {
        this.id = id;
        this.tipo = x;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PozoEntity getPozo() {
        return pozo;
    }

    public void setPozo(PozoEntity pozo) {
        this.pozo = pozo;
    }

    public TipoSensor getTipo() {
        return tipo;
    }

    public void setTipo(TipoSensor tipo) {
        this.tipo = tipo;
    }

}
