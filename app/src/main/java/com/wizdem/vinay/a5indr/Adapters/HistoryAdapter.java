package com.wizdem.vinay.a5indr.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wizdem.vinay.a5indr.R;
import com.wizdem.vinay.a5indr.Utils.Utils;
import com.wizdem.vinay.a5indr.models.SaveLocation;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Date;
/**
 * Created by vinay_1 on 12/30/2016.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<SaveLocation> data = Collections.emptyList();
    private Context context;
    private SimpleDateFormat mDateStamp;

    public HistoryAdapter(Context context, List<SaveLocation> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
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
        try {
          holder.name.setText(Utils.user_name);
          Picasso.with(context).load(Utils.photoUrl).into(holder.imageView);
            mDateStamp = new SimpleDateFormat("ddMMyyyyhhmmss");
            String dateTime = String.valueOf(mDateStamp.parse(current.time));
            holder.date.setText(dateTime);
      }catch (Exception e){
          e.printStackTrace();
      }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView message;
        ImageView imageView;
        TextView date;
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);

            message=(TextView)itemView.findViewById(R.id.message_source);
            name=(TextView)itemView.findViewById(R.id.msg_recever_name);
            imageView=(ImageView)itemView.findViewById(R.id.profile_image);
            date=(TextView)itemView.findViewById(R.id.msg_time_stmp);


        }
    }
}
