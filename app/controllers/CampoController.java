package controllers;

import com.google.inject.Inject;
import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;
import akka.dispatch.MessageDispatcher;

import io.netty.util.concurrent.CompleteFuture;
import models.CampoEntity;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by ls.hernandez10 on 30/08/2016.
 */
public class CampoController extends Controller
{
    @Inject
    HttpExecutionContext ec;

    public CompletionStage<Result> getCampos()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return CampoEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        productEntities -> {
                            return ok(toJson(productEntities));
                        }
                );
    }

    public CompletionStage<Result> getCampo(long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return CampoEntity.FINDER.byId(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        productEntities -> {
                            return ok(toJson(productEntities));
                        }
                );
    }

    public CompletionStage<Result> createCampo(){

        JsonNode nCampo = request().body().asJson();
        CampoEntity campo = Json.fromJson(nCampo, CampoEntity.class);
        return CompletableFuture.supplyAsync(
                () -> {
                    campo.save();
                    return campo;
                }
        ).thenApply(
                        campoEntity -> {
                            return ok(Json.toJson(campoEntity));
                        }
        );
    }

    public CompletionStage<Result> deleteCampo(long id)
    {
        return CompletableFuture.supplyAsync(
                ()->{

                    CampoEntity campo = CampoEntity.FINDER.byId(id);
                    CampoEntity.FINDER.deleteById(id);
                    campo = CampoEntity.FINDER.byId(id);
                    return campo == null;
                }
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity));
                }
        );
    }

    public CompletionStage<Result> updateCampo(Long id){

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode campo = request().body().asJson();
                            CampoEntity c = Json.fromJson(campo, CampoEntity.class);
                            CampoEntity cActualizar = CampoEntity.FINDER.byId(id);
                            cActualizar.setRegion(c.getRegion());
                            cActualizar.update();
                            return cActualizar;
                        }
                        ,ec.current())
                .thenApply(
                        campoEntity -> {
                            return ok(toJson(campoEntity));
                        }
                );
    }

}
