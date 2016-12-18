package com.example.subsurvey.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.multidex.MultiDex;

/*
 * @function 存储系统信息
 */
public class BaseApplication extends Application {
	 static Context _context;

	@Override
	public void onCreate() {
		super.onCreate();
		_context = getApplicationContext();
	}
	
	public static synchronized BaseApplication context() {
		return (BaseApplication) _context;
	}
	
    
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static SharedPreferences getPreferences(String prefName) {
	return context().getSharedPreferences(prefName,
		Context.MODE_MULTI_PROCESS);
    }

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
}
