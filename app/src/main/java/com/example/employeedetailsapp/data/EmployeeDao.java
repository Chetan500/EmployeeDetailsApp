package com.example.employeedetailsapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEmployees(List<Employee> employeesList);

    @Query("select * from employee")
    LiveData<List<Employee>> getEmployees();

    @Query("select count(*) from employee")
    int getEmployeesCount();
}
