package com.company.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by didi on 2018/12/13.
 */

public class DBConnector {
    public static String username="root";
    public static String password="123456";
    public static String url="jdbc:mysql://localhost:3306/bookmanagement?useSSL=false";
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public static Connection getConnect(){
        Connection conn=null;
        try{
            conn=DriverManager.getConnection(url,username,password);


        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    public static void closeDB(ResultSet rs, PreparedStatement pstm,Connection conn){
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(pstm!=null){
            try{
                pstm.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try{
                conn.close();
            }catch(SQLException e){

                e.printStackTrace();
            }
        }
    }
    public static void closeDB(PreparedStatement pstm,Connection conn){

        if(pstm!=null){
            try{
                pstm.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try{
                conn.close();
            }catch(SQLException e){

                e.printStackTrace();
            }
        }
    }
}
