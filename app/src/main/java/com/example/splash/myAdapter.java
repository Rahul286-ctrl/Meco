package com.example.splash;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myAdapter extends FirebaseRecyclerAdapter<mode,myAdapter.myviewholder>
{
    Context context;

    public myAdapter(@NonNull FirebaseRecyclerOptions<mode> options, Context activity) {
        super(options);
        context=activity;

    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull mode model) {

        holder.name.setText(model.getName());
        holder.specialization.setText("Specialization: "+model.getSpecialization());
        holder.experience.setText("Experience: "+model.getExperience()+" yrs");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "clicked at " +position, Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(context,DoctorDetail.class);
                  intent.putExtra("name",model.getName());
                intent.putExtra("specialization",model.getSpecialization());
                intent.putExtra("experience",model.getExperience());
                intent.putExtra("uid",model.getUid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,specialization,experience;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            specialization=(TextView)itemView.findViewById(R.id.specialization);
            experience=(TextView)itemView.findViewById(R.id.experience);


        }
    }

}
