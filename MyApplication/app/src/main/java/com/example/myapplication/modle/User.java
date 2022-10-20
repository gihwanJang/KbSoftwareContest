package com.example.myapplication.modle;

public class User{
    private String id;
    private String pw;
    private String name;
    private String address;
    private String tel;
    private int type;
    private int point;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getPw(){
        return pw;
    }
    public void setPw(String pw){
        this.pw = pw;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getTel(){
        return tel;
    }
    public void setTel(String tel){
        this.tel = tel;
    }

    public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }

    public int getPoint(){
        return point;
    }
    public void setPoint(int point){
        this.point = point;
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", type='" + type + '\'' +
                ", point='" + point + '\'' +
                '}';
    }
}