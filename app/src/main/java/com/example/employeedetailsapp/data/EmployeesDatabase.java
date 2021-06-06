package com.example.employeedetailsapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.employeedetailsapp.utilities.Constants;

@Database(entities = {Employee.class}, version = 1)
public abstract class EmployeesDatabase extends RoomDatabase
{
    public abstract EmployeeDao employeeDao();

    private static volatile EmployeesDatabase INSTANCE = null;

    public static EmployeesDatabase getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (EmployeesDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context, EmployeesDatabase.class, Constants.DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
