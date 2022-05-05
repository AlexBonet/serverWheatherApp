package es.ieslaverda.server.controller;

import es.ieslaverda.model.Ciudad;
import es.ieslaverda.model.Result;
import es.ieslaverda.server.model.ICityService;
import es.ieslaverda.server.model.ImpCityService;
import es.ieslaverda.server.model.JsonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.List;

public class CityController {
    static Logger logger = LoggerFactory.getLogger(CityController.class);

    private static ICityService service = new ImpCityService();
    private static JsonTransformer<Ciudad> jsonTransformer = new JsonTransformer<>();


    public static List<Ciudad> getCiudads(Request req, Response res){
        logger.info("Receiving request for all Ciudads");
        return service.getAll();
    }

    public static Result<Ciudad> getCiudad(Request req, Response res){
        // http://localhost:4567/Ciudad?dni=1111
        String nom = req.queryParams("nom");
        logger.info("Get Ciudad with nom= " + nom);
        Result result = service.get(nom);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result<Ciudad> addCiudad(Request request, Response res) {
        logger.info("Add new Ciudad");

        String body = request.body();
        Ciudad p = jsonTransformer.getObjet(body,Ciudad.class);
        Result<Ciudad> result = service.add(p);

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }

}
