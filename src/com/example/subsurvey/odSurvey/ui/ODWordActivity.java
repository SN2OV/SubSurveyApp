package com.example.subsurvey.odSurvey.ui;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.id;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.personalSetting.UserEntity;

import butterknife.ButterKnife;
import butterknife.InjectView;
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

public class ODWordActivity extends ActionBarActivity {

	@InjectView(R.id.od_word_ET)
	EditText od_word_ET;
	
	private Context context;
	private Intent it;
	private int type,odRowIDgot;
	private AppContext appContext;
	private UserEntity user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_odword);
		init();
		initView();
	}

	private void init() {
		ButterKnife.inject(this);
		context = getApplicationContext();
		appContext = (AppContext)getApplication();
		user = appContext.getUser();
		
		final ActionBar bar =getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
        bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
	}

	private void initView() {
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		it= getIntent();
		String value = null;
		type = it.getIntExtra("type", 0);
		switch(type){
		case AppConfig.OD_CARD_NUM_CODE:
			value = it.getStringExtra("cardNum");
			break;
		case AppConfig.OD_ID_NUM_CODE:
			value = it.getStringExtra("IDNum");
			break;
		case AppConfig.OD_DISTANCE_CODE:
			value = it.getStringExtra("distance");
			break;
		case AppConfig.OD_STATION_COUNT_CODE:
			value = it.getStringExtra("stationCount");
			break;
		//OD路线
		case AppConfig.OD_LINE_DIRE1_CODE:
			value = it.getStringExtra("odlDire1");
			break;
		case AppConfig.OD_LINE_OFF_STATION1_CODE:
			value = it.getStringExtra("odlOffStation1");
			break;
		case AppConfig.OD_LINE_TRANS_LINE2_CODE:
			value = it.getStringExtra("odlTransLine2");
			break;
		case AppConfig.OD_LINE_DIRE2_CODE:
			value = it.getStringExtra("odlDire2");
			break;
		case AppConfig.OD_LINE_OFF_STATION2_CODE:
			value = it.getStringExtra("odlOffStation2");
			break;
		case AppConfig.OD_LINE_TRANS_LINE3_CODE:
			value = it.getStringExtra("odlTransLine3");
			break;
		case AppConfig.OD_LINE_DIRE3_CODE:
			value = it.getStringExtra("odlDire3");
			break;
		case AppConfig.OD_LINE_OFF_STATION3_CODE:
			value = it.getStringExtra("odlOffStation3");
			break;
		//OD时间
		case AppConfig.OD_DATA_GEI_IN_TIME_CODE:
			value = it.getStringExtra("odPerGotoPlatform1");
			break;
		case AppConfig.OD_DATA_ARRIVE_PLATFORM1_TIME_CODE:
			value = it.getStringExtra("odPerArrivePlatform1");
			break;
		case AppConfig.OD_DATA_TRAIN_START_TIME1_CODE:
			value = it.getStringExtra("odPerTrainStart1");
			break;
		case AppConfig.OD_DATA_GET_OFF_TIME1_CODE:
			value = it.getStringExtra("odPerGetOff1");
			break;
		case AppConfig.OD_DATA_ARRIVE_PLATFORM2_TIME_CODE:
			value = it.getStringExtra("odPerArrivePlatform2");
			break;
		case AppConfig.OD_DATA_TRAIN_START_TIME2_CODE:
			value = it.getStringExtra("odPerTrainStart2");
			break;
		case AppConfig.OD_DATA_GET_OFF_TIME2_CODE:
			value = it.getStringExtra("odPerGetOff2");
			break;
		case AppConfig.OD_DATA_ARRIVE_PLATFORM3_TIME_CODE:
			value = it.getStringExtra("odPerArrivePlatform3");
			break;
		case AppConfig.OD_DATA_TRAIN_START_TIME3_CODE:
			value = it.getStringExtra("odPerTrainStart3");
			break;
		case AppConfig.OD_DATA_GET_OFF_TIME3_CODE:
			value = it.getStringExtra("odPerGetOff3");
			break;
		case AppConfig.OD_DATA_GET_OUT_TIME_CODE:
			value = it.getStringExtra("odPerGoOutPlatForm");
			break;
		}
		od_word_ET.setText(value);
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
		switch (item.getItemId()) {
		case R.id.action_finished:
			it = new Intent();
			String value = od_word_ET.getText().toString();
			switch(type){
			case AppConfig.OD_CARD_NUM_CODE:
				it.putExtra("cardNum", value);
				setResult(AppConfig.OD_CARD_NUM_CODE,it);
				user.setODCardNum(value);
				break;
			case AppConfig.OD_ID_NUM_CODE:
				it.putExtra("IDNum", value);
				setResult(AppConfig.OD_ID_NUM_CODE,it);
				user.setODIDNum(value);
				break;
			case AppConfig.OD_DISTANCE_CODE:
				it.putExtra("distance", value);
				setResult(AppConfig.OD_DISTANCE_CODE,it);
				user.setODDistance(value);
				break;
			case AppConfig.OD_STATION_COUNT_CODE:
				it.putExtra("stationCount", value);
				setResult(AppConfig.OD_STATION_COUNT_CODE,it);
				user.setOdStationCount(value);
			//OD路线
			case AppConfig.OD_LINE_DIRE1_CODE:
				it.putExtra("odlDire1", value);
				setResult(AppConfig.OD_LINE_DIRE1_CODE,it);
				user.setOdlDire1(value);
				break;
			case AppConfig.OD_LINE_OFF_STATION1_CODE:
				it.putExtra("odlOffStation1", value);
				setResult(AppConfig.OD_LINE_OFF_STATION1_CODE,it);
				user.setOdlOffStation1(value);
				break;
			case AppConfig.OD_LINE_TRANS_LINE2_CODE:
				it.putExtra("odlTransLine2", value);
				setResult(AppConfig.OD_LINE_TRANS_LINE2_CODE,it);
				user.setOdlTransLine2(value);
				break;
			case AppConfig.OD_LINE_DIRE2_CODE:
				it.putExtra("odlDire2", value);
				setResult(AppConfig.OD_LINE_DIRE2_CODE,it);
				user.setOdlDire2(value);
				break;
			case AppConfig.OD_LINE_OFF_STATION2_CODE:
				it.putExtra("odlOffStation2", value);
				setResult(AppConfig.OD_LINE_OFF_STATION2_CODE,it);
				user.setOdlOffStation2(value);
				break;
			case AppConfig.OD_LINE_TRANS_LINE3_CODE:
				it.putExtra("odlTransLine3", value);
				setResult(AppConfig.OD_LINE_TRANS_LINE3_CODE,it);
				user.setOdlTransLine3(value);
				break;
			case AppConfig.OD_LINE_DIRE3_CODE:
				it.putExtra("odlDire3", value);
				setResult(AppConfig.OD_LINE_DIRE3_CODE,it);
				user.setOdlDire3(value);
				break;
			case AppConfig.OD_LINE_OFF_STATION3_CODE:
				it.putExtra("odlOffStation3", value);
				setResult(AppConfig.OD_LINE_OFF_STATION3_CODE,it);
				user.setOdlOffStation3(value);
				break;
			//OD时间设置
			case AppConfig.OD_DATA_GEI_IN_TIME_CODE:
				it.putExtra("odPerGotoPlatform1", value);
				setResult(AppConfig.OD_DATA_GEI_IN_TIME_CODE,it);
				ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetinStationTime(value);
				break;
			case AppConfig.OD_DATA_ARRIVE_PLATFORM1_TIME_CODE:
				it.putExtra("odPerArrivePlatform1", value);
				setResult(AppConfig.OD_DATA_ARRIVE_PLATFORM1_TIME_CODE,it);
				ODSurveyTimeRecordedNewActivity.odSurveyEntity.setArriveStationTime1(value);
				break;
			case AppConfig.OD_DATA_TRAIN_START_TIME1_CODE:
				it.putExtra("odPerTrainStart1", value);
				setResult(AppConfig.OD_DATA_TRAIN_START_TIME1_CODE,it);
				ODSurveyTimeRecordedNewActivity.odSurveyEntity.setTrainStartingTime1(value);
				break;
			case AppConfig.OD_DATA_GET_OFF_TIME1_CODE:
				it.putExtra("odPerGetOff1", value);
				setResult(AppConfig.OD_DATA_GET_OFF_TIME1_CODE,it);
				ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetoffStationTime1(value);
				break;
			case AppConfig.OD_DATA_ARRIVE_PLATFORM2_TIME_CODE:
				it.putExtra("odPerArrivePlatform2", value);
				setResult(AppConfig.OD_DATA_ARRIVE_PLATFORM2_TIME_CODE,it);
				ODSurveyTimeRecordedNewActivity.odSurveyEntity.setArriveStationTime2(value);
				break;
			case AppConfig.OD_DATA_TRAIN_START_TIME2_CODE:
				it.putExtra("odPerTrainStart2", value);
				setResult(AppConfig.OD_DATA_TRAIN_START_TIME2_CODE,it);
				ODSurveyTimeRecordedNewActivity.odSurveyEntity.setTrainStartingTime2(value);
				break;
			case AppConfig.OD_DATA_GET_OFF_TIME2_CODE:
				it.putExtra("odPerGetOff2", value);
				setResult(AppConfig.OD_DATA_GET_OFF_TIME2_CODE,it);
				ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetoffStationTime2(value);
				break;
			case AppConfig.OD_DATA_ARRIVE_PLATFORM3_TIME_CODE:
				it.putExtra("odPerArrivePlatform3", value);
				setResult(AppConfig.OD_DATA_ARRIVE_PLATFORM3_TIME_CODE,it);
				ODSurveyTimeRecordedNewActivity.odSurveyEntity.setArriveStationTime3(value);
				break;
			case AppConfig.OD_DATA_TRAIN_START_TIME3_CODE:
				it.putExtra("odPerTrainStart3", value);
				setResult(AppConfig.OD_DATA_TRAIN_START_TIME3_CODE,it);
				ODSurveyTimeRecordedNewActivity.odSurveyEntity.setTrainStartingTime3(value);
				break;
			case AppConfig.OD_DATA_GET_OFF_TIME3_CODE:
				it.putExtra("odPerGetOff3", value);
				setResult(AppConfig.OD_DATA_GET_OFF_TIME3_CODE,it);
				ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetoffStationTime3(value);
				break;
			case AppConfig.OD_DATA_GET_OUT_TIME_CODE:
				it.putExtra("odPerGoOutPlatForm", value);
				setResult(AppConfig.OD_DATA_GET_OUT_TIME_CODE,it);
				ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetoutStationTime(value);
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
		return true;
	}

}
