package com.hexavara.hexavarademo.api;

import com.hexavara.hexavarademo.model.LoveItemModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ILoveListServices {
    @GET("mylist")
    Call<ArrayList<LoveItemModel>> getLoveList();
}
