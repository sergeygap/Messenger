package com.gap.messenger.listDialogs;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gap.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private List<User> users = new ArrayList<>();

    private ClickOnItem clickOnItem;

    @SuppressLint("NotifyDataSetChanged")
    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public void setClickOnItem(ClickOnItem clickOnItem) {
        this.clickOnItem = clickOnItem;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        User user = users.get(position);
        String userInfo = String.format("%s %s, %s", user.getName(), user.getLastName(), user.getAge());
        holder.textViewUserInfo.setText(userInfo);
        int bgResId;
        Log.d("TAGTAG", "onBindViewHolder: " + user.isOnline());
//        if (user.getOnline()) {
//            bgResId = R.drawable.circle_green;
//        } else {
//            bgResId = R.drawable.circle_red;
//        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.circle_red);
        holder.isStatus.setBackground(background);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickOnItem != null) {
                    clickOnItem.clickOnItem(user);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    interface ClickOnItem {
        void clickOnItem(User user);
    }

    static class UsersViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewUserInfo;
        private View isStatus;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserInfo = itemView.findViewById(R.id.tv_info);
            isStatus = itemView.findViewById(R.id.isStatus);
        }
    }

}
