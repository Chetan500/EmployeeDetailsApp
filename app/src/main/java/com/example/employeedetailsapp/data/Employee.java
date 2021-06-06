package com.example.employeedetailsapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Employee
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    @SerializedName("dept_name")
    private String deptName;

    public Employee(String name, String deptName)
    {
        this.name = name;
        this.deptName = deptName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }
}
