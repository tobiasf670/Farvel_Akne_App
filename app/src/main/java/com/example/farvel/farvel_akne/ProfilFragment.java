package com.example.farvel.farvel_akne;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tobias on 11-09-2017.
 */

public class ProfilFragment extends Fragment {

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_profil,container,false);
        ((MainActivity)getActivity()).setColorOnBtn(R.layout.fragment_profil);
        return myView;
    }
}
