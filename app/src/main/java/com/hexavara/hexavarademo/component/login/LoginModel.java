package com.hexavara.hexavarademo.component.login;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hexavara.hexavarademo.BR;
import com.hexavara.hexavarademo.R;
import com.hexavara.hexavarademo.utils.App;

import org.parceler.Parcel;

@Parcel
public class LoginModel extends BaseObservable {

    private String username;
    private String password;


    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
        notifyPropertyChanged(BR.usernameErrorMessage);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
        notifyPropertyChanged(BR.passwordErrorMessage);
    }

    @Bindable
    public String getUsernameErrorMessage() {
        return isUsernameValid() ? null : App.getAppContext().getResources().getString(R.string.error_username_cannot_empty);
    }

    @Bindable
    public String getPasswordErrorMessage() {
        return isPasswordValid() ? null : App.getAppContext().getResources().getString(R.string.error_password_length_to_short);
    }

    @Bindable
    public boolean isPasswordValid() {
        return password != null && password.length() > 6;
    }

    @Bindable
    public boolean isUsernameValid() {
        return username != null && !username.isEmpty();
    }
}
