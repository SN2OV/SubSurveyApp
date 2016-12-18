package com.example.subsurvey.old;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.example.subsurvey.R;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;

import android.R.bool;
import android.os.Bundle;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Switch;

public class WalkSurveySettingActivity extends ActionBarActivity {
	
	private ImageView calendarImageView = null;
	private EditText walkSurveyNameET,walkSurveyStationET,walkSurveyLineET,calendarEditText = null;
	private Switch walkSurveyWeekdaySwitch;
	private RadioButton TempRadioButton;
	private RadioGroup walkSurveyTimeRadioGroup;
	private DatePickerDialog.OnDateSetListener listener = null;// 用于设置生日对话框
	private Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
	private SimpleDateFormat df; // 日期格式
	private Context context = null;
	private ImageView inSurveyView;
	private Spinner typeSpinner;
	private String surveyType;//走行类型
	public static String[] typeInfo = {"进站","出站","换乘"};
	//定义走行调查实体，用于将各种数据保存
	public static WalkSurveyEntity walkSurveyEntity;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_walk_survey);
		
		//这两行+onOptionsItemSelected+Manifest的android:theme组成了屏幕左上角的返回按钮
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		context = getApplicationContext();
		WalkSurveySettingActivity.walkSurveyEntity =  new WalkSurveyEntity();
		calendarEditText = (EditText)findViewById(R.id.calendarTextEdit);
		calendarImageView = (ImageView)findViewById(R.id.calendarImageView);
		calendarImageView.setOnClickListener(new calendarImageView());
		inSurveyView = (ImageView)findViewById(R.id.inSurveyImageView);
		inSurveyView.setOnClickListener(new onClickListenerGotoWalkImpl());
		walkSurveyNameET =(EditText)findViewById(R.id.walkSurveyNameET);
		walkSurveyNameET.addTextChangedListener(new textChangedListener(walkSurveyNameET));
		walkSurveyStationET =(EditText)findViewById(R.id.walkSurveyStationET);
		walkSurveyStationET.addTextChangedListener(new textChangedListener(walkSurveyStationET));
		walkSurveyLineET =(EditText)findViewById(R.id.walkSurveyLineET);
		walkSurveyLineET.addTextChangedListener(new textChangedListener(walkSurveyLineET));
		walkSurveyWeekdaySwitch = (Switch)findViewById(R.id.walkSurveyWeekdaySwitch);
		walkSurveyTimeRadioGroup = (RadioGroup)findViewById(R.id.walkSurveyTimeRadioGroup);
		//监听editText
		
		//配置调查类型下拉框
		typeSpinner = (Spinner)findViewById(R.id.typeSpinner);
		ArrayAdapter<CharSequence> adapterType = null;  
		adapterType = new ArrayAdapter<CharSequence>(this,  
					  android.R.layout.simple_spinner_dropdown_item, typeInfo);  
		//设置下拉框的数据适配器adapterType
		typeSpinner.setAdapter(adapterType); 
		//监听spinner
		typeSpinner.setOnItemSelectedListener(new OnItemWalkTypeSelectedListener());
		if(walkSurveyWeekdaySwitch.isChecked())
			WalkSurveySettingActivity.walkSurveyEntity.setWeekdayIf("是");
		else
			WalkSurveySettingActivity.walkSurveyEntity.setWeekdayIf("否");
		//获取radioGroup
		TempRadioButton = (RadioButton)findViewById(walkSurveyTimeRadioGroup.getCheckedRadioButtonId());
		WalkSurveySettingActivity.walkSurveyEntity.setTravelTime(TempRadioButton.getText().toString());
		
		listener = new DatePickerDialog.OnDateSetListener() { 
			@Override
			public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
				calendar.set(Calendar.YEAR, arg1);
				calendar.set(Calendar.MONTH, arg2);
				calendar.set(Calendar.DAY_OF_MONTH, arg3);
				updateDate();
			}
		};
		
	}

	@Override
	protected void onResume() {
		super.onResume();
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
	public class calendarImageView implements OnClickListener{

		@Override
		public void onClick(View v) {
			DatePickerDialog dialog = new DatePickerDialog(WalkSurveySettingActivity.this, listener,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));  
            DatePicker datePicker = dialog.getDatePicker(); 
            datePicker.setMaxDate(System.currentTimeMillis());  
            dialog.show();
		}
		
	}
	
	// 用于将calendar的日期转成“yyyy-MM--dd”的格式显示到界面上
	private void updateDate(){
		df = new SimpleDateFormat("yyyy-MM-dd");
		context = WalkSurveySettingActivity.this.getApplicationContext();
		calendarEditText.setText(df.format(calendar.getTime()));
		WalkSurveySettingActivity.walkSurveyEntity.setDate(df.format(calendar.getTime()));
	}
	
	//跳转
	public class onClickListenerGotoWalkImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
				boolean isInfoEmpty = StringUtils.isEmpty(walkSurveyNameET.getText().toString())||StringUtils.isEmpty(walkSurveyStationET.getText().toString())
									||StringUtils.isEmpty(walkSurveyLineET.getText().toString())||StringUtils.isEmpty(calendarEditText.getText().toString());
				if(isInfoEmpty){
					UIHelper.ToastMessage(context, "请将所有数据补充完整");
					return;
				}
				Intent it = new Intent();
				it.setClass(context, WalkSurveyTimeRecordedActivity.class);
				//将走行类型传过去
				it.putExtra("surveyType", surveyType);
				startActivity(it);
		}
	}
	
	//乘客走行类型
		public class OnItemWalkTypeSelectedListener implements OnItemSelectedListener{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				surveyType = typeInfo[arg2];
				WalkSurveySettingActivity.walkSurveyEntity.setWalkType(surveyType);
					
				System.out.println("--------"+surveyType+"----------");
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

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
				if(EditId==walkSurveyNameET){
					WalkSurveySettingActivity.walkSurveyEntity.setName(s.toString());
				}else if(EditId==walkSurveyStationET){
					WalkSurveySettingActivity.walkSurveyEntity.setStation(s.toString());
				}else if(EditId==walkSurveyLineET){
					WalkSurveySettingActivity.walkSurveyEntity.setLine(s.toString());
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
		
		//监听所有的CompoundButton控件          doClick在xml的 android:onClick="doClick"配置
		public void doClick(View view){
			boolean isChecked =((CompoundButton)view).isChecked();
			switch(view.getId()){
			case R.id.walkSurveyWeekdaySwitch:
				if(isChecked)
					WalkSurveySettingActivity.walkSurveyEntity.setWeekdayIf("是");
				else
					WalkSurveySettingActivity.walkSurveyEntity.setWeekdayIf("否");
				break;
			case R.id.timeFrom7Radio:
				if(isChecked)
					WalkSurveySettingActivity.walkSurveyEntity.setTravelTime("07:00~09:00");
				break;
			case R.id.timeFrom14Radio:
				if(isChecked)
					WalkSurveySettingActivity.walkSurveyEntity.setTravelTime("14:00~16:00");
				break;
			case R.id.timeFrom17Radio:
				if(isChecked)
					WalkSurveySettingActivity.walkSurveyEntity.setTravelTime("17:00~19:00");
				break;
			}
		}

}
