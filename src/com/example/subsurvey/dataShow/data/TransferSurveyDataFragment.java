package com.example.subsurvey.dataShow.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.subsurvey.R;
import com.example.subsurvey.base.BaseFragment;
import com.example.subsurvey.common.FileUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.transferSurvey.adapter.TransferSurveyTotalCountAdapter;
import com.example.subsurvey.transferSurvey.beans.TransferSurveyEntity;
import com.example.subsurvey.transferSurvey.helper.TransferSurveyDataHelper;

public class TransferSurveyDataFragment extends BaseFragment{


	@InjectView(R.id.tsSurveyDataTotalLV)
	ListView tsSurveyDataTotalLV;
	
	public TransferSurveyTotalCountAdapter tsSurveyTotalCountAdapter;
	public Context context = null;
	public List<TransferSurveyEntity> tsInfo;
	private SharedPreferences preferences;
	private int tsRowID;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_transfer_survey_data_total, null);
		ButterKnife.inject(this,view);
		init();
		return view;
	}
	
	public void init(){
		
		context = getActivity();
		tsInfo = new ArrayList<TransferSurveyEntity>();
		TransferSurveyEntity temp = new TransferSurveyEntity();
		temp.setRowId("序号");
		temp.setName("姓名");
		temp.setDate("日期");
		temp.setStation("车站");
		//原来是调查方向 现在暂时改为线路
		temp.setDire("所属线路");
		temp.setSurveyTime("时刻");
		temp.setCountTotal("人数");
		tsInfo.add(temp);
		
		tsInfo = queryTSSurveyInfo(context,tsInfo);
		tsSurveyTotalCountAdapter = new TransferSurveyTotalCountAdapter(context, tsInfo);
		tsSurveyDataTotalLV.setAdapter(tsSurveyTotalCountAdapter);
	}

	//将查询到的信息存放到类里面
	public ArrayList<TransferSurveyEntity> queryTSSurveyInfo(Context context,List<TransferSurveyEntity> info){
		Cursor cursor = TransferSurveyDataHelper.queryTSSurveyData(context);		
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			TransferSurveyEntity temp = new TransferSurveyEntity();
			temp.setRowId(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
			temp.setName(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TName)));
			temp.setDate(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TDate)));
			temp.setStation(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TStation)));
			temp.setDire(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TDire)));
			temp.setSurveyTime(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TSurveyTime)));
			temp.setCountTotal(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TCount)));
			info.add(temp);
		}
		return (ArrayList<TransferSurveyEntity>)info;
	}

}
