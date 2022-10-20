package com.foch.springserver.model.benefit;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Benefit {
	
    @Id
    String id = "";
    int sequence = 0;
    int point = 0;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
    
}
