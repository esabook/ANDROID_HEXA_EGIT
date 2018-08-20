package com.hexavara.hexavarademo.component.lovelist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hexavara.hexavarademo.model.LoveItemModel;

public class LoveItemViewModel extends BaseObservable {
    @Bindable
    public LoveItemModel model = new LoveItemModel();

    public LoveItemViewModel() {
    }

    public LoveItemViewModel(LoveItemModel model) {
        this.model = model;
    }

}
