package bean;

import java.sql.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.TreeMap;

public class LoginBean {

    public LoginBean(){}

    public Connection getConnection() throws SQLException {

        Connection connection = null;
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "password");

        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sportchallengeonline",
                connectionProps);
        return conn;

    }

    public TreeMap<String, String> authenticate(String email, String password) throws SQLException {

        TreeMap<String, String> user = new TreeMap<>();
        Connection connection = getConnection();
        Statement stm = connection.createStatement();
        String query = String.format("SELECT * FROM USER WHERE email = '%s' AND password='%s'",email, password);
        ResultSet resultSet = stm.executeQuery(query);
        while(resultSet.next()) {
            user.put("NOME", resultSet.getString("NOME"));
            user.put("COGNOME", resultSet.getString("COGNOME"));
            user.put("DATADINASCITA", resultSet.getDate("DATADINASCITA").toString());
            user.put("EMAIL", resultSet.getString("EMAIL"));
            user.put("PASSWORD", resultSet.getString("PASSWORD"));
            user.put("TELEFONO", resultSet.getString("TELEFONO"));
            user.put("RENT", "" +resultSet.getInt("RENT"));
        }
        return user;
    }


}
