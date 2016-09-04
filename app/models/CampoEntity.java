package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ls.hernandez10 on 30/08/2016.
 */
@Entity
@Table(name = "campos")
public class CampoEntity extends Model {

    public static Model.Finder<Long, CampoEntity> FINDER = new Model.Finder<>(CampoEntity.class);

    public enum RegionCampo {
        Andina, Pacifica, Orinoquia, Amazonia, Caribe;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Campo")
    private Long id;

    private RegionCampo region;

   @OneToMany(mappedBy = "campo",cascade = CascadeType.ALL)
    private ArrayList<PozoEntity> pozos;

    public CampoEntity() {

    }

    public CampoEntity (Long id, RegionCampo region){
        this.region = region;
        this.id = id;
    }

    public Long getIdCampo() {
        return id;
    }

    public void setIdCampo(Long id) {
        this.id = id;
    }

    public RegionCampo getRegion(){return region;}

    public void setRegion(RegionCampo region){this.region = region;}
}

