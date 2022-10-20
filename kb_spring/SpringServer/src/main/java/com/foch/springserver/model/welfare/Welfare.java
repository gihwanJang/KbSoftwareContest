package com.foch.springserver.model.welfare;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Welfare {
	
	@Id
    private String id;
    private String address1;
	private String address2;
    private String tel;
	private String name;
	private String description;
	private int score;
    
	public String getId(){return id;}
    public void setId(String id){this.id = id;}

	public String getTel(){return tel;}
    public void setTel(String tel){this.tel = tel;}

	public String getName(){return name;}
    public void setName(String name){this.name = name;}

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
        return "Welfare{" +
                "id='" + id + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
