package com.hexavara.hexavarademo.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserModel extends RealmObject {
    @PrimaryKey
    public String username;
    public String token;
    public String email;
    public String fullname;
    public String address;
    public String photo;
}
