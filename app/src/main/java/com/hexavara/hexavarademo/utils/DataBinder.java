package com.hexavara.hexavarademo.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.hexavara.hexavarademo.R;

import de.hdodenhof.circleimageview.CircleImageView;

public final class DataBinder {
    private DataBinder() {

    }

    @BindingAdapter("app:imageURL")
    public static void loadImage(CircleImageView view, String url) {
        getGlideApp(url).into(view);
    }

    @BindingAdapter("app:imageURL")
    public static void loadImage(ImageView view, String url) {
        getGlideApp(url).into(view);
    }

    private static GlideRequest<Drawable> getGlideApp(String url) {
        return GlideApp.with(App.getAppContext())
                .load(Uri.parse(url))
                .centerInside()
                .encodeQuality(80)
                .error(R.drawable.ic_image_off);
    }
}
