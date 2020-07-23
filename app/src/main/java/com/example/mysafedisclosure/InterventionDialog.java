package com.example.mysafedisclosure;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class InterventionDialog extends AppCompatDialogFragment {

    private InterventionDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Privacy Hint")
                .setMessage("Do you really want to share this?")
                .setIcon(R.drawable.privacy_icon)//change the icon later
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.OnPostClicked();
                    }
                });
        return builder.create();
    }

    public interface InterventionDialogListener{
        void OnPostClicked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (InterventionDialogListener) context;
        }
        catch(ClassCastException e){
            throw new ClassCastException(context.toString()+"Must implement InterventionDialogListener");
        }
    }
}
