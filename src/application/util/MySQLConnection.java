/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package application.util;

import java.sql.*;

/**
 *
 * @author oazisn
 */
public class MySQLConnection {
    private static MySQLConnection instance = null;
    private Connection con = null;
    
    public static MySQLConnection getInstance(){
        if (instance == null)
            instance = new MySQLConnection();
        
        return instance;
    }
    
    public Connection getConnection(){
        String username = "swZn2cZGEU";
        String password = "kZLGKjOh2Y";
        String db = "swZn2cZGEU";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://remotemysql.com:3306/" + db + "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull",username, password);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
    
    public void close(){
        try {
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
