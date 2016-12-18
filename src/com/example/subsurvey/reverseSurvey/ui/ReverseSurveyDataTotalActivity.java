package com.example.subsurvey.reverseSurvey.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.DialogSSCount;
import com.example.subsurvey.R;
import com.example.subsurvey.common.ExportHandler;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.reverseSurvey.adapter.ReverseSurveyTotalCountAdapter;
import com.example.subsurvey.reverseSurvey.beans.ReverseSurveyEntity;
import com.example.subsurvey.reverseSurvey.helper.ReverseSurveyDataHelper;
import com.example.subsurvey.transferSurvey.adapter.TransferSurveyTotalCountAdapter;
import com.example.subsurvey.transferSurvey.beans.TransferSurveyEntity;
import com.example.subsurvey.transferSurvey.helper.TransferSurveyDataHelper;
import com.example.subsurvey.transferSurvey.ui.TransferSurveyTimeRecordedNewActivity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemLongClick;

public class ReverseSurveyDataTotalActivity extends ActionBarActivity {

    @InjectView(R.id.rsSurveyDataTotalLV)
    ListView rsSurveyDataTotalLV;

    public ReverseSurveyTotalCountAdapter rsSurveyTotalCountAdapter;
    public Context context = null;
    public List<ReverseSurveyEntity> rsInfo;
    private SharedPreferences preferences;
    private int rsRowID;
    private ExportHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_survey_data_total);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.walk_survey_data_export, menu);
        MenuItemCompat.setShowAsAction(menu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                Log.i("TAG", "=========选中返回键");
                this.finish();
                break;
            case R.id.walk_survey_data_export:
                Dialog dialog = new AlertDialog.Builder(ReverseSurveyDataTotalActivity.this).setTitle("导出数据").setIcon(R.drawable.app_logo)
                        .setMessage("确认导出数据至SD卡并将本地数据删除吗？").setPositiveButton("导出并删除", new onRSExportPositveClickListener())
                        .setNeutralButton("仅删除", new onRSExportNeutralClickListener())
                        .setNegativeButton("取消", new onRSNegativeClickListener())
                        .create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(){
        final ActionBar bar =getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
        bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标

        ButterKnife.inject(this);
        context = getApplicationContext();
        handler = new ExportHandler(context);

        initInfoArraylist();
        rsInfo = queryRSSurveyInfo(context, rsInfo);
        rsSurveyTotalCountAdapter = new ReverseSurveyTotalCountAdapter(context, rsInfo);
        rsSurveyDataTotalLV.setAdapter(rsSurveyTotalCountAdapter);
    }

    //初始化表头
    private void initInfoArraylist(){
        rsInfo = new ArrayList<ReverseSurveyEntity>();
        ReverseSurveyEntity temp = new ReverseSurveyEntity();
        temp.setRowId("序号");
        temp.setName("姓名");
        temp.setDate("日期");
        temp.setStation("车站");
        temp.setDire("调查方向");
        temp.setSurveyTime("时间段");
        temp.setCountTotal("人数");
        temp.setTrainStartTime("列车启动时刻");
        rsInfo.add(temp);
    }

    //将查询到的信息存放到类里面
    public ArrayList<ReverseSurveyEntity> queryRSSurveyInfo(Context context,List<ReverseSurveyEntity> info){
        Cursor cursor = ReverseSurveyDataHelper.queryRSSurveyData(context);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            ReverseSurveyEntity temp = new ReverseSurveyEntity();
            temp.setRowId(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
            temp.setName(cursor.getString(cursor.getColumnIndex(ReverseSurveyEntity.TName)));
            temp.setDate(cursor.getString(cursor.getColumnIndex(ReverseSurveyEntity.TDate)));
            temp.setStation(cursor.getString(cursor.getColumnIndex(ReverseSurveyEntity.TStation)));
            temp.setDire(cursor.getString(cursor.getColumnIndex(ReverseSurveyEntity.TDire)));
            temp.setSurveyTime(cursor.getString(cursor.getColumnIndex(ReverseSurveyEntity.TSurveyTime)));
            temp.setCountTotal(cursor.getString(cursor.getColumnIndex(ReverseSurveyEntity.TCount)));
            temp.setTrainStartTime(cursor.getString(cursor.getColumnIndex(ReverseSurveyEntity.TTrainStartTime)));
            info.add(temp);
        }
        return (ArrayList<ReverseSurveyEntity>)info;
    }

    //删除+导出
    private class onRSExportPositveClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {

            //ListView即时删除
            initInfoArraylist();
            ReverseSurveyTotalCountAdapter rsSurveyTotalCountAdapter = new ReverseSurveyTotalCountAdapter(context,rsInfo);
            rsSurveyDataTotalLV.setAdapter(rsSurveyTotalCountAdapter);
            rsSurveyTotalCountAdapter.notifyDataSetChanged();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //导出指定模板中的数据
                        SurveyUtils.ExportToExcel(AppConfig.REVERSE_SURVEY,context);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //导出csv原始数据
                    SurveyUtils.exportToCSV("反向乘车调查",context);
                    SurveyUtils.delSurveyInfo("rsRowID", context);

                    Message msg = new Message();
                    msg.what = AppConfig.REVERSE_SURVEY;
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("rsInfo",(Serializable)rsInfo);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }).start();

//            try {
//                //导出指定模板中的数据
//                SurveyUtils.ExportToExcel(AppConfig.REVERSE_SURVEY,context);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
////            TODO　暂时注释
//            SurveyUtils.exportToCSV("反向乘车调查", context);
////            SurveyUtils.delSurveyInfo("rsRowID",context);
//
//            //listView即时删除
//            SurveyUtils.delSurveyInfo("rsRowID", context);
//            initInfoArraylist();
//            rsSurveyTotalCountAdapter = new ReverseSurveyTotalCountAdapter(context,rsInfo);
//            rsSurveyDataTotalLV.setAdapter(rsSurveyTotalCountAdapter);
//            rsSurveyTotalCountAdapter.notifyDataSetChanged();
        }
    }

    //取消
    private class onRSNegativeClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    //仅删除
    private class onRSExportNeutralClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            SurveyUtils.delSurveyInfo("rsRowID", context);
            initInfoArraylist();
            //listView即时删除
            rsSurveyTotalCountAdapter = new ReverseSurveyTotalCountAdapter(context,rsInfo);
            rsSurveyDataTotalLV.setAdapter(rsSurveyTotalCountAdapter);
            rsSurveyTotalCountAdapter.notifyDataSetChanged();
        }
    }

    //如何这么优化：将三个选项的listener的类定义提取出来(和之前的导出/删除类似)，然后将position和id传入listener
    @OnItemLongClick(R.id.rsSurveyDataTotalLV)
    public boolean onItemLongClick(final int position,final long id){
        Dialog dialog = new AlertDialog.Builder(ReverseSurveyDataTotalActivity.this).setTitle("操作数据").setIcon(R.drawable.app_logo)
                .setMessage("确认第"+position+"数据修改吗？").setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context = getApplicationContext();
                        ReverseSurveyDataHelper.delReverseSurveyInfoByID(context, id);
                        //删除后处理序号顺序 odInfo.size()-1因为多一行没用的表头数据
                        for(int i = position+1;i<=rsInfo.size()-1;i++){
                            ReverseSurveyDataHelper.updateNo(context, ReverseSurveyTimeRecordedActivity.rsEntity, i);
                            rsInfo.get(i).rowId = (i-1)+"";
                        }
                        System.out.println(rsInfo);
                        rsInfo.remove(position);
                        //处理序号
                        preferences = getSharedPreferences("rsRowID", MODE_WORLD_WRITEABLE);
                        rsRowID = preferences.getInt("rsRowID", 1);//默认为第二个参数1
                        rsRowID--;
                        //通过sharedPreferences将主键保存起来
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("rsRowID",rsRowID);
                        editor.commit();

                        UIHelper.ToastMessage(context, "删除成功");
                        rsSurveyTotalCountAdapter.notifyDataSetChanged();
                        rsSurveyDataTotalLV.setAdapter(rsSurveyTotalCountAdapter);
                    }
                }).setNeutralButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final DialogRSUpdateNo.Builder builder = new DialogRSUpdateNo.Builder(ReverseSurveyDataTotalActivity.this);
                        builder.setTitle("修改人数");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //修改选中数据
                                String newCount = builder.getNewCountET().getText().toString();
                                rsInfo.get(position).setCountTotal(newCount);
                                rsSurveyTotalCountAdapter.notifyDataSetChanged();
                                rsSurveyDataTotalLV.setAdapter(rsSurveyTotalCountAdapter);
                                ReverseSurveyDataHelper.updateCountByNo(context, newCount, position);
                                dialog.dismiss();
                                UIHelper.ToastMessage(context,"本组数据已修改");
                            }
                        });
                        builder.setNegativeButton("取消", new onRSNegativeClickListener());
                        builder.create().show();
                    }
                }).setNegativeButton("取消", new onRSNegativeClickListener()).create();
        //点击dialog以外地方不消失
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return true;
    }



}
