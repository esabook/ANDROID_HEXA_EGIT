package com.hexavara.hexavarademo.component.login;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.hexavara.hexavarademo.R;
import com.hexavara.hexavarademo.api.ApiFactory;
import com.hexavara.hexavarademo.api.IAuthServices;
import com.hexavara.hexavarademo.component.lovelist.LoveListActivity;
import com.hexavara.hexavarademo.model.UserModel;
import com.hexavara.hexavarademo.utils.App;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    public static final String TAG = "LOGIN_PRESENTER";

    /**
     * @param loginModel
     */
    public void OnSubmit(LoginModel loginModel) {
        if (!(loginModel.isPasswordValid() &&
                loginModel.isUsernameValid())) {
            try {
                showErrorToast(App.getAppContext().getString(R.string.warning_please_fill_the_required_form));
            } catch (Exception e) {
            }

            return;
        }

        IAuthServices authServices = new ApiFactory().getRetrofit().create(IAuthServices.class);
        authServices.LoginAuth(loginModel).enqueue(loginAuthenticationCallback);

    }

    /**
     * retrofit callback for authentication
     */
    private Callback<UserModel> loginAuthenticationCallback = new Callback<UserModel>() {
        @Override
        public void onResponse(Call<UserModel> call, Response<UserModel> response) {


            if (response.isSuccessful()) {
                saveCurrentUserSession(response.body());
                setLoggedIn();
            } else {
                try {
                    showErrorToast(response.errorBody().string());
                } catch (IOException e) {
                    Log.d(TAG, e.getLocalizedMessage());
                }

            }
        }

        @Override
        public void onFailure(Call<UserModel> call, Throwable t) {
            showErrorToast(t.getMessage());
        }
    };

    /**
     * save user info to local storage
     *
     * @param userModel
     */
    private void saveCurrentUserSession(UserModel userModel) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(userModel));
    }

    /**
     * Error toast
     *
     * @param msg
     */
    private void showErrorToast(String msg) {
        try {
            Toasty.error(
                    App.getAppContext(),
                    msg,
                    Toast.LENGTH_SHORT,
                    true)
                    .show();
        } catch (Exception e) {
        }

    }

    public void setLoggedIn() {
        if (isLogedIn()) {
            Intent i = new Intent(App.getAppContext(), LoveListActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            App.getAppContext().startActivity(i);
        }
    }


    public boolean isLogedIn() {
        return Realm.getDefaultInstance().where(UserModel.class).findFirst() != null;
    }


}
