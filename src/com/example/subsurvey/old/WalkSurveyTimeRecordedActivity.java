package com.example.subsurvey.old;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.SubSurveyDBHelper;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.old.WalkSurveySettingActivity.textChangedListener;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;
import com.example.subsurvey.walkSurvey.helper.WalkSurveyDataHelper;

import android.R.bool;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.test.UiThreadTest;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewDebug.FlagToString;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class WalkSurveyTimeRecordedActivity extends ActionBarActivity {
	//
//	public static int rowID = 1;
	//
	private Spinner ageSpinner,bagageSpinner,liftSpinner;
	private ImageView startSurveyImageView,searchSurveyImageView;
	private TextView timePerSurveyTextView,todoNextTV;
	private RelativeLayout wsGetOffLineRL,wsGetOnLineRL,wsMachineLocRL;
	private EditText wsGetOffLineET,wsGetOnLineET,wsMachineLocET,wsGetOffDireET,wsGetOnDireET,wsMachineLineET;
	private Context context = null;
	private int countFlag=0,surveyTypeNoTemp;//对要测量的数据计数,fullFlag标志是否正好满满一组
	public static int fullFlag=0,dataSum=1;
	public int rowID;
	private boolean queryFlag = false;//标识一组数据完成情况 queryFlag标示，是否可以查询当前组数据
	private String surveyType;//走行类型
	public static String[] typeInfo 	= {"进站","出站","换乘"};
	public static String[][] todoHints={{"进站刷卡时刻","到达站台时刻","列车开门时刻"},
		{"列车开门时刻","出站刷卡时刻"},{"列车开门时刻","到达站台时刻","出站刷卡时刻"}};
	ArrayList<ArrayList<String>>timeDataArrTotal = new ArrayList<ArrayList<String>>();//时间数据
	ArrayList<String> tempArr = new ArrayList<String>();
	ArrayList<String> timeDataArrPer = new ArrayList<String>();
	SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context=getApplicationContext();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walk_survey_time);

		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		//控件
		todoNextTV = (TextView)findViewById(R.id.todoNextTV);
		
		ArrayAdapter<CharSequence> adapterType = null; 
		//配置年龄段下拉框
		ageSpinner = (Spinner)findViewById(R.id.ageSpinner);
		String[] ageInfo = {"青年","中年","老年"};
		adapterType = new ArrayAdapter<CharSequence>(this,  
					  android.R.layout.simple_spinner_dropdown_item, ageInfo);  
		//设置下拉框的数据适配器adapterType
		ageSpinner.setAdapter(adapterType);
		ageSpinner.setOnItemSelectedListener(new OnItemSelectedSpinnerImpl());
		ageSpinner.setOnTouchListener(new onTouchSpinnerImpl());
		
		//配置行李下拉框
		bagageSpinner = (Spinner)findViewById(R.id.bagageSpinner);
		String[] bagageInfo = {"无行李","一般行李","较重行李"};
		adapterType = new ArrayAdapter<CharSequence>(this,  
					  android.R.layout.simple_spinner_dropdown_item, bagageInfo);  
		//设置下拉框的数据适配器adapterType
		bagageSpinner.setAdapter(adapterType); 
		bagageSpinner.setOnItemSelectedListener(new OnItemSelectedSpinnerImpl());
		bagageSpinner.setOnTouchListener(new onTouchSpinnerImpl());
		
		//配置电梯类型下拉框
		liftSpinner = (Spinner)findViewById(R.id.liftSpinner);
		String[] liftInfo = {"电梯","步梯"};
		adapterType = new ArrayAdapter<CharSequence>(this,  
					  android.R.layout.simple_spinner_dropdown_item, liftInfo);  
		//设置下拉框的数据适配器adapterType
		liftSpinner.setAdapter(adapterType); 
		liftSpinner.setOnItemSelectedListener(new OnItemSelectedSpinnerImpl());
		liftSpinner.setOnTouchListener(new onTouchSpinnerImpl());
		
		startSurveyImageView = (ImageView)findViewById(R.id.startSurveyImageView);
		startSurveyImageView.setOnClickListener(new OnClickTimePerSurveyImpl());
		timePerSurveyTextView = (TextView)findViewById(R.id.timePerSurveyTextView);
		searchSurveyImageView = (ImageView)findViewById(R.id.searchSurveyImageView);
		searchSurveyImageView.setOnClickListener(new OnClickToTimePerSurveyImpl());
		wsGetOffLineRL=(RelativeLayout)findViewById(R.id.wsGetOffLineRL);
		wsGetOnLineRL=(RelativeLayout)findViewById(R.id.wsGetOnLineRL);
		wsMachineLocRL=(RelativeLayout)findViewById(R.id.wsMachineLocRL);
		wsGetOffLineET = (EditText)findViewById(R.id.wsGetOffLineET);
		wsGetOffLineET.addTextChangedListener(new textChangedListener(wsGetOffLineET));
		wsGetOnLineET = (EditText)findViewById(R.id.wsGetOnLineET);
		wsGetOnLineET.addTextChangedListener(new textChangedListener(wsGetOnLineET));
		wsMachineLocET = (EditText)findViewById(R.id.wsMachineLocET);
		wsMachineLocET.addTextChangedListener(new textChangedListener(wsMachineLocET));
		wsGetOffDireET = (EditText)findViewById(R.id.wsGetOffDireET);
		wsGetOffDireET.addTextChangedListener(new textChangedListener(wsGetOffDireET));
		wsGetOnDireET = (EditText)findViewById(R.id.wsGetOnDireET);
		wsGetOnDireET.addTextChangedListener(new textChangedListener(wsGetOnDireET));
		wsMachineLineET = (EditText)findViewById(R.id.wsMachineLineET);
		wsMachineLineET.addTextChangedListener(new textChangedListener(wsMachineLineET));
		
		//接收走行类型
		Intent it = getIntent();
		surveyType =it.getStringExtra("surveyType");
		timeDataArrTotal.add(tempArr);
		int typeNo = getSurveyTypeNo(surveyType);
		todoNextTV.setText(todoHints[typeNo][0]);
		if(typeNo==0)
			wsGetOffLineRL.setVisibility(View.GONE);
		else if(typeNo==1)
			wsGetOnLineRL.setVisibility(View.GONE);
		else if(typeNo==2)
			wsMachineLocRL.setVisibility(View.GONE);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.walk_survey_time, menu);
		return true;
	}
	
	public int getSurveyTypeNo(String surveyType){
		//进站、出站、换乘分别对应0、1、2
		int surveyTypeNo;
		for(surveyTypeNo=0;surveyTypeNo<3;surveyTypeNo++){
			if(WalkSurveyTimeRecordedActivity.typeInfo[surveyTypeNo].equals(surveyType)){
						break;
					} 
				}
		return surveyTypeNo;
	}
	
	//单击获取当前一次记录时间
	public class OnClickTimePerSurveyImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			//获取count即主键
			preferences = getSharedPreferences("wsRowID", MODE_WORLD_WRITEABLE);
			rowID = preferences.getInt("wsRowID", 1);//默认为第二个参数1
			//录入每组数据之间，spinner不可更改
			ageSpinner.setClickable(false);
			bagageSpinner.setClickable(false);
			liftSpinner.setClickable(false);
			//录入每组数据之间，editText不可空且输入完毕后不可更改
			//surveyType
			int typeNo = getSurveyTypeNo(surveyType);
			boolean isEmptyGotoStation = StringUtils.isEmpty(wsGetOnDireET.getText().toString())||StringUtils.isEmpty(wsGetOnLineET.getText().toString())
									||StringUtils.isEmpty(wsMachineLineET.getText().toString())||StringUtils.isEmpty(wsMachineLocET.getText().toString());
			boolean isEmptyGoOutStation = StringUtils.isEmpty(wsGetOffDireET.getText().toString())||StringUtils.isEmpty(wsGetOffLineET.getText().toString())
									||StringUtils.isEmpty(wsMachineLineET.getText().toString())||StringUtils.isEmpty(wsMachineLocET.getText().toString());
			boolean isEmptyTransfer = StringUtils.isEmpty(wsGetOffDireET.getText().toString())||StringUtils.isEmpty(wsGetOffLineET.getText().toString())
									||StringUtils.isEmpty(wsGetOnDireET.getText().toString())||StringUtils.isEmpty(wsGetOnLineET.getText().toString());
			if((typeNo==0&&isEmptyGotoStation)||(typeNo==1&&isEmptyGoOutStation)||(typeNo==2&&isEmptyTransfer)){
				UIHelper.ToastMessage(context, "请将所有数据补充完整");
				return;
			}
			wsGetOffDireET.setEnabled(false);
			wsGetOffLineET.setEnabled(false);
			wsGetOnDireET.setEnabled(false);
			wsGetOnLineET.setEnabled(false);
			wsMachineLineET.setEnabled(false);
			wsMachineLocET.setEnabled(false);
			
			//获取系统时间
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");       
			Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
			String str = formatter.format(curDate);
			timePerSurveyTextView.setText(str.toString());
			
			//进站、出站、换乘分别对应0、1、2
			int surveyTypeNo,sum[]={3,2,3};
			surveyTypeNo=getSurveyTypeNo(surveyType);
			//temp作用是显示下一个提示，if语句为了循环到数组开头
			int temp=countFlag+1;
			if(temp==sum[surveyTypeNo])
				temp=0;
			todoNextTV.setText(todoHints[surveyTypeNo][temp]);
			//8.3 add 根据不同的todoHints执行不同的set方法
			/*
			 * if(todoHints[surveyTypeNo][temp].equals("$%^&*("))
			 * 		WalkSurveySettingActivity.walkSurveyEntity.set^&*();
			 * todoHints={{"进站刷卡时刻","到达站台时刻","列车开门时刻"},
				{"列车开门时刻","出站刷卡时刻"},{"列车开门时刻","到达站台时刻","出站刷卡时刻"}};
			 * 
			 */
			//将记录的时刻赋值到类中
			if(todoHints[surveyTypeNo][countFlag].equals("列车开门时刻")&&surveyTypeNo==0) //当类型为进站时，列车开门时刻在进站和到达站台两个数据的后面，因此用time2
				WalkSurveySettingActivity.walkSurveyEntity.setOpenDoorTime2(str);
			else if(todoHints[surveyTypeNo][countFlag].equals("列车开门时刻")&&surveyTypeNo!=0)
				WalkSurveySettingActivity.walkSurveyEntity.setOpenDoorTime1(str);
			else if(todoHints[surveyTypeNo][countFlag].equals("进站刷卡时刻"))
				WalkSurveySettingActivity.walkSurveyEntity.setGotoPlatformTime(str);
			else if(todoHints[surveyTypeNo][countFlag].equals("到达站台时刻"))
				WalkSurveySettingActivity.walkSurveyEntity.setArrivePlatformTime(str);
			else if(todoHints[surveyTypeNo][countFlag].equals("出站刷卡时刻"))
				WalkSurveySettingActivity.walkSurveyEntity.setGooutPlatformTime(str);
			System.out.println("----------------------开门时刻："+WalkSurveySettingActivity.walkSurveyEntity.openDoorTime1
					+"进站刷卡："+WalkSurveySettingActivity.walkSurveyEntity.gotoPlatformTime+"到达站台："+WalkSurveySettingActivity.walkSurveyEntity.arrivePlatformTime
					+"出站刷卡:"+WalkSurveySettingActivity.walkSurveyEntity.gooutPlatformTime);
			//录完n组数据后 再返回重新录入出现bug的临时解决办法 判断timeDataArrTotal 是否为[[]]这样的
			if(timeDataArrTotal.get(0).size()==0)
				dataSum = 1;
			timeDataArrTotal.get(WalkSurveyTimeRecordedActivity.dataSum-1).add(str);//WalkSurveyTimeRecordedActivity.dataSum->rowID
			countFlag++;
			WalkSurveyTimeRecordedActivity.fullFlag=0;
			
			//本来是在listener里面的
			timeDataArrPer = new ArrayList<String>();
			for(int i=0;i<countFlag;i++){
				String tempData =timeDataArrTotal.get(WalkSurveyTimeRecordedActivity.dataSum-1).get(i).toString();//WalkSurveyTimeRecordedActivity.dataSum->rowID
				timeDataArrPer.add(tempData);
			}
			
			//录完一组数据
			if(countFlag==sum[surveyTypeNo]){
				//录完一组数据后可以更改spinner数据
				ageSpinner.setClickable(true);
				bagageSpinner.setClickable(true);
				liftSpinner.setClickable(true);
				wsGetOffDireET.setEnabled(true);
				wsGetOffLineET.setEnabled(true);
				wsGetOnDireET.setEnabled(true);
				wsGetOnLineET.setEnabled(true);
				wsMachineLineET.setEnabled(true);
				wsMachineLocET.setEnabled(true);
				
//				//获取count即主键
//				preferences = getSharedPreferences("wsRowID", MODE_WORLD_WRITEABLE);
//				rowID = preferences.getInt("wsRowID", 1);//默认为第二个参数1
				
				//每录完一组数据就将数据保存进数据库
				WalkSurveySettingActivity.walkSurveyEntity.setID(rowID);
				WalkSurveyDataHelper.insertIntoWalkSurveyData(context, WalkSurveySettingActivity.walkSurveyEntity);
				
				countFlag=0;
				UIHelper.ToastMessage(WalkSurveyTimeRecordedActivity.this,
						"你已经录入完成"+typeInfo[surveyTypeNo].toString()+"的第"+rowID+"条数据");
				queryFlag = true;
				
				WalkSurveyTimeRecordedActivity.dataSum++;
				tempArr = new ArrayList<String>();
				timeDataArrTotal.add(tempArr);
				System.out.println("----");
				WalkSurveyTimeRecordedActivity.fullFlag=1;
				rowID++;
				
				//通过sharedPreferences将主键保存起来
				Editor editor = preferences.edit();
				editor.putInt("wsRowID",rowID);
				editor.commit();
			}
				
		}
		
	}
	
	//跳转到每次记录信息详情界面
	public class OnClickToTimePerSurveyImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(queryFlag==false){
				UIHelper.ToastMessage(context, "请在录完一组数据后，进行数据修改");
				return;
			}
			queryFlag = false;
			//利用bundle传递数组
			Intent it = new Intent();
			Bundle bundle = new Bundle();
			bundle.putStringArrayList("timeDataArrPer", timeDataArrPer);
			it.putExtras(bundle);
 			it.putExtra("surveyType", surveyType);
 			it.putExtra("rowID", rowID);
			it.setClass(WalkSurveyTimeRecordedActivity.this, WalkSurveyDataPerActivity.class);
			startActivity(it);
			timeDataArrPer=new ArrayList<String>();
		}
		
	}
	
	//监听spinner
	public class OnItemSelectedSpinnerImpl implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> adapter, View view, int position,
				long id) {
			WalkSurveySettingActivity.walkSurveyEntity.setAge(ageSpinner.getSelectedItem().toString());
			WalkSurveySettingActivity.walkSurveyEntity.setBagageType(bagageSpinner.getSelectedItem().toString());
			WalkSurveySettingActivity.walkSurveyEntity.setWalkTool(liftSpinner.getSelectedItem().toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> adapter) {
			System.out.println();
			if(!ageSpinner.isClickable())
				UIHelper.ToastMessage(context, "调查本组数据过程中，现不可编辑此数据");
			else if(!bagageSpinner.isClickable())
				UIHelper.ToastMessage(context, "调查本组数据过程中，现不可编辑此数据");
			else if(!liftSpinner.isClickable())
				UIHelper.ToastMessage(context, "调查本组数据过程中，现不可编辑此数据");
		}
		
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
	
	//spinner设置在记录一组数据之间不可编辑
	public class onTouchSpinnerImpl implements OnTouchListener{

		@Override
		public boolean onTouch(View view, MotionEvent event) {
			switch(view.getId()){
			case R.id.ageSpinner:
				if(!ageSpinner.isClickable())
					UIHelper.ToastMessage(context, "调查本组数据过程中，现不可编辑此数据");
				break;
			case R.id.bagageSpinner:
				if(!bagageSpinner.isClickable())
					UIHelper.ToastMessage(context, "调查本组数据过程中，现不可编辑此数据");
				break;
			case R.id.liftSpinner:
				if(!liftSpinner.isClickable())
					UIHelper.ToastMessage(context, "调查本组数据过程中，现不可编辑此数据");
				break;
			default:
				break;			
			}			
			return false;
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
					if(EditId==wsGetOffDireET){
						WalkSurveySettingActivity.walkSurveyEntity.setGetOffDire(s.toString());
					}else if(EditId==wsGetOffLineET){
						WalkSurveySettingActivity.walkSurveyEntity.setGetOffLine(s.toString());
					}else if(EditId==wsGetOnDireET){
						WalkSurveySettingActivity.walkSurveyEntity.setGetOnDire(s.toString());
					}else if(EditId==wsGetOnLineET){
						WalkSurveySettingActivity.walkSurveyEntity.setGetOnLine(s.toString());
					}else if(EditId==wsMachineLineET){
						WalkSurveySettingActivity.walkSurveyEntity.setMachineLine(s.toString());
					}else if(EditId==wsMachineLocET){
						WalkSurveySettingActivity.walkSurveyEntity.setMachineLoc(s.toString());
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
}
