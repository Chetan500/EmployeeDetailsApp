package com.example.employeedetailsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.employeedetailsapp.data.Employee;
import com.example.employeedetailsapp.data.EmployeeDetailsServiceRepository;
import com.example.employeedetailsapp.data.EmployeesDetailsLocalDataRepository;

import java.util.List;

public class EmployeeDetailsViewModel extends AndroidViewModel
{
    EmployeesDetailsLocalDataRepository employeesDetailsLocalDataRepository;
    EmployeeDetailsServiceRepository employeeDetailsServiceRepository;

    public EmployeeDetailsViewModel(@NonNull Application application)
    {
        super(application);
        employeesDetailsLocalDataRepository = new EmployeesDetailsLocalDataRepository(application);
        employeeDetailsServiceRepository = new EmployeeDetailsServiceRepository(application);
    }

    public LiveData<List<Employee>> getEmployees()
    {
        return employeesDetailsLocalDataRepository.getEmployees();
    }

    public void fetchEmployeeDetailsTask()
    {
        employeeDetailsServiceRepository.fetchEmployeeDetialsTask();
    }
}
