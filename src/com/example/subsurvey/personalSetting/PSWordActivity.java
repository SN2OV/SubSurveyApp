package com.example.subsurvey.personalSetting;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.old.WalkSurveySettingActivity;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;
import com.example.subsurvey.walkSurvey.helper.WalkSurveyDataHelper;
import com.example.subsurvey.walkSurvey.ui.WalkSurveyNewTimeRecordeActivity;

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

public class PSWordActivity extends ActionBarActivity {

	@InjectView(R.id.ps_word_ET)
	EditText psWordET;

	private Context context;
	private Intent it;
	private int type,wsRowIDgot;
	private AppContext appContext;
	private UserEntity user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_psword);
		init();
		initView();
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
		String value;
		switch (item.getItemId()) {
		case R.id.action_finished:
			it=new Intent();
			int timeCode=0;
			value = psWordET.getText().toString();
			switch(type){
			//个人设置界面传入
			case AppConfig.NAME_CODE:
				it.putExtra("name_new", value);
				setResult(AppConfig.NAME_CODE,it);
				user.setName(value);
				break;
			case AppConfig.STATION_CODE:
				it.putExtra("station_new", value);
				setResult(AppConfig.STATION_CODE,it);
				user.setStation(value);
				break;
			case AppConfig.LINE_CODE:
				it.putExtra("line_new", value);
				setResult(AppConfig.LINE_CODE,it);
				user.setLine(value);
				break;
			case AppConfig.TS_LOC_CODE:
				it.putExtra("loc_new", value);
				setResult(AppConfig.TS_LOC_CODE,it);
				user.setTsLoc(value);
				break;
			//反向设置界面
			case AppConfig.RS_LOC_CODE:
				it.putExtra("rsLocNew",value);
				setResult(AppConfig.RS_LOC_CODE,it);
				user.setRsCarriageLoc(value);
				break;
			case AppConfig.RS_DIRE_CODE:
				it.putExtra("rsDireNew",value);
				setResult(AppConfig.RS_DIRE_CODE,it);
				user.setRsDire(value);
				break;
			//走行设置界面传入
			case AppConfig.WS_GATE_LOC_CODE:
				it.putExtra("wsGateLocNew", value);
				setResult(AppConfig.WS_GATE_LOC_CODE,it);
				user.setWsGateLoc(value);
				break;
			case AppConfig.WS_GATE_LINE_CODE:
				it.putExtra("wsGateLineNew", value);
				setResult(AppConfig.WS_GATE_LINE_CODE,it);
				user.setWsGateLine(value);
				break;
			case AppConfig.WS_OFF_DIRE_CODE:
				it.putExtra("wsOffDireNew", value);
				setResult(AppConfig.WS_OFF_DIRE_CODE,it);
				user.setWsOffDire(value);
				break;
			case AppConfig.WS_OFF_LINE_CODE:
				it.putExtra("wsOffLineNew", value);
				setResult(AppConfig.WS_OFF_LINE_CODE,it);
				user.setWsOffLine(value);
				break;
			case AppConfig.WS_ON_DIRE_CODE:
				it.putExtra("wsOnDireNew", value);
				setResult(AppConfig.WS_ON_DIRE_CODE,it);
				user.setWsOnDire(value);
				break;
			case AppConfig.WS_ON_LINE_CODE:
				it.putExtra("wsOnLineNew", value);
				setResult(AppConfig.WS_ON_LINE_CODE,it);
				user.setWsOnLine(value);
				break;
			//走行计时界面传入
			case AppConfig.WS_PER_OPENDOOR1_CODE:
				it.putExtra("wsPerOpenDoorTime1", value);
				setResult(AppConfig.WS_PER_OPENDOOR1_CODE,it);
//				WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setOpenDoorTime1(value);
				timeCode = AppConfig.WS_PER_OPENDOOR1_CODE;
				break;
			case AppConfig.WS_PER_GOTOPLAT_CODE:
				it.putExtra("wsPerGotoPlatTime", value);
				setResult(AppConfig.WS_PER_GOTOPLAT_CODE,it);
//				WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setGotoPlatformTime(value);
				timeCode = AppConfig.WS_PER_GOTOPLAT_CODE;
				break;
			case AppConfig.WS_PER_ARRIVEPLAT_CODE:
				it.putExtra("wsPerArrivePlatTime", value);
				setResult(AppConfig.WS_PER_ARRIVEPLAT_CODE,it);
//				WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setArrivePlatformTime(value);
				timeCode = AppConfig.WS_PER_ARRIVEPLAT_CODE;
				break;
			case AppConfig.WS_PER_OPENDOOR2_CODE:
				it.putExtra("wsPerOpenDoorTime2", value);
				setResult(AppConfig.WS_PER_OPENDOOR2_CODE,it);
//				WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setOpenDoorTime2(value);
				timeCode = AppConfig.WS_PER_OPENDOOR2_CODE;
				break;
			case AppConfig.WS_PER_GOOUTPLAT_CODE:
				it.putExtra("wsPerGoOutPlatTime", value);
				setResult(AppConfig.WS_PER_GOOUTPLAT_CODE,it);
//				WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setGooutPlatformTime(value);
				timeCode = AppConfig.WS_PER_GOOUTPLAT_CODE;
				break;
			}

			//更新走行时间单条数据
			WalkSurveyDataHelper.updateTime(context, value,timeCode,WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.getID());
			UIHelper.ToastMessage(context, "本数据修改成功");

			appContext.setUser(user);
			appContext.saveUser();
			finish();
			break;
		 case android.R.id.home:
	        Log.i("TAG", "=========选中返回键");
	        this.finish();
	        break;
		}
		return true;
	}
	
	public void init(){
		ButterKnife.inject(this);
		context = getApplicationContext();
		appContext = (AppContext)getApplication();
		user = appContext.getUser();
		
		Intent it = getIntent();
		String surveyType =it.getStringExtra("surveyType");
		wsRowIDgot = it.getIntExtra("rowIDGot", 0);
	}
	
	public void initView(){
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		it= getIntent();
		String value = null;
		type = it.getIntExtra("type", 0);
		switch(type){
		case AppConfig.NAME_CODE:
			value = it.getStringExtra("name");
			break;
		case AppConfig.STATION_CODE:
			value = it.getStringExtra("station");
			break;
		case AppConfig.LINE_CODE:
			value = it.getStringExtra("line");
			break;
		case AppConfig.TS_LOC_CODE:
			value = it.getStringExtra("loc");
			break;
		case AppConfig.WS_GATE_LOC_CODE:
			value = it.getStringExtra("wsGateLoc");
			break;
		case AppConfig.WS_GATE_LINE_CODE:
			value = it.getStringExtra("wsGateLine");
			break;
		case AppConfig.WS_OFF_DIRE_CODE:
			value = it.getStringExtra("wsOffDire");
			break;
		case AppConfig.WS_OFF_LINE_CODE:
			value = it.getStringExtra("wsOffLine");
			break;
		case AppConfig.WS_ON_DIRE_CODE:
			value = it.getStringExtra("wsOnDire");
			break;
		case AppConfig.WS_ON_LINE_CODE:
			value = it.getStringExtra("wsOnLine");
			break;
		case AppConfig.WS_PER_OPENDOOR1_CODE:
			value = it.getStringExtra("wsPerOpenDoorTime1");
			break;
		case AppConfig.WS_PER_GOTOPLAT_CODE:
			value = it.getStringExtra("wsPerGotoPlatTime");
			break;
		case AppConfig.WS_PER_ARRIVEPLAT_CODE:
			value = it.getStringExtra("wsPerArrivePlatTime");
			break;
		case AppConfig.WS_PER_OPENDOOR2_CODE:
			value = it.getStringExtra("wsPerOpenDoorTime2");
			break;
		case AppConfig.WS_PER_GOOUTPLAT_CODE:
			value = it.getStringExtra("wsPerGoOutPlatTime");
			break;
		case AppConfig.RS_LOC_CODE:
			value = it.getStringExtra("rsCarriageLoc");
			break;
		case AppConfig.RS_DIRE_CODE:
			value = it.getStringExtra("rsDire");
			break;
		}
		psWordET.setText(value);
		
	}

}
