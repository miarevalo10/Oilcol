package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import mock.RegistroEntity;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by hj.calderon10 on 31/08/2016.
 */
public class RegistroController extends Controller {
    @Inject
    HttpExecutionContext ec;


    public CompletionStage<Result> getRegistros(Long idPozo)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return RegistroEntity.getAll(idPozo);
                            // return SensorEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        sensorEntities -> {
                            return ok(toJson(sensorEntities));
                        }
                );
    }

    public CompletionStage<Result> getRegistro(Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return RegistroEntity.getRegistro(id);
//                            return SensorEntity.FINDER.byId(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        sensorEntities -> {
                            return ok(toJson(sensorEntities));
                        }
                );
    }

    public CompletionStage<Result> createRegistro()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nProduct = request().body().asJson();
        RegistroEntity  product = Json.fromJson( nProduct , RegistroEntity.class ) ;
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

    public CompletionStage<Result> deleteRegistro(Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            RegistroEntity.delete(id);
                            RegistroEntity sensor = RegistroEntity.getRegistro(id);
                            return sensor == null;
//                            SensorEntity.FINDER.deleteById(id);
//                            return id;
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        registroEntities -> {
                            return ok(toJson(registroEntities));
                        }
                );
    }

    public CompletionStage<Result> updateRegistro()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode nProduct = request().body().asJson();
                            RegistroEntity p = Json.fromJson( nProduct , RegistroEntity.class ) ;
                            RegistroEntity pPorActualizar =  RegistroEntity.getRegistro(p.getId());
//                            ProductEntity.db().update(pPorActualizar);

//                            pPorActualizar.setName(p.getName());
//                            pPorActualizar.setAvailable(p.getAvailable());
//                            pPorActualizar.setPrice(p.getPrice());
//                            pPorActualizar.setStock(p.getStock());
                            pPorActualizar.setUpdatedAt(pPorActualizar.getUpdatedAt());

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