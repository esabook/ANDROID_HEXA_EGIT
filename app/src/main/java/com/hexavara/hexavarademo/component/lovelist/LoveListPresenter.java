package com.hexavara.hexavarademo.component.lovelist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hexavara.hexavarademo.api.ApiFactory;
import com.hexavara.hexavarademo.api.ILoveListServices;
import com.hexavara.hexavarademo.model.LoveItemModel;
import com.hexavara.hexavarademo.model.UserModel;

import java.util.ArrayList;

import io.realm.Realm;
import retrofit2.Callback;
import retrofit2.Response;

public class LoveListPresenter extends BaseObservable {
    private static final String TAG = "LOVE_LIST_VIEW_MODEL";

    /**
     * love item collection storage
     */
    private ArrayList<LoveItemModel> loveItems = new ArrayList<>();

    /**
     * love list recycle adapter
     */
    @Bindable
    public LoveListRecycleViewAdapter recycleViewAdapter = new LoveListRecycleViewAdapter(loveItems);

    /**
     * http request callback when receiving data
     */
    private Callback<ArrayList<LoveItemModel>> loveListCallback = new Callback<ArrayList<LoveItemModel>>() {
        @Override
        public void onResponse(retrofit2.Call<ArrayList<LoveItemModel>> call, Response<ArrayList<LoveItemModel>> response) {
            if (response.isSuccessful()) {
                loveItems.addAll(response.body());
                recycleViewAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(retrofit2.Call<ArrayList<LoveItemModel>> call, Throwable t) {

        }
    };

    /**
     * user info for
     *
     * @return
     */
    @Bindable
    public UserModel getUserInfo() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(UserModel.class).findFirst();
    }

    @Bindable
    public ArrayList<LoveItemModel> getLoveItems() {
        return this.loveItems;
    }

    public void setLoveItems(ArrayList<LoveItemModel> loveItems) {
        this.loveItems = loveItems;
    }

    public void updateLoveItems() {
        loveItems.clear();
        ILoveListServices loveListServices = new ApiFactory().getRetrofit().create(ILoveListServices.class);
        loveListServices.getLoveList().enqueue(this.loveListCallback);
    }

}
