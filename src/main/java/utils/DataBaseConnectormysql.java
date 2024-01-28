package utils;

import java.sql.*;

public class DataBaseConnectormysql {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";
    private static Connection connection;
    
    

    // Static block to register the JDBC driver when the class is loaded
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            /**
             * This block is option after java-8
             * "Driver" is a interface "com.mysql.cj.jdbc" and this is package
             * Class.forName is reflection concept; 
             * It is used when we can not create object of class
             * Since java does not want any changes to be made in driver it is made static, 
             * It is not object creation we can say it is accessed using Class.forNmeMethod 
             */
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver");
        }
    }

    /**
     * Establishes a connection to the database.
     */
    public static void connectToDatabase() {
    	
    	
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            
            
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }

    /**
     * Closes the connection to the database.
     */
    public static void closeDatabaseConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to close the database connection");
        }
    }

    /**
     * Inserts data into a specified table and column in the database.
     *
     * @param tableName  The name of the table.
     * @param columnName The name of the column.
     * @param value      The value to be inserted.
     */
    public static void insertData(String tableName, String columnName, String value) {
        try {
            String insertQuery = "INSERT INTO " + tableName + " (" + columnName + ") VALUES (?)";
         // String insertQuery1= "INSERT INTO " +tableName + " (" + columnName + ") VALUES (?)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, value);
                int rowsAffected = preparedStatement.executeUpdate();          
                System.out.println(rowsAffected + " row(s) inserted into the " + tableName + " table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to insert data into the database");
        }
        
        
		try {
			Statement statement = connection.createStatement();

			// Execute a simple query
			ResultSet resultSet = statement.executeQuery("SELECT * FROM tableName");

			// Process the result set
			while (resultSet.next()) {
				// Handle each row of the result set
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to insert data into the database");
		}
	}

    /**
     * Reads data from a specified table in the database and prints the result.
     *
     * @param tableName The name of the table.
     */
    public static void readData(String tableName) {
        try {
            String selectQuery = "SELECT * FROM " + tableName;

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectQuery)) {

                while (resultSet.next()) {
                    String columnName = resultSet.getString(1);  // Assuming a single column table
                    System.out.println("Column Value: " + columnName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read data from the database");
        }
    }

    /**
     * Public method to execute database operations, including connecting to the database,
     * inserting data, reading data, and closing the connection.
     */
    public static void executeDatabaseOperations() {
        // Connect to the database
        connectToDatabase();

        // Insert data into a table
        insertData("example_table", "example_column", "example_value");

        // Read data from a table
        readData("example_table");

        // Close the database connection
        closeDatabaseConnection();
    }
}
