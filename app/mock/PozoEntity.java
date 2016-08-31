package mock;

import java.util.ArrayList;

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

    private SensorEntity sensorCaudal;
    private SensorEntity sensorTemperatura;
    private SensorEntity sensorConsumo;
    private SensorEntity sensorEmergencia;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCampo(){return idCampo;}

    public void setIdCampo(Long id){this.idCampo=id;}

    public double getCaudal() { return sensorCaudal.getUltimo(); }

    public void setCaudal(SensorEntity caudal) { this.sensorCaudal = caudal; }

    public double getConsumoEnergia() { return sensorConsumo.getUltimo(); }

    public void setConsumoEnergia(SensorEntity consumoEnergia) { this.sensorConsumo = consumoEnergia; }

    public double getTemperatura() { return sensorTemperatura.getUltimo(); }

    public void setTemperatura(SensorEntity temperatura) { this.sensorTemperatura = temperatura; }

    public EstadoPozo getEstado() { return estado; }

    public void setEstado(EstadoPozo estado) { this.estado = estado; }

    public double getEmergencia() { return sensorEmergencia.getUltimo(); }

    public void setEmergencia(SensorEntity emergencia) { this.sensorEmergencia = emergencia; }

    public String toString() {
        return "PozoEntity{" +
                "id=" + id +
                "idCampo= " + idCampo+
                ", caudal ='" + getCaudal() +
                ", consumo de energía=" + getConsumoEnergia() +
                ", temperatura =" + getTemperatura() +
                ", estado=" + estado +
                '}';
    }

    public static ArrayList<PozoEntity> lista = new ArrayList<>();


}
