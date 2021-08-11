package com.example.splash;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class Blog  extends RecyclerView.Adapter<Blog.MyviewHolder>{
Context context;
ArrayList<TextModel> list;

    public Blog(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.blog,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
       TextModel blog=list.get(position);
       holder.text.setText(blog.getText());
        holder.time.setText("posted on: "+blog.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyviewHolder extends RecyclerView.ViewHolder{
        TextView text,time;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            text=(TextView)itemView.findViewById(R.id.Text);
            time=(TextView)itemView.findViewById(R.id.time);
        }
    }

}
