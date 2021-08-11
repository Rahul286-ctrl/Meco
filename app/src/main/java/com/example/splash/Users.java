package com.example.splash;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class Users extends RecyclerView.Adapter<Users.myviewholder>{
    private Context context;
    private List<mode> mUsers;
    FirebaseUser fuser;

    public Users(Context activity, List<mode> mUsers) {
        context=activity;
        this.mUsers=mUsers;

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.usersrow, parent, false);
            return new Users.myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull  Users.myviewholder holder, int position) {
        mode mode=mUsers.get(position);
        holder.username.setText(mode.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "clicked at " +position, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,MessageActivity.class);
                intent.putExtra("userid",mode.getUid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        public TextView username;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            username= (TextView) itemView.findViewById(R.id.name);
        }
    }

}
