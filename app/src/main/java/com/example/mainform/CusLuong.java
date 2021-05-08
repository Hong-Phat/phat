package com.example.mainform;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CusLuong extends RecyclerView.Adapter<CusLuong.MyViewHolder> {

    ArrayList MaNV;
    ArrayList HoTen;
    ArrayList LuongCB;
    ArrayList LuongTL;
    ArrayList NgayLap;
    Context context;

    CusLuong(Context context, ArrayList MaNV, ArrayList HoTen,ArrayList LuongCB, ArrayList LuongTL, ArrayList NgayLap) {
        this.context = context;
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.LuongCB = LuongCB;
        this.LuongTL = LuongTL;
        this.NgayLap = NgayLap;
    }

    @NonNull
    @Override
    public CusLuong.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_pb, parent, false);
        return new CusLuong.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CusLuong.MyViewHolder holder, int position) {
        holder.manv.setText(String.valueOf(MaNV.get(position)));
        holder.ten.setText(String.valueOf(HoTen.get(position)));
        holder.luongcb.setText(String.valueOf(LuongCB.get(position)));
        holder.luongtl.setText(String.valueOf(LuongTL.get(position)));
        holder.ngaylap.setText(String.valueOf(NgayLap.get(position)));
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SuaNV.class);
                intent.putExtra("MaNV",String.valueOf(MaNV.get(position)));
                intent.putExtra("HoTen",String.valueOf(HoTen.get(position)));
                intent.putExtra("LuongCB",String.valueOf(LuongCB.get(position)));
                intent.putExtra("LuongTL",String.valueOf(LuongTL.get(position)));
                intent.putExtra("NgayLap",String.valueOf(NgayLap.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MaNV.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView manv, ten, luongcb,luongtl,ngaylap;
        LinearLayout mainlayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            manv = itemView.findViewById(R.id.ma_nv_luong);
            ten = itemView.findViewById(R.id.ten_nv_luong);
            luongcb = itemView.findViewById(R.id.luongcb2);
            luongtl = itemView.findViewById(R.id.luongtl2);
            ngaylap = itemView.findViewById(R.id.ngaylap2);
            mainlayout = itemView.findViewById(R.id.mainlayoutL);
        }
    }

}
