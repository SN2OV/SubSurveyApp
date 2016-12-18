package com.example.subsurvey.base;

import java.util.List;

import com.example.subsurvey.AppContext;
import com.example.subsurvey.personalSetting.UserEntity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

abstract public class BaseActivity extends ActionBarActivity {
	protected Context context;
	protected Intent it;
	protected UserEntity user;
	protected AppContext appContext;
}