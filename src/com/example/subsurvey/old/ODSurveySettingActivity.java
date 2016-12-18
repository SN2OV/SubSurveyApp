package com.example.subsurvey.old;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;

import com.example.subsurvey.R;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

public class ODSurveySettingActivity extends ActionBarActivity {
	
	private DatePickerDialog.OnDateSetListener listener = null;// 用于设置生日对话框
	private Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
	private SimpleDateFormat df; // 日期格式
	private Context context = null;
	public static String[] pathTypeInfo = {"单路径","换乘一次","换乘两次"};
	private String pathType;
	//定义走行调查实体，用于将各种数据保存
	public static ODSurveyEntity odSurveyEntity;
	
	@InjectView (R.id.odSurveyNameET)
	EditText odSurveyNameET;
	@InjectView (R.id.odSurveyTimeET)
	EditText odSurveyTimeET;
	@InjectView (R.id.odSurveyCardNumET)
	EditText odSurveyCardNumET;
	@InjectView (R.id.odSurveyIDNoET)
	EditText odSurveyIDNoET;
	@InjectView (R.id.odSurveyGetinStationET)
	EditText odSurveyGetinStationET;
	@InjectView (R.id.odSurveyGetinStationLineET)
	EditText odSurveyGetinStationLineET;
	@InjectView (R.id.odSurveyStationCountET)
	EditText odSurveyStationCountET;
	@InjectView (R.id.odSurveyDistanceTotalET)
	EditText odSurveyDistanceTotalET;
	@InjectView (R.id.ODCalendarImageView)
	ImageView ODCalendarImageView;
	@InjectView (R.id.pathTypeSpinner)
	Spinner pathTypeSpinner;
	@InjectView (R.id.ODSurveyWeekdaySwitch)
	Switch ODSurveyWeekdaySwitch;
	@InjectView (R.id.odSurveyInImageView)
	ImageView odSurveyInImageView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		setContentView(R.layout.activity_odsurvey_setting);
		ButterKnife.inject(this);
		ODSurveySettingActivity.odSurveyEntity = new ODSurveyEntity();
		init();
	}

	void init(){
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		listener = new DatePickerDialog.OnDateSetListener() { 
			@Override
			public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
				calendar.set(Calendar.YEAR, arg1);
				calendar.set(Calendar.MONTH, arg2);
				calendar.set(Calendar.DAY_OF_MONTH, arg3);
				updateDate();
			}
		};
		ArrayAdapter<CharSequence> adapterType = null;  
		adapterType = new ArrayAdapter<CharSequence>(this,  
					  android.R.layout.simple_spinner_dropdown_item, pathTypeInfo);  
		//设置下拉框的数据适配器adapterType
		pathTypeSpinner.setAdapter(adapterType); 
		//设置属性
		if(ODSurveyWeekdaySwitch.isChecked())
			ODSurveySettingActivity.odSurveyEntity.setWeekdayIf("工作日");
		else
			ODSurveySettingActivity.odSurveyEntity.setWeekdayIf("非工作日");
		ODSurveySettingActivity.odSurveyEntity.setPeakIf("平峰");
		ODSurveySettingActivity.odSurveyEntity.setPathType("单路径");
		//设置editText监听
		odSurveyNameET.addTextChangedListener(new textChangedListener(odSurveyNameET));
		odSurveyTimeET.addTextChangedListener(new textChangedListener(odSurveyTimeET));
		odSurveyCardNumET.addTextChangedListener(new textChangedListener(odSurveyCardNumET));
		odSurveyIDNoET.addTextChangedListener(new textChangedListener(odSurveyIDNoET));
		odSurveyGetinStationET.addTextChangedListener(new textChangedListener(odSurveyGetinStationET));
		odSurveyGetinStationLineET.addTextChangedListener(new textChangedListener(odSurveyGetinStationLineET));
		odSurveyStationCountET.addTextChangedListener(new textChangedListener(odSurveyStationCountET));
		odSurveyDistanceTotalET.addTextChangedListener(new textChangedListener(odSurveyDistanceTotalET));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.odsurvey_setting, menu);
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
	
	//监听日历
	@OnClick(R.id.ODCalendarImageView)
	//不一定是onClick,名字可以任意定义
	void onCalendarClick(){
		DatePickerDialog dialog = new DatePickerDialog(ODSurveySettingActivity.this, listener,
				calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));  
           DatePicker datePicker = dialog.getDatePicker(); 
           datePicker.setMaxDate(System.currentTimeMillis());  
           dialog.show();
	}
	
	@OnItemSelected(R.id.pathTypeSpinner)
	void onItemSelected(int pos){
		pathType = pathTypeInfo[pos];
		ODSurveySettingActivity.odSurveyEntity.setPathType(pathType);
		System.out.println("--------"+pathType+"----------");
	}
	
	@OnClick({R.id.ODSurveyWeekdaySwitch,R.id.ODSurveyTimeFlatRadio,R.id.ODSurveyTimePeakRadio})
	void onSpinnerClick(View view){
		switch(view.getId()){
		case R.id.ODSurveyTimeFlatRadio:
			System.out.println("-------平峰---------");
			ODSurveySettingActivity.odSurveyEntity.setPeakIf("平峰");
			break;
		case R.id.ODSurveyTimePeakRadio:
			System.out.println("-------高峰---------");
			ODSurveySettingActivity.odSurveyEntity.setPeakIf("高峰");
			break;
		case R.id.ODSurveyWeekdaySwitch:
			if(ODSurveyWeekdaySwitch.isChecked()){
				System.out.println("-------工作日---------");
				ODSurveySettingActivity.odSurveyEntity.setWeekdayIf("工作日");
			}
			else{
				System.out.println("-------非工作日---------");
				ODSurveySettingActivity.odSurveyEntity.setWeekdayIf("非工作日");
			}
			break;
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
					if(EditId==odSurveyNameET){
						ODSurveySettingActivity.odSurveyEntity.setName(s.toString());
					}else if(EditId==odSurveyTimeET){
						ODSurveySettingActivity.odSurveyEntity.setDate(s.toString());
					}else if(EditId==odSurveyCardNumET){
						ODSurveySettingActivity.odSurveyEntity.setCardNum(s.toString());
					}else if(EditId==odSurveyIDNoET){
						ODSurveySettingActivity.odSurveyEntity.setIDNo(s.toString());
					}else if(EditId==odSurveyGetinStationET){
						ODSurveySettingActivity.odSurveyEntity.setGetinStation(s.toString());
					}else if(EditId==odSurveyGetinStationLineET){
						ODSurveySettingActivity.odSurveyEntity.setGetinStationLine(s.toString());
					}else if(EditId==odSurveyStationCountET){
						ODSurveySettingActivity.odSurveyEntity.setStationCount(s.toString());
					}else if(EditId==odSurveyDistanceTotalET){
						ODSurveySettingActivity.odSurveyEntity.setDistanceTotal(s.toString());
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
	
	
	// 用于将calendar的日期转成“yyyy-MM--dd”的格式显示到界面上
	@SuppressLint("SimpleDateFormat")
	private void updateDate(){
		df = new SimpleDateFormat("yyyy-MM-dd");
		context = ODSurveySettingActivity.this.getApplicationContext();
		odSurveyTimeET.setText(df.format(calendar.getTime()));
		ODSurveySettingActivity.odSurveyEntity.setDate(df.format(calendar.getTime()));
	}
	
	@OnClick(R.id.odSurveyInImageView)
	void onClick(){
		Intent it = new Intent();
		it.setClass(context, ODSurveyTimeRecordedActivity.class);
		it.putExtra("pathType", pathType);
		startActivity(it);
	}

}
