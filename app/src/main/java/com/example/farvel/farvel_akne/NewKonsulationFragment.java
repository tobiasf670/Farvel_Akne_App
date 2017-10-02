package com.example.farvel.farvel_akne;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Tobias on 11-09-2017.
 */

public class NewKonsulationFragment extends Fragment {

    View myView;
    Button btn_new;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.newkonsulation_layout,container,false);

        btn_new = (Button) myView.findViewById(R.id.btn_newkons);
        btn_new.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.content_frame1, new TakePicFragment()).commit();

            }
        });
        return myView;
    }
}
