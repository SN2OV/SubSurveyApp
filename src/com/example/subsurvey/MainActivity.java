package com.example.subsurvey;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.old.ODSurveySettingActivity;
import com.example.subsurvey.old.StaySurveySettingActivity;
import com.example.subsurvey.old.WalkSurveySettingActivity;
import com.example.subsurvey.staySurvey.ui.StaySurveyDataTotalActivity;
import com.example.subsurvey.transferSurvey.ui.TransferSurveySettingActivity;
import com.example.subsurvey.walkSurvey.adapter.WalkSurveyDataAdapter;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;
import com.example.subsurvey.walkSurvey.helper.WalkSurveyDataHelper;
import com.example.subsurvey.walkSurvey.ui.WalkSurveyDataTotalActivity;

import android.os.Bundle;
import android.os.Environment;
import android.provider.SyncStateContract.Helpers;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	public Context context = null;
	ImageView moreSurveyImageView,walkImageView,odSurveyImageVIew,staySurveyImageView,transferSurvetImageView;
	//StopSurveyImageView
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		context = getApplicationContext();
		
		walkImageView = (ImageView) findViewById(R.id.WalkSurveyImageView);
		walkImageView.setOnClickListener(new onClickListenerWalkImpl());
		moreSurveyImageView = (ImageView)findViewById(R.id.MoreSurveyImageView);
		moreSurveyImageView.setOnClickListener(new onClickListenerMoreImpl());
		odSurveyImageVIew=(ImageView)findViewById(R.id.ODSurveyImageView);
		odSurveyImageVIew.setOnClickListener(new onClickListenerWalkImpl());
		staySurveyImageView = (ImageView)findViewById(R.id.StopSurveyImageView);
		staySurveyImageView.setOnClickListener(new onClickListenerWalkImpl());
		transferSurvetImageView = (ImageView)findViewById(R.id.TransferSurvetImageView);
		transferSurvetImageView.setOnClickListener(new onClickListenerWalkImpl());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//走行时间调查跳转
	public class onClickListenerWalkImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent it = new Intent();
			switch(v.getId()){
			case R.id.WalkSurveyImageView:
				it.setClass(context, WalkSurveySettingActivity.class);
				break;
			case R.id.StopSurveyImageView:
				it.setClass(context, StaySurveySettingActivity.class);
				break;
			case R.id.TransferSurvetImageView:
				it.setClass(context, TransferSurveySettingActivity.class);
				break;
			case R.id.ODSurveyImageView:
				it.setClass(context, ODSurveySettingActivity.class);
				break;
			default:
				break;
			}
			startActivity(it);
		}
	}
	
	//更多调查跳转
	public class onClickListenerMoreImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent it = new Intent();
			it.setClass(context, WalkSurveyDataTotalActivity.class);
			startActivity(it);
		}
		
	}
	
	public class onClickListenerODMoreImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			
		}
	}
	
}
