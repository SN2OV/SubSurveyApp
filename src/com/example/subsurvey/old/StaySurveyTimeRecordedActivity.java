package com.example.subsurvey.old;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.subsurvey.R;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.staySurvey.helper.StaySurveyDataHelper;

public class StaySurveyTimeRecordedActivity extends Activity {
	
	private Spinner ssAgeSpinner,ssBagageSpinner,ssSexSpinner;
	private ImageView ssStartSurveyImageView,ssSearchSurveyImageView;
	private EditText ssGoinLineNoET,ssGetoffLineNoET,ssGoinLineDireET,ssGetoffLineDireET,ssGetOffNumET,ssGetOnNumET,ssLinePeopleNumET;
	private RelativeLayout ssGetOffLineRL,ssGetOnLineRL,ssLinePeopleNumRL,ssGetPeopleNumRL;
	private TextView ssTodoNextTV,ssLastSurveyTextView,ssLinePeopleNumTV;
	private Context context = null;
	private String surveyType,lastInfo,tempInfo;
	private boolean fullFlag = false,countFlagBool = false,queryFlag = false;//标识一组数据完成情况 queryFlag标示，是否可以查询当前组数据
	private int countFlag = 0,countStop=1,dataSum=0,countStopTemp;//countStop标识等的第n辆车,countStopTemp用来临时保存countTemp，从而填充人数数据
	ArrayList<ArrayList<String>>timeDataArrTotal = new ArrayList<ArrayList<String>>();//时间数据
	ArrayList<String> tempArr = new ArrayList<String>();
	ArrayList<String> timeDataArrPer = new ArrayList<String>();
	
	public static String[][] todoHints={{"进站刷卡时刻","到达站台时刻","列车启动时刻和相关人数"},
		{"换乘下车时刻","到达站台时刻","列车启动时刻和相关人数"}};
	public static String[] typeInfo = {"进站","换乘"};
	SharedPreferences preferences;
	public int ssRowID;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stay_survey_time_recorded);
		context = getApplicationContext();
		
		ssAgeSpinner = (Spinner)findViewById(R.id.ssAgeSpinner);
		ssBagageSpinner = (Spinner)findViewById(R.id.ssBagageSpinner);
		ssSexSpinner = (Spinner)findViewById(R.id.ssSexSpinner);
		ssGoinLineNoET = (EditText)findViewById(R.id.ssGoinLineNoET);
		ssGoinLineNoET.addTextChangedListener(new textChangedListener(ssGoinLineNoET));
		ssGoinLineDireET = (EditText)findViewById(R.id.ssGoinLineDireET);
		ssGoinLineDireET.addTextChangedListener(new textChangedListener(ssGoinLineDireET));
		ssGetoffLineNoET = (EditText)findViewById(R.id.ssGetoffLineNoET);
		ssGetoffLineNoET.addTextChangedListener(new textChangedListener(ssGetoffLineNoET));
		ssGetoffLineDireET = (EditText)findViewById(R.id.ssGetoffDireET);
		ssGetoffLineDireET.addTextChangedListener(new textChangedListener(ssGetoffLineDireET));
		ssStartSurveyImageView = (ImageView)findViewById(R.id.ssStartSurveyImageView);
		ssSearchSurveyImageView = (ImageView)findViewById(R.id.ssSearchSurveyImageView);
		ssSearchSurveyImageView.setClickable(false);
		ssSearchSurveyImageView.setOnClickListener(new onClickTimeInfoPerImpl());
		ssGetOffLineRL = (RelativeLayout)findViewById(R.id.ssGetOffLineRL);
		ssGetOnLineRL = (RelativeLayout)findViewById(R.id.ssGetOnLineRL);
		ssStartSurveyImageView.setOnClickListener(new onClickListenerStartImpl());
		ssTodoNextTV = (TextView)findViewById(R.id.ssTodoNextTV);
		ssLastSurveyTextView = (TextView)findViewById(R.id.ssLastSurveyTextView);
		ssLinePeopleNumRL = (RelativeLayout)findViewById(R.id.ssLinePeopleNumRL);
		ssGetPeopleNumRL = (RelativeLayout)findViewById(R.id.ssGetPeopleNumRL);
		ssGetOffNumET = (EditText)findViewById(R.id.ssGetOffNumET);
		ssGetOnNumET = (EditText)findViewById(R.id.ssGetOnNumET);
		ssLinePeopleNumTV = (TextView)findViewById(R.id.ssLinePeopleNumTV);
		ssLinePeopleNumET = (EditText)findViewById(R.id.ssLinePeopleNumET);
		
		//配置年龄段下拉框
		ArrayAdapter<CharSequence> adapterType = null; 
		String[] ageInfo = {"青年","中年","老年"};
		adapterType = new ArrayAdapter<CharSequence>(this,  
					android.R.layout.simple_spinner_dropdown_item, ageInfo);  
		//设置下拉框的数据适配器adapterType
		ssAgeSpinner.setAdapter(adapterType);
		ssAgeSpinner.setOnItemSelectedListener(new OnItemSelectedSpinnerImpl());
		ssAgeSpinner.setOnTouchListener(new onTouchSpinnerImpl());
				
		//配置行李下拉框
		String[] bagageInfo = {"无行李","一般行李","较重行李"};
		adapterType = new ArrayAdapter<CharSequence>(this,  
					  android.R.layout.simple_spinner_dropdown_item, bagageInfo);  
		//设置下拉框的数据适配器adapterType
		ssBagageSpinner.setAdapter(adapterType); 
		ssBagageSpinner.setOnItemSelectedListener(new OnItemSelectedSpinnerImpl());
		ssBagageSpinner.setOnTouchListener(new onTouchSpinnerImpl());
				
		//配置性别类型下拉框
		String[] sexInfo = {"男","女"};
		adapterType = new ArrayAdapter<CharSequence>(this,  
					  android.R.layout.simple_spinner_dropdown_item, sexInfo);  
		//设置下拉框的数据适配器adapterType
		ssSexSpinner.setAdapter(adapterType); 
		ssSexSpinner.setOnItemSelectedListener(new OnItemSelectedSpinnerImpl());
		ssSexSpinner.setOnTouchListener(new onTouchSpinnerImpl());	
		
		//接收走行类型
		Intent it = getIntent();
		surveyType =it.getStringExtra("surveyType");
		timeDataArrTotal.add(tempArr);
		int typeNo = getSurveyTypeNo(surveyType);
		lastInfo = todoHints[typeNo][0];
		ssTodoNextTV.setText(lastInfo);
		if(typeNo==0)
			ssGetOffLineRL.setVisibility(View.GONE);
		else if(typeNo==1)
			ssGetOnLineRL.setVisibility(View.GONE);		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stay_survey_time_recorded, menu);
		return true;
	}
	
	//监听spinner
		public class OnItemSelectedSpinnerImpl implements OnItemSelectedListener{

			@Override
			public void onItemSelected(AdapterView<?> adapter, View view, int position,
					long id) {
				StaySurveySettingActivity.staySurveyEntity.setAge(ssAgeSpinner.getSelectedItem().toString());
				StaySurveySettingActivity.staySurveyEntity.setBagageType(ssBagageSpinner.getSelectedItem().toString());
				StaySurveySettingActivity.staySurveyEntity.setSex(ssSexSpinner.getSelectedItem().toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapter) {
				System.out.println();
				if(!ssAgeSpinner.isClickable())
					UIHelper.ToastMessage(context, "调查本组数据过程中，现不可编辑此数据");
				else if(!ssBagageSpinner.isClickable())
					UIHelper.ToastMessage(context, "调查本组数据过程中，现不可编辑此数据");
				else if(!ssSexSpinner.isClickable())
					UIHelper.ToastMessage(context, "调查本组数据过程中，现不可编辑此数据");
			}
			
		}
		
		//spinner设置在记录一组数据之间不可编辑
		public class onTouchSpinnerImpl implements OnTouchListener{

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				switch(view.getId()){
				case R.id.ssAgeSpinner:
					if(!ssAgeSpinner.isClickable())
						UIHelper.ToastMessage(context, "调查本组数据过程中，现不可编辑此数据");
					break;
				case R.id.ssBagageSpinner:
					if(!ssBagageSpinner.isClickable())
						UIHelper.ToastMessage(context, "调查本组数据过程中，现不可编辑此数据");
					break;
				case R.id.ssSexSpinner:
					if(!ssSexSpinner.isClickable())
						UIHelper.ToastMessage(context, "调查本组数据过程中，现不可编辑此数据");
					break;
				default:
					break;			
				}			
				return false;
			}
			
		}
		
		public int getSurveyTypeNo(String surveyType){
			//进站、出站、换乘分别对应0、1、2
			int surveyTypeNo;
			for(surveyTypeNo=0;surveyTypeNo<2;surveyTypeNo++){
				if(StaySurveyTimeRecordedActivity.typeInfo[surveyTypeNo].equals(surveyType)){
							break;
						} 
					}
			return surveyTypeNo;
		}
		
		public class onClickListenerStartImpl implements OnClickListener{

			@SuppressLint("SimpleDateFormat")
			@Override
			public void onClick(View v) {
				//进站、换乘分别对应0、1
				final int surveyTypeNo;
				surveyTypeNo=getSurveyTypeNo(surveyType);
				//调查前数据不能为空
				boolean isEmptyGotoStation = StringUtils.isEmpty(ssGoinLineNoET.getText().toString())||StringUtils.isEmpty(ssGoinLineDireET.getText().toString());
				boolean isEmptyTransferStation = StringUtils.isEmpty(ssGetoffLineNoET.getText().toString())||StringUtils.isEmpty(ssGetoffLineDireET.getText().toString());
				boolean isEmptyNumNo = StringUtils.isEmpty(ssLinePeopleNumET.getText().toString())||StringUtils.isEmpty(ssGetOffNumET.getText().toString())||StringUtils.isEmpty(ssGetOnNumET.getText().toString()); 
//				boolean isVisibleNum = ssLinePeopleNumET.getVisibility()==View.VISIBLE&&ssGetOffNumET.getVisibility()==View.VISIBLE&&ssGetOnNumET.getVisibility()==View.VISIBLE;//View.visible=0,gone=8;
				boolean isRLVisible = ssLinePeopleNumRL.getVisibility()==View.VISIBLE&&ssLinePeopleNumRL.getVisibility()==View.VISIBLE;
				//
				if((surveyTypeNo==0&&isEmptyGotoStation)||(surveyTypeNo==1&&isEmptyTransferStation)||(isRLVisible&&isEmptyNumNo)){
					UIHelper.ToastMessage(context, "请将所有数据补充完整");
					return;
				}
				
				//录入每组数据之间，spinner不可更改
				ssAgeSpinner.setClickable(false);
				ssBagageSpinner.setClickable(false);
				ssSexSpinner.setClickable(false);
				ssGoinLineDireET.setEnabled(false);
				ssGoinLineNoET.setEnabled(false);
				ssGetoffLineNoET.setEnabled(false);
				ssGetoffLineDireET.setEnabled(false);
				
				//获取系统时间
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");       
				Date curDate = new Date(System.currentTimeMillis());//获取当前时间 
				String str;
				
				//不想把人数统计放到界面中，因此不予显示
				if(lastInfo.length()==15){
					tempInfo = lastInfo.substring(0, 9);
				 	str= tempInfo +"为："+formatter.format(curDate);
				}else if(lastInfo.length()==11){
					tempInfo = lastInfo.substring(0, 5);
				 	str= tempInfo +"为："+formatter.format(curDate);
				}else
					str = lastInfo +"为："+formatter.format(curDate);
					
				//将时间保存进类
				if(todoHints[surveyTypeNo][countFlag].equals("进站刷卡时刻")&&fullFlag==false)
					StaySurveySettingActivity.staySurveyEntity.setGointoStationTime(formatter.format(curDate));
				else if(todoHints[surveyTypeNo][countFlag].equals("换乘下车时刻"))
					StaySurveySettingActivity.staySurveyEntity.setGetoffTime(formatter.format(curDate));
				else if(todoHints[surveyTypeNo][countFlag].equals("到达站台时刻"))
					StaySurveySettingActivity.staySurveyEntity.setArriveStationTime(formatter.format(curDate));
				else if(todoHints[surveyTypeNo][countFlag].equals("列车启动时刻和相关人数"))
				{
					switch(countStop){
					case 1:
						StaySurveySettingActivity.staySurveyEntity.setTrainStartTime1(formatter.format(curDate));
						break;
					case 2:
						StaySurveySettingActivity.staySurveyEntity.setTrainStartTime2(formatter.format(curDate));
						break;
					case 3:
						StaySurveySettingActivity.staySurveyEntity.setTrainStartTime3(formatter.format(curDate));
						break;
					case 4:
						StaySurveySettingActivity.staySurveyEntity.setTrainStartTime4(formatter.format(curDate));
						break;
					case 5:
						StaySurveySettingActivity.staySurveyEntity.setTrainStartTime5(formatter.format(curDate));
						break;
					case 6:
						StaySurveySettingActivity.staySurveyEntity.setTrainStartTime6(formatter.format(curDate));
						break;
					default:
						break;
					}
				}
				//上一记录
				ssLastSurveyTextView.setText(str.toString());   
//				if(fullFlag == false){
//					ssLinePeopleNumRL.setVisibility(View.VISIBLE);
//					ssGetPeopleNumRL.setVisibility(View.GONE);
//				}

				if(countFlag==2){
					ssGetPeopleNumRL.setVisibility(View.VISIBLE);
					//对话框显示出来添加人数数据，在这里就可以添加数据，下面不行？！
					addDataOfStaySurvey(countStop);
					//对话框点选择“是否”后并不能马上将填充的数据保存，即显示和保存分开。
					Dialog dialog = new AlertDialog.Builder(StaySurveyTimeRecordedActivity.this).setTitle("留乘调查").setIcon(R.drawable.app_logo)
							.setMessage("该乘客是否成功上车？").setPositiveButton("是", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									ssLinePeopleNumRL.setVisibility(View.VISIBLE);
									fullFlag = true;
									countFlag = 0;
									//这里重复了
									if(countStop!=1){
										ssLinePeopleNumTV.setText("第"+countStop+"班车上下车人数填写：");
										ssLinePeopleNumET.setVisibility(View.GONE);
									}else{
										ssLinePeopleNumTV.setText("起始排队人数");
										ssLinePeopleNumET.setVisibility(View.VISIBLE);
									}
										
									lastInfo = todoHints[surveyTypeNo][0];
									ssTodoNextTV.setText(lastInfo);
									dataSum++;
									//countStop为留乘班次数量，一组完事后要进行初始化
									StaySurveySettingActivity.staySurveyEntity.setCountStop(countStop);
									countStopTemp = countStop+1;
									countStop = 1;
									System.out.println();
									
								}
							}).setNegativeButton("否", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									//判断是不是等的第一趟车，起始排队人数只用填一个
									if(countStop==1){
										ssLinePeopleNumRL.setVisibility(View.VISIBLE);
									}else
										ssLinePeopleNumET.setVisibility(View.GONE);
									countFlag = 2;
									String str = "第"+(countStop+1)+"班车";
									if(countStop!=1){
										ssLinePeopleNumTV.setText("第"+countStop+"班车上下车人数填写：");
									}else{
										ssLinePeopleNumTV.setText("起始排队人数");
										ssLinePeopleNumET.setVisibility(View.VISIBLE);
									}
										
									lastInfo = str+todoHints[surveyTypeNo][2];
									ssTodoNextTV.setText(lastInfo);
									countStop++;
								}
							}).create();
					//点击dialog以外地方不消失
					dialog.setCanceledOnTouchOutside(false);
					dialog.show();
				}
				countFlag++;
				countFlag=(countFlag==3)?2:countFlag;
				lastInfo = todoHints[surveyTypeNo][countFlag];
				//排队人数添加
				if(ssLinePeopleNumRL.getVisibility()==View.VISIBLE)
					StaySurveySettingActivity.staySurveyEntity.setStartLineNum(ssLinePeopleNumET.getText().toString());
				
				ssTodoNextTV.setText(lastInfo);
				
				if(fullFlag == true){
					
					addDataOfStaySurvey(countStopTemp);
					ssLinePeopleNumRL.setVisibility(View.GONE);
					ssGetPeopleNumRL.setVisibility(View.GONE);
					ssAgeSpinner.setClickable(true);
					ssBagageSpinner.setClickable(true);
					ssSexSpinner.setClickable(true);
					ssGoinLineDireET.setEnabled(true);
					ssGoinLineNoET.setEnabled(true);
					ssGetoffLineNoET.setEnabled(true);
					ssGetoffLineDireET.setEnabled(true);
					
					queryFlag = true;
					ssSearchSurveyImageView.setClickable(true);
					//获取count即主键
					preferences = getSharedPreferences("ssRowID", MODE_WORLD_WRITEABLE);
					ssRowID = preferences.getInt("ssRowID", 1);//默认为第二个参数1
					UIHelper.ToastMessage(context, "你已经录入完成第"+ssRowID+"条数据");
					//每录完一组数据就将数据保存进数据库
					StaySurveySettingActivity.staySurveyEntity.setID(ssRowID);
					StaySurveyDataHelper.insertIntoStaySurveyData(context,StaySurveySettingActivity.staySurveyEntity);
					fullFlag = false;
					ssRowID++;
					//通过sharedPreferences将主键保存起来
					Editor editor = preferences.edit();
					editor.putInt("ssRowID",ssRowID);
					editor.commit();
				}
				//一轮清零，该隐藏的隐藏
				ssGetOffNumET.setText("");
				ssGetOnNumET.setText("");
			}
			
		}
		
		//根据ET的id来监听多个editText输入数据
		public class textChangedListener implements TextWatcher{
			
			private EditText EditId = null;
			
			public textChangedListener(EditText id){
				EditId = id;
			}
			@Override
			public void afterTextChanged(Editable s) {
				if(EditId==ssGoinLineNoET){
					StaySurveySettingActivity.staySurveyEntity.setGoinLineNo(s.toString());
				}else if(EditId==ssGetoffLineNoET){
					StaySurveySettingActivity.staySurveyEntity.setGetOffLineNo(s.toString());
				}else if(EditId==ssGoinLineDireET){
					StaySurveySettingActivity.staySurveyEntity.setGoinLineDire(s.toString());
				}else if(EditId==ssGetoffLineDireET){
					StaySurveySettingActivity.staySurveyEntity.setGetoffLineDire(s.toString());
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				
			}
			
		}
		
		public class onClickTimeInfoPerImpl implements OnClickListener{

			@Override
			public void onClick(View v) {
				if(queryFlag==false){
					UIHelper.ToastMessage(context, "请在录完一组数据后，进行数据修改");
					return;
				}
				queryFlag = false;
				Intent it = new Intent();
				it.putExtra("surveyType", surveyType);
				it.putExtra("ssRowID",ssRowID);
				it.setClass(context, StaySurveyDataPerActivity.class);
				startActivity(it);
			}
			
		}
		
		//填充staySurveyEntity
		public void addDataOfStaySurvey(int countStop){
			//添加人数 用设置监听？？？？？
			switch(countStop-1){
			case 0:
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum1(ssGetOffNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum1(ssGetOnNumET.getText().toString());
				break;
			case 1:
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum1(ssGetOffNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum1(ssGetOnNumET.getText().toString());
				break;
			case 2:
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum2(ssGetOffNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum2(ssGetOnNumET.getText().toString());
				break;
			case 3:
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum3(ssGetOffNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum3(ssGetOnNumET.getText().toString());
				break;
			case 4:
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum4(ssGetOffNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum4(ssGetOnNumET.getText().toString());
				break;
			case 5:
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum5(ssGetOffNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum5(ssGetOnNumET.getText().toString());
				break;
			case 6:
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum6(ssGetOffNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum6(ssGetOnNumET.getText().toString());
				break;
			default:
				break;
			}
		}

}
