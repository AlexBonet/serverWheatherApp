package es.ieslaverda.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import es.ieslaverda.properties.MyConfig;

import javax.sql.DataSource;

public class MyDataSource {
    public static DataSource getMyMariaDBDataSource(){
        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        String host = MyConfig.getInstance().getDBHost();
        String port = MyConfig.getInstance().getDBPort();
        String schema = MyConfig.getInstance().getDBSchema();
        String user = MyConfig.getInstance().getUsername();
        String password = MyConfig.getInstance().getPassword();

        // jdbc:mysql://<host>:<port>/<schema>
        mysqlDataSource.setURL("jdbc:mysql://"+ host + ":" + port +"/"+ schema);
        mysqlDataSource.setUser(user);
        mysqlDataSource.setPassword(password);

        return mysqlDataSource;
    }
}
