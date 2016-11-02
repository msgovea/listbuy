package br.puccamp.listbuy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@149.56.173.103:1521:xe", "grupo1", "listbuy");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao conectar com banco de dados. \n Erro: ", e);
        }

    }
}
