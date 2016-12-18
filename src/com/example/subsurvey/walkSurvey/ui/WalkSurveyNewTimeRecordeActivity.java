package com.example.subsurvey.walkSurvey.ui;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.array;
import com.example.subsurvey.R.id;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.old.WalkSurveyTimeRecordedActivity;
import com.example.subsurvey.personalSetting.UserEntity;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;
import com.example.subsurvey.walkSurvey.helper.WalkSurveyDataHelper;
import com.example.subsurvey.widget.SpinerPopWindow;
import com.example.subsurvey.widget.AbstractSpinerAdapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
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
import android.widget.TextView;

public class WalkSurveyNewTimeRecordeActivity extends ActionBarActivity implements AbstractSpinerAdapter.IOnItemSelectListener {

	private Context context;
	private UserEntity user;
	private AppContext appContext;
	private SpinerPopWindow mSpinerPopWindow;
	private List<String> ageList = new ArrayList<String>();
	private List<String> bagageList = new ArrayList<String>();
	private List<String> liftList = new ArrayList<String>();
	private List<String> sexList = new ArrayList<String>();
	private List<String> telList = new ArrayList<String>();
	private List<String> speedList = new ArrayList<String>();
	private ArrayList<ArrayList<String>>timeDataArrTotal = new ArrayList<ArrayList<String>>();//时间数据
	private ArrayList<String> tempArr = new ArrayList<String>();
	private ArrayList<String> timeDataArrPer = new ArrayList<String>();
	private SharedPreferences preferences;
	//countFlag:调查类型某种类型次数序号 dataSum:总组数 fullFlag:分组结束标志
	private int countFlag=0,transCountFlag=0,surveyTypeNo,dataSum=1,fullFlag=0;
	//定义走行调查实体，用于将各种数据保存
	public static WalkSurveyEntity walkSurveyEntity = new WalkSurveyEntity();
	private WalkSurveyEntity tempWSEntity = new WalkSurveyEntity();
	private boolean queryFlag = false,readyFlag=false,transReadyFlag=false;//标识一组数据完成情况 queryFlag标示，是否可以查询当前组数据;readyFlag为true时，下一次计时为该小组数据的结束
	private boolean transResetFlag = false,inOutResetFlag = false,intoDBFlag = false;//transResetFlag标识换乘单组数据重置位置
	private int rowID = 1,lineNo=0,transNo=0,transRowID=1,databaseRowID=1,transTodoNo=0,todoNo =0,revokeCode = -1;//databaseRowID为数据库行数,flag仅用来帮助timeDataArrTotal填充数据
	private String curTimeBackup, curTime;


	@InjectView(R.id.wsAgeValueTV)
	TextView wsAgeValueTV;
	@InjectView(R.id.wsBagageValueTV)
	TextView wsBagageValueTV;
	@InjectView(R.id.wsLiftValueTV)
	TextView wsLiftValueTV;
	@InjectView(R.id.wsSexValueTV)
	TextView wsSexValueTV;
	@InjectView(R.id.wsTelValueTV)
	TextView wsTelValueTV;
	@InjectView(id.wsSpeedValueTV)
	TextView wsSpeedValueTV;
	@InjectView(R.id.wsLineShowTV)
	TextView wsLineShowTV;
	@InjectView(R.id.startSurveyImageView)
	ImageView startSurveyImageView;
	@InjectView(R.id.searchSurveyImageView)
	ImageView searchSurveyImageView;
	@InjectView(R.id.wsTodoNextTV)
	TextView wsTodoNextTV;
	@InjectView(R.id.wsLastTimeTV)
	TextView wsLastTimeTV;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walk_survey_new_time_recorde);
		init();
		initView();
	}



	@Override
	protected void onResume() {

//		switch(surveyTypeNo){
//		case AppConfig.WS_GETON_NO:
//			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setMachineLoc(user.getWsGateLoc());
//			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setMachineLine(user.getWsGateLine());
//			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setGetOnDire(user.getWsOnDire());
//			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setGetOnLine(user.getWsOnLine());
//			break;
//		case AppConfig.WS_GETOFF_NO:
//			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setGetOffDire(user.getWsOffDire());
//			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setGetOffLine(user.getWsOffLine());
//			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setMachineLoc(user.getWsGateLoc());
//			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setMachineLine(user.getWsGateLine());
//			break;
//		case AppConfig.WS_TRANSFER_NO:
//			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setGetOnDire(user.getWsOnDire());
//			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setGetOnLine(user.getWsOnLine());
//			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setGetOffDire(user.getWsOffDire());
//			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setGetOffLine(user.getWsOffLine());
//			break;
//		}
		super.onResume();
	}



	private void init() {

		context = getApplicationContext();
		appContext = (AppContext)getApplication();
		user = appContext.getUser();
		ButterKnife.inject(this);

		final ActionBar bar =getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
        bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标

		//判空没有做

		if(!SurveyUtils.fillUserInfoIntoEntity(context,WalkSurveyNewTimeRecordeActivity.walkSurveyEntity, user)){
			UIHelper.ToastMessage(context,"请在界面“我”中完成相关信息设置");
			this.finish();
		}
		WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setAge(wsAgeValueTV.getText().toString());
		WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setBagageType(wsBagageValueTV.getText().toString());
		WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setWalkTool(wsLiftValueTV.getText().toString());
		WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setSex(wsSexValueTV.getText().toString());
		WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setTelConcern(wsTelValueTV.getText().toString());
		WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setSpeed(wsSpeedValueTV.getText().toString());

		ageList = fillArrayToList(getResources().getStringArray(R.array.wsAge));
		bagageList = fillArrayToList(getResources().getStringArray(R.array.wsBagage));
		liftList = fillArrayToList(getResources().getStringArray(R.array.wsLift));
		sexList = fillArrayToList(getResources().getStringArray(array.wsSex));
		telList = fillArrayToList(getResources().getStringArray(array.wsTel));
		speedList = fillArrayToList(getResources().getStringArray(array.wsSpeed));

		surveyTypeNo = SurveyUtils.getSurveyTypeNo(user.getWsType(), AppConfig.wsTypeInfo);
		timeDataArrTotal.add(tempArr);
	}

	private void initView() {
		mSpinerPopWindow = new SpinerPopWindow(this);
		mSpinerPopWindow.setItemListener(this);
		wsTodoNextTV.setText(AppConfig.wsTodoHints[surveyTypeNo][0]);
		SurveyUtils.getWSLine(wsLineShowTV, user,lineNo,0);

	}

	@OnClick({R.id.wsAgeValueTV,R.id.wsBagageValueTV,R.id.wsLiftValueTV,R.id.wsSexValueTV, R.id.wsTelValueTV, id.wsSpeedValueTV})
	void onSpinnerClick(View v){
		switch(v.getId()){
		case R.id.wsAgeValueTV:
			mSpinerPopWindow.refreshData(ageList, 0);
			mSpinerPopWindow.saveRID(R.id.wsAgeValueTV);
			showSpinWindow(wsAgeValueTV);
			break;
		case R.id.wsBagageValueTV:
			mSpinerPopWindow.refreshData(bagageList, 0);
			mSpinerPopWindow.saveRID(R.id.wsBagageValueTV);
			showSpinWindow(wsBagageValueTV);
			break;
		case R.id.wsLiftValueTV:
			mSpinerPopWindow.refreshData(liftList, 0);
			mSpinerPopWindow.saveRID(R.id.wsLiftValueTV);
			showSpinWindow(wsLiftValueTV);
			break;
		case R.id.wsSexValueTV:
			mSpinerPopWindow.refreshData(sexList, 0);
			mSpinerPopWindow.saveRID(R.id.wsSexValueTV);
			showSpinWindow(wsSexValueTV);
			break;
		case id.wsTelValueTV:
			mSpinerPopWindow.refreshData(telList, 0);
			mSpinerPopWindow.saveRID(R.id.wsTelValueTV);
			showSpinWindow(wsTelValueTV);
			break;
		case id.wsSpeedValueTV:
			mSpinerPopWindow.refreshData(speedList, 0);
			mSpinerPopWindow.saveRID(R.id.wsSpeedValueTV);
			showSpinWindow(wsSpeedValueTV);
			break;
		}
	}

	@OnClick({R.id.startSurveyImageView,R.id.searchSurveyImageView})
	void onImageClick(View v){

		curTime = StringUtils.getSystemTime();
		preferences = getSharedPreferences("wsDatabaseRowID", MODE_WORLD_WRITEABLE);
		databaseRowID = preferences.getInt("wsDatabaseRowID", 1);//默认为第二个参数1
		switch(v.getId()){
		case R.id.startSurveyImageView:
			intoDBFlag = false;
			queryFlag = false;
			switch (surveyTypeNo) {
			case AppConfig.WS_ON_TO_OFF_NO:

				revokeCode = AppConfig.WS_INOUT_REVOKE_NORMAL;

				if(inOutResetFlag == true){
					inOutResetFlag = false;
					timeDataArrTotal = new ArrayList<ArrayList<String>>();
					timeDataArrTotal.add(new ArrayList<String>());
				}

//				//调查中间不允许更换乘客类型
//				wsAgeValueTV.setClickable(false);
//				wsLiftValueTV.setClickable(false);
//				wsBagageValueTV.setClickable(false);
//				wsTelValueTV.setClickable(false);
//				wsSexValueTV.setClickable(false);
//				wsSpeedValueTV.setClickable(false);

				todoNo=(countFlag+1)%(AppConfig.wsTodoHints[AppConfig.WS_ON_TO_OFF_NO].length);
				//填充view
				wsTodoNextTV.setText(AppConfig.wsTodoHints[surveyTypeNo][todoNo]);
				wsLastTimeTV.setText(curTime);

				//将小组时间保存入小组
				if(timeDataArrTotal.get(0).size()==0)
					dataSum = 1;
				timeDataArrTotal.get(dataSum-1).add(curTime);//WalkSurveyTimeRecordedActivity.dataSum->rowID

				//赋值入类
				fillInfoIntoWSClass(AppConfig.WS_ON_TO_OFF_NO);

				//处理到达站台前列车已经开门情况
				if(todoNo == 2){
					Dialog dialog = new AlertDialog.Builder(WalkSurveyNewTimeRecordeActivity.this).setTitle("提示").setIcon(R.drawable.app_logo)
							.setMessage("到达站台时列车开门是否已经开启？").setPositiveButton("确定", new onWSInOffSamePositveClickListener())
							.setNegativeButton("取消", new onWSInOffSameNegativeClickListener())
							.create();
					dialog.setCanceledOnTouchOutside(false);
					dialog.show();
				}

				//四分之一组录完
				if(readyFlag==true)
				{
					//将出站的情况剔除，只保留两个列车开门时间相同的情况
					if(countFlag != 4){
						Dialog dlg = new AlertDialog.Builder(WalkSurveyNewTimeRecordeActivity.this).setTitle("提示").setIcon(R.drawable.app_logo)
								.setMessage("是否使用相同的列车开门时间，来开始下一组调查？").setPositiveButton("确定", new onWSInOffNextPositveClickListener())
								.setNegativeButton("取消", new onWSInOffNextNegativeClickListener())
								.create();
						dlg.setCanceledOnTouchOutside(false);
						dlg.show();
					}

					int showNo = lineNo + 1 ;
					if(showNo == 0) showNo = 4;
					UIHelper.ToastMessage(WalkSurveyNewTimeRecordeActivity.this,
							"你已经录入完成第"+rowID+"组"+AppConfig.wsTypeInfo[surveyTypeNo].toString()+"的第"+showNo+"条数据");
					intoDBFlag = true;
					queryFlag = true;
					//路线信息保存进实体类
					insertInOutLineInfo(WalkSurveyNewTimeRecordeActivity.walkSurveyEntity,user,lineNo);
					lineNo = (lineNo+1)%4;
					//半组录完
					if(AppConfig.wsTodoHints[AppConfig.WS_ON_TO_OFF_NO][countFlag].equals("出站刷卡时刻")){
						countFlag = -1;
						revokeCode = AppConfig.WS_INOUT_REVOKE_TWO;
					}

					//一大组数据录完
					if(lineNo==0){
						//todo 整组数据减一
						UIHelper.ToastMessage(WalkSurveyNewTimeRecordeActivity.this,"你已经录入完成第"+rowID+"组数据");
						revokeCode = AppConfig.WS_INOUT_REVOKE_FOUR;
						rowID++;
						inOutResetFlag = true;

//						timeDataArrTotal = new ArrayList<ArrayList<String>>();
					}
					readyFlag = false;

					//小组数据保存入库
					insertWSInfoIntoDB();

//					//调查小组结束后可以修改乘客类型
//					wsAgeValueTV.setClickable(true);
//					wsLiftValueTV.setClickable(true);
//					wsBagageValueTV.setClickable(true);
//					wsTelValueTV.setClickable(true);
//					wsSexValueTV.setClickable(true);
//					wsSpeedValueTV.setClickable(true);

					dataSum++;
					tempArr = new ArrayList<String>();
					timeDataArrTotal.add(tempArr);
				}

				//lineNo为0和2时是进站
				//todoNo还没改
				SurveyUtils.getWSLine(wsLineShowTV, user,lineNo,todoNo);
				//小组数据即将结束条件
				if(todoNo == 2 || todoNo ==4)
					readyFlag = true;
				countFlag++;
				curTimeBackup = curTime;
				break;
			//换乘走行
			case AppConfig.WS_TRANSF_NO:

				revokeCode = AppConfig.WS_TRANSFER_REVOKE_NORMAL;

				transTodoNo = (transCountFlag + 1)%(AppConfig.wsTodoHints[surveyTypeNo].length);

				//填充view
				wsTodoNextTV.setText(AppConfig.wsTodoHints[surveyTypeNo][transTodoNo]);
				wsLastTimeTV.setText(curTime);

				//将小组时间保存入数组
				if(timeDataArrTotal.get(0).size()==0)
					dataSum = 1;
				timeDataArrTotal.get(dataSum-1).add(curTime);

				//赋值入类
				fillInfoIntoWSClass(AppConfig.WS_TRANSF_NO);

				//处理到达站台前列车已经开门情况
				if(transTodoNo == 2){
					Dialog dialog = new AlertDialog.Builder(WalkSurveyNewTimeRecordeActivity.this).setTitle("提示").setIcon(R.drawable.app_logo)
							.setMessage("到达站台时列车开门是否已经开启？").setPositiveButton("确定", new onWSTransSamePositveClickListener())
							.setNegativeButton("取消", new onWSTransSameNegativeClickListener())
							.create();
					dialog.setCanceledOnTouchOutside(false);
					dialog.show();
				}

				if(transReadyFlag){
					//弹出窗口询问是否使用同一组数据
					Dialog dialog = new AlertDialog.Builder(WalkSurveyNewTimeRecordeActivity.this).setTitle("提示").setIcon(R.drawable.app_logo)
							.setMessage("是否使用相同的列车开门时间，来开始下一组调查？").setPositiveButton("确定", new onWSTransNextPositveClickListener())
							.setNegativeButton("取消", new onWSTransNextNegativeClickListener())
							.create();
					dialog.setCanceledOnTouchOutside(false);
					dialog.show();

					int showNo = transNo + 1 ;
					if(showNo == 0) showNo = 4;
					UIHelper.ToastMessage(WalkSurveyNewTimeRecordeActivity.this,
//							"你已经录入完成第"+transRowID+"组"+AppConfig.wsTypeInfo[surveyTypeNo].toString()+"的第"+(transNo+1)+"条数据");
							"你已经录入完成第"+transRowID+"组"+AppConfig.wsTypeInfo[surveyTypeNo].toString()+"的第"+showNo+"条数据");
					intoDBFlag = true;
					//赋值入类
					insertTransferLineInfo(WalkSurveyNewTimeRecordeActivity.walkSurveyEntity,user,transNo);
					transNo = (transNo+1)%4;
					transReadyFlag = false;
					//分组数据记录完毕后允许查询
					queryFlag = true;
					//分组数据入库
					insertWSInfoIntoDB();
					//数组重置
					tempArr = new ArrayList<String>();
					timeDataArrTotal.add(tempArr);
					dataSum++;
				}
				SurveyUtils.getWSLine(wsLineShowTV, user,transNo,transTodoNo);

				//小组数据即将结束条件
				if(transTodoNo == 2)
					transReadyFlag = true;
				transCountFlag++;
				curTimeBackup = curTime;

			}
			break;
		case R.id.searchSurveyImageView:
			if(queryFlag==false){
				UIHelper.ToastMessage(context, "请在录完一组数据后，进行数据修改");
				return;
			}
			Intent it = new Intent();
			Bundle bundle = new Bundle();
			bundle.putSerializable("timeDataArrTotal", timeDataArrTotal);
			it.putExtras(bundle);
 			it.putExtra("surveyType", user.getWsType());
 			it.putExtra("lineNo", lineNo);
			 it.putExtra("transNo",transNo);
 			it.putExtra("rowID", databaseRowID);//改
			it.setClass(getApplicationContext(), WalkSurveyDataPerNewActivity.class);
			startActivity(it);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.walk_survey_change_line, menu);
		MenuItemCompat.setShowAsAction(menu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	public void onItemClick(int pos,int rID) {
		switch(rID){
		case R.id.wsAgeValueTV:
			Log.d("-------", "wsAgeValueTV");
			setData(pos,ageList,wsAgeValueTV);
			if(wsAgeValueTV.isClickable()==false)
				UIHelper.ToastMessage(context, "此时不可更改调查人属性");
			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setAge(wsAgeValueTV.getText().toString());
			break;
		case R.id.wsBagageValueTV:
			setData(pos,bagageList,wsBagageValueTV);
			if(wsBagageValueTV.isClickable()==false)
				UIHelper.ToastMessage(context, "此时不可更改调查人属性");
			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setBagageType(wsBagageValueTV.getText().toString());
			break;
		case R.id.wsLiftValueTV:
			setData(pos,liftList,wsLiftValueTV);
			if(wsLiftValueTV.isClickable()==false)
				UIHelper.ToastMessage(context, "此时不可更改调查人属性");
			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setWalkTool(wsLiftValueTV.getText().toString());
			break;
		case R.id.wsSexValueTV:
			setData(pos,sexList,wsSexValueTV);
			if(wsSexValueTV.isClickable()==false)
				UIHelper.ToastMessage(context, "此时不可更改调查人属性");
			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setSex(wsSexValueTV.getText().toString());
			break;
		case R.id.wsTelValueTV:
			setData(pos,telList,wsTelValueTV);
			if(wsTelValueTV.isClickable()==false)
				UIHelper.ToastMessage(context, "此时不可更改调查人属性");
			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setTelConcern(wsTelValueTV.getText().toString());
			break;
		case R.id.wsSpeedValueTV:
			setData(pos,speedList,wsSpeedValueTV);
			if(wsSpeedValueTV.isClickable()==false)
				UIHelper.ToastMessage(context, "此时不可更改调查人属性");
			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setSpeed(wsSpeedValueTV.getText().toString());
			break;
		}
	}

	private void setData(int pos,List<String> tempList,TextView tv){
		if (pos >= 0 && pos <= tempList.size()){
			String value = tempList.get(pos);
			tv.setText(value);
		}
	}

	private void showSpinWindow(TextView tv){
		mSpinerPopWindow.setWidth(tv.getWidth());
		mSpinerPopWindow.showAsDropDown(tv);
	}

	private List<String> fillArrayToList(String arr[]){
		List<String> tempList = new ArrayList<String>();
		for(int i = 0; i < arr.length; i++){
			tempList.add(arr[i]);
		}
		return tempList;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			break;
		case id.walk_survey_revoke:
			int revokeArrCode = AppConfig.WS_INOUT_REVOKE_ARR_ODD;
			switch(surveyTypeNo){
			//进出站类型走行撤销数据
			case AppConfig.WS_ON_TO_OFF_NO:
				switch (revokeCode){
					case AppConfig.WS_INOUT_REVOKE_FORBID:
						UIHelper.ToastMessage(context,"禁止撤销");
						break;
					case AppConfig.WS_INOUT_REVOKE_NORMAL:
						readyFlag = false;
						queryFlag = false;
						if(countFlag == 3){
							lineNo--;
							readyFlag = true;
						}
						countFlag = countFlag - 1;
						break;
					//只要误点出“到达站台前列车开门”的对话框，不管第二个对话框选什么，都复位到同一地方
					case AppConfig.WS_INOUT_REVOKE_EQUAL:
						countFlag = 1;
						readyFlag = false;
						queryFlag = false;
						lineNo--;
						revokeCode = AppConfig.WS_INOUT_REVOKE_NORMAL;
						break;
					case AppConfig.WS_INOUT_REVOKE_NEXT:
						countFlag = 1;
						readyFlag = false;
						queryFlag = false;
						lineNo--;
						revokeCode = AppConfig.WS_INOUT_REVOKE_NORMAL;
						break;
					case AppConfig.WS_INOUT_REVOKE_TWO:
						countFlag = 4;
						readyFlag = true;
						queryFlag = false;
						lineNo--;
						revokeCode = AppConfig.WS_INOUT_REVOKE_NORMAL;
						revokeArrCode = AppConfig.WS_INOUT_REVOKE_ARR_EVEN;
						break;
					case AppConfig.WS_INOUT_REVOKE_FOUR:
						rowID--;
						countFlag = 4;
						readyFlag = true;
						queryFlag = false;
						lineNo--;
						//inOutResetFlag为true时，记录时间的数组清空
						inOutResetFlag = false;
						revokeCode = AppConfig.WS_INOUT_REVOKE_NORMAL;
						revokeArrCode = AppConfig.WS_INOUT_REVOKE_ARR_EVEN;
						break;
				}
				//更新路线显示
				todoNo = (countFlag +5)%(AppConfig.wsTodoHints[surveyTypeNo].length);
				wsTodoNextTV.setText(AppConfig.wsTodoHints[surveyTypeNo][todoNo]);
				SurveyUtils.getWSLine(wsLineShowTV, user,lineNo,todoNo);
				//撤销到最开始的地方则禁止撤销
				if(countFlag == 0 || countFlag == 3)
					revokeCode = AppConfig.WS_INOUT_REVOKE_FORBID;
				//录入新的数据情况下，撤销要删除最新一条数据
				if(intoDBFlag == true){
					//处理数据库
					WalkSurveyDataHelper.delWalkSurveyInfoByNewestID(context);
					Editor editor = preferences.edit();
					databaseRowID--;
					editor.putInt("wsDatabaseRowID", databaseRowID);
					editor.commit();
					UIHelper.ToastMessage(context, "已撤销一条记录");
					intoDBFlag = false;
					copyWSEntity(tempWSEntity,WalkSurveyNewTimeRecordeActivity.walkSurveyEntity);
					//处理数组
					timeDataArrTotal.remove(--dataSum);
					timeDataArrTotal.get(dataSum-1).clear();
					//todo 单组数据
					//撤销后，将原始数据保存在数组中，以便单组查询修改;两种情形：前组保存进站刷卡时刻(奇数组)，后者保存列车开门时刻（偶数组）
					if(revokeArrCode == AppConfig.WS_INOUT_REVOKE_ARR_ODD)
						timeDataArrTotal.get(dataSum-1).add(tempWSEntity.getGotoPlatformTime());
					else if(revokeArrCode == AppConfig.WS_INOUT_REVOKE_ARR_EVEN)
						timeDataArrTotal.get(dataSum-1).add(tempWSEntity.getOpenDoorTime1());
				}else{
					//数组更新
					int last = timeDataArrTotal.get(dataSum-1).size() - 1;
					if(last >= 0)
						timeDataArrTotal.get(dataSum-1).remove(last);
				}
				break;
			//换乘类型走行撤销数据
			case AppConfig.WS_TRANSF_NO:
				switch (revokeCode){
					case AppConfig.WS_TRANSFER_REVOKE_FORBID:
						UIHelper.ToastMessage(context,"禁止撤销");
						break;
					//一般情况
					case AppConfig.WS_TRANSFER_REVOKE_NORMAL:
						queryFlag = false;
						transReadyFlag = false;
						if(transCountFlag == 3){
							transNo--;
							transReadyFlag = true;
						}
						transCountFlag = transCountFlag-1;
						break;
					//只要误点出“到达站台前列车开门”的对话框，不管第二个对话框选什么，都复位到同一地方
					case AppConfig.WS_TRANSFER_REVOKE_EQUAL:
						transCountFlag = 1;
						transReadyFlag = false;
						queryFlag = false;
						transNo--;
						revokeCode = AppConfig.WS_TRANSFER_REVOKE_NORMAL;
						break;
					case AppConfig.WS_TRANSFER_REVOKE_NEXT:
						transCountFlag = 1;
						transNo--;
						transReadyFlag = false;
						queryFlag = false;
						revokeCode = AppConfig.WS_TRANSFER_REVOKE_NORMAL;
						break;
				}

				//更新路线显示
				transTodoNo = (transCountFlag +3)%(AppConfig.wsTodoHints[surveyTypeNo].length);
				wsTodoNextTV.setText(AppConfig.wsTodoHints[surveyTypeNo][transTodoNo]);
				SurveyUtils.getWSLine(wsLineShowTV, user,transNo,transTodoNo);
				//撤销到最开始的地方则禁止撤销
				if(transCountFlag == 0)
					revokeCode = AppConfig.WS_TRANSFER_REVOKE_FORBID;
				//录入新的数据情况下，撤销要删除最新一条数据
				if(intoDBFlag == true){
					//处理数据库
					WalkSurveyDataHelper.delWalkSurveyInfoByNewestID(context);
					Editor editor = preferences.edit();
					databaseRowID--;
					editor.putInt("wsDatabaseRowID", databaseRowID);
					editor.commit();
					UIHelper.ToastMessage(context, "已撤销一条记录");
					intoDBFlag = false;
					copyWSEntity(tempWSEntity,WalkSurveyNewTimeRecordeActivity.walkSurveyEntity);
					//处理数组
					timeDataArrTotal.remove(--dataSum);
					timeDataArrTotal.get(dataSum-1).clear();
					//撤销后，将原始数据保存在数组中，以便单组查询修改;默认撤销都回到只保留第一个列车开门时刻
					timeDataArrTotal.get(dataSum-1).add(tempWSEntity.getOpenDoorTime1());
				}else{
					//数组更新
					int last = timeDataArrTotal.get(dataSum-1).size() - 1;
					if(last >= 0)
						timeDataArrTotal.get(dataSum-1).remove(last);
				}
				break;
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initWSEntity(WalkSurveyEntity wsEntity){
		wsEntity.setArrivePlatformTime("");
		wsEntity.setOpenDoorTime1("");
		wsEntity.setOpenDoorTime2("");
		wsEntity.setGotoPlatformTime("");
		wsEntity.setGooutPlatformTime("");
		wsEntity.setMachineLoc("");
		wsEntity.setMachineLine("");
		wsEntity.setGetOnLine("");
		wsEntity.setGetOnDire("");
		wsEntity.setGetOffLine("");
		wsEntity.setGetOffDire("");
	}

	private void insertTransferLineInfo(WalkSurveyEntity wsEntity,UserEntity user,int transferNo){
		switch((transferNo+1)%4){
		case 0:
			wsEntity.setGetOffLine(user.getWslStartLine());
			wsEntity.setGetOffDire(user.getWslStartLineStartDire());
			wsEntity.setGetOnLine(user.getWslEndLine());
			wsEntity.setGetOnDire(user.getWslEndLineStartDire());
			wsEntity.setRouteNo("4");
			break;
		case 1:
			wsEntity.setGetOffLine(user.getWslEndLine());
			wsEntity.setGetOffDire(user.getWslEndLineStartDire());
			wsEntity.setGetOnLine(user.getWslStartLine());
			wsEntity.setGetOnDire(user.getWslStartLineEndDire());
			wsEntity.setRouteNo("1");
			break;
		case 2:
			wsEntity.setGetOffLine(user.getWslStartLine());
			wsEntity.setGetOffDire(user.getWslStartLineEndDire());
			wsEntity.setGetOnLine(user.getWslEndLine());
			wsEntity.setGetOnDire(user.getWslEndLineEndDire());
			wsEntity.setRouteNo("2");
			break;
		case 3:
			wsEntity.setGetOffLine(user.getWslEndLine());
			wsEntity.setGetOffDire(user.getWslEndLineEndDire());
			wsEntity.setGetOnLine(user.getWslStartLine());
			wsEntity.setGetOnDire(user.getWslStartLineStartDire());
			wsEntity.setRouteNo("3");
			break;
		}
	}

	private void insertInOutLineInfo(WalkSurveyEntity wsEntity,UserEntity user,int transferNo){
		switch((transferNo+1)%4){
		case 0:
			wsEntity.setMachineLoc(user.getWslStartGLoc());
			wsEntity.setMachineLine(user.getWslGLine());
			wsEntity.setGetOnLine(user.getWslTrainLine());
			wsEntity.setGetOnDire(user.getWslDireSToE());
			wsEntity.setRouteNo("4");
			break;
		case 1:
			wsEntity.setGetOffLine(user.getWslTrainLine());
			wsEntity.setGetOffDire(user.getWslDireSToE());
			wsEntity.setMachineLine(user.getWslGLine());
			wsEntity.setMachineLoc(user.getWslEndGLoc());
			wsEntity.setRouteNo("1");
			break;
		case 2:
			wsEntity.setMachineLoc(user.getWslEndGLoc());
			wsEntity.setMachineLine(user.getWslGLine());
			wsEntity.setGetOnLine(user.getWslTrainLine());
			wsEntity.setGetOnDire(user.getWslDireEToS());
			wsEntity.setRouteNo("2");
			break;
		case 3:
			wsEntity.setGetOffLine(user.getWslTrainLine());
			wsEntity.setGetOffDire(user.getWslDireEToS());
			wsEntity.setMachineLine(user.getWslGLine());
			wsEntity.setMachineLoc(user.getWslStartGLoc());
			wsEntity.setRouteNo("3");
			break;
		}
	}

	private void insertWSInfoIntoDB(){
		//小组数据入库
		WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setID(databaseRowID);
		WalkSurveyDataHelper.insertIntoWalkSurveyData(context, WalkSurveyNewTimeRecordeActivity.walkSurveyEntity);
		copyWSEntity(WalkSurveyNewTimeRecordeActivity.walkSurveyEntity,tempWSEntity);
		initWSEntity(WalkSurveyNewTimeRecordeActivity.walkSurveyEntity);
		databaseRowID++;
		Editor editor = preferences.edit();
		editor.putInt("wsDatabaseRowID",databaseRowID);
		editor.commit();
	}


	//立即开始下一组数据
	private class onWSTransNextPositveClickListener implements DialogInterface.OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			//处理数组
			timeDataArrTotal.get(dataSum-1).add(curTime);

			initWSEntity(WalkSurveyNewTimeRecordeActivity.walkSurveyEntity);
			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setOpenDoorTime1(curTime);

			transCountFlag = 0;
			transTodoNo = (transCountFlag + 1)%(AppConfig.wsTodoHints[surveyTypeNo].length);
			wsTodoNextTV.setText(AppConfig.wsTodoHints[surveyTypeNo][transTodoNo]);
			SurveyUtils.getWSLine(wsLineShowTV, user,transNo,transTodoNo);
			transCountFlag++;

			revokeCode = AppConfig.WS_TRANSFER_REVOKE_NEXT;
		}
	}

	//取消
	private class onWSTransNextNegativeClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
		}
	}

	//处理到达站台时刻和列车开门时刻相同的情形--换乘走行
	private class onWSTransSamePositveClickListener implements DialogInterface.OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			Dialog dlg = new AlertDialog.Builder(WalkSurveyNewTimeRecordeActivity.this).setTitle("提示").setIcon(R.drawable.app_logo)
					.setMessage("是否使用相同的列车开门时间，来开始下一组调查？").setPositiveButton("确定", new onWSTransNextPositveClickListener())
					.setNegativeButton("取消", new onWSTransNextNegativeClickListener())
					.create();
			dlg.setCanceledOnTouchOutside(false);
			dlg.show();
			//数组
			timeDataArrTotal.get(dataSum-1).add(curTime);
			//入类
			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setOpenDoorTime2(curTime);
			insertTransferLineInfo(WalkSurveyNewTimeRecordeActivity.walkSurveyEntity,user,transNo);
			int showNo = transNo + 1 ;
			if(showNo == 0) showNo = 4;
			UIHelper.ToastMessage(WalkSurveyNewTimeRecordeActivity.this,
					"你已经录入完成第"+transRowID+"组"+AppConfig.wsTypeInfo[surveyTypeNo].toString()+"的第"+ showNo +"条数据");
			intoDBFlag = true;
			//数组重置
			tempArr = new ArrayList<String>();
			timeDataArrTotal.add(tempArr);
			dataSum ++;
			//保存数据入库
			insertWSInfoIntoDB();

			//相关数据更改
			transNo = (transNo+1)%4;
			transReadyFlag = false;
			queryFlag = true;
			//更新线路
			transCountFlag = -1;
			transTodoNo = (transCountFlag + 1)%(AppConfig.wsTodoHints[surveyTypeNo].length);
			wsTodoNextTV.setText(AppConfig.wsTodoHints[surveyTypeNo][transTodoNo]);
			SurveyUtils.getWSLine(wsLineShowTV, user,transNo,transTodoNo);
			transCountFlag++;

			revokeCode = AppConfig.WS_TRANSFER_REVOKE_EQUAL;
		}
	}

	//取消
	private class onWSTransSameNegativeClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
		}
	}

	//赋值入类
	private void fillInfoIntoWSClass(int wsType){
		switch (wsType){
			case AppConfig.WS_TRANSF_NO:
				if(AppConfig.wsTodoHints[surveyTypeNo][transCountFlag%3].equals("列车开门时刻")){
					if(transCountFlag%3==0)
						WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setOpenDoorTime1(curTime);
					if(transCountFlag%3==2){
						WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setOpenDoorTime2(curTime);
					}
				}
				else if(AppConfig.wsTodoHints[surveyTypeNo][transCountFlag%3].equals("到达站台时刻")){
					WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setArrivePlatformTime(curTime);
				}
				break;
			case AppConfig.WS_ON_TO_OFF_NO:
				if(AppConfig.wsTodoHints[surveyTypeNo][countFlag%5].equals("进站刷卡时刻"))
					WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setGotoPlatformTime(curTime);
				else if(AppConfig.wsTodoHints[surveyTypeNo][countFlag%5].equals("到达站台时刻"))
					WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setArrivePlatformTime(curTime);
				else if(AppConfig.wsTodoHints[surveyTypeNo][countFlag%5].equals("出站刷卡时刻")){
					WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setGooutPlatformTime(curTime);
					WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setOpenDoorTime1(curTimeBackup);
					timeDataArrTotal.get(dataSum-1).add(curTimeBackup);
				}
				else if(AppConfig.wsTodoHints[surveyTypeNo][countFlag%5].equals("列车开门时刻")&&(lineNo==0||lineNo==2))
					WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setOpenDoorTime2(curTime);
				break;
		}
	}

	//处理到达站台时刻和列车开门时刻相同的情形--进出站走行
	private class onWSInOffSamePositveClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			Dialog dlg = new AlertDialog.Builder(WalkSurveyNewTimeRecordeActivity.this).setTitle("提示").setIcon(R.drawable.app_logo)
					.setMessage("是否使用相同的列车开门时间，来开始下一组调查？").setPositiveButton("确定", new onWSInOffNextPositveClickListener())
					.setNegativeButton("取消", new onWSInOffNextNegativeClickListener())
					.create();
			dlg.setCanceledOnTouchOutside(false);
			dlg.show();
			//数组
			timeDataArrTotal.get(dataSum-1).add(curTime);
			//入类
			insertInOutLineInfo(WalkSurveyNewTimeRecordeActivity.walkSurveyEntity,user,lineNo);
			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setOpenDoorTime2(curTime);
			int showNo = lineNo + 1 ;
			if(showNo == 0) showNo = 4;
			UIHelper.ToastMessage(WalkSurveyNewTimeRecordeActivity.this,
					"你已经录入完成第"+rowID+"组"+AppConfig.wsTypeInfo[surveyTypeNo].toString()+"的第"+showNo+"条数据");
			intoDBFlag = true;
			//数组重置
			tempArr = new ArrayList<String>();
			timeDataArrTotal.add(tempArr);
			dataSum ++;
			//保存入库
			insertWSInfoIntoDB();
			//相关数据更改
			queryFlag = true;
			lineNo = (lineNo +1)%4;
			readyFlag = false;
			//更新线路
			countFlag = 2;
			todoNo = (countFlag + 1)%(AppConfig.wsTodoHints[surveyTypeNo].length);
			wsTodoNextTV.setText(AppConfig.wsTodoHints[surveyTypeNo][todoNo]);
			SurveyUtils.getWSLine(wsLineShowTV, user,lineNo,todoNo);
			countFlag++;

			revokeCode = AppConfig.WS_INOUT_REVOKE_EQUAL;
		}
	}

	//取消
	private class onWSInOffSameNegativeClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
		}
	}

	//立即开始下一组数据
	private class onWSInOffNextPositveClickListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {

			//处理数组
			timeDataArrTotal.get(dataSum-1).add(curTime);

			initWSEntity(WalkSurveyNewTimeRecordeActivity.walkSurveyEntity);
			WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setOpenDoorTime1(curTime);

			countFlag = 3;
			todoNo = (countFlag + 1)%(AppConfig.wsTodoHints[surveyTypeNo].length);
			wsTodoNextTV.setText(AppConfig.wsTodoHints[surveyTypeNo][todoNo]);
			SurveyUtils.getWSLine(wsLineShowTV, user,lineNo,todoNo);
			countFlag++;
			readyFlag = true;

			revokeCode = AppConfig.WS_INOUT_REVOKE_NEXT;
		}
	}

	//取消
	private class onWSInOffNextNegativeClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
		}
	}

	public void  copyWSEntity(WalkSurveyEntity oldWS,WalkSurveyEntity newWS){
//		wsEntity.setArrivePlatformTime("");
//		wsEntity.setOpenDoorTime1("");
//		wsEntity.setOpenDoorTime2("");
//		wsEntity.setGotoPlatformTime("");
//		wsEntity.setGooutPlatformTime("");
		//换乘类型的备份
		newWS.setOpenDoorTime1(oldWS.getOpenDoorTime1());
		newWS.setOpenDoorTime2(oldWS.getOpenDoorTime2());
		newWS.setArrivePlatformTime(oldWS.getArrivePlatformTime());
		//进出站
		newWS.setGotoPlatformTime(oldWS.getGotoPlatformTime());
		newWS.setGooutPlatformTime(oldWS.getGooutPlatformTime());
	}

	//撤销后，将原始数据保存在数组中，以便单组查询修改
	public void copyWSDataArrTotal(WalkSurveyEntity oldWS,ArrayList<ArrayList<String>>timeDataArrTotal){
		//默认撤销都回到只保留第一个列车开门时刻
//		ArrayList<String> tempArr = new ArrayList<String>();
//		tempArr.add(oldWS.getOpenDoorTime1());
//		timeDataArrTotal.add(tempArr);
		timeDataArrTotal.get(dataSum-1).add(oldWS.getOpenDoorTime1());
	}
}
