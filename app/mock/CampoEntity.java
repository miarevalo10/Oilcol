package mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hj.calderon10 on 30/08/2016.
 */
public class CampoEntity {

    public enum RegionCampo
    {
        Andina, Pacifica, Orinoquia, Amazonia, Caribe;
    }

    private Long id;

    private List<PozoEntity> pozos;

    private RegionCampo region;

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

    public CampoEntity (Long id, RegionCampo region){
        this.region = region;
        pozos = new ArrayList<>();
        this.id = id;
    }


    public RegionCampo getRegion(){return region;}

    public void setRegion(RegionCampo region){this.region = region;}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public static ArrayList<CampoEntity> lista = new ArrayList<>();

    public static int buscar(Long id){
        boolean found = false;
        int rta = -1;
        for (int i = 0; i<lista.size() && !found;i++){
            CampoEntity actual = lista.get(i);
            if(actual.getId()==id){
                rta = i;
                found = true;
            }
        }
        return rta;
    }

    public static CampoEntity get(Long id){
        int i = buscar(id);
        CampoEntity rta = null;
        if(i>-1)
            rta = lista.get(i);
        return rta;
    }

    public static boolean delete(Long id){
        int rta = buscar(id);
        boolean rta2 = false;
        if(rta > -1) {
            lista.remove(rta);
            rta2 = true;
        }
        return rta2;
    }

    public void save(){
        lista.add(this);
    }
    public void update(){
        int i = buscar(this.id);
        lista.set(i, this);
    }



}
