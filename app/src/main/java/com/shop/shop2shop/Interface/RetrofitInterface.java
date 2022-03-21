package com.shop.shop2shop.Interface;

import com.shop.shop2shop.Models.ResponseModel;
import com.shop.shop2shop.Models.RidersOrder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @GET("/riderOrders")
    Call<ResponseModel> getOrder();
//
//    @POST("/stk_callback")
//    Call<ResultStk>  getResponse();

//    @GET("/")
//    Call<Result>  getResult();
//
//    @POST("/stk/query")
//    Call<StkQuery>   stk_Query  (@Body Map<String ,String> stkQuey);


}
