package com.example.mainform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DSLuong extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper DB;
    ArrayList<String> MaNV, Ten, LuongCB, LuongTL, NgayLap;
    CusLuong cusluong;
    SQLiteDatabase sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsluong);
        FloatingActionButton add = findViewById(R.id.addLuong);
        recyclerView = findViewById(R.id.dsLuong);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), timesheet.class);
                startActivity(intent);
            }
        });

        DB = new DBHelper(this);

        MaNV = new ArrayList<>();
        Ten = new ArrayList<>();
        LuongCB = new ArrayList<>();
        LuongTL = new ArrayList<>();
        NgayLap = new ArrayList<>();


        displayData();

        cusluong = new CusLuong(DSLuong.this, MaNV, Ten, LuongCB, LuongTL, NgayLap);
        recyclerView.setAdapter(cusluong);
        recyclerView.setLayoutManager(new LinearLayoutManager(DSLuong.this));
    }
    void displayData(){
        Cursor cursor = DB.readLuong();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                MaNV.add(cursor.getString(0));
                Ten.add(cursor.getString(1));
                LuongCB.add(cursor.getString(2));
                LuongTL.add(cursor.getString(3));
                NgayLap.add(cursor.getString(4));

            }
        }
    }
}
