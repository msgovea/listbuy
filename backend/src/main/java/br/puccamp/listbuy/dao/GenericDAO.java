package br.puccamp.listbuy.dao;

import br.puccamp.listbuy.database.ConnectionFactory;
import java.sql.Connection;

public class GenericDAO {
    
    public Connection getConnection() {
        return ConnectionFactory.getConnection();
    }

}
