package fi.jamk.customdialogexercise;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Pedo on 5.10.2016.
 */
public class TextEntryDialogFragment extends DialogFragment {

    public interface TextEntryDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String text);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    TextEntryDialogListener mListener;

    @Override
    public void onAttach(Activity activity){ // < api 23
    //public void onAttach(Context context){ // >= api 23
        //super.onAttach(context);
        //Activity activity = (Activity) context; // content can be typecasted to Activity

        super.onAttach(activity);

        try {
            mListener = (TextEntryDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement TextEntryDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // create an AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // inflate and set the layout for the dialog
        final View dialogView = inflater.inflate(R.layout.textentry_dialog, null);

        builder.setView(dialogView).setTitle("Give a new text").setPositiveButton("Add", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id) {
                EditText editText = (EditText) dialogView.findViewById(R.id.editText);
                String text = editText.getText().toString();
                // send the positive btn event back to the host activity
                mListener.onDialogPositiveClick(TextEntryDialogFragment.this, text);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
                // send the negative btn event back to the host activity
                mListener.onDialogNegativeClick(TextEntryDialogFragment.this);
            }
        });
        return builder.create();
    }
}
