package com.example.subsurvey.common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.R;
import com.example.subsurvey.transferSurvey.adapter.TransferSurveyTotalCountAdapter;
import com.example.subsurvey.transferSurvey.beans.TransferSurveyEntity;
import com.example.subsurvey.transferSurvey.ui.TransferSurveyDataTotalActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by SN2OV on 2016/10/19.
 */
public class ExportHandler extends android.os.Handler{

    Context context ;

    public ExportHandler(Context context){
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        Bundle bundle;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        switch (msg.what){
            case AppConfig.WALK_SURVEY:
                break;
            case AppConfig.STAY_SURVEY:
                break;
            case AppConfig.OD_SURVEY:
                break;
            case AppConfig.TRANSFER_SURVEY:
//                bundle = msg.getData();
//                ListView tsSurveyDataTotalLV = (ListView)inflater.inflate(R.layout.activity_transfer_survey_data_total,null).findViewById(R.id.tsSurveyDataTotalLV);
//                List<TransferSurveyEntity> tsInfo = (List<TransferSurveyEntity> )bundle.getSerializable("tsInfo");
//                TransferSurveyTotalCountAdapter tsSurveyTotalCountAdapter = new TransferSurveyTotalCountAdapter(context,tsInfo);
//                tsSurveyDataTotalLV.setAdapter(tsSurveyTotalCountAdapter);
//                tsSurveyTotalCountAdapter.notifyDataSetChanged();
                break;
            case AppConfig.REVERSE_SURVEY:
                break;
            default:
                break;
        }

        UIHelper.ToastMessage(context, "调查数据已导出至/sdcard/客流调查/");
        super.handleMessage(msg);
        Looper.loop();
    }

}



