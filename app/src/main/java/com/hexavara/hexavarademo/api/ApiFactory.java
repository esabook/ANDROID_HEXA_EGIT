package com.hexavara.hexavarademo.api;


import android.util.Log;

import com.hexavara.hexavarademo.model.UserModel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private static final String TAG = "API_FACTORY";
    private static final String url = "http://hexavara.ip-dynamic.com/androidrec/public/api/";
    private int connectTimeOutMS = 1000;
    private int readTimeOutMS = 1000;

    /**
     * create retrofit object based
     *
     * @param t Class of T
     * @return
     */
    public <T> T create(Class<T> t) {
        return getRetrofit().create(t);
    }


    /**
     * Get retrofit build
     *
     * @return Retrofit
     */
    public Retrofit getRetrofit() {
        UserModel userModel = Realm.getDefaultInstance().where(UserModel.class).findFirst();
        return getRetrofit(userModel != null ? userModel.token : "", url);
    }

    /**
     * Get retrofit build with token
     *
     * @return Retrofit
     */
    public Retrofit getRetrofit(String token, String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpConfiguration(token))
                .build();
    }

    /**
     * @param url
     * @return Retrofit
     */
    public Retrofit getRetrofit(String url) {
        return getRetrofit(url);
    }


    /**
     * @return OkHttpClient
     */
    public OkHttpClient getHttpConfiguration(String token) {


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        Log.i(TAG, token);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request()
                                .newBuilder()
                                .addHeader("Authorization", token)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(interceptor)
                .connectTimeout(connectTimeOutMS, TimeUnit.SECONDS)
                .readTimeout(readTimeOutMS, TimeUnit.SECONDS)
                .build();
        return client;
    }
}



