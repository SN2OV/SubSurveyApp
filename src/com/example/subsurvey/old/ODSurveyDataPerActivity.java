package com.example.subsurvey.old;

import java.lang.annotation.Inherited;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.odSurvey.helper.ODSurveyDataHelper;
import com.example.subsurvey.odSurvey.ui.ODSurveyDataTotalActivity;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ODSurveyDataPerActivity extends ActionBarActivity {
//一班
	@InjectView(R.id.odSurveyGetinStationTimeET)
	EditText odSurveyGetinStationTimeET;
	@InjectView(R.id.odSurveyGetinStationTimeReset)
	ImageView odSurveyGetinStationTimeReset;
	@InjectView(R.id.odSurveyArriveStationTime1ET)
	EditText odSurveyArriveStationTime1ET;
	@InjectView(R.id.odSurveyArriveStationTime1Reset)
	ImageView odSurveyArriveStationTime1Reset;
	@InjectView(R.id.odSurveyTrainStartingTime1ET)
	EditText odSurveyTrainStartingTime1ET;
	@InjectView(R.id.odSurveyTrainStartingTime1Reset)
	ImageView odSurveyTrainStartingTime1Reset;
	@InjectView(R.id.odSurveyGetoffStationTime1ET)
	EditText odSurveyGetoffStationTime1ET;
	@InjectView(R.id.odSurveyGetoffStationTime1Reset)
	ImageView odSurveyGetoffStationTime1Reset;
//二班
	@InjectView(R.id.odSurveyNo2LL)
	LinearLayout odSurveyNo2LL;
	@InjectView(R.id.odSurveyArriveStationTime2ET)
	EditText odSurveyArriveStationTime2ET;
	@InjectView(R.id.odSurveyArriveStationTime2Reset)
	ImageView odSurveyArriveStationTime2Reset;
	@InjectView(R.id.odSurveyTrainStartingTime2ET)
	EditText odSurveyTrainStartingTime2ET;
	@InjectView(R.id.odSurveyTrainStartingTime2Reset)
	ImageView odSurveyTrainStartingTime2Reset;
	@InjectView(R.id.odSurveyGetoffStationTime2ET)
	EditText odSurveyGetoffStationTime2ET;
	@InjectView(R.id.odSurveyGetoffStationTime2Reset)
	ImageView odSurveyGetoffStationTime2Reset;
//三班	
	@InjectView(R.id.odSurveyNo3LL)
	LinearLayout odSurveyNo3LL;
	@InjectView(R.id.odSurveyArriveStationTime3ET)
	EditText odSurveyArriveStationTime3ET;
	@InjectView(R.id.odSurveyArriveStationTime3Reset)
	ImageView odSurveyArriveStationTime3Reset;
	@InjectView(R.id.odSurveyTrainStartingTime3ET)
	EditText odSurveyTrainStartingTime3ET;
	@InjectView(R.id.odSurveyTrainStartingTime3Reset)
	ImageView odSurveyTrainStartingTime3Reset;
	@InjectView(R.id.odSurveyGetoffStationTime3ET)
	EditText odSurveyGetoffStationTime3ET;
	@InjectView(R.id.odSurveyGetoffStationTime3Reset)
	ImageView odSurveyGetoffStationTime3Reset;
	
	@InjectView(R.id.odSurveyGetoutStationTimeRL)
	RelativeLayout odSurveyGetoutStationTimeRL;
	@InjectView(R.id.odSurveyGetoutStationTimeET)
	EditText odSurveyGetoutStationTimeET;
	@InjectView(R.id.odSurveyGetoutStationTimeReset)
	ImageView odSurveyGetoutStationTimeReset;
	@InjectView(R.id.odSureyQueryIV)
	ImageView odSureyQueryIV;
	@InjectView(R.id.odSureySaveIV)
	ImageView odSureySaveIV;
	
	private String pathType;
	private int type,rowIDGot;
	private Context context = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_odsurvey_data_per);
		
		ButterKnife.inject(this);
		Intent it = getIntent();
		pathType = it.getStringExtra("pathType");
		rowIDGot = it.getIntExtra("odRowID", 0);
		type = StringUtils.getSurveyTypeNo(pathType, AppConfig.pathTypeInfo);
		
		init();
	}

	void init(){
		context = getApplicationContext();
		
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		if(type==0){
			odSurveyNo2LL.setVisibility(View.GONE);
			odSurveyNo3LL.setVisibility(View.GONE);
		}else if(type==1)
			odSurveyNo3LL.setVisibility(View.GONE);
		
		odSurveyGetinStationTimeET.setText(ODSurveySettingActivity.odSurveyEntity.getGetinStationTime());
		odSurveyArriveStationTime1ET.setText(ODSurveySettingActivity.odSurveyEntity.getArriveStationTime1());
		odSurveyTrainStartingTime1ET.setText(ODSurveySettingActivity.odSurveyEntity.getTrainStartingTime1());
		odSurveyGetoffStationTime1ET.setText(ODSurveySettingActivity.odSurveyEntity.getGetoffStationTime1());
		odSurveyArriveStationTime2ET.setText(ODSurveySettingActivity.odSurveyEntity.getArriveStationTime2());
		odSurveyTrainStartingTime2ET.setText(ODSurveySettingActivity.odSurveyEntity.getTrainStartingTime2());
		odSurveyGetoffStationTime2ET.setText(ODSurveySettingActivity.odSurveyEntity.getGetoffStationTime2());
		odSurveyArriveStationTime3ET.setText(ODSurveySettingActivity.odSurveyEntity.getArriveStationTime3());
		odSurveyTrainStartingTime3ET.setText(ODSurveySettingActivity.odSurveyEntity.getTrainStartingTime3());
		odSurveyGetoffStationTime3ET.setText(ODSurveySettingActivity.odSurveyEntity.getGetoffStationTime3());
		odSurveyGetoutStationTimeET.setText(ODSurveySettingActivity.odSurveyEntity.getGetoutStationTime());
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.odsurvey_data_per, menu);
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
	
	@OnClick({R.id.odSureyQueryIV,R.id.odSureySaveIV})
	void onClick(View view){
		switch(view.getId()){
		case R.id.odSureyQueryIV:
			System.out.println("--------------query");
			Intent it = new Intent();
			it.setClass(context,ODSurveyDataTotalActivity.class);
			startActivity(it);
			break;
		case R.id.odSureySaveIV:
			System.out.println("--------------save");
			
			ODSurveySettingActivity.odSurveyEntity.setGetinStationTime(odSurveyGetinStationTimeET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setArriveStationTime1(odSurveyArriveStationTime1ET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setTrainStartingTime1(odSurveyTrainStartingTime1ET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setGetoffStationTime1(odSurveyGetoffStationTime1ET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setArriveStationTime2(odSurveyArriveStationTime2ET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setTrainStartingTime2(odSurveyTrainStartingTime2ET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setGetoffStationTime2(odSurveyGetoffStationTime2ET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setArriveStationTime3(odSurveyArriveStationTime3ET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setTrainStartingTime3(odSurveyTrainStartingTime3ET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setGetoffStationTime3(odSurveyGetoffStationTime3ET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setGetoutStationTime(odSurveyGetoutStationTimeET.getText().toString());
			
			ODSurveySettingActivity.odSurveyEntity.setID(rowIDGot-1);
			ODSurveyDataHelper.updateTime(context, ODSurveySettingActivity.odSurveyEntity);
			UIHelper.ToastMessage(context, "本组数据保存成功");
			break;
		}
	}
	
	@OnClick({R.id.odSurveyGetinStationTimeReset,R.id.odSurveyArriveStationTime1Reset,R.id.odSurveyTrainStartingTime1Reset,R.id.odSurveyGetoffStationTime1Reset
		,R.id.odSurveyArriveStationTime2Reset,R.id.odSurveyTrainStartingTime2Reset,R.id.odSurveyGetoffStationTime2Reset
		,R.id.odSurveyArriveStationTime3Reset,R.id.odSurveyTrainStartingTime3Reset,R.id.odSurveyGetoffStationTime3Reset,R.id.odSurveyGetoutStationTimeReset})
	public void onResetClick(View view){
		switch(view.getId()){
		case R.id.odSurveyGetinStationTimeReset:
			odSurveyGetinStationTimeET.setText("");
			break;
		case R.id.odSurveyArriveStationTime1Reset:
			odSurveyArriveStationTime1ET.setText("");
			break;
		case R.id.odSurveyTrainStartingTime1Reset:
			odSurveyTrainStartingTime1ET.setText("");
			break;
		case R.id.odSurveyGetoffStationTime1Reset:
			odSurveyGetoffStationTime1ET.setText("");
			break;
			
		case R.id.odSurveyArriveStationTime2Reset:
			odSurveyArriveStationTime2ET.setText("");
			break;
		case R.id.odSurveyTrainStartingTime2Reset:
			odSurveyTrainStartingTime2ET.setText("");
			break;
		case R.id.odSurveyGetoffStationTime2Reset:
			odSurveyGetoffStationTime2ET.setText("");
			break;
			
		case R.id.odSurveyArriveStationTime3Reset:
			odSurveyArriveStationTime3ET.setText("");
			break;
		case R.id.odSurveyTrainStartingTime3Reset:
			odSurveyTrainStartingTime3ET.setText("");
			break;
		case R.id.odSurveyGetoffStationTime3Reset:
			odSurveyGetoffStationTime3ET.setText("");
			break;
		case R.id.odSurveyGetoutStationTimeReset:
			odSurveyGetoutStationTimeET.setText("");
			break;
		}
	}

}
