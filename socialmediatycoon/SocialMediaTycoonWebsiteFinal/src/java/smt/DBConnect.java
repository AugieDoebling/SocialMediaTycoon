package smt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shubhamkahal
 */
public class DBConnect {
	
	private static final Logger LOGGER = Logger.getLogger( Connection.class.getName());

	
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/SocialMediaTycoon", "postgres",
                    "password");
        } catch (SQLException e) {
            LOGGER.log(Level.FINE, "Connection Failed! Check output console", e);
            return null;
            
        }
        return connection;
    }
}
