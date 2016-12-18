package com.example.subsurvey.walkSurvey.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.base.BaseActivity;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.common.UIHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WSTransferLineSettingNewActivity extends BaseActivity {

    @InjectView(R.id.wstStartLineRL)
    RelativeLayout wstStartLineRL;
    @InjectView(R.id.wstStartLineTV)
    TextView wstStartLineTV;
    @InjectView(R.id.wstStartLineStartDireRL)
    RelativeLayout wstStartLineStartDireRL;
    @InjectView(R.id.wstStartLineStartDireTV)
    TextView wstStartLineStartDireTV;
    @InjectView(R.id.wstStartLineEndDireRL)
    RelativeLayout wstStartLineEndDireRL;
    @InjectView(R.id.wstStartLineEndDireTV)
    TextView wstStartLineEndDireTV;
    @InjectView(R.id.wstEndLineRL)
    RelativeLayout wstEndLineRL;
    @InjectView(R.id.wstEndLineTV)
    TextView wstEndLineTV;
    @InjectView(R.id.wstEndLineStartDireRL)
    RelativeLayout wstEndLineStartDireRL;
    @InjectView(R.id.wstEndLineStartDireTV)
    TextView wstEndLineStartDireTV;
    @InjectView(R.id.wstEndLineEndDireRL)
    RelativeLayout wstEndLineEndDireRL;
    @InjectView(R.id.wstEndLineEndDireTV)
    TextView wstEndLineEndDireTV;

    private String lineInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wstransfer_line_setting_new);
        ButterKnife.inject(this);
        context = getApplicationContext();
        init();
        initView();
    }

    private void init() {
        appContext = (AppContext)getApplication();
        user = appContext.getUser();
        final ActionBar bar =getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
        bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
    }

    private void initView() {
        if(user != null){
            wstStartLineTV.setText(user.getWslStartLine());
            wstStartLineStartDireTV.setText(user.getWslStartLineStartDire());
            wstStartLineEndDireTV.setText(user.getWslStartLineEndDire());
            wstEndLineTV.setText(user.getWslEndLine());
            wstEndLineStartDireTV.setText(user.getWslEndLineStartDire());
            wstEndLineEndDireTV.setText(user.getWslEndLineEndDire());
        }
    }

    @OnClick({R.id.wstStartLineRL,R.id.wstStartLineStartDireRL,R.id.wstStartLineEndDireRL,R.id.wstEndLineRL,R.id.wstEndLineStartDireRL,R.id.wstEndLineEndDireRL})
    void onWSTLine(View v){
        Intent it = new Intent();
        it.setClass(context, WSWordActivity.class);
        switch(v.getId()){
            case R.id.wstStartLineRL:
                it.putExtra("wslStartLine",wstStartLineTV.getText().toString());
                it.putExtra("type", AppConfig.WS_TRANS_LINE_START_LINE_CODE);
                startActivityForResult(it, AppConfig.WS_TRANS_LINE_START_LINE_CODE);
                break;
            case R.id.wstStartLineStartDireRL:
                it.putExtra("wslStartLineStartDire",wstStartLineStartDireTV.getText().toString());
                it.putExtra("type", AppConfig.WS_TRANS_LINE_START_LINE_START_DIRE_CODE);
                startActivityForResult(it, AppConfig.WS_TRANS_LINE_START_LINE_START_DIRE_CODE);
                break;
            case R.id.wstStartLineEndDireRL:
                it.putExtra("wslStartLineEndDire",wstStartLineEndDireTV.getText().toString());
                it.putExtra("type", AppConfig.WS_TRANS_LINE_START_LINE_END_DIRE_CODE);
                startActivityForResult(it, AppConfig.WS_TRANS_LINE_START_LINE_END_DIRE_CODE);
                break;
            case R.id.wstEndLineRL:
                it.putExtra("wslEndLine",wstEndLineTV.getText().toString());
                it.putExtra("type", AppConfig.WS_TRANS_LINE_END_LINE_CODE);
                startActivityForResult(it, AppConfig.WS_TRANS_LINE_END_LINE_CODE);
                break;
            case R.id.wstEndLineStartDireRL:
                it.putExtra("wslEndLineStartDire",wstEndLineStartDireTV.getText().toString());
                it.putExtra("type", AppConfig.WS_TRANS_LINE_END_LINE_START_DIRE_CODE);
                startActivityForResult(it, AppConfig.WS_TRANS_LINE_END_LINE_START_DIRE_CODE);
                break;
            case R.id.wstEndLineEndDireRL:
                it.putExtra("wslEndLineEndDire",wstEndLineEndDireTV.getText().toString());
                it.putExtra("type", AppConfig.WS_TRANS_LINE_END_LINE_END_DIRE_CODE);
                startActivityForResult(it, AppConfig.WS_TRANS_LINE_END_LINE_END_DIRE_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {	//
        if(data == null)
            return ;
        switch(requestCode){
            case AppConfig.WS_TRANS_LINE_START_LINE_CODE:
                String startLine = data.getStringExtra("wslStartLine");
                wstStartLineTV.setText(startLine);
                break;
            case AppConfig.WS_TRANS_LINE_START_LINE_START_DIRE_CODE:
                String startLineStartDire = data.getStringExtra("wslStartLineStartDire");
                wstStartLineStartDireTV.setText(startLineStartDire);
                break;
            case AppConfig.WS_TRANS_LINE_START_LINE_END_DIRE_CODE:
                String startLineEndDire = data.getStringExtra("wslStartLineEndDire");
                wstStartLineEndDireTV.setText(startLineEndDire);
                break;
            case AppConfig.WS_TRANS_LINE_END_LINE_CODE:
                String endLine = data.getStringExtra("wslEndLine");
                wstEndLineTV.setText(endLine);
                break;
            case AppConfig.WS_TRANS_LINE_END_LINE_START_DIRE_CODE:
                String endLineStartDire = data.getStringExtra("wslEndLineStartDire");
                wstEndLineStartDireTV.setText(endLineStartDire);
                break;
            case AppConfig.WS_TRANS_LINE_END_LINE_END_DIRE_CODE:
                String endLineEndDire = data.getStringExtra("wslEndLineEndDire");
                wstEndLineEndDireTV.setText(endLineEndDire);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.wstransfer_line_setting, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        System.out.println();
        switch(item.getItemId()){
            case android.R.id.home:
                Log.i("TAG", "=========选中返回键");
                this.finish();
                break;
            case R.id.wsline_transfer_info:
                Dialog dialog = new AlertDialog.Builder(WSTransferLineSettingNewActivity.this).setTitle("路线信息").setIcon(R.drawable.app_logo)
                        .setMessage(SurveyUtils.getWSLineString(user,AppConfig.WS_TRANSF_NO))
                        .setNegativeButton("确定", new onWSLineNegativeClickListener())
                        .create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                break;
            case R.id.wsline_transfer_change:
                UIHelper.ToastMessage(context,"路线切换");
                SurveyUtils.changeWSLine(user,AppConfig.WS_TRANSFER_NO);
                appContext.setUser(user);
                appContext.saveUser();
                initView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //取消
    private class onWSLineNegativeClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    }


}
