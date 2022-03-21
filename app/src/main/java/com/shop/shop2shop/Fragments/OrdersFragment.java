

package com.shop.shop2shop.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shop.shop2shop.Adpaters.OrdersAdapter;
import com.shop.shop2shop.Interface.RetrofitInterface;
import com.shop.shop2shop.Models.ResponseModel;
import com.shop.shop2shop.Models.RidersOrder;
import com.shop.shop2shop.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OrdersFragment extends Fragment {
View root;
    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "https://api.freightintime.com/";

    private OrdersAdapter adapter;
    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       root = inflater.inflate(R.layout.fragment_orders, container, false);
       recyclerView = root.findViewById(R.id.myOrders);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<ResponseModel> call = retrofitInterface.getOrder();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if(response.body().getStatus().equals("ok")) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();

                        return;
                    }

                    if (response.code() == 200){
                        List<RidersOrder> articleList = response.body().getRidersOrders();

                        Log.i("TAG", "Elmasha: " + articleList);
                    }else if (response.code() == 404){


                        Log.i("TAG", "Elmasha: " + response.body());
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("TAG", "ElmashaInfo: " + t.getMessage());
            }
        });



        new fetchData().execute();
        return root;
    }

    private void LoadOrders() {


    }


    public class fetchData extends AsyncTask<String, String, String> {
        @Override
        public void onPreExecute() {
            super .onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String result = null;
            try {
                URL url = new URL("http://api.freightintime.com/riderOrders");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String temp;

                    while ((temp = reader.readLine()) != null) {
                        stringBuilder.append(temp);
                    }
                    result = stringBuilder.toString();
                }else  {
                    result = "error";
                }

            } catch (Exception  e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        public void onPostExecute(String s) {
            super .onPostExecute(s);
            if (s != null){

                try {
                    // System.out.println(object + "ggggggggggggggggggggggggggggggggggppppppppppppppppppeeeeeeeeeeeeeeeeeeeeettttttttttttttttttt");
                   // Log.i("TAG", "Elmasha2: " + objects);

                    List<RidersOrder> yourPojos = new ArrayList<RidersOrder>();
                    JSONObject jsonObject = new JSONObject(s);
                    RidersOrder yourPojo = new RidersOrder();
                    yourPojo.setID(jsonObject.getString("CF570640-17DE-482A-B257-97DE238499DF"));
                    yourPojos.add(yourPojo);

                     Log.i("TAG", "Elmasha22: " + yourPojo);

                } catch (JSONException e) {
                    System.out.println( "");

                    e.printStackTrace();
                    Log.i("TAG", "Elmasha2: " +  e.getMessage());
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            //CustomAdapter adapter = new CustomAdapter(MainActivity.this, arrayList);
            //listView.setAdapter(adapter);

        }
    }

}