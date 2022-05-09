package es.ieslaverda.server.model;

import es.ieslaverda.model.Ciudad;
import es.ieslaverda.model.Result;

import java.util.List;

public interface ICityService {
    List<Ciudad> getAll();
//    Result<Ciudad> get(String nom);
//    Result<Ciudad> delete(String nom);
    Result<Ciudad> add(Ciudad c);

}
