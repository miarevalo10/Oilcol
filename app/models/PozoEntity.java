package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "pozos")
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

    @OneToMany(mappedBy = "pozo",cascade = CascadeType.ALL)
    private ArrayList<SensorEntity> sensores;

    private EstadoPozo estado;

    @ManyToOne(cascade = CascadeType.ALL)
    private CampoEntity campo;

    public PozoEntity() {
        this.id=null;
    }

    public PozoEntity(Long id) {
        this();
        this.id = id;
    }

    public PozoEntity(Long id, EstadoPozo estado, CampoEntity c ) {
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

    public EstadoPozo getEstado() { return estado; }

    public void setEstado(EstadoPozo estado)
    {
//        if(estado != EstadoPozo.CLAUSURADO)
        this.estado = estado;
    }

    public CampoEntity getCampo() {
        return campo;
    }

    public void setCampo(CampoEntity campo) {
        this.campo = campo;
    }
//    public EmergenciaPozo getEmergencia() { return emergencia; }
//
//    public void setEmergencia(EmergenciaPozo emergencia) { this.emergencia = emergencia; }

}
