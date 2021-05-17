package com.example.basicbankingapp;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.example.basicbankingapp.ui.SelectUser;

public class EnterAmountDialog extends AppCompatDialogFragment {

    public interface DialogAmountListener{
        void sendMoney(int amount);
    }

    private EditText editAmount;
    FragmentActivity mActivity;
    DialogAmountListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mActivity = getActivity();
        return openTransferDialog(getActivity());
    }

    Dialog openTransferDialog(FragmentActivity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.enter_amount_dialog, null);
        editAmount = view.findViewById(R.id.edit_amount);

        builder.setView(view)
                .setTitle("Enter amount")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showCancellationDialog(getContext());
                    }
                })
                .setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String amountString = editAmount.getText().toString();
                        if(!(amountString.equals(""))) {
                            int amount = Integer.parseInt(amountString);
                            //transfer money intent
                            Toast.makeText(getContext(), "Transferring amount: " + amount, Toast.LENGTH_SHORT).show();
                            mListener.sendMoney(amount);
                            dismiss();
                        }
                        else{
                            Toast.makeText(getContext(), "Please enter the amount!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return builder.create();
    }

    public void showCancellationDialog(Context ctx){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Cancel transfer?")
                .setMessage("Are you sure to cancel the transfer process?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ctx, "Continue your transaction", Toast.LENGTH_SHORT).show();
                        openTransferDialog(mActivity);
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ctx, "Transferring amount canceled", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                });

        builder.show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (DialogAmountListener) context;
    }
}