package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHandler {

	static Connection connection;
	
	public static boolean connectToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Success loading Driver");
		} catch (Exception e) {
			System.out.println("Driver cannot load: " + e);
			return false;
		}
		
		try {
			connection = DriverManager.getConnection(DBLogin.url, DBLogin.user, DBLogin.pass);
			return true;
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			return false;
		}		
	}
	
	static void addToTable(String table, String[] row) {
		try {
			if (table.equals("philosophers")) {
				PreparedStatement prepState = connection.prepareStatement("INSERT INTO `" + table + "` (Name, CityID, CountryID, "
						+ "YearBorn, YearDied, Note) VALUES (?, ?, ?, ?, ?, ?);");
				
				prepState.setString(1, row[0]);
				prepState.setInt(2, parseToInt(row[1]));
				prepState.setInt(3, parseToInt(row[2]));
				prepState.setInt(4, parseToInt(row[3]));
				prepState.setInt(5, parseToInt(row[4]));
				prepState.setString(6, row[5]);
				
				prepState.execute();
				
			} else if(table.equals("Countries")) {
				
				PreparedStatement prepState = connection.prepareStatement("INSERT INTO `" + table + "` (Name, Capital) VALUES (?, ?);");
				prepState.setString(1, row[0]);
				prepState.setString(2, row[1]);
				
				prepState.execute();
			} else if (table.equals("Cities")) {
				PreparedStatement prepState = connection.prepareStatement("INSERT INTO `" + table + "` (Name, CountryID, Note) VALUES (?, ?, ?);");
				prepState.setString(1, row[0]);
				prepState.setInt(2, parseToInt(row[1]) );
				prepState.setString(3, row[2]);
				
				prepState.execute();
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}	
	}
	
	static String getDBAsTable(String table) {
		try {
			ResultSet result = connection.createStatement().executeQuery("SELECT * FROM `" + table + "`;");
			
			StringBuilder resString = new StringBuilder();
			
			int columnCount = result.getMetaData().getColumnCount();
			resString.append("<table><tr>");
			
			for (int column = 1; column <= columnCount; ++column) {
				resString.append("<th>" + result.getMetaData().getColumnName(column) + "</th>");
			}
			resString.append("</tr>");
			
			while (result.next()) {
				resString.append("<tr>");
				for (int column = 1; column <= columnCount; ++column) {
					resString.append("<td>" + result.getString(column) + "</td>");
				}
				resString.append("</tr>");
			}
			resString.append("</table>");
		
			return resString.toString();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			
			return "";
		}		
	}
	
	public static String getTableMapAsDatalist(String table) {
		try {
			ResultSet result = connection.createStatement().executeQuery("SELECT ID, Name FROM `" + table + "`;");
			
			StringBuilder resString = new StringBuilder();
			
			while (result.next()) {
				resString.append("<option value='" + result.getString(1) + ". " + result.getString(2) + "'>");
			}
			
			return resString.toString();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			
			return "";
		}	
	}
	
	static boolean addToDB(String database, String[] values) {
		return true;
	}
	
	static private int parseToInt(String str) {
		int ret = 0;
		try {
			ret = Integer.parseInt(str.substring(0, str.indexOf('.') < 0 ? str.length() : str.indexOf('.')));
		} catch (Exception e) {
			
		}
		return ret;
	}
}
