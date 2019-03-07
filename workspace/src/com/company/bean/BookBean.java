package com.company.bean;


/**
 * Created by didi on 2018/12/10.
 */
public class BookBean {
    private int bno;
    private String name;
    private String author;
    private int num;
    private String press;
    private String type;
    private int status;//0不可借，1可借
    public int getBno(){return bno;}
    public void setBno(Integer bno){this.bno=bno;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author=author;}
    public int getNum(){return num;}
    public void setNum(int num){this.num=num;}
    public String getPress(){return press;}
    public void setPress(String press){this.press=press;}
    public String getType(){return type;}
    public void setType(String type){this.type=type;}
    public int getStatus(){return status;};
    public void setStatus(int status){this.status=status;};


}
