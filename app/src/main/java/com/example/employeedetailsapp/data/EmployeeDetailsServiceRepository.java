package com.example.employeedetailsapp.data;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.employeedetailsapp.api.EmployeeDetailsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeDetailsServiceRepository
{
    private final String BASE_URL = "http://karma.equinoxlab.com/";
    private Retrofit retrofit = null;
    private EmployeesDetailsLocalDataRepository employeesDetailsLocalDataRepository;
    private Context context;

    public EmployeeDetailsServiceRepository(Context context)
    {
        this.context = context;
        employeesDetailsLocalDataRepository = new EmployeesDetailsLocalDataRepository(context);
        initRetrofit();
    }

    private void initRetrofit()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    private void fetchEmployeeDetials()
    {
        EmployeeDetailsApi employeeDetailsApi = retrofit.create(EmployeeDetailsApi.class);

        Call<EmployeeDetailsApiResponse> call = employeeDetailsApi.fetchEmployeeDetails();

        call.enqueue(new Callback<EmployeeDetailsApiResponse>()
        {
            @Override
            public void onResponse(Call<EmployeeDetailsApiResponse> call, Response<EmployeeDetailsApiResponse> response)
            {
                if (response != null && response.isSuccessful())
                {
                    EmployeeDetailsApiResponse employeeDetailsApiResponse = response.body();

                    if (employeeDetailsApiResponse != null && employeeDetailsApiResponse.getResponseCode().equals("200"))
                    {
                        employeesDetailsLocalDataRepository.insertEmployees(employeeDetailsApiResponse.getEmployeesList());
                    }
                }
            }

            @Override
            public void onFailure(Call<EmployeeDetailsApiResponse> call, Throwable t)
            {
                Toast.makeText(context, "Some error occured while fetching data from server!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchEmployeeDetialsTask()
    {
        new FetchEmployeeDetialsAsyncTask(employeesDetailsLocalDataRepository).execute();
    }

    private class FetchEmployeeDetialsAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private EmployeesDetailsLocalDataRepository employeesDetailsLocalDataRepository;

        public FetchEmployeeDetialsAsyncTask(EmployeesDetailsLocalDataRepository employeesDetailsLocalDataRepository)
        {
            this.employeesDetailsLocalDataRepository = employeesDetailsLocalDataRepository;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            int employeesCount = employeesDetailsLocalDataRepository.getEmployeesCount();
            if (employeesCount == 0)
            {
                fetchEmployeeDetials();
            }
            return null;
        }
    }
}
