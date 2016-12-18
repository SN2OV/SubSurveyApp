package com.example.subsurvey.personalSetting;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.base.BaseFragment;
import com.example.subsurvey.base.BaseViewPagerFragment;
import com.example.subsurvey.dataShow.ViewPagerFragmentAdapter;
import com.example.subsurvey.transferSurvey.ui.TransferSurveySettingActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalSettingFragment extends BaseFragment {

	@InjectView(R.id.textView_name)
	TextView tvName;
	@InjectView(R.id.textView_date)
	TextView tvDate;
	@InjectView(R.id.textView_station)
	TextView tvStation;
	@InjectView(R.id.textView_line)
	TextView tvLine;
	@InjectView(R.id.textView_weekday)
	TextView tvWeekday;
	@InjectView(R.id.imageView_name_bg)
	ImageView ivName;
	@InjectView(R.id.imageView_date_bg)
	ImageView ivDate;
	@InjectView(R.id.imageView_station_bg)
	ImageView ivStation;
	@InjectView(R.id.imageView_line_bg)
	ImageView ivLine;
	@InjectView(R.id.imageView_weekday_bg)
	ImageView ivWeekDay;
	
	private Context context;
	private DatePickerDialog.OnDateSetListener listener = null;// 用于设置生日对话框
	private Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
	private SimpleDateFormat df; // 日期格式
	private UserEntity user = null;
	private AppContext appContext;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_personal_setting, null);
		ButterKnife.inject(this,view);
		context = getActivity();
		init();
		return view;
	}

	public void init(){
		context = getActivity();
		appContext = (AppContext)getActivity().getApplication();
		
		listener = new DatePickerDialog.OnDateSetListener() { 
			@Override
			public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
				calendar.set(Calendar.YEAR, arg1);
				calendar.set(Calendar.MONTH, arg2);
				calendar.set(Calendar.DAY_OF_MONTH, arg3);
				updateDate();
			}
		};
		ivName.setOnClickListener(new nameOnClickListenerImpl());
		ivStation.setOnClickListener(new nameOnClickListenerImpl());
		ivLine.setOnClickListener(new nameOnClickListenerImpl());
		
		user = appContext.getUser();
		if(user!=null){
			tvName.setText(user.getName());
			tvDate.setText(user.getDate());
			tvStation.setText(user.getStation());
			tvLine.setText(user.getLine());
			tvWeekday.setText(user.getWeekdayIf());
		}
	}

	public class nameOnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent it = new Intent();
			it.setClass(context, PSWordActivity.class);
			switch(v.getId()){
			case R.id.imageView_name_bg:
				it.putExtra("name",tvName.getText().toString());
				it.putExtra("type", AppConfig.NAME_CODE);
				startActivityForResult(it, AppConfig.NAME_CODE);
				break;
			case R.id.imageView_station_bg:
				it.putExtra("station", tvStation.getText().toString());
				it.putExtra("type", AppConfig.STATION_CODE);
				startActivityForResult(it, AppConfig.STATION_CODE);
				break;
			case R.id.imageView_line_bg:
				it.putExtra("line", tvLine.getText().toString());
				it.putExtra("type", AppConfig.LINE_CODE);
				startActivityForResult(it,AppConfig.LINE_CODE);
				break;
			}
		}
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null)
			return ;
		switch(requestCode){
		case AppConfig.NAME_CODE:
			String name = data.getStringExtra("name_new");
			tvName.setText(name);
			break;
		case AppConfig.STATION_CODE:
			String station = data.getStringExtra("station_new");
			tvStation.setText(station);
			break;
		case AppConfig.LINE_CODE:
			String line = data.getStringExtra("line_new");
			tvLine.setText(line);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@OnClick({R.id.imageView_weekday_bg,R.id.imageView_date_bg})
	public
	void onClick(View view){
		switch(view.getId()){
		case R.id.imageView_weekday_bg:
			if(tvWeekday.getText().equals("是")){
				tvWeekday.setText("否");
				user.setWeekdayIf("否");
			}
			else{
				tvWeekday.setText("是");
				user.setWeekdayIf("是");
			}
			break;
		case R.id.imageView_date_bg:
			DatePickerDialog dialog = new DatePickerDialog(context, listener,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));  
	           DatePicker datePicker = dialog.getDatePicker(); 
	           datePicker.setMaxDate(System.currentTimeMillis());  
	           dialog.show();
			break;
		}
		appContext.setUser(user);
		appContext.saveUser();
	}
	
	// 用于将calendar的日期转成“yyyy-MM--dd”的格式显示到界面上
	@SuppressLint("SimpleDateFormat")
	private void updateDate(){
		df = new SimpleDateFormat("yyyy-MM-dd");
		context = getActivity();
		tvDate.setText(df.format(calendar.getTime()));
		user.setDate(df.format(calendar.getTime()));
		appContext.setUser(user);
		appContext.saveUser();
	}

}
