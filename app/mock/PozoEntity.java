package mock;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hj.calderon10 on 30/08/2016.
 */
public class PozoEntity {

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

    private Long id;

    private Long idCampo;

//    private SensorEntity sensorCaudal;
//    private SensorEntity sensorTemperatura;
//    private SensorEntity sensorConsumo;
//    private SensorEntity sensorEmergencia;


    //    /**
//     * Se define como la suma de barriles de crudo (petróleo) y fluido (principalmente agua) diarios que la bomba de
//     un equipo de producción extrae del pozo.
//     */
//    private double caudal;
//
//    /**
//     * El consumo diario de energía de la bomba dado en kWh (kilowatts-hora).
//     */
//    private double consumoEnergia;
//
//    /**
//     * La temperatura en °C (grados centígrados) del generador principal encargado de energizar la bomba.
//     */
//    private double temperatura;
//
//
    private EstadoPozo estado;

//    /**
//     * Ocurrencia de una emergencia en pozo, que pueden ser: incendio, bloqueo en pozo y daño eléctrico.
//     */
//    private EmergenciaPozo emergencia;


    public PozoEntity() {
        this.id=null;
    }

    public PozoEntity(Long id) {
        this();
        this.id = id;
    }

    public PozoEntity(Long id, EstadoPozo estado ) {
        this.id = id;
        this.estado = estado;
    }

    public PozoEntity(Long idCampo, Long id, EstadoPozo estado){
        this.idCampo = idCampo;
        this.id = id;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCampo(){return idCampo;}

    public void setIdCampo(Long id){this.idCampo=id;}
//
//    public double getCaudal() { return sensorCaudal.getUltimo(); }
//
//    public void setCaudal(SensorEntity caudal) { this.sensorCaudal = caudal; }
//
//    public double getConsumoEnergia() { return sensorConsumo.getUltimo(); }
//
//    public void setConsumoEnergia(SensorEntity consumoEnergia) { this.sensorConsumo = consumoEnergia; }
//
//    public double getTemperatura() { return sensorTemperatura.getUltimo(); }
//
//    public void setTemperatura(SensorEntity temperatura) { this.sensorTemperatura = temperatura; }

    public EstadoPozo getEstado() { return estado; }

    public void setEstado(EstadoPozo estado) { this.estado = estado; }

//    public double getEmergencia() { return sensorEmergencia.getUltimo(); }
//
//    public void setEmergencia(SensorEntity emergencia) { this.sensorEmergencia = emergencia; }

    public String toString() {
        return "PozoEntity{" +
                "id=" + id +
                "idCampo= " + idCampo+
                //", caudal ='" + sensorCaudal.getUltimo() +
                //", consumo de energía=" + sensorConsumo.getUltimo() +
                //", temperatura =" + sensorTemperatura.getUltimo() +
                ", estado=" + estado +
                '}';
    }

    public static List<PozoEntity> lista = new ArrayList<>();

    public static void invariante(){
        if(lista.size()==0){
            lista.add(new PozoEntity(new Long(1), new Long(1), EstadoPozo.ABIERTO));
            lista.add(new PozoEntity(new Long(2), new Long(2), EstadoPozo.PARADO));
            lista.add(new PozoEntity(new Long(3), new Long(3), EstadoPozo.ABIERTO));
            lista.add(new PozoEntity(new Long(1), new Long(4), EstadoPozo.ABIERTO));
            lista.add(new PozoEntity(new Long(1), new Long(5), EstadoPozo.PRODUCCION));
            lista.add(new PozoEntity(new Long(2), new Long(6), EstadoPozo.PRODUCCION));
            lista.add(new PozoEntity(new Long(1), new Long(7), EstadoPozo.ABIERTO));
        }
    }

    public static List<PozoEntity> get(Long idCampo){
        invariante();
        List<PozoEntity> rta = new ArrayList<>();
        for (int i = 0; i<lista.size(); i++){
            PozoEntity actual = lista.get(i);
            if(actual.getIdCampo().equals(idCampo)) {
                rta.add(actual);
            }
        }
        return rta;
    }

    public static PozoEntity getAlone(Long id){
        invariante();
        PozoEntity rta = null;
        for(int i = 0; i< lista.size() && rta == null;i++){
            PozoEntity actual = lista.get(i);
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
            PozoEntity actual = lista.get(i);
            if(actual.getId().equals(id)){
                x = i;
            }
        }
        return x;
    }

    public static void delete(Long id){
        PozoEntity x = getAlone(id);
        lista.remove(x);
    }



}
