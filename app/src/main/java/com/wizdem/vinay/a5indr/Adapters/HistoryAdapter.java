package com.wizdem.vinay.a5indr.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wizdem.vinay.a5indr.R;
import com.wizdem.vinay.a5indr.models.SaveLocation;

import java.util.Collections;
import java.util.List;

/**
 * Created by vinay_1 on 12/30/2016.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<SaveLocation> data = Collections.emptyList();

    public HistoryAdapter(Context context, List<SaveLocation> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)throws RuntimeException {

        View view = inflater.inflate(R.layout.custom_row,null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SaveLocation current = data.get(position);
        holder.message.setText(current.message);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView message;

        public MyViewHolder(View itemView) {
            super(itemView);

            message=(TextView)itemView.findViewById(R.id.message_source);
        }
    }
}
