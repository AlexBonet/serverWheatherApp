package es.ieslaverda.server;

import es.ieslaverda.model.Ciudad;
import es.ieslaverda.server.controller.CityController;
import es.ieslaverda.server.model.JsonTransformer;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get(API.Routes.ALL_CITIES, CityController::getCiudads, new JsonTransformer<>());
        post(API.Routes.CITY_ADD, CityController::addCiudad, new JsonTransformer<>());
    }
}