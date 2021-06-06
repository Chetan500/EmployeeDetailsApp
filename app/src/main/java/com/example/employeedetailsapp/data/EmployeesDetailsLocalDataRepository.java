package com.example.employeedetailsapp.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EmployeesDetailsLocalDataRepository
{
    private EmployeeDao employeeDao;

    public EmployeesDetailsLocalDataRepository(Context context)
    {
        employeeDao = EmployeesDatabase.getInstance(context).employeeDao();
    }

    void insertEmployees(List<Employee> employeesList)
    {
        new InsertEmployeesAsyncTask(employeeDao).execute(employeesList);
    }

    public LiveData<List<Employee>> getEmployees()
    {
        return employeeDao.getEmployees();
    }

    public int getEmployeesCount()
    {
        return employeeDao.getEmployeesCount();
    }

    private class InsertEmployeesAsyncTask extends AsyncTask<List<Employee>, Void, Void>
    {
        private EmployeeDao employeeDao;

        public InsertEmployeesAsyncTask(EmployeeDao employeeDao)
        {
            this.employeeDao = employeeDao;
        }

        @Override
        protected Void doInBackground(List<Employee>... employeesList)
        {
            employeeDao.insertEmployees(employeesList[0]);
            return null;
        }
    }
}
