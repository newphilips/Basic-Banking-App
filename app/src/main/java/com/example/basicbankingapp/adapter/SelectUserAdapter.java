package com.example.basicbankingapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.data.User;

import java.util.ArrayList;
import java.util.List;

public class SelectUserAdapter extends RecyclerView.Adapter<SelectUserAdapter.SelectUserViewHolder> {

    public interface UserClickedListener{
        void userClicked(int position);
    }

    UserClickedListener mListener;
    Context mContext;
    List<User> mList = new ArrayList<>();

    public SelectUserAdapter(Context context) {
        mContext  = context;
    }

    public void setList(List<User> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setmListener(Context context){
        mListener = (UserClickedListener) context;
    }

    static class SelectUserViewHolder extends RecyclerView.ViewHolder{
        TextView mUserName;
        TextView mUserPhone;

        public SelectUserViewHolder(@NonNull View itemView, UserClickedListener listener) {
            super(itemView);
            mUserName = itemView.findViewById(R.id.customer_name);
            mUserPhone = itemView.findViewById(R.id.customer_phone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener instanceof UserClickedListener){
                        int position = getAdapterPosition();
                        listener.userClicked(position);
                    }
                    else{
                        Log.d("Not an instance of: ", " UserClickedListener");
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public SelectUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.select_user_row, parent, false);
        return new SelectUserViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectUserViewHolder holder, int position) {
        holder.mUserName.setText(mList.get(position).getUser_name());
        holder.mUserPhone.setText(mList.get(position).getUser_phone());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
