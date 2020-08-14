package com.blizaerts.fmbv00.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blizaerts.fmbv00.R;

import java.util.ArrayList;

public class BusInfoAdapter extends RecyclerView.Adapter<BusInfoAdapter.BusInfoHolder> {



    Context context;
    ArrayList<AddBusDetail> datalist = new ArrayList<>();
    public BusInfoAdapter(Context con, ArrayList<AddBusDetail>list){
        context = con;
        datalist = list;
    }

    @NonNull
    @Override
    public BusInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_recycler_busdetails,parent,false);
        BusInfoHolder busInfoHolder = new BusInfoHolder(view);
        return busInfoHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull BusInfoHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class BusInfoHolder extends RecyclerView.ViewHolder{

        TextView textView = findViewById(R.id.textView_busNo);

        public BusInfoHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
