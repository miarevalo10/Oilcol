package controllers;

/**
 * Created by maria on 30/08/2016.
 */
import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;

import mock.PozoEntity;
import akka.dispatch.MessageDispatcher;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import javax.inject.Inject;
import play.libs.concurrent.HttpExecutionContext;

public class PozoController extends Controller
{
    @Inject HttpExecutionContext ec;


    public CompletionStage<Result> getPozos(Long idCampo)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return PozoEntity.get(idCampo);
                            //return PozoEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pozoEntities -> {
                            return ok(toJson(pozoEntities));
                        }
                );
    }

    public CompletionStage<Result> getPozo(Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return PozoEntity.getAlone(id);
                            //return PozoEntity.FINDER.byId(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pozoEntities -> {
                            return ok(toJson(pozoEntities));
                        }
                );
    }

    public CompletionStage<Result> createPozo()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nProduct = request().body().asJson();
        PozoEntity product = Json.fromJson( nProduct , PozoEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    product.save();
                    return product;
                }
        ).thenApply(
                pozoEntity -> {
                    return ok(Json.toJson(pozoEntity));
                }
        );
    }

    public CompletionStage<Result> deletePozo(Long idCampo, Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            PozoEntity.delete(id);
                            PozoEntity pozo = PozoEntity.getAlone(id);
                            return pozo == null;
                           // PozoEntity.FINDER.deleteById(id);
                            //return id;
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pozoEntities -> {
                            return ok(toJson(pozoEntities));
                        }
                );
    }

    public CompletionStage<Result> updatePozo(Long idCampo, Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode nProduct = request().body().asJson();
                            PozoEntity p = Json.fromJson( nProduct , PozoEntity.class ) ;
                            PozoEntity pPorActualizar =  PozoEntity.getAlone(id);
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
                        pozoEntity -> {
                            return ok(toJson(pozoEntity));
                        }
                );
    }

    public CompletionStage<Result> cambiarEstadoPozo(Long idPozo)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nProduct = request().body().asJson();
        PozoEntity product = Json.fromJson( nProduct , PozoEntity.class ) ;
        return CompletableFuture.
                supplyAsync(
                        () -> {
                            PozoEntity pozo = PozoEntity.getAlone(idPozo);
                            pozo.setEstado(product.getEstado());
                            return pozo;
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pozoEntities -> {
                            return ok(toJson(pozoEntities));
                        }
                );
    }
}
