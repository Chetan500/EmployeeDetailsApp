package com.example.employeedetailsapp.api;

import com.example.employeedetailsapp.data.EmployeeDetailsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeDetailsApi
{
    @GET("betaDailyUpdateApi/Service1.svc/getManager")
    Call<EmployeeDetailsApiResponse> fetchEmployeeDetails();
}
