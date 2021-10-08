package com.techrz.fevrt.firebase;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Employee implements Serializable
{


    @Exclude
    private String key;
    private String ID;
    private int point;
    public Employee(){}
    public Employee(String ID, int point)
    {
        this.ID = ID;
        this.point = point;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String name)
    {
        this.ID = ID;
    }

    public int getpoint()
    {
        return point;
    }

    public void setpoint(int point)
    {
        this.point = point;
    }
    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}
