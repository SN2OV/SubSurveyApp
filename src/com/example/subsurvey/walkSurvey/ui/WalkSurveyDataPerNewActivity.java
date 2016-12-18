package com.example.subsurvey.walkSurvey.ui;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.id;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.personalSetting.PSWordActivity;
import com.example.subsurvey.personalSetting.UserEntity;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WalkSurveyDataPerNewActivity extends ActionBarActivity {
	
	private Context context;
	private AppContext appContext;
	private UserEntity user;
	private Intent it;
	private int surveyTypeNo,rowIDGot,lineNoGot,transNoGot;
	public ArrayList<String> timeDataArrPer = new ArrayList<String>();
	private Serializable obj;
	private ArrayList<ArrayList<String>> timeDataArrTotal;
	
	@InjectView(R.id.wsPerOpenDoor1RL)
	RelativeLayout wsPerOpenDoor1RL;
	@InjectView(R.id.wsPerOpenDoor1TV)
	TextView wsPerOpenDoor1TV;
	@InjectView(R.id.wsPerGotoPlatformRL)
	RelativeLayout wsPerGotoPlatformRL;
	@InjectView(R.id.wsPerGotoPlatformTV)
	TextView wsPerGotoPlatformTV;
	@InjectView(R.id.wsPerArrivePlatformRL)
	RelativeLayout wsPerArrivePlatformRL;
	@InjectView(R.id.wsPerArrivePlatformTV)
	TextView wsPerArrivePlatformTV;
	@InjectView(R.id.wsPerOpenDoor2RL)
	RelativeLayout wsPerOpenDoor2RL;
	@InjectView(R.id.wsPerOpenDoor2TV)
	TextView wsPerOpenDoor2TV;
	@InjectView(R.id.wsPerGoOutPlatFormRL)
	RelativeLayout wsPerGoOutPlatFormRL;
	@InjectView(R.id.wsPerGoOutPlatFormTV)
	TextView wsPerGoOutPlatFormTV;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walk_survey_data_per_new);
		
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
		
		it = getIntent();
		String surveyType =it.getStringExtra("surveyType");
		rowIDGot = it.getIntExtra("rowID", 0);
		lineNoGot = it.getIntExtra("lineNo", 0);
		transNoGot = it.getIntExtra("transNo", 0);
		Bundle bundle =it.getExtras();
		obj = bundle.getSerializable("timeDataArrTotal");
		if(obj != null && obj instanceof ArrayList<?>)
			timeDataArrTotal = (ArrayList<ArrayList<String>>)obj;
		surveyTypeNo = SurveyUtils.getSurveyTypeNo(surveyType, AppConfig.wsTypeInfo);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	private void initView() {
		switch(surveyTypeNo){
		case AppConfig.WS_ON_TO_OFF_NO:
			initOnToOFFRelativeLayout(lineNoGot);
			fillOnToOFFDataIntoView(timeDataArrTotal,lineNoGot);
			break;
		case AppConfig.WS_TRANSF_NO:
			initTransferRelativeLayout(transNoGot);
			fillTransferDataIntoView(timeDataArrTotal,transNoGot);
			break;
		}
		
	}
	

	private void initOnToOFFRelativeLayout(int lineNoGot) {
		switch(lineNoGot){
		case 1:
		case 3:
			wsPerOpenDoor1RL.setVisibility(View.GONE);
			wsPerGoOutPlatFormRL.setVisibility(View.GONE);
			break;
		case 0:
		case 2:
			wsPerGotoPlatformRL.setVisibility(View.GONE);
			wsPerArrivePlatformRL.setVisibility(View.GONE);
			wsPerOpenDoor2RL.setVisibility(View.GONE);
			break;
		}
	}
	
	private void initTransferRelativeLayout(int transNoGot) {
		wsPerGotoPlatformRL.setVisibility(View.GONE);
		wsPerGoOutPlatFormRL.setVisibility(View.GONE);
	}

	private void fillOnToOFFDataIntoView(ArrayList<ArrayList<String>> timeDataArrTotal,
			int lineNoGot) {
		switch(lineNoGot){
		case 1:
		case 3:
			wsPerGotoPlatformTV.setText(timeDataArrTotal.get(lineNoGot-1).get(0));
			wsPerArrivePlatformTV.setText(timeDataArrTotal.get(lineNoGot-1).get(1));
			wsPerOpenDoor2TV.setText(timeDataArrTotal.get(lineNoGot-1).get(2));
			break;
		case 0:
			wsPerOpenDoor1TV.setText(timeDataArrTotal.get(3).get(0));
			wsPerGoOutPlatFormTV.setText(timeDataArrTotal.get(3).get(1));
			break;
		case 2:
			wsPerOpenDoor1TV.setText(timeDataArrTotal.get(lineNoGot-1).get(0));
			wsPerGoOutPlatFormTV.setText(timeDataArrTotal.get(lineNoGot-1).get(1));
			break;
		}
	}
	
	private void fillTransferDataIntoView(ArrayList<ArrayList<String>> timeDataArrTotal,
			int transNoGota) {
		switch(transNoGota){
		case 0:
			wsPerOpenDoor1TV.setText(timeDataArrTotal.get(3).get(0));
			wsPerArrivePlatformTV.setText(timeDataArrTotal.get(3).get(1));
			wsPerOpenDoor2TV.setText(timeDataArrTotal.get(3).get(2));
			break;
		case 1:
			wsPerOpenDoor1TV.setText(timeDataArrTotal.get(0).get(0));
			wsPerArrivePlatformTV.setText(timeDataArrTotal.get(0).get(1));
			wsPerOpenDoor2TV.setText(timeDataArrTotal.get(0).get(2));
			break;
		case 2:
			wsPerOpenDoor1TV.setText(timeDataArrTotal.get(1).get(0));
			wsPerArrivePlatformTV.setText(timeDataArrTotal.get(1).get(1));
			wsPerOpenDoor2TV.setText(timeDataArrTotal.get(1).get(2));
			break;
		case 3:
			wsPerOpenDoor1TV.setText(timeDataArrTotal.get(2).get(0));
			wsPerArrivePlatformTV.setText(timeDataArrTotal.get(2).get(1));
			wsPerOpenDoor2TV.setText(timeDataArrTotal.get(2).get(2));
			break;
		}
	}




	@OnClick({R.id.wsPerOpenDoor1RL,R.id.wsPerGotoPlatformRL,R.id.wsPerArrivePlatformRL,R.id.wsPerOpenDoor2RL,R.id.wsPerGoOutPlatFormRL})
	void onPerDataClick(View view){
		Intent it = new Intent();
		it.setClass(context, PSWordActivity.class);
		it.putExtra("rowIDGot", rowIDGot);
		switch(view.getId()){
		case R.id.wsPerOpenDoor1RL:
			it.putExtra("wsPerOpenDoorTime1",wsPerOpenDoor1TV.getText().toString());
			it.putExtra("type", AppConfig.WS_PER_OPENDOOR1_CODE);
			startActivityForResult(it, AppConfig.WS_PER_OPENDOOR1_CODE);
			break;
		case R.id.wsPerGotoPlatformRL:
			it.putExtra("wsPerGotoPlatTime",wsPerGotoPlatformTV.getText().toString());
			it.putExtra("type", AppConfig.WS_PER_GOTOPLAT_CODE);
			startActivityForResult(it, AppConfig.WS_PER_GOTOPLAT_CODE);
			break;
		case R.id.wsPerArrivePlatformRL:
			it.putExtra("wsPerArrivePlatTime",wsPerArrivePlatformTV.getText().toString());
			it.putExtra("type", AppConfig.WS_PER_ARRIVEPLAT_CODE);
			startActivityForResult(it, AppConfig.WS_PER_ARRIVEPLAT_CODE);
			break;
		case R.id.wsPerOpenDoor2RL:
			it.putExtra("wsPerOpenDoorTime2",wsPerOpenDoor2TV.getText().toString());
			it.putExtra("type", AppConfig.WS_PER_OPENDOOR2_CODE);
			startActivityForResult(it, AppConfig.WS_PER_OPENDOOR2_CODE);
			break;
		case R.id.wsPerGoOutPlatFormRL:
			it.putExtra("wsPerGoOutPlatTime",wsPerGoOutPlatFormTV.getText().toString());
			it.putExtra("type", AppConfig.WS_PER_GOOUTPLAT_CODE);
			startActivityForResult(it, AppConfig.WS_PER_GOOUTPLAT_CODE);
			break;
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null)
			return ;
		switch(requestCode){
		case AppConfig.WS_PER_OPENDOOR1_CODE:
			String gateLoc = data.getStringExtra("wsPerOpenDoorTime1");
			wsPerOpenDoor1TV.setText(gateLoc);
			break;
		case AppConfig.WS_PER_GOTOPLAT_CODE:
			String gateLine = data.getStringExtra("wsPerGotoPlatTime");
			wsPerGotoPlatformTV.setText(gateLine);
			break;
		case AppConfig.WS_PER_ARRIVEPLAT_CODE:
			String offDire = data.getStringExtra("wsPerArrivePlatTime");
			wsPerArrivePlatformTV.setText(offDire);
			break;
		case AppConfig.WS_PER_OPENDOOR2_CODE:
			String offLine = data.getStringExtra("wsPerOpenDoorTime2");
			wsPerOpenDoor2TV.setText(offLine);
			break;
		case AppConfig.WS_PER_GOOUTPLAT_CODE:
			String onDire = data.getStringExtra("wsPerGoOutPlatTime");
			wsPerGoOutPlatFormTV.setText(onDire);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.walk_survey_data_per_new, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
        System.out.println();
        switch(item.getItemId()){
        case android.R.id.home:
            Log.i("TAG", "=========选中返回键");
            this.finish();
            break;
        }
        return super.onOptionsItemSelected(item); 
    }
}
