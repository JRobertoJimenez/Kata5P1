
package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author José Roberto Jiménez
 */
public class Kata5 {

    public static void main(String[] args) {
        Connection conn=conectar();
        consulta(conn);
        close(conn);
        
    }
    
    private static  Connection conectar(){
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:Kata5.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connexión a SQLite establecida");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return conn;
    }
    
    private static  void consulta(Connection conn){
        String sql = "SELECT * FROM PEOPLE";
        try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                rs.getString("Name") + "\t" +
                rs.getString("Apellidos") + "\t" +
                rs.getString("Departamento") + "\t");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    private static void close(Connection conn) {
            try {
                if (conn != null) {
                conn.close();
                
                System.out.println("Connexión a SQLite cerrada");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
}


