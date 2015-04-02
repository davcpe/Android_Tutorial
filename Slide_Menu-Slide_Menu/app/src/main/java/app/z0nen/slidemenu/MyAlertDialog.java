package app.z0nen.slidemenu;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by davcpe on 3/28/2015.
 */
public class MyAlertDialog extends DialogFragment{
    AlertDialog.Builder objAlert;
    public  void errorDialog(Context context, String strTitle, String strMessage){

        objAlert = new AlertDialog.Builder(context);
        objAlert.setIcon(R.drawable.questionicon);
        objAlert.setTitle(strTitle);
        objAlert.setMessage(strMessage);
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();

    }// errorDialog
}
