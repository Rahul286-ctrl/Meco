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
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.myviewholder>{
    private Context context;
    private List<FileModel> mUsers;
    FirebaseUser fuser;

    public PatientAdapter(Context activity, List<FileModel> mUsers) {
        context=activity;
        this.mUsers=mUsers;

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.usersrow, parent, false);
        return new PatientAdapter.myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull  PatientAdapter.myviewholder holder, int position) {
        FileModel fileModel=mUsers.get(position);
        holder.username.setText(fileModel.getTitle());
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "clicked at " +position, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,MessageActivity.class);
                intent.putExtra("userid",mode.getUid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });*/
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
