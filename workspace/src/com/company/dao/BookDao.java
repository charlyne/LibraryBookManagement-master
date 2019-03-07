package com.company.dao;

import com.company.bean.Book2Bean;
import com.company.bean.BookBean;
import com.company.utils.DBConnector;
import com.sun.org.apache.bcel.internal.generic.Select;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


/**
 * Created by didi on 2018/12/17.
 */
public class BookDao {



    //java 不支持参数带默认值，可以使用函数重载来实现
    public void addBook(String name,String author,String press,String type){
        int num=1;
        int status=1;
        this.addBook(name,author,num,press,type,status);
    }
    public void addBook(String name,String author,int num,String press,String type){
        int status=1;
        this.addBook(name,author,num,press,type,status);
    }

    //新添书籍
    public void addBook(String name,String author,int num,String press,String type,int status){
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        try {
            String sql="insert into book (name,author,num,press,type,status) values(?,?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,author);
            pstmt.setInt(3,num);
            pstmt.setString(4,press);
            pstmt.setString(5,type);
            pstmt.setInt(6,status);
            pstmt.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.closeDB(pstmt,conn);
        }


    }

    //查询借阅书籍历史
    public ArrayList<Book2Bean> borrowHistory(String username) throws SQLException {
        //
        ArrayList<Book2Bean> booklist=new ArrayList<Book2Bean>();
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            //查询所有的借阅历史
            String sql="select bookname,is_return,should_return_time from borrow_history where username=? ORDER BY return_time ASC ;";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,username);
            rs=pstmt.executeQuery();

            while (rs.next()){
                Book2Bean book2=new Book2Bean();
                book2.setIs_return(rs.getInt("is_return"));
                book2.setShould_return_time(rs.getString("should_return_time"));
                BookBean book=new BookBean();
                book=getBookInfo(rs.getString("bookname"));
                book2.setName(book.getName());
                book2.setType(book.getType());
                book2.setBno(book.getBno());
                book2.setPress(book.getPress());
                book2.setAuthor(book.getAuthor());
                //book2.setStatus(book.getStatus());
                //book2.setNum(book.getNum());
                booklist.add(book2);
            }
            return  booklist;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(rs,pstmt,conn);
        }
        return booklist;
    }

    //通过书编号获取单本书信息
    public BookBean getBookInfo(int bno){
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        BookBean book=new BookBean();
        try{
            String sql="select * from book where bno=?;";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,bno);
            rs=pstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("bno")+"||"+rs.getString("author")+"||"+rs.getString("name")+"||"+rs.getString("press")+"||"+rs.getString("type"));
                book.setBno(rs.getInt("bno"));
                book.setAuthor(rs.getString("author"));
                book.setName(rs.getString("name"));
                book.setNum(rs.getInt("num"));
                book.setPress(rs.getString("press"));
                book.setType(rs.getString("type"));
                book.setStatus(rs.getInt("status"));
            }
            return book;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(rs,pstmt,conn);

        }
        return book;
    }
    //通过书名获取单本书信息
    public BookBean getBookInfo(String bookname){
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        BookBean book=new BookBean();
        try{
            String sql="select * from book where name=?;";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,bookname);
            rs=pstmt.executeQuery();
            while(rs.next()){
                book.setBno(rs.getInt("bno"));
                book.setAuthor(rs.getString("author"));
                book.setName(rs.getString("name"));
                book.setNum(rs.getInt("num"));
                book.setPress(rs.getString("press"));
                book.setType(rs.getString("type"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(rs,pstmt,conn);

        }
        return book;
    }

    //获取全部书籍
    public ArrayList<BookBean> getAllBooks(){
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList<BookBean> booklist=new ArrayList<BookBean>();
        try{
            String sql="select * from book;";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while (rs.next()){
                BookBean book=new BookBean();
                book.setBno(rs.getInt("bno"));
                book.setAuthor(rs.getString("author"));
                book.setName(rs.getString("name"));
                book.setNum(rs.getInt("num"));
                book.setPress(rs.getString("press"));
                book.setType(rs.getString("type"));
                book.setStatus(rs.getInt("status"));
                booklist.add(book);

            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(rs,pstmt,conn);
        }
        return booklist;
    }

    //查询符合条件的书
    public ArrayList<BookBean> queryBooks(String name){
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList<BookBean> booklist=new ArrayList<BookBean>();
        try{
            String sql="select * from book where name LIKE '%"+name+"%' and status=1";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while (rs.next()){
                BookBean book=new BookBean();
                book.setBno(rs.getInt("bno"));
                book.setAuthor(rs.getString("author"));
                book.setName(rs.getString("name"));
                book.setNum(rs.getInt("num"));
                book.setPress(rs.getString("press"));
                book.setType(rs.getString("type"));
                book.setStatus(rs.getInt("status"));
                booklist.add(book);

            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(rs,pstmt,conn);
        }
        return booklist;

    }

    public void modifyBookInfo(String name,String author,String press,int type,int status) {
        modifyBookInfo(name,author,1,press,type,status);
    }

    //修改书的信息
    public void modifyBookInfo(String name,String author,int num,String press,int type,int status){
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        try {
            //name不能修改
            String sql="update book set author=?,num=?,press=?,type=?,status=?;";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,author);
            pstmt.setInt(2,num);
            pstmt.setString(3,press);
            pstmt.setInt(4,type);
            pstmt.setInt(5,status);
            pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(pstmt,conn);
        }

    }
    //删除
    public boolean delBook(String name){
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        try {
           //查询书是否外借
            String sql="select * from borrow_history where bookname=? and status=0";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                //此书在外借，不能删除
                return false;
            }else{
                //此书可以删除
                String del_sql="delete from book where bookname=?";
                pstmt=conn.prepareStatement(del_sql);
                pstmt.setString(1,name);
                pstmt.executeUpdate();
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.closeDB(pstmt,conn);

        }
        return false;
    }
    //获取所有书的所有类型
    public ArrayList<String> getAllBookType() {
        ArrayList<String>list=new ArrayList<String>();
        DBConnector db=new DBConnector();
        Connection conn=db.getConnect();
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try{
            String sql="select type_name from book_type";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while (rs.next()){
                //System.out.println(rs.getString(1));
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

    public static void main(String[] args) throws SQLException {
       BookDao bookdao=new BookDao();
       // book.borrowBook("admin","On the road");
       // bookdao.borrowHistory("admin");
        //System.out.print(book.getName()+" "+book.getPress());
      /*  BookBean book=new BookBean();
        BookBean bb= bookdao.getBookInfo("On The Road");
        System.out.print(bb.getName());*/
        ArrayList<String>list=null;
        list.add("333");

    }

}
