package com.ibraheem.nashat.trackmyfood4.application;

import android.app.Application;

/**
 * Created by nashati on 12/29/2015.
 */
public class MyApp extends Application {
    public static Storage storage;

    @Override
    public void onCreate() {
        super.onCreate();
        storage = new Storage(this);
    }
}
