package controllers;

/**
 * Created by maria on 30/08/2016.
 */

import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;

import akka.dispatch.MessageDispatcher;
import mock.SensorEntity;
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
                            return SensorEntity.getAll(idPozo);
                           // return SensorEntity.FINDER.all();
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
                            return SensorEntity.get(id);
//                            return SensorEntity.FINDER.byId(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        sensorEntities -> {
                            return ok(toJson(sensorEntities));
                        }
                );
    }

    public CompletionStage<Result> createSensor(Long idCampo, Long idPozo)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nProduct = request().body().asJson();
        SensorEntity  product = Json.fromJson( nProduct , SensorEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    product.save();
                    return product;
                }
        ).thenApply(
                sensorEntity -> {
                    return ok(Json.toJson(sensorEntity));
                }
        );
    }

    public CompletionStage<Result> deleteSensor(Long idCampo, Long idPozo,Long id)
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

    public CompletionStage<Result> updateSensor(Long idCampo, Long idPozo, Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode nProduct = request().body().asJson();
                            SensorEntity p = Json.fromJson( nProduct , SensorEntity.class ) ;
                            SensorEntity pPorActualizar =  SensorEntity.FINDER.byId(id);
//                            ProductEntity.db().update(pPorActualizar);

//                            pPorActualizar.setName(p.getName());
//                            pPorActualizar.setAvailable(p.getAvailable());
//                            pPorActualizar.setPrice(p.getPrice());
//                            pPorActualizar.setStock(p.getStock());

                            pPorActualizar.update();

                            return pPorActualizar;
                        }
                        ,ec.current())
                .thenApply(
                        sensorEntity -> {
                            return ok(toJson(sensorEntity));
                        }
                );
    }


}
