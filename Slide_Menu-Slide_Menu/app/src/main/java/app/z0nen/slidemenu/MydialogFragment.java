package app.z0nen.slidemenu;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by davcpe on 3/28/2015.
 */
public class MydialogFragment extends DialogFragment implements  View.OnClickListener {

    private Button btn;
    private  View rootview;


    public View onCreView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState){
        rootview = inflater.inflate(R.layout.dialog_fragment, container, false);
        btn = (Button)rootview.findViewById(R.id.buttonFFF);


        return rootview;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.buttonFFF){

            Toast.makeText(getActivity(),"Fuck Yeah",Toast.LENGTH_LONG).show();
        }

    }
}
