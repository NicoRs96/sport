package bean;

import java.sql.*;
import java.util.Properties;

public class IscrizioneBean {

    public IscrizioneBean() {
    }

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

    public Boolean checkIfUserAlreadyExists(String name, String surname) throws SQLException {

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM USER WHERE NOME='%s' AND COGNOME='%s'", name, surname);

        ResultSet result = statement.executeQuery(query);

        if(result.next()){
            connection.close();
            return true;
            }
        connection.close();
        return false;
    }

    public Boolean addUser(String name, String surname, String email, String date, String password,String telephone, int isRent) throws SQLException {

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("INSERT INTO USER(NOME, COGNOME, DATADINASCITA,EMAIL,PASSWORD, TELEFONO, RENT) VALUES('%s','%s','%s','%s','%s','%s',%s)", name, surname, date, email, password,telephone, isRent);

        Boolean result = statement.execute(query);
        connection.close();
        return true;

    }



}
