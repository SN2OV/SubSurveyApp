package com.example.subsurvey.staySurvey.ui;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.personalSetting.UserEntity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class SSWordActivity extends ActionBarActivity {
	
	@InjectView(R.id.ss_word_ET)
	EditText ss_word_ET;
	
	private Context context;
	private Intent it;
	private AppContext appContext;
	private UserEntity user;
	private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ssword);
		init();
		initView();
	}
	
	private void init() {
		final ActionBar bar =getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
        bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		ButterKnife.inject(this);
		context = getApplicationContext();
		appContext = (AppContext)getApplication();
		user = appContext.getUser();
	}

	private void initView() {
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		it= getIntent();
		String value = null;
		type = it.getIntExtra("type", 0);
		switch(type){
		case AppConfig.SS_DATA_SETTING_CARRIAGE_LOC_CODE:
			value = it.getStringExtra("carriageLoc");
			break;
		case AppConfig.SS_LINE_FROM_LINE_CODE:
			value = it.getStringExtra("fromLine");
			break;
		case AppConfig.SS_LINE_FROM_LOC_CODE:
			value = it.getStringExtra("fromLoc");
			break;
		case AppConfig.SS_LINE_TO_LINE_CODE:
			value = it.getStringExtra("toLine");
			break;
		case AppConfig.SS_LINE_TO_LOC_CODE:
			value = it.getStringExtra("toLoc");
			break;
			
		case AppConfig.SS_DATA_GO_INTO_STATION_CODE:
			value = it.getStringExtra("ssPerGointoStation");
			break;
		case AppConfig.SS_DATA_GET_OFF_CODE:
			value = it.getStringExtra("ssPerGetoff");
			break;
		case AppConfig.SS_DATA_ARRIVE_PLATFORM_CODE:
			value = it.getStringExtra("ssPerArrivePlatform");
			break;
		case AppConfig.SS_DATA_ORIGN_LINE_NUM_CODE:
			value = it.getStringExtra("ssPerOrignLineNum");
			break;
		case AppConfig.SS_DATA_GET_OFF_NUM1_CODE:
			value = it.getStringExtra("ssPerGetOffNum1");
			break;
		case AppConfig.SS_DATA_GET_ON_NUM1_CODE:
			value = it.getStringExtra("ssPerGetOnNum1");
			break;
		case AppConfig.SS_DATA_TRAIN_START_TIME1_CODE:
			value = it.getStringExtra("ssPerTrainStartTime1");
			break;
		case AppConfig.SS_DATA_GET_OFF_NUM2_CODE:
			value = it.getStringExtra("ssPerGetOffNum2");
			break;
		case AppConfig.SS_DATA_GET_ON_NUM2_CODE:
			value = it.getStringExtra("ssPerGetOnNum2");
			break;
		case AppConfig.SS_DATA_TRAIN_START_TIME2_CODE:
			value = it.getStringExtra("ssPerTrainStartTime2");
			break;
		case AppConfig.SS_DATA_GET_OFF_NUM3_CODE:
			value = it.getStringExtra("ssPerGetOffNum3");
			break;
		case AppConfig.SS_DATA_GET_ON_NUM3_CODE:
			value = it.getStringExtra("ssPerGetOnNum3");
			break;
		case AppConfig.SS_DATA_TRAIN_START_TIME3_CODE:
			value = it.getStringExtra("ssPerTrainStartTime3");
			break;
		case AppConfig.SS_DATA_GET_OFF_NUM4_CODE:
			value = it.getStringExtra("ssPerGetOffNum4");
			break;
		case AppConfig.SS_DATA_GET_ON_NUM4_CODE:
			value = it.getStringExtra("ssPerGetOnNum4");
			break;
		case AppConfig.SS_DATA_TRAIN_START_TIME4_CODE:
			value = it.getStringExtra("ssPerTrainStartTime4");
			break;
		case AppConfig.SS_DATA_GET_OFF_NUM5_CODE:
			value = it.getStringExtra("ssPerGetOffNum5");
			break;
		case AppConfig.SS_DATA_GET_ON_NUM5_CODE:
			value = it.getStringExtra("ssPerGetOnNum5");
			break;
		case AppConfig.SS_DATA_TRAIN_START_TIME5_CODE:
			value = it.getStringExtra("ssPerTrainStartTime5");
			break;
		case AppConfig.SS_DATA_GET_OFF_NUM6_CODE:
			value = it.getStringExtra("ssPerGetOffNum6");
			break;
		case AppConfig.SS_DATA_GET_ON_NUM6_CODE:
			value = it.getStringExtra("ssPerGetOnNum6");
			break;
		case AppConfig.SS_DATA_TRAIN_START_TIME6_CODE:
			value = it.getStringExtra("ssPerTrainStartTime6");
			break;
		}
		ss_word_ET.setText(value);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 MenuInflater inflater = getMenuInflater();
	     inflater.inflate(R.menu.data_edit, menu);
	     MenuItemCompat.setShowAsAction(menu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
	     return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_finished:
			it = new Intent();
			String value = ss_word_ET.getText().toString();
			switch(type){
			case AppConfig.SS_DATA_SETTING_CARRIAGE_LOC_CODE:
				it.putExtra("carriageLoc", value);
				setResult(AppConfig.SS_DATA_SETTING_CARRIAGE_LOC_CODE,it);
				user.setSsCarriageLoc(value);
				break;
			case AppConfig.SS_LINE_FROM_LINE_CODE:
				it.putExtra("fromLine", value);
				setResult(AppConfig.SS_LINE_FROM_LINE_CODE,it);
				user.setSsFromLine(value);
				break;
			case AppConfig.SS_LINE_FROM_LOC_CODE:
				it.putExtra("fromLoc", value);
				setResult(AppConfig.SS_LINE_FROM_LOC_CODE,it);
				user.setSsFromLoc(value);
				break;
			case AppConfig.SS_LINE_TO_LINE_CODE:
				it.putExtra("toLine", value);
				setResult(AppConfig.SS_LINE_TO_LINE_CODE,it);
				user.setSsToLine(value);
				break;
			case AppConfig.SS_LINE_TO_LOC_CODE:
				it.putExtra("toLoc", value);
				setResult(AppConfig.SS_LINE_TO_LOC_CODE,it);
				user.setSsToLoc(value);
				break;
			//设置数据信息
			case AppConfig.SS_DATA_GO_INTO_STATION_CODE:
				it.putExtra("ssPerGointoStation", value);
				setResult(AppConfig.SS_DATA_GO_INTO_STATION_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGointoStationTime(value);
				break;
			case AppConfig.SS_DATA_GET_OFF_CODE:
				it.putExtra("ssPerGetoff", value);
				setResult(AppConfig.SS_DATA_GET_OFF_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetoffTime(value);
				break;
			case AppConfig.SS_DATA_ARRIVE_PLATFORM_CODE:
				it.putExtra("ssPerArrivePlatform", value);
				setResult(AppConfig.SS_DATA_ARRIVE_PLATFORM_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setArriveStationTime(value);
				break;
			case AppConfig.SS_DATA_ORIGN_LINE_NUM_CODE:
				it.putExtra("ssPerOrignLineNum", value);
				setResult(AppConfig.SS_DATA_ORIGN_LINE_NUM_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setStartLineNum(value);
				break;
			case AppConfig.SS_DATA_GET_OFF_NUM1_CODE:
				it.putExtra("ssPerGetOffNum1", value);
				setResult(AppConfig.SS_DATA_GET_OFF_NUM1_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffNum1(value);
				break;
			case AppConfig.SS_DATA_GET_ON_NUM1_CODE:
				it.putExtra("ssPerGetOnNum1", value);
				setResult(AppConfig.SS_DATA_GET_ON_NUM1_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOnNum1(value);
				break;
			case AppConfig.SS_DATA_TRAIN_START_TIME1_CODE:
				it.putExtra("ssPerTrainStartTime1", value);
				setResult(AppConfig.SS_DATA_TRAIN_START_TIME1_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTrainStartTime1(value);
				break;
			case AppConfig.SS_DATA_GET_OFF_NUM2_CODE:
				it.putExtra("ssPerGetOffNum2", value);
				setResult(AppConfig.SS_DATA_GET_OFF_NUM2_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffNum2(value);
				break;
			case AppConfig.SS_DATA_GET_ON_NUM2_CODE:
				it.putExtra("ssPerGetOnNum2", value);
				setResult(AppConfig.SS_DATA_GET_ON_NUM2_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOnNum2(value);
				break;
			case AppConfig.SS_DATA_TRAIN_START_TIME2_CODE:
				it.putExtra("ssPerTrainStartTime2", value);
				setResult(AppConfig.SS_DATA_TRAIN_START_TIME2_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTrainStartTime2(value);
				break;
			case AppConfig.SS_DATA_GET_OFF_NUM3_CODE:
				it.putExtra("ssPerGetOffNum3", value);
				setResult(AppConfig.SS_DATA_GET_OFF_NUM3_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffNum3(value);
				break;
			case AppConfig.SS_DATA_GET_ON_NUM3_CODE:
				it.putExtra("ssPerGetOnNum3", value);
				setResult(AppConfig.SS_DATA_GET_ON_NUM3_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOnNum3(value);
				break;
			case AppConfig.SS_DATA_TRAIN_START_TIME3_CODE:
				it.putExtra("ssPerTrainStartTime3", value);
				setResult(AppConfig.SS_DATA_TRAIN_START_TIME3_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTrainStartTime3(value);
				break;
			case AppConfig.SS_DATA_GET_OFF_NUM4_CODE:
				it.putExtra("ssPerGetOffNum4", value);
				setResult(AppConfig.SS_DATA_GET_OFF_NUM4_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffNum4(value);
				break;
			case AppConfig.SS_DATA_GET_ON_NUM4_CODE:
				it.putExtra("ssPerGetOnNum4", value);
				setResult(AppConfig.SS_DATA_GET_ON_NUM4_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOnNum4(value);
				break;
			case AppConfig.SS_DATA_TRAIN_START_TIME4_CODE:
				it.putExtra("ssPerTrainStartTime4", value);
				setResult(AppConfig.SS_DATA_TRAIN_START_TIME4_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTrainStartTime4(value);
				break;
			case AppConfig.SS_DATA_GET_OFF_NUM5_CODE:
				it.putExtra("ssPerGetOffNum5", value);
				setResult(AppConfig.SS_DATA_GET_OFF_NUM5_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffNum5(value);
				break;
			case AppConfig.SS_DATA_GET_ON_NUM5_CODE:
				it.putExtra("ssPerGetOnNum5", value);
				setResult(AppConfig.SS_DATA_GET_ON_NUM5_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOnNum5(value);
				break;
			case AppConfig.SS_DATA_TRAIN_START_TIME5_CODE:
				it.putExtra("ssPerTrainStartTime5", value);
				setResult(AppConfig.SS_DATA_TRAIN_START_TIME5_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTrainStartTime5(value);
				break;
			case AppConfig.SS_DATA_GET_OFF_NUM6_CODE:
				it.putExtra("ssPerGetOffNum6", value);
				setResult(AppConfig.SS_DATA_GET_OFF_NUM6_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffNum6(value);
				break;
			case AppConfig.SS_DATA_GET_ON_NUM6_CODE:
				it.putExtra("ssPerGetOnNum6", value);
				setResult(AppConfig.SS_DATA_GET_ON_NUM6_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOnNum6(value);
				break;
			case AppConfig.SS_DATA_TRAIN_START_TIME6_CODE:
				it.putExtra("ssPerTrainStartTime6", value);
				setResult(AppConfig.SS_DATA_TRAIN_START_TIME6_CODE,it);
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTrainStartTime6(value);
				break;
			}
			
			appContext.setUser(user);
			appContext.saveUser();
			this.finish();
			break;
		 case android.R.id.home:
	        Log.i("TAG", "=========选中返回键");
	        this.finish();
	        break;
		}
		return super.onOptionsItemSelected(item);
	}

}
