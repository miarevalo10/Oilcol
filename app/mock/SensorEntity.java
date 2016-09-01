package mock;

import models.*;
import sun.management.Sensor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hj.calderon10 on 30/08/2016.
 */
public class SensorEntity
{

    public enum TipoSensor
    {
        Temperatura, Caudal, Emergencia, ConsumoEnergia;
    }

    private Long id;

    private Long idPozo;

    private TipoSensor tipo;


    public SensorEntity(Long id, Long idPozo, TipoSensor x)
    {
        this.id = id;
        this.idPozo = idPozo;
        this.tipo = x;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPozo() {
        return idPozo;
    }

    public void setIdPozo(Long pozo) {
        this.idPozo = pozo;
    }

    public TipoSensor getTipo() {
        return tipo;
    }

    public void setTipo(TipoSensor tipo) {
        this.tipo = tipo;
    }

    public static ArrayList<SensorEntity> lista = new ArrayList<>();


//    public static void invariante(){
//        if(lista.size()==0){
//            lista.add(new PozoEntity(new Long(1), new Long(1), EstadoPozo.ABIERTO));
//            lista.add(new PozoEntity(new Long(2), new Long(2), EstadoPozo.PARADO));
//            lista.add(new PozoEntity(new Long(3), new Long(3), EstadoPozo.ABIERTO));
//            lista.add(new PozoEntity(new Long(1), new Long(4), EstadoPozo.ABIERTO));
//            lista.add(new PozoEntity(new Long(1), new Long(5), EstadoPozo.PRODUCCION));
//            lista.add(new PozoEntity(new Long(2), new Long(6), EstadoPozo.PRODUCCION));
//            lista.add(new PozoEntity(new Long(1), new Long(7), EstadoPozo.ABIERTO));
//        }
//    }

    public static List<SensorEntity> getAll(Long idPozo){
//        invariante();
        List<SensorEntity> rta = new ArrayList<>();
        for (int i = 0; i<lista.size(); i++){
            SensorEntity actual = lista.get(i);
            if(actual.getIdPozo().equals(idPozo)) {
                rta.add(actual);
            }
        }
        return rta;
    }

    public static SensorEntity get(Long id){
//        invariante();
        SensorEntity rta = null;
        for(int i=0; i<lista.size() && rta == null;i++){
            SensorEntity actual = lista.get(i);
            if(actual.getId().equals(id))
                rta = actual;
        }
        return rta;
    }


    public void save(){
        lista.add(this);
    }


}
