package com.example.mysafedisclosure;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Html;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InterventionDialog extends AppCompatDialogFragment {

    private InterventionDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String warningMsg = getArguments().getString("Warning");
        String warningHeader = getArguments().getString("Header");
        final int idMsg = getArguments().getInt("Id");

        /*final TextView text = new TextView(getContext());
        text.setText(Html.fromHtml(warningMsg));
        text.setGravity(Gravity.CENTER);
        text.setTextSize(16);
        text.setTextColor(Color.BLACK);
        text.setPadding(10, 12, 10, 12);*/

        builder.setTitle(warningHeader)
                .setMessage(Html.fromHtml(warningMsg))
                //.setView(text)
                .setCancelable(false)
                .setIcon(R.drawable.privacy_icon)//change the icon later
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.OnEditClicked(idMsg);

                    }
                })
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.OnPostClicked(idMsg);
                    }
                });



        return builder.create();
    }

    public interface InterventionDialogListener{//Methods that must be implemented by the class that uses this interface
        void OnPostClicked(int idMsg);
        void OnEditClicked(int idMsg);
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
