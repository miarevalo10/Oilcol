package controllers;

/**
 * Created by maria on 30/08/2016.
 */

import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;

import akka.dispatch.MessageDispatcher;
import models.PozoEntity;
import models.SensorEntity;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import javax.inject.Inject;
import play.libs.concurrent.HttpExecutionContext;

public class SensorController extends Controller
{

    @Inject HttpExecutionContext ec;


    public CompletionStage<Result> getSensores(Long idPozo)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return SensorEntity.FINDER.where().eq("pozo_id",idPozo).findList();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        sensorEntities -> {
                            return ok(toJson(sensorEntities));
                        }
                );
    }

    public CompletionStage<Result> getSensor(Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {

                            return SensorEntity.FINDER.byId(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        sensorEntities -> {
                            return ok(toJson(sensorEntities));
                        }
                );
    }

    public CompletionStage<Result> createSensor(Long idPozo)
    {
        JsonNode nSensor = request().body().asJson();
        SensorEntity  sensor = Json.fromJson( nSensor , SensorEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    PozoEntity p = PozoEntity.FINDER.byId(idPozo);
                    sensor.setPozo(p);
                    sensor.save();
                    return sensor;
                }
        ).thenApply(
                sensorEntity -> {
                    return ok(Json.toJson(sensorEntity));
                }
        );
    }

    public CompletionStage<Result> deleteSensor(Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {

                            SensorEntity.FINDER.deleteById(id);
                            return id;
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        sensorEntities -> {
                            return ok(toJson(sensorEntities));
                        }
                );
    }

    public CompletionStage<Result> updateSensor(Long id)
    {
        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode nSensor = request().body().asJson();
                            SensorEntity s = Json.fromJson( nSensor , SensorEntity.class ) ;
                            SensorEntity sPorActualizar =  SensorEntity.FINDER.byId(id);

                            sPorActualizar.setTipo(s.getTipo());

                            sPorActualizar.update();

                            return sPorActualizar;
                        }
                        ,ec.current())
                .thenApply(
                        sensorEntity -> {
                            return ok(toJson(sensorEntity));
                        }
                );
    }


}
