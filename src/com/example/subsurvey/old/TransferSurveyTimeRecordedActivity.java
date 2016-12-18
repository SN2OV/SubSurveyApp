package com.example.subsurvey.old;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.odSurvey.helper.ODSurveyDataHelper;
import com.example.subsurvey.odSurvey.ui.ODSurveyDataTotalActivity;
import com.example.subsurvey.transferSurvey.adapter.TransferSurveyDataPerAdapter;
import com.example.subsurvey.transferSurvey.helper.TransferSurveyDataHelper;
import com.example.subsurvey.transferSurvey.ui.TransferSurveyDataTotalActivity;
import com.example.subsurvey.transferSurvey.ui.TransferSurveySettingActivity;
import com.example.subsurvey.transferSurvey.ui.TransferSurveySettingActivity.textChangedListener;

import android.R.bool;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

public class TransferSurveyTimeRecordedActivity extends ActionBarActivity {

	@InjectView(R.id.tsStartTimeET)
	EditText tsStartTimeET;
	@InjectView(R.id.tsEndTimeET)
	EditText tsEndTimeET;
	@InjectView(R.id.tsAddOneIV)
	ImageView tsAddOneIV;
	@InjectView(R.id.tsAddTwoIV)
	ImageView tsAddTwoIV;	
	@InjectView(R.id.tsAddFiveIV)
	ImageView tsAddFiveIV;
	@InjectView(R.id.tsQueryDataIV)
	ImageView tsQueryDataIV;
	@InjectView(R.id.tsSaveDataIV)
	ImageView tsSaveDataIV;
	@InjectView(R.id.tsDataPerLV)
	ListView tsDataPerLV;
	@InjectView(R.id.tsStartTimeIV)
	ImageView tsStartTimeIV;
	@InjectView(R.id.tsEndTimeIV)
	ImageView tsEndTimeIV; 
	
	private Context context;
	//每组数据里面的id
	private int id = 0,peopleTotalNum = 0,timePickerFlag = 0;
	private HashMap<String, String> perData = new HashMap<String, String>();
	private ArrayList<HashMap<String, String>> totalData = new ArrayList<HashMap<String,String>>();
	private TransferSurveyDataPerAdapter tsDataPerAdapter;
	private TimePickerDialog.OnTimeSetListener listener = null;// 用于设置时间对话框
	private Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
	private Calendar startCalendar = null,endCalendar = null;
	private SimpleDateFormat df;
	private AlarmManager am;
	private PendingIntent pi;
	private long tempTimeMillis; //指定的调查截止时间
	private final static int START_TIME_FLAG = 1;//用于监听timePickerFlag
	private final static int END_TIME_FLAG = 2;
	private SharedPreferences preferences;
	private int tsRowID;
	private boolean timeError = true;//若开始时间在结束时间后，则timeError为false
	
	@Override
	protected void onDestroy() {
//		am.cancel(pi);
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transfer_survey_time_recorded);
		context = getApplicationContext();
		ButterKnife.inject(this);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.transfer_survey_time_recorded, menu);
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
	
	public void init(){
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		initRowID();
		//在manifest中注册
		Intent intent = new Intent("ELITOR_CLOCK");  
		intent.putExtra("msg","结束时间已到，本组调查结束，录入的数据已保存");   
		pi = PendingIntent.getBroadcast(this,0,intent,0);    
		//AlarmManager对象,注意这里并不是new一个对象，Alarmmanager为系统级服务  
		IntentFilter filter = new IntentFilter("ELITOR_CLOCK");  
		MyReceiver receiver = new MyReceiver();  
		registerReceiver(receiver, filter); 
		
		totalData.add(insertData("序号", "时间", "当前人数", "总人数"));
		tsDataPerAdapter = new TransferSurveyDataPerAdapter(totalData, context);
		tsDataPerLV.setAdapter(tsDataPerAdapter);
		tsDataPerAdapter.notifyDataSetChanged();
		tsEndTimeET.setEnabled(false);
		tsStartTimeET.setEnabled(false);
		
		listener = new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendar.set(Calendar.MINUTE, minute);
				calendar.set(Calendar.SECOND, 0);
				switch(timePickerFlag){
				case END_TIME_FLAG:
					if(startCalendar == null){
						UIHelper.ToastMessage(context, "请先设置开始时间");
						return;
					}
					if(view.isShown())
						updateTime();
					tempTimeMillis = calendar.getTimeInMillis();
					break;
				case START_TIME_FLAG:
					
					if(view.isShown())
						updateTime();
					break;
				}
			}
		};
		
		tsEndTimeET.addTextChangedListener(new textChangedListener(tsEndTimeET));
		tsStartTimeET.addTextChangedListener(new textChangedListener(tsStartTimeET));
	}
	
	public void resetAfterSave(){
		tsEndTimeET.setText("");
		tsStartTimeET.setText("");
		startCalendar = null;
		endCalendar = null;
	}
	
	//设置总人数数据的主键
	public void initRowID(){
		preferences = getSharedPreferences("tsRowID", MODE_WORLD_WRITEABLE);
		tsRowID = preferences.getInt("tsRowID", 1);//默认为第二个参数1
		TransferSurveySettingActivity.tsEntity.setID(tsRowID);
		//通过sharedPreferences将主键保存起来
		Editor editor = preferences.edit();
		editor.putInt("tsRowID",tsRowID);
		editor.commit();
	}
	//根据ET的id来监听多个editText输入数据
		public class textChangedListener implements TextWatcher{
			
			private EditText EditId = null;
			
			public textChangedListener(EditText id){
				EditId = id;
			}

			@Override
			public void afterTextChanged(Editable s) {
				//后半段的控制条件为了防止，产生点击“取消”出的bug
				if(EditId==tsEndTimeET&&!StringUtils.isEmpty(tsEndTimeET.getText().toString())&&timeError){
					Dialog dialog = new AlertDialog.Builder(TransferSurveyTimeRecordedActivity.this).setTitle("设置本组截止时间").setIcon(R.drawable.app_logo)
							.setMessage("确认"+s.toString()+"截止吗？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									tsEndTimeET.setClickable(false);
									//根据设置的时间提醒
									am = (AlarmManager)getSystemService(ALARM_SERVICE);    
									am.set(AlarmManager.RTC_WAKEUP,tempTimeMillis,pi);
								}
							}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									tsEndTimeET.setText("");//这句话会导致继续调用afterTextChanged，继续产生AlertDialog，继续无法退出
								}
							}).create();
					//点击dialog以外地方不消失
					dialog.setCanceledOnTouchOutside(false);
					dialog.show();
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
	
	@OnClick({R.id.tsAddOneIV,R.id.tsAddTwoIV,R.id.tsAddFiveIV})
	void onAddClick(View view){
		if(StringUtils.isEmpty(tsEndTimeET.getText().toString())||StringUtils.isEmpty(tsStartTimeET.getText().toString())){
			UIHelper.ToastMessage(context, "请先设置时间");
			return;
		}
		String curTime = StringUtils.getSystemTime();
		id++;
		switch(view.getId()){
		case R.id.tsAddOneIV:
			peopleTotalNum++;
			totalData.add(insertData(id+"",curTime,"1",peopleTotalNum+""));
			break;
		case R.id.tsAddTwoIV:
			peopleTotalNum+=2;
			totalData.add(insertData(id+"",curTime,"2",peopleTotalNum+""));
			break;
		case R.id.tsAddFiveIV:
			peopleTotalNum+=5;
			totalData.add(insertData(id+"",curTime,"5",peopleTotalNum+""));
			break;
		}
		tsDataPerAdapter.notifyDataSetChanged();
		tsDataPerLV.setAdapter(tsDataPerAdapter);
	}
	
	//保存和查询数据
	@OnClick({R.id.tsSaveDataIV,R.id.tsQueryDataIV})
	void onButtonClick(View view){
		switch(view.getId()){
		case R.id.tsSaveDataIV:
			//手动保存
			Dialog dialog = new AlertDialog.Builder(TransferSurveyTimeRecordedActivity.this).setTitle("设置本组截止时间").setIcon(R.drawable.app_logo)
					.setMessage("确认终止并保存本组数据？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if(peopleTotalNum == 0){
								UIHelper.ToastMessage(context, "保存失败");
								return;
							}
//							TransferSurveySettingActivity.tsEntity.setCountTotal(peopleTotalNum+"");
							//设置主键加1并保存
							TransferSurveySettingActivity.tsEntity.setID(tsRowID);
							tsRowID++;
							Editor editor = preferences.edit();
							editor.putInt("tsRowID",tsRowID);
							editor.commit();
							
							TransferSurveyDataHelper.insertIntoTSSurveyData(context, TransferSurveySettingActivity.tsEntity);
							int tempDataSize = totalData.size();
							for(int i=tempDataSize-1;i>=1;i--)
								totalData.remove(i);
							//序号重置
							id = 0;
							peopleTotalNum = 0;
							tsDataPerLV.setAdapter(tsDataPerAdapter);
							tsDataPerAdapter.notifyDataSetChanged();
							//手动保存则取消自动提醒
							am.cancel(pi);
							UIHelper.ToastMessage(context, "数据已保存");
							resetAfterSave();
						}
					}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					}).create();
			//点击dialog以外地方不消失
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			break;
		case R.id.tsQueryDataIV:
			Intent it = new Intent();
			it.setClass(getApplicationContext(), TransferSurveyDataTotalActivity.class);
			startActivity(it);
			break;
		}
	}
	
	HashMap<String, String> insertData(String id,String curTime,String perCount,String totalCount){
		HashMap<String, String> tempData = new HashMap<String, String>();
		tempData.put("ID",id);
		tempData.put("Time", curTime);
		tempData.put("perCount", perCount);
		tempData.put("totalCount", totalCount);
		return tempData;
		
	}
	
	//监听日历
		@OnClick({R.id.tsEndTimeIV,R.id.tsStartTimeIV})
		void onCalendarClick(View view){
			switch(view.getId()){
			case R.id.tsEndTimeIV:
				timePickerFlag = END_TIME_FLAG;
				break;
			case R.id.tsStartTimeIV:
				timePickerFlag = START_TIME_FLAG;
				break;
			}
			boolean is24HourView = true;
			calendar.setTimeInMillis(System.currentTimeMillis()); 
			int hour = calendar.get(Calendar.HOUR);
			if(calendar.get(Calendar.AM_PM) == 1)
				hour +=12;
			TimePickerDialog dialog = new TimePickerDialog(TransferSurveyTimeRecordedActivity.this, 
					 listener, hour, calendar.get(Calendar.MINUTE), is24HourView);
	        dialog.show();
		}
		
		// 用于将calendar的日期转成“yyyy-MM--dd”的格式显示到界面上
		@SuppressLint("SimpleDateFormat")
		private void updateTime(){
			df = new SimpleDateFormat("HH:mm");
			context = TransferSurveyTimeRecordedActivity.this.getApplicationContext();
			switch(timePickerFlag){
			case END_TIME_FLAG:
				tsEndTimeET.setText(df.format(calendar.getTime()));
				String currTime = df.format(StringUtils.getSystemCalendar().getTime());
				endCalendar = calendar;
				boolean endStartEqual = (tsEndTimeET.getText().toString().equals(tsStartTimeET.getText().toString()));
				boolean endCurrEqual =  (tsEndTimeET.getText().toString().equals(currTime));
				if((StringUtils.StringToCalendar(tsEndTimeET.getText().toString())).before(StringUtils.StringToCalendar(currTime))||endCurrEqual){
					UIHelper.ToastMessage(context, "结束时间应在当前时间之后，请重新设置");
					timeError = false;
					tsEndTimeET.setText("");
					return;
				}
				if((StringUtils.StringToCalendar(tsEndTimeET.getText().toString())).before(StringUtils.StringToCalendar(tsStartTimeET.getText().toString()))||endStartEqual){
					UIHelper.ToastMessage(context, "结束时间应在开始时间之后，请重新设置");
					timeError = false;
					tsEndTimeET.setText("");
					return;
				}
				timeError = true;
				TransferSurveySettingActivity.tsEntity.setEndTime(df.format(calendar.getTime()));
				break;
			case START_TIME_FLAG:
				startCalendar = calendar;
				tsStartTimeET.setText(df.format(calendar.getTime()));
				TransferSurveySettingActivity.tsEntity.setStartTime(df.format(calendar.getTime()));
				break;
			}
			
		}
		
		//设置到达终止时间后的设置 自动保存
		public class MyReceiver extends BroadcastReceiver  
		{  
		    @Override  
		    public void onReceive(Context context, Intent intent)  
		    {  
		    	if(!timeError){
		    		UIHelper.ToastMessage(context, "时间设置错误，请重新设置");
		        	return;
		    	}
		        if(peopleTotalNum == 0){
		        	UIHelper.ToastMessage(context, "人数为0,保存失败");
		        	return;
		        }
		        String msg = intent.getStringExtra("msg");  
		        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();  
		        
		      //设置主键加1并保存
				TransferSurveySettingActivity.tsEntity.setID(tsRowID);
				tsRowID++;
				Editor editor = preferences.edit();
				editor.putInt("tsRowID",tsRowID);
				editor.commit();
				
//		        TransferSurveySettingActivity.tsEntity.setCountTotal(peopleTotalNum+"");
				TransferSurveyDataHelper.insertIntoTSSurveyData(context, TransferSurveySettingActivity.tsEntity);
				
				int tempDataSize = totalData.size();
				for(int i=tempDataSize-1;i>=1;i--)
					totalData.remove(i);
				//序号重置
				id = 0;
				peopleTotalNum = 0;
				tsDataPerLV.setAdapter(tsDataPerAdapter);
				tsDataPerAdapter.notifyDataSetChanged();
				resetAfterSave();
		        stopService(intent);
		    }  
		  
		} 

}
