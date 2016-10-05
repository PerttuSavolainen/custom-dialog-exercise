package fi.jamk.customdialogexercise;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;

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
    public void onAttach(Context context){
    //public void onAttach(Activity activity){ // deprecated
        //super.onAttach(activity);
        super.onAttach(context);
        Activity activity = (Activity) context;

        try {
            mListener = (TextEntryDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement TextEntryDialogListener");
        }
    }

}
