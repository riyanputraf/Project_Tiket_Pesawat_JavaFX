package connectivity;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    Connection connection;
    public Connection getConnection(){
        String dbName = "db_tiketpesawat";
        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/"+dbName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }


        return connection;
    }
}
