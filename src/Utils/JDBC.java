/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;
import java.sql.*;
import java.util.Properties;
/**
 *
 * @author space
 */
public class JDBC {
    public static final Properties prop = JDBC.loadPro();
    // kết nối SQL
    public static Connection getConnection()
    {
        try {
//            String URL = "jdbc:sqlserver://localhost;databaseName=YUMFOOD;username=sa;password=080304;encrypt=false";
            return DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user")
                    ,prop.getProperty("pass"));
//            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Properties loadPro()
    {
        try 
        {
            Properties pro = new Properties();
            pro.load(JDBC.class.getResourceAsStream("JDBC.properties"));
            return pro;
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("ERROR: PROPERTIES");
        }
        return null;
    }
    
    public static PreparedStatement pre(String sql, Object...args)
    {
        try
        {
            PreparedStatement preS = null;
            if(sql.contains("{"))
            {
                preS = getConnection().prepareCall(sql);
            }
            else
            {
                preS = getConnection().prepareStatement(sql);
            }
            for (int i = 0; i < args.length; i++)
            {
                preS.setObject(i+1, args[i]);
            }
            return preS;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public static ResultSet querry(String sql, Object...args)
    {
        try 
        {
            PreparedStatement state = JDBC.pre(sql, args);
            return state.executeQuery();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    return null;
    }
    
    public static void update(String sql, Object...args)
    {
        try
        {
            getConnection();
            PreparedStatement state = JDBC.pre(sql, args);
            state.executeUpdate();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
