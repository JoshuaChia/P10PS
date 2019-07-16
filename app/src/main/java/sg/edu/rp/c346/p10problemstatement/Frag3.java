package sg.edu.rp.c346.p10problemstatement;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Frag3 extends Fragment {

    Button btnChangeColor;
    ArrayList<String> alcolor;
    public Frag3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_3, container,false);
        btnChangeColor = view.findViewById(R.id.btnColor);
        alcolor = new ArrayList<String>();
        alcolor.add("RED");
        alcolor.add("YELLOW");
        alcolor.add("GRAY");
        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(alcolor);
                alcolor.get(0);

                int color;
                if ( alcolor.get(0).equals("RED")) {
                    color = Color.RED;
                } else if (alcolor.get(0).equals("YELLOW")) {
                    color = Color.YELLOW;
                } else if (alcolor.get(0).equals("GRAY")) {
                    color = Color.GRAY;
                } else {
                    color = Color.BLUE;
                }
                view.setBackgroundColor(color);
            }
        });
        return view;
    }

}