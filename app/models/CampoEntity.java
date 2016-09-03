package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ls.hernandez10 on 30/08/2016.
 */
@Entity
@Table(name = "campo")
public class CampoEntity extends Model {
    public static Model.Finder<Long, CampoEntity> FINDER = new Model.Finder<>(CampoEntity.class);

    public enum RegionCampo {
        Andina, Pacifica, Orinoquia, Amazonia, Caribe;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Campo")
    private Long idCampo;

    private RegionCampo region;

    public CampoEntity() {

    }

    public CampoEntity (Long id, RegionCampo region){
        this.region = region;
        this.idCampo = id;
    }

    public Long getId() {
        return idCampo;
    }

    public void setId(Long id) {
        this.idCampo = id;
    }


    public RegionCampo getRegion(){return region;}

    public void setRegion(RegionCampo region){this.region = region;}
}

