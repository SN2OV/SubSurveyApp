package com.example.subsurvey.staySurvey.ui;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.DialogSSCount;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.old.StaySurveySettingActivity;
import com.example.subsurvey.old.StaySurveyTimeRecordedActivity;
import com.example.subsurvey.personalSetting.UserEntity;
import com.example.subsurvey.staySurvey.beans.StaySurveyEntity;
import com.example.subsurvey.staySurvey.helper.StaySurveyDataHelper;
import com.example.subsurvey.walkSurvey.ui.WalkSurveyNewTimeRecordeActivity;
import com.example.subsurvey.widget.AbstractSpinerAdapter;
import com.example.subsurvey.widget.SpinerPopWindow;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StaySurveyTimeRecordedNewActivity extends ActionBarActivity implements AbstractSpinerAdapter.IOnItemSelectListener{

	@InjectView(R.id.ssAgeValueTV)
	TextView ssAgeValueTV;
	@InjectView(R.id.ssBagageValueTV)
	TextView ssBagageValueTV;
	@InjectView(R.id.ssSexValueTV)
	TextView ssSexValueTV;
	@InjectView(R.id.ssLineShowTV)
	TextView ssLineShowTV;
	@InjectView(R.id.ssTodoNextTV)
	TextView ssTodoNextTV;
	@InjectView(R.id.ssLastTimeTV)
	TextView ssLastTimeTV;
	@InjectView(R.id.startSurveyImageView)
	ImageView startSurveyImageView;
	@InjectView(R.id.searchSurveyImageView)
	ImageView searchSurveyImageView;
	
	private Context context;
	private UserEntity user;
	private AppContext appContext;
	private SpinerPopWindow mSpinerPopWindow;
	private int surveyTypeNo;
	private List<String> ageList = new ArrayList<String>();
	private List<String> bagageList = new ArrayList<String>();
	private List<String> sexList = new ArrayList<String>();
	private ArrayList<ArrayList<String>>timeDataArrTotal = new ArrayList<ArrayList<String>>();//时间数据
	private ArrayList<String> tempArr = new ArrayList<String>();
	private ArrayList<String> timeDataArrPer = new ArrayList<String>();
	private SharedPreferences preferences;
	private int countFlag = 0,countStop=1;//countFlag标识第n项内容，countStop标识等候的第n量车
	public static int orignLineNumVisiblity; 
	public static StaySurveyEntity staySurveyEntity = new StaySurveyEntity();
	public int ssRowID,revokeCode = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stay_survey_time_recorded_new);
		init();
		initView();
	}

	private void init() {
		context = getApplicationContext();
		appContext = (AppContext)getApplication();
		user = appContext.getUser();
		ButterKnife.inject(this);
		
		final ActionBar bar =getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
        bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		ageList = fillArrayToList(getResources().getStringArray(R.array.wsAge));
		bagageList = fillArrayToList(getResources().getStringArray(R.array.wsBagage));
		sexList = fillArrayToList(getResources().getStringArray(R.array.ssSex));
		
		StaySurveyTimeRecordedNewActivity.staySurveyEntity.setAge(ssAgeValueTV.getText().toString());
		StaySurveyTimeRecordedNewActivity.staySurveyEntity.setBagageType(ssBagageValueTV.getText().toString());
		StaySurveyTimeRecordedNewActivity.staySurveyEntity.setSex(ssSexValueTV.getText().toString());
		
		surveyTypeNo = SurveyUtils.getSurveyTypeNo(user.getSsType(), AppConfig.ssTypeInfo);//进站为0换乘为1
		if(!SurveyUtils.fillUserInfoIntoEntity(context,StaySurveyTimeRecordedNewActivity.staySurveyEntity, user)){
			UIHelper.ToastMessage(context,"请在界面“我”中完成相关信息设置");
			this.finish();
		}
	}

	private void initView() {
		mSpinerPopWindow = new SpinerPopWindow(this);
		mSpinerPopWindow.setItemListener(this);
//		ssLineShowTV.setText(user.getSsFromLoc()+"-"+user.getSsToLoc());
		SurveyUtils.getSSLine(ssLineShowTV,user,1);
		ssTodoNextTV.setText(AppConfig.ssTodoHints[surveyTypeNo][0]);
	}	

	@Override
	protected void onResume() {
		StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGoinLineNo(user.getSsFromLine());
		StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGoinLineDire(user.getSsFromLoc());
		StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffLineNo(user.getSsToLine());
		StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetoffLineDire(user.getSsToLoc());
		super.onResume();
	}
	
	private void showSpinWindow(TextView tv){
		mSpinerPopWindow.setWidth(tv.getWidth());
		mSpinerPopWindow.showAsDropDown(tv);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stay_survey_revoke, menu);
		MenuItemCompat.setShowAsAction(menu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		return true;
	}
	
	@OnClick({R.id.startSurveyImageView,R.id.searchSurveyImageView})
	void onSSImageClick(View v){
//		showAlertDialog();
		switch(v.getId()){
		case R.id.startSurveyImageView:
			
			ssAgeValueTV.setClickable(false);
			ssSexValueTV.setClickable(false);
			ssBagageValueTV.setClickable(false);
			//点击查询按钮后revokeCode就处于常规状态
			revokeCode = AppConfig.SS_REVOKE_NORMAL;
			String curTime = StringUtils.getSystemTime();
			int todoNo = countFlag+1;
			//赋值入类
			switch(countFlag){
			case 0:
				if(surveyTypeNo == 0)
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGointoStationTime(curTime);
				else if(surveyTypeNo == 1)
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetoffTime(curTime);
				break;
			case 1:
				StaySurveyTimeRecordedNewActivity.staySurveyEntity.setArriveStationTime(curTime);
				break;
			case 2:
				switch(countStop){
				case 1:
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTrainStartTime1(curTime);
					break;
				case 2:
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTrainStartTime2(curTime);
					break;
				case 3:
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTrainStartTime3(curTime);
					break;
				case 4:
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTrainStartTime4(curTime);
					break;
				case 5:
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTrainStartTime5(curTime);
					break;
				case 6:
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTrainStartTime6(curTime);
					break;
				}
				break;
			}
			
			ssLastTimeTV.setText(curTime);
			if(todoNo == 2)
				showPeopleNumDialog();
			else if(todoNo == 3){
				Dialog dialog = new AlertDialog.Builder(StaySurveyTimeRecordedNewActivity.this).setTitle("留乘调查").setIcon(R.drawable.app_logo)
						.setMessage("该乘客是否成功上车？").setPositiveButton("是", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//获取count即主键
								preferences = getSharedPreferences("ssRowID", MODE_WORLD_WRITEABLE);
								ssRowID = preferences.getInt("ssRowID", 1);//默认为第二个参数1
								UIHelper.ToastMessage(context, "你已经录入完成第"+ssRowID+"条数据");
								//每录完一组数据就将数据保存进数据库
								StaySurveyTimeRecordedNewActivity.staySurveyEntity.setID(ssRowID);
								StaySurveyDataHelper.insertIntoStaySurveyData(context,StaySurveyTimeRecordedNewActivity.staySurveyEntity);
								ssRowID++;
								//通过sharedPreferences将主键保存起来
								Editor editor = preferences.edit();
								editor.putInt("ssRowID",ssRowID);
								editor.commit();
								
								//跳转到详细界面,并且添加数据啊啊啊啊
								/*
								 */
								Intent it = new Intent();
								it.setClass(context, StaySurveyDataPerNewActivity.class);
								it.putExtra("countStop", countStop);
								it.putExtra("ssRowID", ssRowID);
								startActivity(it);
								
								countFlag = 0;
								countStop = 1;
								finish();
							}
						}).setNegativeButton("否", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//countFlag值待定
								countFlag = 1;
								ssTodoNextTV.setText(AppConfig.ssTodoHints[surveyTypeNo][countFlag+1]);
								countFlag++;
								//countStop为留乘的第n辆车
								countStop++;
								showPeopleNumDialog();
								//更新路线显示
								SurveyUtils.getSSLine(ssLineShowTV,user,countStop);
							}
						}).create();
				//点击dialog以外地方不消失
				dialog.setCanceledOnTouchOutside(false);
				dialog.show();
			}
			
			ssTodoNextTV.setText(AppConfig.ssTodoHints[surveyTypeNo][todoNo]);
			countFlag++;
			
			break;
			
		case R.id.searchSurveyImageView:
			Intent it = new Intent();
			it.setClass(context, StaySurveyDataPerNewActivity.class);
			it.putExtra("countStop", countStop);
			it.putExtra("ssRowID", ssRowID);
			startActivity(it);
			break;
		}
		
	}
	
	@OnClick({R.id.ssAgeValueTV,R.id.ssBagageValueTV,R.id.ssSexValueTV})
	void onSSpinnerClick(View v){
		switch(v.getId()){
		case R.id.ssAgeValueTV:
			mSpinerPopWindow.refreshData(ageList, 0);
			mSpinerPopWindow.saveRID(R.id.ssAgeValueTV);
			showSpinWindow(ssAgeValueTV);
			break;
		case R.id.ssBagageValueTV:
			mSpinerPopWindow.refreshData(bagageList, 0);
			mSpinerPopWindow.saveRID(R.id.ssBagageValueTV);
			showSpinWindow(ssBagageValueTV);
			break;
		case R.id.ssSexValueTV:
			mSpinerPopWindow.refreshData(sexList, 0);
			mSpinerPopWindow.saveRID(R.id.ssSexValueTV);
			showSpinWindow(ssSexValueTV);
			break;
		}
	}

	@Override
	public void onItemClick(int pos, int rID) {
		switch(rID){
		case R.id.ssAgeValueTV:
			Log.d("-------", "ssAgeValueTV");
			setData(pos,ageList,ssAgeValueTV);
			if(ssAgeValueTV.isClickable()==false)
				UIHelper.ToastMessage(context, "此时不可更改调查人属性");
			StaySurveyTimeRecordedNewActivity.staySurveyEntity.setAge(ssAgeValueTV.getText().toString());
			break;
		case R.id.ssBagageValueTV:
			Log.d("-------", "ssBagageValueTV");
			setData(pos,bagageList,ssBagageValueTV);
			if(ssBagageValueTV.isClickable()==false)
				UIHelper.ToastMessage(context, "此时不可更改调查人属性");
			StaySurveyTimeRecordedNewActivity.staySurveyEntity.setBagageType(ssBagageValueTV.getText().toString());
			break;
		case R.id.ssSexValueTV:
			Log.d("-------", "ssLiftValueTV");
			setData(pos,sexList,ssSexValueTV);
			if(ssSexValueTV.isClickable()==false)
				UIHelper.ToastMessage(context, "此时不可更改调查人属性");
			StaySurveyTimeRecordedNewActivity.staySurveyEntity.setSex(ssSexValueTV.getText().toString());
			break;
		}
	}
	
	private void setData(int pos,List<String> tempList,TextView tv){
		if (pos >= 0 && pos <= tempList.size()){
			String value = tempList.get(pos);
			tv.setText(value);
		}
	}
	
	private List<String> fillArrayToList(String arr[]){
		List<String> tempList = new ArrayList<String>();
		for(int i = 0; i < arr.length; i++){
			tempList.add(arr[i]);
		}
		return tempList;
	}
	
	public void showPeopleNumDialog() {

		final DialogSSCount.Builder builder = new DialogSSCount.Builder(this);
		builder.setTitle("第"+countStop+"班车人数统计");
		if(countStop==1)	
			StaySurveyTimeRecordedNewActivity.orignLineNumVisiblity = View.VISIBLE;
		else
			StaySurveyTimeRecordedNewActivity.orignLineNumVisiblity = View.GONE;
		revokeCode = AppConfig.SS_REVOKE_SUB;
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
				String a = builder.getOrignLineNumET().getText().toString();
				
				switch(countStop){
				case 1:
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setStartLineNum(builder.getOrignLineNumET().getText().toString());
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOnNum1(builder.getGetOnNumET().getText().toString());
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffNum1(builder.getGetOffNumET().getText().toString());
					break;
				case 2:
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOnNum2(builder.getGetOnNumET().getText().toString());
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffNum2(builder.getGetOffNumET().getText().toString());
					break;
				case 3:
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOnNum3(builder.getGetOnNumET().getText().toString());
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffNum3(builder.getGetOffNumET().getText().toString());
					break;
				case 4:
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOnNum4(builder.getGetOnNumET().getText().toString());
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffNum4(builder.getGetOffNumET().getText().toString());
					break;
				case 5:
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOnNum5(builder.getGetOnNumET().getText().toString());
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffNum5(builder.getGetOffNumET().getText().toString());
					break;
				case 6:
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOnNum6(builder.getGetOnNumET().getText().toString());
					StaySurveyTimeRecordedNewActivity.staySurveyEntity.setGetOffNum6(builder.getGetOffNumET().getText().toString());
					break;
				}
				
				UIHelper.ToastMessage(context, "点击确定");
				dialog.dismiss();
				//设置你的操作事项
			}
		});

		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						UIHelper.ToastMessage(context, "点击取消");
					}
				});

		builder.create().show();
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
        System.out.println();
        switch(item.getItemId()){
        case android.R.id.home:
            Log.i("TAG", "=========选中返回键");
            this.finish();
            break;
		case R.id.stay_survey_revoke:
			switch (revokeCode){
				case AppConfig.SS_REVOKE_FORBID:
					UIHelper.ToastMessage(context,"禁止撤销");
					break;
				case AppConfig.SS_REVOKE_NORMAL:
					countFlag--;
					break;
				case AppConfig.SS_REVOKE_SUB:
					if(countStop > 1)
						countStop--;
					else
						countFlag--;
					break;
			}
			//更新待测数据
			int todoNo = countFlag;
			ssTodoNextTV.setText(AppConfig.ssTodoHints[surveyTypeNo][todoNo]);
			//更新路线显示
			SurveyUtils.getSSLine(ssLineShowTV,user,countStop);
			//撤销到最开始的地方禁止撤销
			if(countFlag == 0)
				revokeCode = AppConfig.SS_REVOKE_FORBID;
			break;


        }
        return super.onOptionsItemSelected(item); 
    }
}
