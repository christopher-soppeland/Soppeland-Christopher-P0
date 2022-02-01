package com.revature.expenseReimburesment.dl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
	//private static ConnectionFactory connectionFactory;
	private static final ConnectionFactory connectionFactory = new ConnectionFactory();
	private Properties props = new Properties();
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	// load driver
	static {
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private ConnectionFactory() {
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			props.load(loader.getResourceAsStream("db.properties"));
		}
		catch(IOException e) {
			e.printStackTrace();
			logger.error("Can't find db.properties file");
		}
	}
	
	public static ConnectionFactory getInstance() {
		/*if(connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}*/
		return connectionFactory;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(
					props.getProperty("url"),
					props.getProperty("username"),
					props.getProperty("password"));
		}
		catch (SQLException e){
			e.printStackTrace();
			logger.error("Can't get connection");
		}
		return conn;
	}
}
