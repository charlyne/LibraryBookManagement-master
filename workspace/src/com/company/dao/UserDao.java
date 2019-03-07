package com.company.dao;


import com.company.bean.UserBean;
import com.company.utils.DBConnector;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by didi on 2018/12/13.
 */
public class UserDao {

    //注册
    public int addUser(String username,String password,String phone,String email){
        int role=2;//2是读者,1是管理员
       return addUser(username,password,phone,email,role);
    }


    //注册
    public int addUser(String username,String password,String phone,String email,int role){
        int num=0;
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        UserBean user=new UserBean();
        PreparedStatement pstm=null;
        //Date create_time=new java.sql.Date(new java.util.Date().getTime());
       // java.util.Date date=new java.util.Date();
       // System.out.println(date.getTime());
       // String nowTime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
       // System.out.print(nowTime);
       // Timestamp create_time=Timestamp.valueOf(nowTime);

        Calendar calendar=Calendar.getInstance();
        Timestamp create_time=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTimeInMillis()));


        String sql="insert into user (username,password,phone,email,role,create_time,update_time) values(?,?,?,?,?,?,?)";
        try{
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,username);
            pstm.setString(2,password);
            pstm.setString(3,phone);
            pstm.setString(4,email);
            pstm.setInt(5,role);
            pstm.setTimestamp(6,create_time);
            pstm.setTimestamp(7,create_time);
            num=pstm.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(pstm,conn);
        }
        return num;
    }

    //用户登录 1是管理员，2是读者
    public int logIn(String username,String password){
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        String sql="select role from user where username='"+username+"' and password='"+password+"';";
        try{
            pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
               return rs.getInt(1);
            }else{
                return 0;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(pstmt,conn);
        }
        return 0;
    }

    //获取用户信息
    public UserBean getUserInfo(String username,String password){
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        UserBean user=new UserBean();
        String sql="select * from user where username=? and password=?;";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            rs=pstmt.executeQuery();
            while (rs.next()){
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getInt("role"));
                user.setUid(rs.getInt("uid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.closeDB(rs,pstmt,conn);
        }
        return user;

    }
    //删除用户
    public boolean delUser(String username){
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        String sql="delete from user where username='"+username+"'";
        try{
            pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(pstmt,conn);

        }
        return false;
    }

    //更新用户信息
    public void updateUserInfo(String username,String password,String phone,String email,int role){
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        try{
            String sql="update user set username=?,password=?,phone=?,email=?,role=?;";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            pstmt.setString(3,phone);
            pstmt.setString(4,email);
            pstmt.setInt(5,role);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
         db.closeDB(pstmt,conn);
        }


    }




    //借书
    public int borrowBook(String username,String bookname){
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        int num=0;
        try{
            // String sql="select * from book where name='"+bookname+"' and num>0 AND status=1;";
            String sql="UPDATE book SET num=num-1 WHERE name='"+bookname+"' AND status=1 and num>0;";

            pstmt=conn.prepareStatement(sql);
            num=pstmt.executeUpdate();
            if (num>0){
                String sql_borrow="insert into borrow_history (username,bookname,borrow_time,should_return_time,return_time,is_return) values(?,?,?,?,?,?);";
                Calendar calendar=Calendar.getInstance();
                Timestamp borrow_time=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTimeInMillis()));
                calendar.add(Calendar.MONTH,1);
                Timestamp should_return_time=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTimeInMillis()));
                Timestamp return_time=Timestamp.valueOf("1970-01-01 00:00:00");
                pstmt=conn.prepareStatement(sql_borrow);
                pstmt.setString(1,username);
                pstmt.setString(2,bookname);
                pstmt.setTimestamp(3,borrow_time);
                pstmt.setTimestamp(4,should_return_time);
                pstmt.setTimestamp(5,return_time);
                pstmt.setInt(6,0);
                num=pstmt.executeUpdate();
                return num;


            }


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(pstmt,conn);

        }
        return num;
    }

    //还书
    public int returnBook(String username,String bookname){
        int num=1;
        return returnBook(username,bookname,num);
    }
    //还书
    public int returnBook( String username,String bookname,int num){
        int numm=0;
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        try{
//其实这里应该是一个事务执行才对
            String sql="update book set num=num+"+num+" where name='"+bookname+"';";
            pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
            Calendar calendar=Calendar.getInstance();
            Timestamp return_time=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTimeInMillis()));
            String returned_sql="update borrow_history set is_return=?,return_time=? where bookname='"+bookname+"' and username='"+username+"';";
            //System.out.print(returned_sql);
            pstmt=conn.prepareStatement(returned_sql);
            pstmt.setInt(1,1);
            pstmt.setTimestamp(2,return_time);
            numm=pstmt.executeUpdate();
            return  numm;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(pstmt,conn);

        }
        return numm;
    }

    public static void main(String[] args){
        String username="大声喊济吧看看again";
        String password="test";
        String phone="123123123";
        String email="nanbiandehe@sina.com";
        int role=0;
        UserDao user=new UserDao();
        int num=user.addUser(username,password,phone,email,role);
       // System.out.println(num);
        //System.out.print(user.signIn("adminn","test"));
        System.out.print(user.getUserInfo("ssssss","sss"));





    }
}
