package com.example.employeedetailsapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.employeedetailsapp.data.EmployeeDetailsServiceRepository;

public class NetworkConnectionChangeReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        ConnectivityManager connectivityManager =  (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = (networkInfo != null && networkInfo.isConnected());
        EmployeeDetailsServiceRepository employeeDetailsServiceRepository = new EmployeeDetailsServiceRepository(context);

        if (isConnected)
        {
            employeeDetailsServiceRepository.fetchEmployeeDetialsTask();
        }
        else
        {
            Toast.makeText(context, "Internet Connection Not Available!", Toast.LENGTH_LONG).show();
        }
    }
}
