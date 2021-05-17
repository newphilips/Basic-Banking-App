package com.example.basicbankingapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.data.User;

import java.util.ArrayList;
import java.util.List;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.AllUsersViewHolder> implements Filterable {

    public interface UserClickedListener {
        void userClicked(int position);
    }

    UserClickedListener mListener;
    Context mContext;
    List<User> mList = new ArrayList<>();
    List<User> mFullList = new ArrayList<>();

    public AllUsersAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<User> list) {
        mList = list;
        mFullList = new ArrayList<>(mList);
        notifyDataSetChanged();
    }

    public void setmListener(Context context) {
        mListener = (UserClickedListener) context;
    }

    static class AllUsersViewHolder extends RecyclerView.ViewHolder {
        TextView mUserName;
        TextView mUserPhone;
        TextView mUserBalance;

        public AllUsersViewHolder(@NonNull View itemView, UserClickedListener listener) {
            super(itemView);
            mUserName = itemView.findViewById(R.id.customer_name);
            mUserBalance = itemView.findViewById(R.id.customer_balance);
            mUserPhone = itemView.findViewById(R.id.customer_phone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener instanceof UserClickedListener) {
                        int position = getAdapterPosition();
                        listener.userClicked(position);
                    } else {
                        Log.d("Not an instance of: ", " UserClickedListener");
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public AllUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.customer_row, parent, false);
        return new AllUsersViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AllUsersViewHolder holder, int position) {
        holder.mUserName.setText(mList.get(position).getUser_name());
        holder.mUserPhone.setText(mList.get(position).getUser_phone());
        holder.mUserBalance.setText("Rs. " + mList.get(position).getUser_balance() + "/-");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<User> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mFullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (User thisUser : mFullList) {
                    if (thisUser.user_name.toLowerCase().contains(filterPattern)) {
                        filteredList.add(thisUser);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList.clear();
            mList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
