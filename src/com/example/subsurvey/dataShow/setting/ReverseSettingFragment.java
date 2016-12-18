package com.example.subsurvey.dataShow.setting;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.base.BaseFragment;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.personalSetting.PSWordActivity;
import com.example.subsurvey.personalSetting.UserEntity;
import com.example.subsurvey.reverseSurvey.ui.ReverseSurveyDataTotalActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReverseSettingFragment extends BaseFragment {

    @InjectView(R.id.rsSettingTypeRL)
    RelativeLayout rsSettingTypeRL;
    @InjectView(R.id.rsSettingTV_Type)
    TextView rsSettingTV_Type;
    @InjectView(R.id.rsSettingLocRL)
    RelativeLayout rsSettingLocRL;
    @InjectView(R.id.rsSettingTV_loc)
    TextView rsSettingTV_loc;
    @InjectView(R.id.rsSettingPeriodRL)
    RelativeLayout rsSettingPeriodRL;
    @InjectView(R.id.rsSettingTV_period)
    TextView rsSettingTV_period;
    @InjectView(R.id.rsSettingDataRL)
    RelativeLayout rsSettingDataRL;
    @InjectView(R.id.rsSettingDireRL)
    RelativeLayout rsSettingDireRL;
    @InjectView(R.id.rsSettingTV_dire)
    TextView rsSettingTV_dire;

    private Context context;
    private AppContext appContext;
    private UserEntity user = null;

    public ReverseSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reverse_setting, container, false);
        ButterKnife.inject(this,view);
        context = getActivity();
        init();
        initView();
        return view;
    }

    @Override
    public void onResume() {
        user = appContext.getUser();
        rsSettingTV_loc.setText(user.getRsCarriageLoc());
        rsSettingTV_period.setText(user.getRsTimePeriod());
        rsSettingTV_dire.setText(user.getRsDire());
        rsSettingTV_Type.setText(user.getRsType());
        super.onResume();
    }

    private void init() {
        appContext = (AppContext)getActivity().getApplication();
        rsSettingTypeRL.setOnClickListener(new OnRSSettingClickListenerImpl());
        rsSettingLocRL.setOnClickListener(new OnRSSettingClickListenerImpl());
        rsSettingPeriodRL.setOnClickListener(new OnRSSettingClickListenerImpl());
        rsSettingDataRL.setOnClickListener(new OnRSSettingClickListenerImpl());
        rsSettingDireRL.setOnClickListener(new OnRSSettingClickListenerImpl());
        user = appContext.getUser();
//        if(user!=null){
//            rsSettingTV_period.setText(user.getTsLoc());
//        }
    }

    private void initView(){
        user = appContext.getUser();
        if(user!=null){
            rsSettingTV_Type.setText(user.getRsType());
            rsSettingTV_period.setText(user.getRsTimePeriod());
        }
    }

    public class OnRSSettingClickListenerImpl implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent it = new Intent();
            Class cls;
            switch (v.getId()){
                case R.id.rsSettingTypeRL:
                    SurveyUtils.onClickChangeArray(rsSettingTV_Type, R.array.rsType, context);
                    user.setRsType(rsSettingTV_Type.getText().toString());
                    appContext.setUser(user);
                    appContext.saveUser();
                    break;
                case R.id.rsSettingLocRL:
                    cls = PSWordActivity.class;
                    it.setClass(context, cls);
                    it.putExtra("rsCarriageLoc", rsSettingTV_loc.getText().toString());
                    it.putExtra("type", AppConfig.RS_LOC_CODE);
                    getParentFragment().startActivityForResult(it, AppConfig.RS_LOC_CODE);
                    break;
                case R.id.rsSettingPeriodRL:
                    SurveyUtils.onClickChangeArray(rsSettingTV_period, R.array.rsTimePeriod, context);
                    user.setRsTimePeriod(rsSettingTV_period.getText().toString());
                    appContext.setUser(user);
                    appContext.saveUser();
                    break;
                case R.id.rsSettingDireRL:
                    cls = PSWordActivity.class;
                    it.setClass(context, cls);
                    it.putExtra("rsDire", rsSettingTV_dire.getText().toString());
                    it.putExtra("type", AppConfig.RS_DIRE_CODE);
                    getParentFragment().startActivityForResult(it, AppConfig.RS_DIRE_CODE);
                    break;
                case R.id.rsSettingDataRL:
                    it.setClass(context, ReverseSurveyDataTotalActivity.class);
                    startActivity(it);
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null)
            return ;
        switch (requestCode){
            case AppConfig.RS_LOC_CODE:
                String carriageLoc = data.getStringExtra("rsLocNew");
                rsSettingTV_loc.setText(carriageLoc);
                break;
            case AppConfig.RS_DIRE_CODE:
                String dire = data.getStringExtra("rsDireNew");
                rsSettingTV_dire.setText(dire);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
