package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.RegistroEntity;
import models.SensorEntity;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by hj.calderon10 on 31/08/2016.
 */
public class RegistroController extends Controller {
    @Inject
    HttpExecutionContext ec;


    public CompletionStage<Result> getRegistros(Long idSensor)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return RegistroEntity.FINDER.where().eq("sensor_id",idSensor).findList();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        registroEntities -> {
                            return ok(toJson(registroEntities));
                        }
                );
    }

    public CompletionStage<Result> getRegistro(Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {

                            return RegistroEntity.FINDER.byId(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        registroEntity -> {
                            return ok(toJson(registroEntity));
                        }
                );
    }

    public CompletionStage<Result> createRegistro(Long idSensor)
    {
        JsonNode nRegistro = request().body().asJson();
        RegistroEntity  registro = Json.fromJson( nRegistro , RegistroEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{

                    SensorEntity s = SensorEntity.FINDER.byId(idSensor);
                    registro.setSensor(s);
//                    String laFecha = "23/09/2016";
//                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//                    Date startDate = null;
//                    try {
//                        startDate = df.parse(laFecha);
//
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
                    registro.setCreatedAt(new Date());
                    registro.save();
                    return registro;
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

                            RegistroEntity.FINDER.deleteById(id);
                            return id;
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        registroEntities -> {
                            return ok(toJson(registroEntities));
                        }
                );
    }

    public CompletionStage<Result> updateRegistro(Long id)
    {

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            JsonNode nProduct = request().body().asJson();
                            RegistroEntity p = Json.fromJson( nProduct , RegistroEntity.class ) ;
                            RegistroEntity pPorActualizar =  RegistroEntity.FINDER.byId(id);

//                            pPorActualizar.setUpdatedAt(p.getUpdatedAt());
                            pPorActualizar.setMedida(p.getMedida());

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

    public CompletionStage<Result> getRegistroFecha(String fechaI, String fechaF)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                            Date startDate = null;
                            Date endDate = null;
                            try {
                                startDate = df.parse(fechaI);
                                endDate = df.parse(fechaF);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            System.out.println(startDate + " to " + endDate + " ");
                            List<RegistroEntity> r = RegistroEntity.FINDER.where().between("created_at" , startDate , endDate).findList();
                            return r;

                        }
                        ,jdbcDispatcher)
                .thenApply(
                        registroEntities -> {
                            return ok(toJson(registroEntities));
                        }
                );
//    }


}}