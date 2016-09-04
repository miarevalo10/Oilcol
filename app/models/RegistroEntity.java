package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by maria on 4/09/2016.
 */

@Entity
@Table(name = "registros")
public class RegistroEntity extends Model
{
    public static Model.Finder<Long, RegistroEntity> FINDER = new Model.Finder<>(RegistroEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Registro")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private SensorEntity sensor;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.DATE)
    private Calendar createdAt;

    private double medida;

    public RegistroEntity() {
        this.id=null;
    }

    public RegistroEntity(Long id) {
        this();
        this.id = id;
    }

    public RegistroEntity(Long id,double medida ) {
        this.id = id;
        this.medida= medida;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SensorEntity getSensor() {
        return sensor;
    }

    public void setSensor(SensorEntity sensor) {
        this.sensor = sensor;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public double getMedida() {
        return medida;
    }

    public void setMedida(double medida) {
        this.medida = medida;
    }

    @PrePersist
    private void creationTimestamp() {
        this.createdAt = Calendar.getInstance();
    }









}
