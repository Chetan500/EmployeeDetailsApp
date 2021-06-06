package com.example.employeedetailsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeedetailsapp.R;
import com.example.employeedetailsapp.data.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeItemsAdapter extends RecyclerView.Adapter<EmployeeItemsAdapter.EmployeeItemsViewHolder> implements Filterable
{
    private List<Employee> employeesList = new ArrayList<>();
    private List<Employee> employeesFilteredList = new ArrayList<>();

    public void updateEmployeesList(List<Employee> employeesList)
    {
        this.employeesList = employeesList;
        this.employeesFilteredList = employeesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_employee_detail, parent, false);
        return new EmployeeItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeItemsViewHolder holder, int position)
    {
        Employee employee = employeesFilteredList.get(position);
        if (employee != null)
        {
            holder.bindEmployeeData(employee);
        }
    }

    @Override
    public int getItemCount()
    {
        return (employeesFilteredList != null ? employeesFilteredList.size() : 0);
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                String query = constraint.toString();

                if (query.isEmpty())
                {
                    employeesFilteredList = employeesList;
                }
                else
                {
                    List<Employee> filteredList = new ArrayList<>();
                    for (Employee employee : employeesList)
                    {
                        if (employee.getName().toLowerCase().contains(query.toLowerCase()) || employee.getDeptName().toLowerCase().contains(query.toLowerCase()))
                        {
                            filteredList.add(employee);
                        }
                    }

                    employeesFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = employeesFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                employeesFilteredList = (ArrayList<Employee>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class EmployeeItemsViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvEmpName, tvDeptName;

        public EmployeeItemsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView)
        {
            tvEmpName = (TextView) itemView.findViewById(R.id.tvEmpName);
            tvDeptName = (TextView) itemView.findViewById(R.id.tvDeptName);
        }

        public void bindEmployeeData(Employee employee)
        {
            tvEmpName.setText(employee.getName());
            tvDeptName.setText(employee.getDeptName());
        }
    }
}
