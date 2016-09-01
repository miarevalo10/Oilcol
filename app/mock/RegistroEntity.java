package mock;

import java.util.*;

/**
 * Created by hj.calderon10 on 31/08/2016.
 */
public class RegistroEntity {

//    private Date createdAt;
    private String updatedAt;
    /**
     * Se define como la suma de barriles de crudo (petróleo) y fluido (principalmente agua) diarios que la bomba de
     * un equipo de producción extrae del pozo.
     * * El consumo diario de energía de la bomba dado en kWh (kilowatts-hora).
     * La temperatura en °C (grados centígrados () del generador principal encargado de energizar la bomba.
     */
    private double registro;

    private Long id;

    private Long idSensor;


    public RegistroEntity(Long idSensor,Long id, String updatedAt, double registro)
    {
        this.id = id;
        this.idSensor = idSensor;
        this.registro = registro;
//        this.updatedAt = updatedAt;
    }

    public double get(){return registro;}
    public void set(double n){registro= n;}

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public Long getIdSensor(){return idSensor;}
    public void setIdSensor(Long idSensor){
        this.idSensor = idSensor;
    }

//    public Date getCreatedAt() {
//        return createdAt;
//    }

//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


//    public void agregarRegistro(Calendar createdAt, Calendar updatedAt, int tipo, double registro, int idSensor)
//    {
//        for (int i = 0; i < listaSensores.size(); i++)
//        {
//            if(listaSensores.get(i).getId() == idSensor)
//            {
//                listaSensores.get(i).getRegistros().add(new Registro(createdAt, updatedAt, registro, tipo));
//            }
//        }
//    }

    public static ArrayList<RegistroEntity> lista = new ArrayList<>();


    public static void invariante(){
        if(lista.size()==0){
            lista.add(new RegistroEntity(new Long(1), new Long(1), "31/08/2016", 81));
            lista.add(new RegistroEntity(new Long(1), new Long(2), "25/08/2016", 15));
            lista.add(new RegistroEntity(new Long(1), new Long(3), "2/05/2016", 50));
            lista.add(new RegistroEntity(new Long(1), new Long(4), "15/04/2016", 35));
            lista.add(new RegistroEntity(new Long(2), new Long(5), "25/10/2016", 105));
            lista.add(new RegistroEntity(new Long(2), new Long(6), "20/10/2016", 125));
            lista.add(new RegistroEntity(new Long(3), new Long(7), "20/01/2016", 500));
        }
    }

    public static List<RegistroEntity> getAll(Long idSensor){
        invariante();
        List<RegistroEntity> rta = new ArrayList<>();
        for (int i = 0; i<lista.size(); i++){
            RegistroEntity actual = lista.get(i);
            if(actual.getIdSensor().equals(idSensor)) {
                rta.add(actual);
            }
        }
        return rta;
    }

    public static RegistroEntity getRegistro(Long id){
        invariante();
        RegistroEntity rta = null;
        for(int i=0; i<lista.size() && rta == null;i++){
            RegistroEntity actual = lista.get(i);
            if(actual.getId().equals(id))
                rta = actual;
        }
        return rta;
    }


    public void save(){
        lista.add(this);
    }

    public void update(){
        int x = buscarIndice(this.getId());
        lista.set(x, this);
    }

    public static int buscarIndice(Long id){
        int x = -1;
        for(int i=0;i<lista.size();i++){
            RegistroEntity actual = lista.get(i);
            if(actual.getId().equals(id)){
                x = i;
            }
        }
        return x;
    }

    public static void delete(Long id){
        RegistroEntity x = getRegistro(id);
        lista.remove(x);
    }

}
