package com.example.myapplication.modle;

public class Store {
    String id;
    String name;
    String tel;
    String address1;
    String address2;
    String description;
    int score;
    String account;

    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public String getAccount(){return account;}
    public void setAccount(String account){this.account = account;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getTel(){return tel;}
    public void setTel(String tel){this.tel = tel;}

    public String getAddress1(){return address1;}
    public void setAddress1(String address1){this.address1 = address1;}

    public String getAddress2(){return address2;}
    public void setAddress2(String address2){this.address2 = address2;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public int getScore(){return score;}
    public void setScore(int score){this.score = score;}

    @Override
    public String toString(){
        return "Store{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", description='" + description + '\'' +
                ", score='" + score + '\'' +
                ", accountNumber='" + account + '\''+
                ", user_id='" + id + '\'' +
                '}';
    }
}