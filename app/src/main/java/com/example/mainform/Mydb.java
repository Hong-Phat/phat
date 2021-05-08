package com.example.mainform;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class Mydb extends SQLiteOpenHelper {
    Context c;
    public static String dbName = "QLNSdb";
    public static int VERSION = 1;

    public Mydb(Context context) {
        super(context, dbName, null, VERSION);
        c = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String qry = "Create table TaiKhoan (TenTaiKhoan TEXT PRIMARY KEY, MatKhau TEXT)";
            db.execSQL(qry);
            Toast.makeText(c,"Create table Successfully", Toast.LENGTH_LONG).show();
        }catch (Exception e){
        Log.e("Mydb","Create table error",e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String TenTaiKhoan, String MatKhau){
        try{
            String qry = "insert into TaiKhoan values('"+TenTaiKhoan+"','"+MatKhau+"')";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(qry);
            Toast.makeText(c,"Registered Successfully", Toast.LENGTH_LONG).show();
            return true;
        }catch (Exception e){
            Log.e("Mydb","Record Insertion Failed",e);
            return false;
        }
    }

    public Cursor loginCheck(String Tentaikhoan) {
        try {
            String qry = "SELECT TenTaiKhoan, MatKhau from QLNSdb where TenTaiKhoan='"+Tentaikhoan+"'";
            SQLiteDatabase db = getWritableDatabase();
            Cursor c = db.rawQuery(qry, null);
            return c;
        } catch (Exception e) {
            Log.e("Mydb", "Login error", e);
            return null;
        }

    }
}
