package com.fatec.LBDSpring010.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class GenericDao 
{
	private Connection c;

    public Connection getConnection() throws SQLException, ClassNotFoundException
    {
        String hostName = "localhost";
        String dbName = "produtoSpring";
        String user = "usuarioDB";
        String password = "123456";

        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        
        c = DriverManager.getConnection(String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s",
                    hostName, dbName, user, password));


        return c;
    }
}
