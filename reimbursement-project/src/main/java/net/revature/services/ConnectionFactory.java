package net.revature.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionFactory {
	

	private static ConnectionFactory connectionFactory = null;
	private static Properties properties;
	//private static Connection connection = null;
	
	/*private ConnectionFactory() {
		properties = new Properties();
		try {
			InputStream propertiesFileStream = ConnectionFactory.class.
					getClassLoader().getResourceAsStream("dbConfig.properties");
			properties.load(propertiesFileStream);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionFactory getConnectionFactory() {
		if (connectionFactory == null)
			connectionFactory = new ConnectionFactory();
		return connectionFactory;
	}
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(properties.getProperty("drv"));
			connection = DriverManager.getConnection(
					properties.getProperty("aws"),
					System.getenv("DB_USER"),
					System.getenv("DB_PASS"));
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
*/
	private ConnectionFactory() {
		InputStream stream = ConnectionFactory.class.getClassLoader().getResourceAsStream("dbConfig.properties");
		try {
			properties = new Properties();
			properties.load(stream);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static ConnectionFactory getConnectionFactory() {
		if(connectionFactory==null) connectionFactory = new ConnectionFactory();
		return connectionFactory;
	}
	public static Connection getConnection() {
		Connection connection = null;
		//if (connection == null) {
		String url = "jdbc:postgresql://localhost:5432/postgres"; 
		String username = "postgres";
		String password = "Tampabay-7";
		
			
			//ResourceBundle bundle = ResourceBundle.getBundle("dbConfig.properties");
			//String url = bundle.getString("url");
			//String username = bundle.getString("username");
			//String password = bundle.getString("password");
			
			try {
				
				connection = DriverManager.getConnection(url, username, password);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		return connection;
	}
}
