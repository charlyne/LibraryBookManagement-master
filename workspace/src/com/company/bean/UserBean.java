package com.company.bean;

/**
 * Created by didi on 2018/12/10.
 */
public class UserBean {
    private int uid;
    private String username;
    private String password;
    private int role;//1=admin 2=reader
    private String email;
    private String phone;


    public int getUid(){return uid;}
    public void setUid(int uid){this.uid=uid;};
    public String getUsername(){return username;};
    public void setUsername(String username){this.username=username;};
    public String getPassword(){return password;};
    public void setPassword(String password){this.password=password;};
    public int getRole(){return role;};
    public void setRole(int role){this.role=role;};
    public String getEmail(){return email;};
    public void setEmail(String email){this.email=email;};
    public String getPhone(){return phone;};
    public void setPhone(String phone){this.phone=phone;};
}

