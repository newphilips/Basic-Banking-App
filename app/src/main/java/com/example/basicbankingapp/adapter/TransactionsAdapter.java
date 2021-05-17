package com.example.basicbankingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.data.Transactions;

import java.util.ArrayList;
import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>{

    Context mContext;

    public void setList(List<Transactions> list) {
        this.list = list;
    }

    List<Transactions> list = new ArrayList<>();

    public TransactionsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout cons_layout;
        TextView from_user_name;
        TextView to_user_name;
        TextView text_money;
        TextView text_time;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            cons_layout = itemView.findViewById(R.id.cons_layout);
            from_user_name = itemView.findViewById(R.id.from_user);
            to_user_name = itemView.findViewById(R.id.to_user);
            text_money = itemView.findViewById(R.id.text_money);
            text_time = itemView.findViewById(R.id.text_time);
        }
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.transaction_row, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {

        if(list.get(position).getStatus() == 1)
            holder.cons_layout.setBackgroundResource(R.color.light_green);

        else
            holder.cons_layout.setBackgroundResource(R.color.light_red);


        String string_money = String.valueOf(list.get(position).getMoney());
        String string_formatted = "Rs. " + string_money + " /-";
        holder.from_user_name.setText(list.get(position).getFrom());
        holder.to_user_name.setText(list.get(position).getTo());
        holder.text_money.setText(string_formatted);
        holder.text_time.setText(list.get(position).getTime().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
