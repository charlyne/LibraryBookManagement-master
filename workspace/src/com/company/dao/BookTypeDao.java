package com.company.dao;

import com.company.utils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by didi on 2019/1/3.
 */
public class BookTypeDao {
    public ArrayList<String> getAllBookType() {
        ArrayList<String>list=null;
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try{
            String sql="select type_name from book_type";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while (rs.next()){
                list.add(rs.getString("type_name"));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(rs,pstmt,conn);
        }
        return list;

    }

}
