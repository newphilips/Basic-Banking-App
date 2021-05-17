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

public class TransferMoneyDialog extends AppCompatDialogFragment {

    String from_user_name;
    String to_user_name;
    int from_user_id;
    int money;

    public interface ConfirmTransferListener{
        void confirmTransfer(boolean isConfirmed);
    }

    ConfirmTransferListener mTransferListener;

    public void setDetails(String from_user_name, String to_user_name, int from_user_id, int money){
        this.from_user_name = from_user_name;
        this.to_user_name = to_user_name;
        this.from_user_id = from_user_id;
        this.money = money;
    }

    FragmentActivity mActivity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mActivity = getActivity();
        return openTransferDialog(getActivity());
    }

    Dialog openTransferDialog(FragmentActivity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setTitle("Are you sure to transfer " + money + " to " + to_user_name)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTransferListener.confirmTransfer(false);
                        dismiss();
                    }
                })
                .setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do updation in db...
                        mTransferListener.confirmTransfer(true);
                        dismiss();
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mTransferListener = (ConfirmTransferListener) context;
    }
}