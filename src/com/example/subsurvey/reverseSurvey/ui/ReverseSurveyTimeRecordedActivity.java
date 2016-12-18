package com.example.subsurvey.reverseSurvey.ui;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.personalSetting.UserEntity;
import com.example.subsurvey.reverseSurvey.adapter.ReverseSurveyDataPerAdapter;
import com.example.subsurvey.reverseSurvey.beans.ReverseSurveyEntity;
import com.example.subsurvey.reverseSurvey.helper.ReverseSurveyDataHelper;
import com.example.subsurvey.transferSurvey.adapter.TransferSurveyDataPerAdapter;
import com.example.subsurvey.transferSurvey.adapter.TransferSurveyTotalCountAdapter;
import com.example.subsurvey.transferSurvey.beans.TransferSurveyEntity;
import com.example.subsurvey.transferSurvey.helper.TransferSurveyDataHelper;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ReverseSurveyTimeRecordedActivity extends ActionBarActivity {

    @InjectView(R.id.rsDataPerLV)
    ListView rsDataPerLV;
    @InjectView(R.id.rsAddOneIV)
    ImageView rsAddOneIV;
    @InjectView(R.id.rsAddTwoIV)
    ImageView rsAddTwoIV;
    @InjectView(R.id.rsAddFiveIV)
    ImageView rsAddFiveIV;

    private ReverseSurveyDataPerAdapter rsDataPerAdapter;
    private HashMap<String, String> perData = new HashMap<String, String>();
    private ArrayList<HashMap<String, String>> totalData = new ArrayList<HashMap<String,String>>();
    private Context context;
    private int id = 0 ,peopleTotalNum = 0;
    public static ReverseSurveyEntity rsEntity=new ReverseSurveyEntity();
    private AppContext appContext;
    private int rsRowID;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_survey_time_recorded);
        context = getApplicationContext();
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        final ActionBar bar =getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
        bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标

        context = getApplicationContext();
        appContext = (AppContext)getApplication();
        ButterKnife.inject(this);
        vibrator = (Vibrator)getSystemService(Service.VIBRATOR_SERVICE);

        initRowId();

        totalData.add(insertData("序号", "时间", "当前人数", "总人数"));
        rsDataPerAdapter = new ReverseSurveyDataPerAdapter(totalData, context);
        rsDataPerLV.setAdapter(rsDataPerAdapter);
        rsDataPerAdapter.notifyDataSetChanged();

        UserEntity user = appContext.getUser();
        ReverseSurveyTimeRecordedActivity.rsEntity.setName(user.getName());
        ReverseSurveyTimeRecordedActivity.rsEntity.setDate(user.getDate());
        ReverseSurveyTimeRecordedActivity.rsEntity.setStation(user.getStation());
        ReverseSurveyTimeRecordedActivity.rsEntity.setDire(user.getRsDire());
        ReverseSurveyTimeRecordedActivity.rsEntity.setSurveyTime(user.getRsTimePeriod());
    }

    private void initRowId() {
        preferences = getSharedPreferences("rsRowID", MODE_WORLD_WRITEABLE);
        rsRowID = preferences.getInt("rsRowID", 1);//默认为第二个参数1
        ReverseSurveyTimeRecordedActivity.rsEntity.setID(rsRowID);
        //通过sharedPreferences将主键保存起来
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("rsRowID",rsRowID);
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reverse_survey_time_recorded, menu);
        //图片icon菜单
        MenuItemCompat.setShowAsAction(menu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        MenuItemCompat.setShowAsAction(menu.getItem(1), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int rid = item.getItemId();
        switch (rid){
            case R.id.rs_action_next:
                String curTime = StringUtils.getSystemTime();
                ReverseSurveyTimeRecordedActivity.rsEntity.setTrainStartTime(curTime);
                ReverseSurveyTimeRecordedActivity.rsEntity.setCount(peopleTotalNum + "");
                ReverseSurveyTimeRecordedActivity.rsEntity.setID(rsRowID++);
                ReverseSurveyDataHelper.insertIntoRSSurveyData(context, ReverseSurveyTimeRecordedActivity.rsEntity);
                initTotalData();
                peopleTotalNum = 0;
                editor = preferences.edit();
                editor.putInt("rsRowID", rsRowID);
                editor.commit();
                UIHelper.ToastMessage(context,"本次列车数据已保存");
                break;
            case R.id.rs_action_query:
                Intent it = new Intent();
                it.setClass(context,ReverseSurveyDataTotalActivity.class);
                startActivity(it);
                break;
            case R.id.rs_action_revoke:
                int latestIndex = totalData.size()-1;
                if(latestIndex == 0){
                    UIHelper.ToastMessage(context,"当前无数据，无法撤销");
                    break;
                }
                peopleTotalNum -= Integer.parseInt(totalData.get(latestIndex).get("perCount"));
                id--;
                totalData.remove(latestIndex);
                rsDataPerAdapter = new ReverseSurveyDataPerAdapter(totalData, context);
                rsDataPerLV.setAdapter(rsDataPerAdapter);
                rsDataPerAdapter.notifyDataSetChanged();
//                editor = getSharedPreferences("rsRowID", MODE_WORLD_WRITEABLE).edit();
//                rsRowID--;
//                editor.putInt("rsRowID", 1);
//                editor.commit();
                UIHelper.ToastMessage(context, "已撤销一条记录");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    HashMap<String, String> insertData(String id,String curTime,String perCount,String totalCount){
        HashMap<String, String> tempData = new HashMap<String, String>();
        tempData.put("ID",id);
        tempData.put("Time", curTime);
        tempData.put("perCount", perCount);
        tempData.put("totalCount", totalCount);
        return tempData;
    }

    @OnClick({R.id.rsAddOneIV,R.id.rsAddTwoIV,R.id.rsAddFiveIV})
    void onRsIVClick(View view){
        //设置系统时间
        String curTime = StringUtils.getSystemTime();
        ReverseSurveyTimeRecordedActivity.rsEntity.setSurveyTime(curTime);
        id++;
        switch(view.getId()) {
            case R.id.rsAddOneIV:
                peopleTotalNum++;
                totalData.add(insertData(id + "", curTime, "1", peopleTotalNum + ""));
                ReverseSurveyTimeRecordedActivity.rsEntity.setCount("1");
                break;
            case R.id.rsAddTwoIV:
                peopleTotalNum += 2;
                ReverseSurveyTimeRecordedActivity.rsEntity.setCount("2");
                totalData.add(insertData(id + "", curTime, "2", peopleTotalNum + ""));
                break;
            case R.id.rsAddFiveIV:
                peopleTotalNum += 5;
                ReverseSurveyTimeRecordedActivity.rsEntity.setCount("5");
                totalData.add(insertData(id + "", curTime, "5", peopleTotalNum + ""));
                break;
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("rsRowID", rsRowID);
        editor.commit();
        rsDataPerAdapter.notifyDataSetChanged();
        rsDataPerLV.setAdapter(rsDataPerAdapter);
        //震动-立即开始，持续10ms，不循环
        vibrator.vibrate(new long[]{0, 10}, -1);
    }

    private void initTotalData(){
        totalData = new ArrayList<HashMap<String, String>>();
        totalData.add(insertData("序号", "时间", "当前人数", "总人数"));
        rsDataPerAdapter = new ReverseSurveyDataPerAdapter(totalData, context);
        rsDataPerLV.setAdapter(rsDataPerAdapter);
        rsDataPerAdapter.notifyDataSetChanged();
        id = 0;
    }
}
