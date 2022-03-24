package com.shop.shop2shop.Adpaters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shop.shop2shop.Models.RidersOrder;
import com.shop.shop2shop.R;

import java.util.List;

public class OrdersAdapter  extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {


    private List<RidersOrder> ridersOrderArrayList;
    private Context context;



    public OrdersAdapter(List<RidersOrder> ridersOrderList) {
        this.ridersOrderArrayList = ridersOrderList;
    }


    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row, parent, false);
        return new OrdersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {
        final RidersOrder articleModel = ridersOrderArrayList.get(position);
        if(!TextUtils.isEmpty(articleModel.getName())) {
            holder.titleText.setText(articleModel.getName());
            // holder.date.setText(articleModel.get);
            holder.order_number.setText(articleModel.getOrderNumber());
            holder.pickup_point.setText(articleModel.getContact_PersonName_FirstName());



        }

    }

    @Override
    public int getItemCount() {
        return ridersOrderArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText,date,order_number,pickup_point;
        private ImageView article_image;
        private TextView descriptionText;
        private LinearLayout artilceAdapterParentLinear;
        public ViewHolder(@NonNull View view) {
            super(view);
//            titleText = view.findViewById(R.id.row_text);
//            date =  view.findViewById(R.id.date);
//            order_number =  view.findViewById(R.id.order_number);
//            pickup_point =  view.findViewById(R.id.pickup_point);


        }
    }

}