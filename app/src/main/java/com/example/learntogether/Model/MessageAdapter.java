package com.example.learntogether.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.learntogether.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    public Context mcontext;
    public List<Messagefromfirebase> mDatalist;

    public MessageAdapter(Context mcontext, List<Messagefromfirebase> mDatalist) {
        this.mcontext = mcontext;
        this.mDatalist = mDatalist;
    }

    public MessageAdapter() {
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.activity_chats,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {

        Messagefromfirebase messagefromfirebase = mDatalist.get(position);
        holder.textView.setText(messagefromfirebase.getMessage());


    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.entervalues);
        }
    }

}
