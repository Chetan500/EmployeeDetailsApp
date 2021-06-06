package com.example.employeedetailsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.employeedetailsapp.adapter.EmployeeItemsAdapter;
import com.example.employeedetailsapp.data.Employee;
import com.example.employeedetailsapp.receiver.NetworkConnectionChangeReceiver;
import com.example.employeedetailsapp.viewmodel.EmployeeDetailsViewModel;

import java.util.List;

public class EmployeeDetailsActivity extends AppCompatActivity
{
    private TextView tvNoEmployees;
    private RecyclerView rvEmployeesList;
    private EmployeeItemsAdapter employeeItemsAdapter;
    private EmployeeDetailsViewModel employeeDetailsViewModel;
    private NetworkConnectionChangeReceiver networkConnectionChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        init();
        showEmployeeDetails();
        registerReceiver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchview_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_menu_item);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search by Emp or Dept Name...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query)
            {
                employeeItemsAdapter.getFilter().filter(query);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (networkConnectionChangeReceiver != null)
        {
            unregisterReceiver(networkConnectionChangeReceiver);
        }
    }

    private void init()
    {
        tvNoEmployees = (TextView) findViewById(R.id.tvNoEmployees);
        employeeDetailsViewModel = new ViewModelProvider(EmployeeDetailsActivity.this).get(EmployeeDetailsViewModel.class);
        rvEmployeesList = (RecyclerView) findViewById(R.id.rvEmployeesList);
        employeeItemsAdapter = new EmployeeItemsAdapter();
        rvEmployeesList.setLayoutManager(new LinearLayoutManager(EmployeeDetailsActivity.this));
        rvEmployeesList.setAdapter(employeeItemsAdapter);
    }

    private void registerReceiver()
    {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        networkConnectionChangeReceiver = new NetworkConnectionChangeReceiver();
        registerReceiver(networkConnectionChangeReceiver, filter);
    }

    private void showEmployeeDetails()
    {
        employeeDetailsViewModel.getEmployees().observe(EmployeeDetailsActivity.this, new Observer<List<Employee>>()
        {
            @Override
            public void onChanged(List<Employee> employeesList)
            {
                if (employeesList != null && employeesList.size() == 0)
                {
                    rvEmployeesList.setVisibility(View.GONE);
                    tvNoEmployees.setVisibility(View.VISIBLE);
                }
                else
                {
                    rvEmployeesList.setVisibility(View.VISIBLE);
                    tvNoEmployees.setVisibility(View.GONE);
                }

                employeeItemsAdapter.updateEmployeesList(employeesList);
            }
        });
    }
}