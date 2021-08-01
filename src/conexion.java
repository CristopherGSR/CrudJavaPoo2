

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ADMIN
 */
public class conexion {
    static String bd = "bd_sistema_gas";
    static String login = "root";
    static String password = "";
    String url = "jdbc:mysql://localhost:3306/"+bd;
    Connection conn = null;
    public conexion()
    {      try
           {   //obtenemos el driver de para mysql
               Class.forName("com.mysql.jdbc.Driver");
               //obtenemos la conexi�n
               conn = DriverManager.getConnection(url, login, password);
               if (conn!=null)
               {   System.out.println("Conexion a base de datos "+bd+" OK");
               }
           }
           catch(SQLException e){
              System.out.println(e);   
           }
          catch(ClassNotFoundException e){
             System.out.println(e);      }
	}
         public Connection getConnection(){
                 return conn;
        }

         public void desconectar(){
	conn = null;   
        }
}