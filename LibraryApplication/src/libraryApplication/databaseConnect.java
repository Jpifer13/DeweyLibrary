package libraryApplication;
import java.io.Console;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class databaseConnect{
	
    static void loadDriver() {
        try {
                // this will load the MySQL driver
                // make sure the corresponding jar file is included on the java CLASSPATH
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver instance ok...");  
            }
            catch (Exception e) {
                System.err.println("Unable to load driver.");
                e.printStackTrace();
            }
    }
    
    //Establish a connection with specified database. Return connection object
    static Connection establish_connection(String database_name, String sql_username, String sql_passwd) 
    {
        Connection conn = null;
        try {
            
            System.out.println("Establishing connection with webdev..");
            conn = DriverManager.getConnection(
            "jdbc:mysql://webdev.cislabs.uncw.edu/"+database_name+"?noAccessToProcedureBodies=true"+"&user="+sql_username+"&password="+sql_passwd);
            
            System.out.println("Connection with webdev.cislabs.uncw.edu established.");
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState:     " + e.getSQLState());
            System.out.println("VendorError:  " + e.getErrorCode());
        }
        
        return conn;
    }
    
    //Use the specified connection object to interact with the database
    static void use_database(Connection conn) {
        try {
        // Do something with the Connection
            Statement stmt = conn.createStatement();

            //Execute a query - which will return a result set
            ResultSet rset = stmt.executeQuery("SELECT * from instructor");

            //Iterate over the result set and process each tuple
            while (rset.next()) {
                    System.out.println(rset.getString(2) + "\t" + rset.getString("salary"));
            }

            //Execute an update - nothing returned
            stmt.executeUpdate("update instructor set salary = salary*1.05;");
            System.out.println("\n********Salaries updated*******\n");
            
            //Execute a query - which will return a result set
            rset = stmt.executeQuery("SELECT * from instructor");

            //Iterate over the result set and process each tuple
            while (rset.next()) {
                    System.out.println(rset.getString(2) + "\t" + rset.getString("salary"));
            }
            
            // Clean up
            rset.close();
            stmt.close();
            conn.close();

        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState:     " + e.getSQLState());
            System.out.println("VendorError:  " + e.getErrorCode());
        }
    }
    private static void sql_inject(Connection conn) {
        try {
            // Create a statement object
            Statement stmt = conn.createStatement();
            Console c = System.console();
            
            System.out.println("SQL injection demo...");
            
            System.out.println("Enter instructor name: ");
            String name = c.readLine();
            
            //What happens if user enters this:  Einstein’ or ’Y’ = ’Y  ,exactly as shown
            
            String queryString = "SELECT * from instructor where name = '" + name + "'";
            System.out.println("Query is " + queryString);
            
            //Execute a query using the user input - which will return a result set
            ResultSet rset = stmt.executeQuery(queryString);
            
            
            //Iterate over the result set and process each tuple
            while (rset.next()) {
                    System.out.println(rset.getString(2) + "\t" + rset.getString("salary"));
                }
            }
        catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState:     " + e.getSQLState());
                System.out.println("VendorError:  " + e.getErrorCode());
            }
    }
    private static void prepared_statement(Connection conn) {
        try {
            Console c = System.console();
            
            System.out.println("Prepared statement demo...");
            
            System.out.println("Enter instructor name: ");
            String name = c.readLine();
            
            //Say user enters this:  Einstein’ or ’Y’ = ’Y  ,exactly as shown
            
            PreparedStatement pStmt = conn.prepareStatement(
            "select * from instructor where name = ?");
            
            //Assign name to prepared statement placeholder 
            pStmt.setString(1, name); 
            System.out.println("Prepared statement is " + pStmt);
            
            //Execute a query using the user input - which will return a result set
            ResultSet rset = pStmt.executeQuery();
                        
            //Iterate over the result set and process each tuple
            while (rset.next()) {
                    System.out.println(rset.getString(2) + "\t" + rset.getString("salary"));
                }
            }
        catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState:     " + e.getSQLState());
                System.out.println("VendorError:  " + e.getErrorCode());
            }
    }
    //Method demonstrates calling a stored procedure
    private static void call_stored_procedure(Connection conn) {
        try {
            Console c = System.console();
            
            System.out.println("Stored procedure demo..");

            System.out.println("Enter price threshold: ");
            int threshold = Integer.parseInt(c.readLine());
            System.out.println("Threshold = "+threshold);
            
            CallableStatement cs = conn.prepareCall("{call CountProducts(?, ?)}");
            
            cs.setInt(1, threshold); //note that threshold is declared as an int
            cs.registerOutParameter(2, Types.INTEGER);
            
            //Execute the query associated with the callable statement
            cs.executeQuery();
            
            //Retrieve the result which is associated with the second 
            //parameter (the out parameter)
            int productCount = cs.getInt(2);
                        
            System.out.println("Number of products with price > "+threshold
              +" = "+productCount);
        }
        catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState:     " + e.getSQLState());
                System.out.println("VendorError:  " + e.getErrorCode());
            }
    }
    public static void main(String[] Args)
    {
        loadDriver();

        Console c = System.console();
        //read user name, database name, and password
        Scanner scan = new Scanner(System.in);
        System.out.println("Database name?: ");
        String database_name = scan.nextLine();
        System.out.println("Username?: ");
        String username = scan.nextLine();
        System.out.println("Password?: ");
        String password = scan.nextLine();
       //char [] password = c.readPassword("Password? ");

        Connection conn = establish_connection(database_name, username, password);
        
        use_database(conn);
        //sql_inject(conn);
        //prepared_statement(conn);
        //call_stored_procedure(conn);
    }
}
