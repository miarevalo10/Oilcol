package controllers;

import play.api.Play;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by maria on 25/10/2016.
 */
public class ApplicationController extends Controller{
    public String getURI( String any){
        String resp = "";
        switch (any) {
            case "modules/campo/campo":
                resp = "/public/modules/campo/campo.html";
                break;

            case "modules/campo/crearCampo":
                resp = "/public/modules/campo/crearCampo.html";
                break;

            case "modules/pozo/pozo":
                resp = "/public/modules/pozo/pozo.html";
                break;


        }
        return resp;
    }
    public Result loadPublicHTML(String any) {
        File projectRoot = Play.current().path().getAbsoluteFile();

        File file = new File(projectRoot + getURI(any));
        if (file.exists()) {
            try {
                return ok(new String(Files.readAllBytes(Paths.get(file.getCanonicalPath())))).as("text/html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return notFound();
        }
        return null;
    }

}
