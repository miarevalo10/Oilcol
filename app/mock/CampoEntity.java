package mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hj.calderon10 on 30/08/2016.
 */
public class CampoEntity {


    private Long id;

    private List<PozoEntity> pozos;

    public CampoEntity()
    {
        pozos = new ArrayList<>();
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


    public static ArrayList<PozoEntity> lista = new ArrayList<>();



}
