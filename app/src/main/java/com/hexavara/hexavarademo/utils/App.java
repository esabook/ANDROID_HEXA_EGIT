package com.hexavara.hexavarademo.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;

import io.realm.Realm;

public class App extends MultiDexApplication {
    private static Context context;
    private static Activity activity;


    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        Realm.init(this);

        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                setActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                setActivity(activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                setActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                setActivity(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                setActivity(null);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                setActivity(null);
            }
        });
    }

    public static void setActivity(Activity activity) {
        App.activity = activity;
    }

    public static Activity getActivity() {
        return App.activity;
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static boolean isActCtxFound() {
        return getActivity() != null && getActivity().getApplicationContext() != null;
    }

    public static boolean isAppCtxFound() {
        return getAppContext() != null;
    }

}
