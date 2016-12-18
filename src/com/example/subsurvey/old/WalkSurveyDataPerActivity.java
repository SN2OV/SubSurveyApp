package com.example.subsurvey.old;

import java.util.ArrayList;

import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;
import com.example.subsurvey.walkSurvey.helper.WalkSurveyDataHelper;
import com.example.subsurvey.walkSurvey.ui.WalkSurveyDataTotalActivity;

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
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class WalkSurveyDataPerActivity extends ActionBarActivity {

	private EditText openDoorTimeET1,openDoorTimeET2,goToPlatformET,arrivePlatformET,goOutPlatformET,tempET;
	private TextView openDoorTimeTV1,openDoorTimeTV2,goToPlatformTV,arrivePlatformTV,goOutPlatformTV;
	private ImageView openDoorTimeReset1,openDoorTimeReset2,goToPlatformReset,arrivePlatformReset,goOutPlatformReset,saveSurveyDataIV,querySureyDataIV;
	public ArrayList<String> timeDataArrPer = new ArrayList<String>();
	Context context = null;
	public int rowIDgot;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walk_survey_data_per);
		
		context = getApplicationContext();
		
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		openDoorTimeET1 = (EditText)findViewById(R.id.openDoorTimeET1);
		openDoorTimeET2 = (EditText)findViewById(R.id.openDoorTimeET2);
		goToPlatformET = (EditText)findViewById(R.id.gotoPlatformET);
		arrivePlatformET = (EditText)findViewById(R.id.arrivePlatformET); 
		goOutPlatformET = (EditText)findViewById(R.id.goOutPlatformET);
		openDoorTimeTV1 = (TextView)findViewById(R.id.openDoorTimeTV1);
		openDoorTimeTV2 = (TextView)findViewById(R.id.openDoorTimeTV2);
		goToPlatformTV = (TextView)findViewById(R.id.gotoPlatformTV);
		arrivePlatformTV = (TextView)findViewById(R.id.arrivePlatformTV); 
		goOutPlatformTV = (TextView)findViewById(R.id.goOutPlatformTV);
		openDoorTimeReset1 = (ImageView)findViewById(R.id.openDoorTimeReset1);
		openDoorTimeReset2 = (ImageView)findViewById(R.id.openDoorTimeReset2);
		goToPlatformReset = (ImageView)findViewById(R.id.gotoPlatformReset);
		arrivePlatformReset = (ImageView)findViewById(R.id.arrivePlatformReset);
		goOutPlatformReset = (ImageView)findViewById(R.id.goOutPlatformReset);
		saveSurveyDataIV = (ImageView)findViewById(R.id.saveSurveyDataIV);
		querySureyDataIV = (ImageView)findViewById(R.id.querySureyDataIV);
		
		openDoorTimeReset1.setOnClickListener(new onClickListenerResetImpl());
		openDoorTimeReset2.setOnClickListener(new onClickListenerResetImpl());
		goToPlatformReset.setOnClickListener(new onClickListenerResetImpl());
		arrivePlatformReset.setOnClickListener(new onClickListenerResetImpl());
		goOutPlatformReset.setOnClickListener(new onClickListenerResetImpl());
		saveSurveyDataIV.setOnClickListener(new onClickListenerSaveDataImpl());
		querySureyDataIV.setOnClickListener(new onClickListenerQueryDataImpl());
		
		Intent it = getIntent();
		String surveyType =it.getStringExtra("surveyType");
		rowIDgot = it.getIntExtra("rowID", 0);
		Bundle bundle =it.getExtras();
		//获取当前记录的时间来填充textView
		timeDataArrPer = bundle.getStringArrayList("timeDataArrPer");
		//进站、出站、换乘分别对应0、1、2
		int surveyTypeNo;
		for(surveyTypeNo=0;surveyTypeNo<3;surveyTypeNo++){
			if(WalkSurveyTimeRecordedActivity.typeInfo[surveyTypeNo].equals(surveyType)){
						break;
					} 
				}
		switch(surveyTypeNo){
		//进站
		case 0:
			openDoorTimeET1.setVisibility(View.GONE);
			openDoorTimeTV1.setVisibility(View.GONE);
			openDoorTimeReset1.setVisibility(View.GONE);
			goOutPlatformET.setVisibility(View.GONE);
			goOutPlatformTV.setVisibility(View.GONE);
			goOutPlatformReset.setVisibility(View.GONE);
			break;
		//出站
		case 1:
			openDoorTimeET1.setVisibility(View.GONE);
			openDoorTimeTV1.setVisibility(View.GONE);
			openDoorTimeReset1.setVisibility(View.GONE);
			goToPlatformET.setVisibility(View.GONE);
			goToPlatformTV.setVisibility(View.GONE);
			goToPlatformReset.setVisibility(View.GONE);
			arrivePlatformET.setVisibility(View.GONE);
			arrivePlatformTV.setVisibility(View.GONE);
			arrivePlatformReset.setVisibility(View.GONE);
			break;
		//换乘
		case 2:
			goToPlatformET.setVisibility(View.GONE);
			goToPlatformTV.setVisibility(View.GONE);
			goToPlatformReset.setVisibility(View.GONE);
			openDoorTimeET2.setVisibility(View.GONE);
			openDoorTimeTV2.setVisibility(View.GONE);
			openDoorTimeReset2.setVisibility(View.GONE);
			break;
		default:
			break;
		}
		
		int etArr[] ={R.id.openDoorTimeET1,R.id.gotoPlatformET,R.id.arrivePlatformET,R.id.openDoorTimeET2,R.id.goOutPlatformET};
		for(int i=0,j=0;i<5&&j<timeDataArrPer.size();i++){
			tempET=(EditText)findViewById(etArr[i]);
			if(tempET.getVisibility()!=View.GONE){
				tempET.setText(timeDataArrPer.get(j).toString());
				j++;
			}
		}
	}
	
	//配置各种reset图标的监听
	public class onClickListenerResetImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.openDoorTimeReset1:
				openDoorTimeET1.setText("");
				break;
			case R.id.openDoorTimeReset2:
				openDoorTimeET2.setText("");
				break;
			case R.id.gotoPlatformReset:
				goToPlatformET.setText("");
				break;
			case R.id.arrivePlatformReset:
				arrivePlatformET.setText("");
				break;
			case R.id.goOutPlatformReset:
				goOutPlatformET.setText("");
				break;
			default:
				break;
			}
		}
		
	}
	
	public class onClickListenerSaveDataImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			//如果满了，WalkSurveyTimeRecordedActivity.fullflag==1,因为前面数据已入库因此这里必须再次入库，id也可以获取。否则不用
			WalkSurveySettingActivity.walkSurveyEntity.setOpenDoorTime1(openDoorTimeET1.getText().toString());
			WalkSurveySettingActivity.walkSurveyEntity.setOpenDoorTime2(openDoorTimeET2.getText().toString());
			WalkSurveySettingActivity.walkSurveyEntity.setGotoPlatformTime(goToPlatformET.getText().toString());
			WalkSurveySettingActivity.walkSurveyEntity.setGooutPlatformTime(goOutPlatformET.getText().toString());
			WalkSurveySettingActivity.walkSurveyEntity.setArrivePlatformTime(arrivePlatformET.getText().toString());
			
			if(WalkSurveyTimeRecordedActivity.fullFlag==1){
				WalkSurveySettingActivity.walkSurveyEntity.setID(rowIDgot-1);//保存完一组数据后rowID++，因此这里需要-1，以根据ID更新数据
//				这里需要改0 0
//				WalkSurveyDataHelper.updateTime(context, WalkSurveySettingActivity.walkSurveyEntity,0);
			}
			UIHelper.ToastMessage(context, "本组数据保存成功");
		}
		
	}
	
	public class onClickListenerQueryDataImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent it = new Intent();
			it.setClass(context, WalkSurveyDataTotalActivity.class);
			startActivity(it);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.walk_survey_data_per, menu);
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
