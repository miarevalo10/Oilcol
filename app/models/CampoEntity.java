¿
        .0package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ls.hernandez10 on 30/08/2016.
 */
@Entity
@Table(name = "campoentity")
public class CampoEntity extends Model
{
    public static Model.Finder<Long,CampoEntity> FINDER = new Model.Finder<>(CampoEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Campo")
    private Long id;

    private List<PozoEntity> pozos;

    public CampoEntity()
    {
        pozos = new ArrayList<>(); //TODO
    }

    public CampoEntity(Long id)
    {
        this();
        this.id = id;
    }

    public CampoEntity (List<PozoEntity> pozos)
    {
        this.pozos = pozos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PozoEntity> getPozos() {
        return pozos;
    }

    public void setPozos(List<PozoEntity> pozos) {
        this.pozos = pozos;
    }
}
