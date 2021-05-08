package com.example.mainform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class Categories extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout search;
    NavigationView navigationView;
    Toolbar toolbar;
    Button employee, department, payroll, timesheet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        //NAV_Menu
        search=findViewById(R.id.search);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                search,
                toolbar,
                R.string.nav_drawer__open, R.string.nav_drawer_close);
        search.addDrawerListener(toggle);
        toggle.syncState();

        employee = (Button)findViewById(R.id.employee);
        department = (Button)findViewById(R.id.department);
        payroll = (Button)findViewById(R.id.payroll);
        timesheet = (Button)findViewById(R.id.timesh);

        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Employee.class);
                startActivity(intent);
            }
        });

        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), department.class);
                startActivity(intent);
            }
        });
        payroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), payroll.class);
                startActivity(intent);
            }
        });
        timesheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), timesheet.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(search.isDrawerOpen(GravityCompat.START))
        {
            search.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.staff:
                Intent intent = new Intent(Categories.this,Employee.class);
                startActivity(intent);
                break;

            case R.id.addstaff:
                Intent intent1 = new Intent(Categories.this,AddEmployee.class);
                startActivity(intent1);
                break;

            case R.id.depart:
                Intent intent2 = new Intent(Categories.this, department.class);
                startActivity(intent2);
                break;

            case R.id.adddepartment:
                Intent intent3 = new Intent(Categories.this,AddEmployee.class);
                startActivity(intent3);
                break;

            case R.id.Timekeeping:
                Intent intent4 = new Intent(Categories.this, com.example.mainform.timesheet.class);
                startActivity(intent4);
                break;

            case R.id.Salary:
                Intent intent5 = new Intent(Categories.this, DSLuong.class);
                startActivity(intent5);
                break;
        }
        search.closeDrawer(GravityCompat.START);
        return true;
    }
}