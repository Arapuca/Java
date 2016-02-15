//Declaring the imports
/*
 * @author: Matheus Lopes dos Santos
 * ID: 2014184
 * 
 */
//Declaring the imports
package Utilitaries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Database {

	// Declaring the variables for the connectnio
	public Connection conn = null;
	private Statement stmt = null;
	public ResultSet rs = null;
	private String host = "jdbc:mysql://localhost/java";
	private String user = "root";
	private String pass = "";

	public void connect() {

		// Doing the connection with the server
		try {
			conn = DriverManager.getConnection(host, user, pass);

		} catch (SQLException ex) {
			// handle any errors
			JOptionPane.showMessageDialog(null, "Connection Error");
		}
	}

	// method to execute the sql commands
	public void executeSQL(String sql) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			// handle any errors
			JOptionPane.showMessageDialog(null, " Error");
		}
	}

	// method to disconnect the server
	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException ex) {
			// handle any errors
			JOptionPane.showMessageDialog(null,
					"An Error happened when we were disconnecting the server" + ex.getMessage());
		}
	}

}
