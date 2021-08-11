package com.example.splash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.myviewholder>{
        public static final int MSG_TYPE_LEFT=0;
        public static final int MSG_TYPE_RIGHT=1;
        private Context context;
        private List<Chat> mChat;
        FirebaseUser fuser;

public MessageAdapter(Context activity, List<Chat> mChat) {
       context=activity;
       this.mChat=mChat;

        }

@NonNull
@Override
public MessageAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType==MSG_TYPE_RIGHT) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_iem_right, parent, false);
        return new MessageAdapter.myviewholder(view);
    }else {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left, parent, false);
        return new MessageAdapter.myviewholder(view);
    }
        }

    @Override
    public void onBindViewHolder(@NonNull  MessageAdapter.myviewholder holder, int position) {
         Chat chat=mChat.get(position);
         holder.show_message.setText(chat.getMessage());
    }


    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        public TextView show_message;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            show_message = (TextView) itemView.findViewById(R.id.show_message);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;
        }else {
            return MSG_TYPE_LEFT;
        }

    }
}
