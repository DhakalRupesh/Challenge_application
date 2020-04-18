package com.rupesh.baji.helper;

public class ImgStrictMode {
    public static void ImgMode() {
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();

        android.os.StrictMode.setThreadPolicy(policy);
    }

}
