package com.example.subsurvey.transferSurvey.ui;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.AppContext;
import com.example.subsurvey.MainNewActivity;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.personalSetting.PersonalSettingFragment;
import com.example.subsurvey.personalSetting.UserEntity;
import com.example.subsurvey.transferSurvey.adapter.TransferSurveyDataPerAdapter;
import com.example.subsurvey.transferSurvey.beans.TransferSurveyEntity;
import com.example.subsurvey.transferSurvey.helper.TransferSurveyDataHelper;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class TransferSurveyTimeRecordedNewActivity extends ActionBarActivity {

	@InjectView(R.id.tsnDataPerLV)
	ListView tsnDataPerLV;
	@InjectView(R.id.tsnAddOneIV)
	ImageView tsnAddOneIV;
	@InjectView(R.id.tsnAddTwoIV)
	ImageView tsnAddTwoIV;	
	@InjectView(R.id.tsnAddFiveIV)
	ImageView tsAddFiveIV;

	private TransferSurveyDataPerAdapter tsDataPerAdapter;
	private HashMap<String, String> perData = new HashMap<String, String>();
	private ArrayList<HashMap<String, String>> totalData = new ArrayList<HashMap<String,String>>();
	private Context context;
	private int id = 0 ,peopleTotalNum = 0;
	public static TransferSurveyEntity tsEntity=new TransferSurveyEntity();
	private AppContext appContext;
	private int tsRowID;
	private SharedPreferences preferences;
	private Vibrator vibrator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transfer_survey_time_recorded_new);
		context = getApplicationContext();
		ButterKnife.inject(this);
		init();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.transfer_survey_time_recorded_new,
				menu);
		MenuItemCompat.setShowAsAction(menu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.transfer_survey_menu_revoke:
			int latestIndex = totalData.size()-1;
			if(latestIndex == 0)
				break;
			peopleTotalNum -= Integer.parseInt(totalData.get(latestIndex).get("perCount"));
			id--;
			totalData.remove(latestIndex);
			tsDataPerAdapter = new TransferSurveyDataPerAdapter(totalData, context);
			tsnDataPerLV.setAdapter(tsDataPerAdapter);
			tsDataPerAdapter.notifyDataSetChanged();
			TransferSurveyDataHelper.delTransferSurveyInfoByNewestID(context);

			Editor editor = getSharedPreferences("tsRowID", MODE_WORLD_WRITEABLE).edit();
			tsRowID--;
			editor.putInt("tsRowID", 1);
			editor.commit();
			UIHelper.ToastMessage(context, "已撤销一条记录");
			break;
		case android.R.id.home:
            Log.i("TAG", "=========选中返回键");
            this.finish();
            break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void init(){
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		context = getApplicationContext();
		appContext = (AppContext)getApplication();
		ButterKnife.inject(this);
		vibrator = (Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
		
		initRowID();
		totalData.add(insertData("序号", "时间", "当前人数", "总人数"));
		tsDataPerAdapter = new TransferSurveyDataPerAdapter(totalData, context);
		tsnDataPerLV.setAdapter(tsDataPerAdapter);
		tsDataPerAdapter.notifyDataSetChanged();
		
		UserEntity user = appContext.getUser();
		TransferSurveyTimeRecordedNewActivity.tsEntity.setName(user.getName());
		TransferSurveyTimeRecordedNewActivity.tsEntity.setDate(user.getDate());
		TransferSurveyTimeRecordedNewActivity.tsEntity.setStation(user.getStation());
		TransferSurveyTimeRecordedNewActivity.tsEntity.setTimePeriod(user.getTsTimePeriod());
		TransferSurveyTimeRecordedNewActivity.tsEntity.setDire(user.getTsLoc());
		Log.d("测试位置","所示");
	}
	

	HashMap<String, String> insertData(String id,String curTime,String perCount,String totalCount){
		HashMap<String, String> tempData = new HashMap<String, String>();
		tempData.put("ID",id);
		tempData.put("Time", curTime);
		tempData.put("perCount", perCount);
		tempData.put("totalCount", totalCount);
		return tempData;
	}
	
	
	@OnClick({R.id.tsnAddOneIV,R.id.tsnAddTwoIV,R.id.tsnAddFiveIV})
	void onAddClick(View view){
		//设置系统时间
		String curTime = StringUtils.getSystemTime();
		TransferSurveyTimeRecordedNewActivity.tsEntity.setSurveyTime(curTime);
		id++;
		switch(view.getId()){
		case R.id.tsnAddOneIV:
			peopleTotalNum++;
			totalData.add(insertData(id+"",curTime,"1",peopleTotalNum+""));
			TransferSurveyTimeRecordedNewActivity.tsEntity.setCount("1");
			break;
		case R.id.tsnAddTwoIV:
			peopleTotalNum+=2;
			TransferSurveyTimeRecordedNewActivity.tsEntity.setCount("2");
			totalData.add(insertData(id+"",curTime,"2",peopleTotalNum+""));
			break;
		case R.id.tsnAddFiveIV:
			peopleTotalNum+=5;
			TransferSurveyTimeRecordedNewActivity.tsEntity.setCount("5");
			totalData.add(insertData(id+"",curTime,"5",peopleTotalNum+""));
			break;
		}
		//主键序号增加
		TransferSurveyTimeRecordedNewActivity.tsEntity.setID(tsRowID);
		tsRowID++;
		Editor editor = preferences.edit();
		editor.putInt("tsRowID",tsRowID);
		editor.commit();
		TransferSurveyDataHelper.insertIntoTSSurveyData(context,TransferSurveyTimeRecordedNewActivity.tsEntity);
		tsDataPerAdapter.notifyDataSetChanged();
		tsnDataPerLV.setAdapter(tsDataPerAdapter);
		//震动-立即开始，持续10ms，不循环
		vibrator.vibrate(new long[] {0,10},-1);
	}
	
	//设置总人数数据的主键
		public void initRowID(){
			preferences = getSharedPreferences("tsRowID", MODE_WORLD_WRITEABLE);
			tsRowID = preferences.getInt("tsRowID", 1);//默认为第二个参数1
			TransferSurveyTimeRecordedNewActivity.tsEntity.setID(tsRowID);
			//通过sharedPreferences将主键保存起来
			Editor editor = preferences.edit();
			editor.putInt("tsRowID",tsRowID);
			editor.commit();
		}
		
}
