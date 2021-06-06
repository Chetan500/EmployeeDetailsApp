package com.example.employeedetailsapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeDetailsApiResponse
{
    @SerializedName("CODE")
    private String responseCode;

    @SerializedName("DATA")
    private List<Employee> employeesList;

    private String message;

    public String getResponseCode()
    {
        return responseCode;
    }

    public void setResponseCode(String responseCode)
    {
        this.responseCode = responseCode;
    }

    public List<Employee> getEmployeesList()
    {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList)
    {
        this.employeesList = employeesList;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
