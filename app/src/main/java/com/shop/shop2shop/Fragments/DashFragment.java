package com.shop.shop2shop.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shop.shop2shop.Activities.UploadActivity;
import com.shop.shop2shop.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DashFragment extends Fragment {
View root;

    private CardView newOrder;


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
        newOrder = root.findViewById(R.id.new_Order);

        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), UploadActivity.class));
            }
        });
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());

        TimeDate.setText(date);


        return root;
    }
}