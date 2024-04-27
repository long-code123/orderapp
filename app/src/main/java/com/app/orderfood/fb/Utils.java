package com.app.orderfood.fb;

import com.google.firebase.auth.FirebaseAuth;

public class Utils {

    public static long getNewId() {
        return System.currentTimeMillis();
    }

    public static String getUID() {
        return "mw2lJ6D40pOiPHoZCNd4GpQ2UHy2";
        //return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}
