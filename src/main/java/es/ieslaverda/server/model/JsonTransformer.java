package es.ieslaverda.server.model;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer<T> implements ResponseTransformer {
    private Gson gson = new Gson();

    @Override
    public String render(Object o) throws Exception {
        return null;
    }

    public T getObjet(String json, Class<T> clazz){
        return gson.fromJson(json,clazz);
    }
}
