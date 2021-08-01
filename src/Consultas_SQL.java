
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Consultas_SQL {
    conexion mc;
    private static int cantidad_filas=0;
    Consultas_SQL(){
        mc=new conexion();
    }
    
    public Object[][] Leer_Cliente(){
        Object[][] reg= new Object[100][5];        
        String sql="SELECT * FROM cliente";
        System.out.println(sql);
        try
        {
            PreparedStatement pstm=mc.getConnection().prepareStatement(sql);
            ResultSet rs=(ResultSet)pstm.executeQuery();
            int c=0;
            while(rs.next())
            {
                reg[c][0]=rs.getString("Nombre");
                reg[c][1]=rs.getString("Direccion");
                reg[c][2]=rs.getString("Telefono");
                reg[c][3]=rs.getInt("Descuento");
                reg[c][4]=rs.getInt("IDCliente");
                c++;
            }           
            cantidad_filas=c-1;
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return reg;
    }
    public Object[][] Leer_Producto(){
        Object[][] reg= new Object[100][5];        
        String sql="SELECT * FROM producto";
        System.out.println(sql);
        try
        {
            PreparedStatement pstm=mc.getConnection().prepareStatement(sql);
            ResultSet rs=(ResultSet)pstm.executeQuery();
            int c=0;
            while(rs.next())
            {
                reg[c][0]=rs.getString("Tipos");
                reg[c][1]=rs.getInt("IDProveedor");
                reg[c][2]=rs.getInt("Peso");
                reg[c][3]=rs.getInt("Precio");
                reg[c][4]=rs.getInt("IDProducto");
                c++;
            }     
            cantidad_filas=c-1;
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return reg;
    }
    public Object[][] Leer_Proveedor(){
        Object[][] reg= new Object[100][6];        
        String sql="SELECT * FROM proveedor";
        System.out.println(sql);
        try
        {
            PreparedStatement pstm=mc.getConnection().prepareStatement(sql);
            ResultSet rs=(ResultSet)pstm.executeQuery();
            int c=0;
            while(rs.next())
            {
                reg[c][0]=rs.getInt("IDProveedor");
                reg[c][1]=rs.getString("Nombre");
                reg[c][2]=rs.getString("Telefono");
                reg[c][3]=rs.getString("Numerodecuenta");
                reg[c][4]=rs.getString("Direccion");
                reg[c][5]=rs.getString("CorreoElectronico");
                c++;
            }        
            cantidad_filas=c-1;
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return reg;
    }
    public Object[][] Leer_Venta(){
        Object[][] reg= new Object[100][5];        
        String sql="SELECT * FROM vista_venta";
        System.out.println(sql);
        try
        {
            PreparedStatement pstm=mc.getConnection().prepareStatement(sql);
            ResultSet rs=(ResultSet)pstm.executeQuery();
            int c=0;
            while(rs.next())
            {
                reg[c][0]=rs.getInt("IDVenta");
                reg[c][1]=rs.getString("Nombre");
                reg[c][2]=rs.getString("Producto");
                reg[c][3]=rs.getInt("Cantidad");
                reg[c][4]=rs.getString("Fecha");
                c++;
            }            
           cantidad_filas=c-1;
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return reg;
    }
    public void Reg_Cliente(String nombre, String direccion, String telefono, int descuento)
    {   
        String sql="INSERT INTO cliente VALUES('"+nombre+"','"+direccion+"','"+telefono+"',"+descuento+",0)";
        System.out.println(sql);
        try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            pstm.executeUpdate();
            mc.desconectar();
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
    }
    public void Reg_Venta(int id_cli, int id_pro, int cantidad)
    {   
        LocalDate fecha = java.time.LocalDate.now();
        String sql="INSERT INTO venta VALUES(0,"+id_cli+","+id_pro+","+cantidad+",'"+fecha+"')";
        System.out.println(sql);
        try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            pstm.executeUpdate();
            mc.desconectar();
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
    }
    public void Reg_Producto(String tipos,int id_pro, int peso, int precio)
    {   
        
        String sql="INSERT INTO producto VALUES('"+tipos+"',"+id_pro+","+peso+","+precio+",0)";
        System.out.println(sql);
        try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            pstm.executeUpdate();
            mc.desconectar();
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
    }
    public void Reg_Proveedor(String nombre,String telefono,String n_cuenta,String direccion,String c_electronico)
    {   
        String sql="INSERT INTO proveedor VALUES(0,'"+nombre+"','"+telefono+"','"+n_cuenta+"','"+direccion+"','"+c_electronico+"')";
        System.out.println(sql);
        try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            pstm.executeUpdate();
            mc.desconectar();
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
    }
    public void Eliminar(String nombre_tabla, String nombre_id, int id){   
        String sql="DELETE FROM "+nombre_tabla+" WHERE `"+nombre_id+"`='"+id+"'";
        System.out.println(sql);
         try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            pstm.executeUpdate();            
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
    }
    public void Actualizar_Cliente(String nombre, String direccion, String telefono, int descuento, int id)
    {   mc=new conexion();
        String sql="UPDATE cliente SET Nombre='"+nombre+"', Direccion='"+direccion+"',Telefono='"+telefono+"',Descuento='"+descuento+"' WHERE `IDCliente`="+id+"";
        System.out.println(sql);
         try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            pstm.executeUpdate();
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
    }
    public void Actualizar_Venta(int idcli, int idp, int cantidad, int idven)
    {   mc=new conexion();
        String sql="UPDATE venta SET `IDCliente`='"+idcli+"',`IDProducto`='"+idp+"',Cantidad='"+cantidad+"' WHERE `IDVenta`="+idven+"";
        System.out.println(sql);
         try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            pstm.executeUpdate();
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
    }
    public void Actualizar_Producto(String tipos, int idp, int peso, int precio, int idprod)
    {   mc=new conexion();
        String sql="UPDATE producto SET Tipos='"+tipos+"', `IDProveedor`='"+idp+"',Peso='"+peso+"',Precio='"+precio+"' WHERE `IDProducto`="+idprod+"";
        System.out.println(sql);
         try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            pstm.executeUpdate();
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
    }
    public void Actualizar_Proveedor(String nom, String tel, String ncuenta,String direccion, String correo,int idpro)
    {   mc=new conexion();
        String sql="UPDATE proveedor SET Nombre='"+nom+"', Telefono='"+tel+"',`Numerodecuenta`='"+ncuenta+"',Direccion='"+direccion+"',`CorreoElectronico`='"+correo+"' WHERE `IDProveedor`="+idpro+"";
        System.out.println(sql);
         try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            pstm.executeUpdate();
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
    }
    public void Desconectar(){
        mc.desconectar();
    }
    public int Obt_cantidad_filas(){
        return cantidad_filas;
    }
            
    
}
