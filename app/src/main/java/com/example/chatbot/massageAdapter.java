package com.example.chatbot;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.ConversationActions;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class massageAdapter  extends RecyclerView.Adapter<massageAdapter.viewHolder> {

    List<messageModel>modelList;

    public massageAdapter(List<messageModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_massage,null);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {


        messageModel model=modelList.get(position);
        if (model.getSentBy().equals(messageModel.sent_By_Me)){

            holder.leftchat.setVisibility(View.GONE);
            holder.rightchat.setVisibility(View.VISIBLE);
            holder.rightText.setText(model.getMessage());
        }
        else {
            holder.leftchat.setVisibility(View.VISIBLE);
            holder.rightchat.setVisibility(View.GONE);
            holder.leftText.setText(model.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout leftchat,rightchat ;
        TextView  leftText,rightText;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            leftchat=itemView.findViewById((R.id.left_chat));
            rightchat=itemView.findViewById((R.id.right_chat));
            leftText=itemView.findViewById((R.id.left_message));
            rightText=itemView.findViewById((R.id.right_message));
        }
    }

}













