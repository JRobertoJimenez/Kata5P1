
package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;



/**
 *
 * @author José Roberto Jiménez
 */
public class Kata5 {
    private static final String fileName="email.txt";
    private static final String url = "jdbc:sqlite:Mail.db";
    private static List<String> mailList =null;

    public static void main(String[] args) {
        mailList=MailListReader.read(fileName);
        Connection conn=conectar(url);
        /**
        consulta(conn);
        **/
        
        crearTabla(conn);
        insertar(conn, mailList);
        
        close(conn);
        
    }
    
    private static  Connection conectar(String url){
        Connection conn = null;
        try {
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

    private static void crearTabla(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS direcc_email (\n"
        + " id integer PRIMARY KEY AUTOINCREMENT,\n"
        + " direccion text NOT NULL);";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    private static void insertar(Connection conn, List<String> mailList){
        for (String email : mailList) {
            String sql = "INSERT INTO direcc_email(direccion) VALUES(?)";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, email);
                System.out.println("Registro insertado");
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        
    }
    
    
}


