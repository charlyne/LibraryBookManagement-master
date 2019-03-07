package com.company.bean;

/**
 * Created by didi on 2018/12/24.
 */
public class Book2Bean extends BookBean{
    private int is_return;
    private String should_return_time;
    public int getIs_return(){
        return this.is_return;
    }
    public String should_return_time(){
        return this.should_return_time;
    }
    public void setIs_return(int is_return){
        this.is_return=is_return;
    }
    public void setShould_return_time(String should_return_time){this.should_return_time=should_return_time;}
}

