package com.example.subsurvey.odSurvey.ui;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;
import com.example.subsurvey.odSurvey.helper.ODSurveyDataHelper;
import com.example.subsurvey.personalSetting.UserEntity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ODSurveyTimeRecordedNewActivity extends ActionBarActivity {

	@InjectView(R.id.odLineShowTV)
	TextView odLineShowTV;
	@InjectView(R.id.ODFlightNumberPicker)
	NumberPicker ODFlightNumberPicker;
	@InjectView(R.id.odTodoNextTV)
	TextView odTodoNextTV;
	@InjectView(R.id.odLastTimeTV)
	TextView odLastTimeTV;
	@InjectView(R.id.ODStartSurveyImageView)
	ImageView ODStartSurveyImageView;
	@InjectView(R.id.ODSearchSurveyImageView)
	ImageView ODSearchSurveyImageView;
	@InjectView(R.id.odFlightRL)
	RelativeLayout odFlightRL;

	private Context context;
	private UserEntity user;
	private AppContext appContext;
	private int surveyTypeNo, countFlag = 0, noFlag = 0, odDatabaseID = 1, odLineNo = 0; //countFlag标志记录第n小组数据,noFlag记录
	public static ODSurveyEntity odSurveyEntity;
	private ODSurveyEntity odTempData;
	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_odsurvey_time_recorded_new);
		init();
		initView();
	}


	private void init() {
		final ActionBar bar = getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		context = getApplicationContext();
		appContext = (AppContext) getApplication();
		user = appContext.getUser();
		ButterKnife.inject(this);
		ODSurveyTimeRecordedNewActivity.odSurveyEntity = new ODSurveyEntity();

		surveyTypeNo = SurveyUtils.getSurveyTypeNo(user.getOdType(), AppConfig.odTypeInfo);
		//od属性复制入类
		fillDataIntoODEntityByUser(ODSurveyTimeRecordedNewActivity.odSurveyEntity, user);

	}

	private void initView() {
		ODFlightNumberPicker.setValue(1);
		ODFlightNumberPicker.setMinValue(1);
		ODFlightNumberPicker.setMaxValue(6);
		odTodoNextTV.setText("进站刷卡时刻");
		SurveyUtils.getODLine(odLineShowTV, user, 0);
		odTempData = appContext.getODTempData();
		Intent it = getIntent();
		odFlightRL.setVisibility(View.GONE);

		//根据暂存的数据初始化界面
		if (!odTempData.getFinished()) {
			if (!it.getBooleanExtra("isNewSurvey", true)) {
				//继续之前的调查
				//TODO 通过appContext.getODTempData()来获取ODSurveyEntity类，并根据sequenceNo和pathType来填充类和指定正确的界面
				int sequenceNo = Integer.parseInt(odTempData.getSequenceNO());
				String pathType = odTempData.getPathType();
				//初始化surveyTypeNo，为了应对由于利用缓存数据重新init()导致的surveyTypeNo错误的问题
				surveyTypeNo = Integer.parseInt(odTempData.getTransferCount());
				// 将getODTempData赋值入类，注意要把调查类型传进来，并赋值
				putTempdataIntoEntity(odTempData);
				switch (sequenceNo){
					case 1:
						odLastTimeTV.setText(odTempData.getGetinStationTime());

						odTodoNextTV.setText("到达站台时刻");
						noFlag = 1;
						break;
					case 2:
						odLastTimeTV.setText(odTempData.getArriveStationTime1());
						odTodoNextTV.setText("列车开行时刻");
						odFlightRL.setVisibility(View.VISIBLE);
						noFlag = 2;
						break;
					case 3:
						odLastTimeTV.setText(odTempData.getTrainStartingTime1());
						odTodoNextTV.setText("下车时刻");
						noFlag = 3;
						break;
					case 4:
						odLastTimeTV.setText(odTempData.getGetoffStationTime1());
						//如果是无换乘则出站了，否则为到达站台时刻
						odLineNo = 1;
						if(pathType.equals("无换乘")){
							noFlag = -1;
							odTodoNextTV.setText("出站刷卡时刻");
						}
						else{
							odTodoNextTV.setText("到达站台时刻");
							noFlag = 1;
						}

						break;

					case 6:
						odLineNo = 1;
						odLastTimeTV.setText(odTempData.getArriveStationTime2());
						noFlag = 2;
						odTodoNextTV.setText("列车开行时刻");
						odFlightRL.setVisibility(View.VISIBLE);
						break;
					case 7:
						odLineNo = 1;
						noFlag = 3;
						odLastTimeTV.setText(odTempData.getTrainStartingTime2());
						odTodoNextTV.setText("下车时刻");
						break;
					case 8:
						odLineNo = 2;
						odLastTimeTV.setText(odTempData.getGetoffStationTime2());
						if(pathType.equals("换乘一次")){
							noFlag = -1;
							odTodoNextTV.setText("出站刷卡时刻");
							odLineNo = 1;
						}else{
							noFlag = 1;
							odTodoNextTV.setText("到达站台时刻");
						}

						break;

					case 10:
						odLineNo = 2;
						noFlag = 2;
						odLastTimeTV.setText(odTempData.getArriveStationTime3());
						odTodoNextTV.setText("列车开行时刻");
						odFlightRL.setVisibility(View.VISIBLE);
						break;
					case 11:
						odLineNo = 2;
						noFlag = 3;
						odLastTimeTV.setText(odTempData.getTrainStartingTime3());
						odTodoNextTV.setText("下车时刻");
						break;
					case 12:
						odLineNo = 2;
						noFlag = -1;
						odLastTimeTV.setText(odTempData.getGetoffStationTime3());
						odTodoNextTV.setText("出站刷卡时刻");
						break;
				}
			}
		}
		//TODO:注意，OD缓存数据不缓存User信息，当user信息改动后还继续调查就不能保证路线信息的正确性
		SurveyUtils.getODLine(odLineShowTV, user, odLineNo);
	}

	/**
	 * 将OD调查的缓存数据存入
	 * @param odTempData
     */
	private void putTempdataIntoEntity(ODSurveyEntity odTempData) {
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetinStationTime(odTempData.getGetinStationTime());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setArriveStationTime1(odTempData.getArriveStationTime1());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setTrainStartingTime1(odTempData.getTrainStartingTime1());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetoffStationTime1(odTempData.getGetoffStationTime1());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setArriveStationTime2(odTempData.getArriveStationTime2());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setTrainStartingTime2(odTempData.getTrainStartingTime2());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetoffStationTime2(odTempData.getGetoffStationTime2());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setArriveStationTime3(odTempData.getArriveStationTime3());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setTrainStartingTime3(odTempData.getTrainStartingTime3());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetoffStationTime3(odTempData.getGetoffStationTime3());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetoutStationTime(odTempData.getGetoutStationTime());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setShift1(odTempData.getShift1());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setShift2(odTempData.getShift2());
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setShift3(odTempData.getShift3());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.odsurvey_time_recorded_new, menu);
		return true;
	}

	@OnClick({R.id.ODStartSurveyImageView, R.id.ODSearchSurveyImageView})
	void onODRecordedClick(View v) {
		odTempData = appContext.getODTempData();
		odTempData.setFinished(false);
		odTempData.setPathType(user.getOdType());

		switch (v.getId()) {
			case R.id.ODStartSurveyImageView:

				String curTime = StringUtils.getSystemTime();
				odLastTimeTV.setText(curTime);
			/*
			 * 根据提示内容赋值
			 */
				if (odTodoNextTV.getText().equals("进站刷卡时刻")) {
					ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetinStationTime(curTime);
					odTempData.setGetinStationTime(curTime);
					//设置缓存数据的OD类型
					odTempData.setTransferCount(surveyTypeNo + "");
					odTempData.setSequenceNO("1");
				}
				switch (odLineNo) {
					//第一班车
					case 0:
						if (odTodoNextTV.getText().equals("到达站台时刻")) {
							ODSurveyTimeRecordedNewActivity.odSurveyEntity.setArriveStationTime1(curTime);
							odTempData.setArriveStationTime1(curTime);
							odTempData.setSequenceNO("2");
						} else if (odTodoNextTV.getText().equals("列车开行时刻")) {
							ODSurveyTimeRecordedNewActivity.odSurveyEntity.setTrainStartingTime1(curTime);
							odTempData.setTrainStartingTime1(curTime);
							odTempData.setSequenceNO("3");
						} else if (odTodoNextTV.getText().equals("下车时刻")) {
							ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetoffStationTime1(curTime);
							odTempData.setGetoffStationTime1(curTime);
							odTempData.setSequenceNO("4");
						}
						break;
					//第二班车
					case 1:
						if (odTodoNextTV.getText().equals("到达站台时刻")) {
							ODSurveyTimeRecordedNewActivity.odSurveyEntity.setArriveStationTime2(curTime);
							odTempData.setArriveStationTime2(curTime);
							odTempData.setSequenceNO("6");
						} else if (odTodoNextTV.getText().equals("列车开行时刻")) {
							ODSurveyTimeRecordedNewActivity.odSurveyEntity.setTrainStartingTime2(curTime);
							odTempData.setTrainStartingTime2(curTime);
							odTempData.setSequenceNO("7");
						} else if (odTodoNextTV.getText().equals("下车时刻")) {
							ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetoffStationTime2(curTime);
							odTempData.setGetoffStationTime2(curTime);
							odTempData.setSequenceNO("8");
						}
						break;
					//第三班车
					case 2:
						if (odTodoNextTV.getText().equals("到达站台时刻")) {
							ODSurveyTimeRecordedNewActivity.odSurveyEntity.setArriveStationTime3(curTime);
							odTempData.setArriveStationTime3(curTime);
							odTempData.setSequenceNO("10");
						} else if (odTodoNextTV.getText().equals("列车开行时刻")) {
							ODSurveyTimeRecordedNewActivity.odSurveyEntity.setTrainStartingTime3(curTime);
							odTempData.setTrainStartingTime3(curTime);
							odTempData.setSequenceNO("11");
						} else if (odTodoNextTV.getText().equals("下车时刻")) {
							ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetoffStationTime3(curTime);
							odTempData.setGetoffStationTime3(curTime);
							odTempData.setSequenceNO("12");
						}
						break;
				}
				if (odTodoNextTV.getText().equals("出站刷卡时刻")) {
					ODSurveyTimeRecordedNewActivity.odSurveyEntity.setGetoutStationTime(curTime);
					odTempData.setGetoutStationTime(curTime);
				}

				//调出numberPicker
				if (noFlag == 1) {
					odFlightRL.setVisibility(View.VISIBLE);
				} else
					odFlightRL.setVisibility(View.GONE);
				//录完一大组数据,存库
				if (noFlag == 2) {
					switch (odLineNo) {
						case 0:
							ODSurveyTimeRecordedNewActivity.odSurveyEntity.setShift1(ODFlightNumberPicker.getValue() + "");
							odTempData.setShift1(ODFlightNumberPicker.getValue() + "");
							break;
						case 1:
							ODSurveyTimeRecordedNewActivity.odSurveyEntity.setShift2(ODFlightNumberPicker.getValue() + "");
							odTempData.setShift2(ODFlightNumberPicker.getValue() + "");
							break;
						case 2:
							ODSurveyTimeRecordedNewActivity.odSurveyEntity.setShift3(ODFlightNumberPicker.getValue() + "");
							odTempData.setShift3(ODFlightNumberPicker.getValue() + "");
							break;
					}
				} else if (noFlag == -1) {
					UIHelper.ToastMessage(context, "本组数据录入完成");
					odTodoNextTV.setText("");

					preferences = getSharedPreferences("odDatabaseID", MODE_WORLD_WRITEABLE);
					odDatabaseID = preferences.getInt("odDatabaseID", 1);//默认为第二个参数1
					ODSurveyTimeRecordedNewActivity.odSurveyEntity.setID(odDatabaseID);
					ODSurveyDataHelper.insertIntoODSurveyData(context, ODSurveyTimeRecordedNewActivity.odSurveyEntity);
					odDatabaseID++;
					//通过sharedPreferences将主键保存起来
					Editor editor = preferences.edit();
					editor.putInt("odDatabaseID", odDatabaseID);
					editor.commit();

					//跳转到详情看刚才输入的数据
					Intent it = new Intent();
					it.setClass(context, ODSurveyDataPerNewActivity.class);
					it.putExtra("surveyTypeNo", surveyTypeNo);
					it.putExtra("odDatabaseID", odDatabaseID);
					startActivity(it);
					finish();

					//TODO 将ODTempData的isFinished置为true，并将数据置空
					odTempData = new ODSurveyEntity();
					odTempData.setFinished(true);
					appContext.setODTempData(odTempData);
					appContext.saveODTempData();
					return;
				} else if (noFlag == 3) {
					switch (surveyTypeNo) {
						case AppConfig.OD_TRANSFER_NONE:
							odTodoNextTV.setText("出站刷卡时刻");
							noFlag = -1;
							odLineNo = 0;
							appContext.setODTempData(odTempData);
							appContext.saveODTempData();
							return;
						case AppConfig.OD_TRANSFER_ONCE:
//							//TODO　这里应该更新surveyTypeNo
//							odTodoNextTV.setText(AppConfig.odToHints[0]);
//							noFlag = 0;
//							surveyTypeNo--;
//							odLineNo++;
//							break;
						case AppConfig.OD_TRANSFER_TWICE:
							odTodoNextTV.setText(AppConfig.odToHints[0]);
							noFlag = 0;
							surveyTypeNo--;
							//将surveyType暂存在transferCount中
							odTempData.setTransferCount(surveyTypeNo+"");
							odLineNo++;
							break;
					}
				}
				SurveyUtils.getODLine(odLineShowTV, user, odLineNo);
				odTodoNextTV.setText(AppConfig.odToHints[noFlag]);
				noFlag++;
				break;
			case R.id.ODSearchSurveyImageView:
				Intent it = new Intent();
				it.setClass(context, ODSurveyDataPerNewActivity.class);
				startActivity(it);
				break;
		}
		appContext.setODTempData(odTempData);
		appContext.saveODTempData();
	}

	public void fillDataIntoODEntityByUser(ODSurveyEntity odSurveyEntity, UserEntity user) {

		odSurveyEntity.setCardNum(user.getOdCardNum());
		odSurveyEntity.setIDNo(user.getOdIDNum());
		odSurveyEntity.setDistanceTotal(user.getOdDistance());
		odSurveyEntity.setName(user.getName());
		odSurveyEntity.setDate(user.getDate());
		odSurveyEntity.setStation(user.getStation() + "");

		odSurveyEntity.setTransferCount(SurveyUtils.getSurveyTypeNo(user.getOdType(), AppConfig.odTypeInfo) + "");

		odSurveyEntity.setGetinStationLine(user.getLine());
		odSurveyEntity.setWeekdayIf(user.getWeekdayIf());
		odSurveyEntity.setStationCount(user.getOdStationCount());
		odSurveyEntity.setPeakIf(user.getOdTimePeriod());

		String type = user.getOdType();

		//无换乘
		odSurveyEntity.setTrainDire1(user.getOdlDire1());
		odSurveyEntity.setGetoffStation1(user.getOdlOffStation1());
		if(type.equals("换乘一次")){
			odSurveyEntity.setTransferLine1(user.getOdlTransLine2());
			odSurveyEntity.setTrainDire2(user.getOdlDire2());
			odSurveyEntity.setGetoffStation2(user.getOdlOffStation2());
		}else if(type.equals("换乘两次")){
			odSurveyEntity.setTransferLine1(user.getOdlTransLine2());
			odSurveyEntity.setTrainDire2(user.getOdlDire2());
			odSurveyEntity.setGetoffStation2(user.getOdlOffStation2());

			odSurveyEntity.setTransferLine2(user.getOdlTransLine3());
			odSurveyEntity.setTrainDire3(user.getOdlDire3());
			odSurveyEntity.setGetoffStation3(user.getOdlOffStation3());
		}
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		System.out.println();
		switch (item.getItemId()) {
			case android.R.id.home:
				Log.i("TAG", "=========选中返回键");
				this.finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
