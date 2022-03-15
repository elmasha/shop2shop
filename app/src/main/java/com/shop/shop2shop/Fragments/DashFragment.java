package com.shop.shop2shop.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shop.shop2shop.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DashFragment extends Fragment {
View root;

    public DashFragment() {
        // Required empty public constructor
    }


private TextView TimeDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_dash, container, false);
        TimeDate = root.findViewById(R.id.Date);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());

        TimeDate.setText(date);


        return root;
    }
}