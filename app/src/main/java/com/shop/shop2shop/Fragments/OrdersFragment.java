

package com.shop.shop2shop.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import java.util.Iterator;
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
    ArrayList<RidersOrder> myorders;
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
       // recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                    myorders = new ArrayList<>();
                    JSONObject object = new JSONObject(s);
                    //loop on every item of the json
                    Iterator<?> keys = object.keys();
                    while(keys.hasNext() ) {
                        String key = (String)keys.next();
                        if ( object.get(key) instanceof JSONObject ) {

                            // create a string of every respose filed  example Status,OrderNumber,Latitude,Longitude..
                            String status = ((JSONObject) object.get(key)).getString("Status") ;
                            //your order class..sijui umetumia gani but use this method to add new items to your orders array

                            // ArrayList<Order> myorders --> hii weka huko juu place ya ku define variabes
                            //         myorders = new ArrayList<>() -- > hii weka kwa oncreate method

                            myorders.add(new RidersOrder(((JSONObject) object.get(key)).getString("ID"), ((JSONObject) object.get(key)).getString("Name"), ((JSONObject) object.get(key)).getString("Name"), "21", "100", ((JSONObject) object.get(key)).getString("FirstName"), ((JSONObject) object.get(key)).getString("LastName"), ((JSONObject) object.get(key)).getString("OrderNumber")));



                        }
                    }

                    //ikishamaliza kuloop kwa while, initiate adpter
                    adapter = new OrdersAdapter( myorders);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    System.out.println( "");

                    e.printStackTrace();
                }
            }
            //CustomAdapter adapter = new CustomAdapter(MainActivity.this, arrayList);
            //listView.setAdapter(adapter);

        }
    }

}