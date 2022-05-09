package es.ieslaverda.server.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import es.ieslaverda.model.Ciudad;
import es.ieslaverda.model.MyDataSource;
import es.ieslaverda.model.Result;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpCityService implements ICityService{
    @Override
    public List<Ciudad> getAll() {
        List<Ciudad> ciudadList = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMyMariaDBDataSource();

        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ciudad")){

            String nom;
            String lat;
            String lon;

            while(resultSet.next()){
                nom = resultSet.getString("nom");
                lat = resultSet.getString("lat");
                lon = resultSet.getString("lon");

                ciudadList.add(new Ciudad(nom,lat,lon));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ciudadList;
    }
    @Override
    public Result<Ciudad> add(Ciudad c) {
        DataSource ds = MyDataSource.getMyMariaDBDataSource();

        try(Connection con = ds.getConnection();
            Statement statement = con.createStatement();) {
            String sql = "INSERT INTO " +
                    "ciudad VALUES ('"+c.getNom()+"','"+c.getLat()+"','"+c.getLon()+"')";

            int count = statement.executeUpdate(sql);
            if(count==1)
                return new Result.Success<>(c);
            else
                return new Result.Error(404,"No se ha podido añadir la ciudad");

        } catch (SQLException throwables) {
            return new Result.Error(404,throwables.getMessage());
        }
    }

    public Result<Ciudad> get(String nom) {
        DataSource dataSource = MyDataSource.getMyMariaDBDataSource();

        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ciudad where nom=?")){

            String lat;
            String lon;

            if(resultSet.next()){
                lat = resultSet.getString("lat");
                lon = resultSet.getString("lon");
                Ciudad c = new Ciudad(nom,lat,lon);

                return new Result.Success<>(c);
            } else  {
                return new Result.Error(404,"No existe ninguna Ciudad con el nombre: " + nom);
            }

        } catch (SQLSyntaxErrorException sqlee) {
            return new Result.Error(sqlee.getErrorCode(),"Error de acceso a la BD. Consulte con el administrador.");
        }catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(),throwables.getMessage());
        }catch (Exception e) {
            return new Result.Error(1,e.getMessage());
        }
    }
    //    @Override
//    public Result<Ciudad> delete(String nom) {
//        DataSource ds = MyDataSource.getMyMariaDBDataSource();
//        int count = 0 ;
//        String sql = "DELETE FROM ciudad WHERE nombre=?";
//
//        try(Connection con = ds.getConnection();
//            PreparedStatement pstmt = con.prepareStatement(sql);) {
//
//            int pos = 0;
//            pstmt.setString(++pos, nom);
//
//            count = pstmt.executeUpdate();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return Result.Success.Error;
//    }
    public Result<Ciudad> delete(String nom) {
        return null;
    }


}
