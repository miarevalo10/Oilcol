package controllers;

/**
 * Created by maria on 30/08/2016.
 */
import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;

import models.CampoEntity;
import models.PozoEntity;
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


    public CompletionStage<Result> getAllPozos()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return PozoEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        productEntities -> {
                            return ok(toJson(productEntities));
                        }
                );
    }

    public CompletionStage<Result> getPozos(Long idCampo)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return PozoEntity.FINDER.where().eq("campo_id",idCampo).findList();
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

                            return PozoEntity.FINDER.byId(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pozoEntities -> {
                            return ok(toJson(pozoEntities));
                        }
                );
    }

    public CompletionStage<Result> createPozo(Long idCampo)
    {
        JsonNode nPozo = request().body().asJson();
        PozoEntity pozo = Json.fromJson( nPozo , PozoEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    CampoEntity c = CampoEntity.FINDER.byId(idCampo);
                    pozo.setCampo(c);
                    pozo.save();
                    return pozo;
                }
        ).thenApply(
                pozoEntity -> {
                    return ok(Json.toJson(pozoEntity));
                }
        );
    }

    public CompletionStage<Result> deletePozo(Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        System.out.println("El id a eliminar" + id);

        return CompletableFuture.
                supplyAsync(
                        () -> {

                            PozoEntity.FINDER.deleteById(id);
                            return id;
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pozoEntities -> {
                            return ok(toJson(pozoEntities));
                        }
                );
    }

    public CompletionStage<Result> updatePozo(Long id)
    {
        JsonNode nPozo = request().body().asJson();
        PozoEntity pozo = Json.fromJson( nPozo , PozoEntity.class ) ;
        return CompletableFuture.
                supplyAsync(
                        () -> {

                            PozoEntity pPorActualizar =  PozoEntity.FINDER.byId(id);
//                            ProductEntity.db().update(pPorActualizar);

                            pPorActualizar.setEstado(pozo.getEstado());

                            pPorActualizar.update();
                            return pPorActualizar;
                        }
                        ,ec.current())
                .thenApply(
                        pozoEntities -> {
                            return ok(toJson(pozoEntities));
                        }
                );
    }
}
