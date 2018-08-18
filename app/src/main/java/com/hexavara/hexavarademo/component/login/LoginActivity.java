package com.hexavara.hexavarademo.component.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hexavara.hexavarademo.R;
import com.hexavara.hexavarademo.databinding.ActivityLoginBinding;

import org.parceler.Parcels;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LOGIN";

    LoginModel loginModel;
    ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        if (savedInstanceState != null) {
            loginModel = Parcels.unwrap(savedInstanceState.getParcelable(TAG));
        } else {
            loginModel = new LoginModel();
        }

        loginBinding.setLoginInfo(loginModel);
        loginBinding.setLoginPresenter(new LoginPresenter());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(TAG, Parcels.wrap(loginModel));
    }
}
