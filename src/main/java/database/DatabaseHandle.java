package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Person;

public class DatabaseHandle {
	
	private String url = "jdbc:mysql://localhost:3306/devprox";
	private String username = "root";
	private String password = "Aamdgjddd";
	private final String DRIVER = "com.mysql.cj.jdbc.Driver"; //The new driver class is `com.mysql.cj.jdbc.Driver instead of the old `com.mysql.jdbc.Driver`
	private static DatabaseHandle dbHandler = null;
	private Connection connection = null;
	private PreparedStatement statement = null;
	
	/**
	 * Constructs the database, initiate connection and create all necessary table
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private DatabaseHandle() throws ClassNotFoundException, SQLException {
		createConnection();
		
	}
	/**
	 *  Set the database instance and set it as static meaning this instance will be the ONLY shared
	 *  accross the board.
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException  
	 */
	public static DatabaseHandle getInstance() throws ClassNotFoundException, SQLException   {
        if (dbHandler == null)
        	dbHandler = new DatabaseHandle();
      
        return dbHandler;
    }
	
	/**
	 * Initiates, establishes connection
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	private void createConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName(DRIVER);
		this.connection = DriverManager.getConnection(url, username, password);
	}
	
	private void createDatabase(String databaseName, String query) {
		
		
	}
	
	/**
	 *  Creates table into database given the schema (name and attributes)
	 * @param table
	 * @param query
	 */
	private void createTable(String tableName, String query) {
		
		try
		{
			statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS  " + tableName + query);

			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, tableName.toUpperCase(), null);

			if (!tables.next())
				statement.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Table Creation Failure : "+ e.getMessage());
		}
		
	}
	
	/**
	 * Method pulls tuples from requested table matching the criteria
	 * @param query
	 * @return
	 */
	public ResultSet Select(String query) {
		
        ResultSet result;
        try 
        {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
        } 
        catch(SQLException ex) 
        {
			System.out.println("Select Query Failure: "+ex.getMessage());
            return null;
        }
        return result;
    }
	
	/**
	 * Method inserts a new User record into database
	 * @param query
	 * @param person/user
	 * @return
	 */
	public void Insert(Person person) {
		
		try 
		{
			statement = connection.prepareStatement("insert into person values (?,?,?,?)");
			statement.setString(1, person.getFirstName());
			statement.setString(2, person.getLastName());
			statement.setString(3, person.getId());
			statement.setString(4, person.getDateOfBirth());
			statement.executeUpdate();
		} 
		catch(SQLException ex) 
		{
			System.out.println("User Insertion Failure: "+ex.getMessage());
		}
	}
	
	/**
	 *
	 */
	  public void closeSession()
	  {
		   	try 
		   	{
				if(statement != null)statement.close();
				if(connection != null)connection.close();
			} 
		   	catch (SQLException ex) 
		   	{
				System.out.println("Session Close Failure: "+ex.getMessage());
			}
	  }
	
	
}






