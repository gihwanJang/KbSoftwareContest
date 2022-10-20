package com.example.myapplication.modle;


import java.sql.Timestamp;

public class Manager {

    public int accPoint;
    public int seq_num;
    public Timestamp updated_dt;

    public Manager(Manager manager)
    {
        this.accPoint = manager.getAccPoint();
        this.seq_num = manager.getSequence_num();
    }

    public Manager() {
        return;
    }

    public void setUpdated_dt(Timestamp updated_dt){this.updated_dt = updated_dt;}
    public Timestamp getUpdated_dt(){return updated_dt;}
    public void setSequence_num(int seq_num){this.seq_num = seq_num;}
    public int getSequence_num(){return seq_num;}

    public void setAccPoint(int accPoint){this.accPoint = accPoint;}
    public int getAccPoint(){return accPoint;}

}
