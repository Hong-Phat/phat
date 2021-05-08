package com.example.mainform;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class timesheet extends AppCompatActivity {

    EditText editNgayLap,txtMaNV,txtHoTen,txtLuongcb,txtNgayNghi,txtLuongtl;
    Button Luu;
    Spinner spinPB;
    String arrMa[], arrTen[];
    DBHelper DB;
    SQLiteDatabase sqlite;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timesheet);

        txtMaNV=(EditText)findViewById(R.id.empma);
        txtHoTen=(EditText)findViewById(R.id.empname);
        spinPB=(Spinner)findViewById(R.id.tenPhongBan);
        txtLuongcb=(EditText)findViewById(R.id.empluongcb);
        txtNgayNghi=(EditText)findViewById(R.id.empngaynghi);
        txtLuongtl=(EditText)findViewById(R.id.empluongtl);
        DB = new DBHelper(this);
        sqlite = openOrCreateDatabase("QLNS.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
        Cursor c = sqlite.rawQuery("Select MaPB,TenPB from PhongBan", null);
        arrMa = new String[c.getCount()];
        arrTen = new String[c.getCount()];
        c.moveToFirst();
        for (int i = 0; i < arrMa.length; i++) {
            arrMa[i] = c.getString(0);
            arrTen[i] = c.getString(1);
            c.moveToNext();
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrTen);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinPB.setAdapter(arrayAdapter);
        editNgayLap = (EditText)findViewById(R.id.editNgayLap);
        Calendar calendar = Calendar.getInstance();

        String ngay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) ;
        int thang2 = calendar.get(Calendar.MONTH)+1;
        String thang = String.valueOf(thang2);
        Toast.makeText(timesheet.this,"thang "+thang, Toast.LENGTH_SHORT).show();
        String nam = String.valueOf(calendar.get(Calendar.YEAR)) ;


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String curenntDay = simpleDateFormat.format(calendar.getTime());
        editNgayLap.setText(curenntDay);
        Luu=(Button)findViewById(R.id.Luu);
        Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MaNV = txtMaNV.getText().toString().trim();
                String HoTen = txtHoTen.getText().toString().trim();
                String Tenpb = spinPB.getSelectedItem().toString().trim();
                Float LuongCB = Float.parseFloat(txtLuongcb.getText().toString().trim());
                Float LuongTL = Float.parseFloat(txtLuongtl.getText().toString().trim());
                int NgayNghi = Integer.parseInt(txtNgayNghi.getText().toString().trim()) ;

                if (MaNV.equals("") || HoTen.equals("") || spinPB.equals("") || LuongCB.equals("") || NgayNghi<0 || LuongTL.equals("")) {
                    Toast.makeText(timesheet.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkLuong = DB.checkLuong(MaNV, ngay, thang, nam);
                    if (checkLuong == false) {

                        Boolean insert = DB.insertLuong(MaNV, HoTen, Tenpb, LuongCB, NgayNghi, LuongTL, ngay, thang, nam);
                        if (insert == true) {
                            Toast.makeText(timesheet.this, "Thêm phiếu thành công!", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(getApplicationContext(), Employee.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(timesheet.this, "Thêm phiếu lương không thành công!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(timesheet.this, "Nhân viên này đã có bảng lương!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}