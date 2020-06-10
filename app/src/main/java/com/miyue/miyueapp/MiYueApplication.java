package com.miyue.miyueapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import android.os.Bundle;
import android.support.multidex.MultiDexApplication;



/**
 * Created by Administrator on 2019-10-08.
 */

public class MiYueApplication extends MultiDexApplication {
    public static Context applicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
    }
}
