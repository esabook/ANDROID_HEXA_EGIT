package com.hexavara.hexavarademo.api;

import com.hexavara.hexavarademo.component.login.LoginModel;
import com.hexavara.hexavarademo.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthServices {

    @POST("login")
    Call<UserModel> LoginAuth(@Body LoginModel loginModel);
}
