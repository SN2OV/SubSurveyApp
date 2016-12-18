package com.example.subsurvey.dataShow;


import com.example.subsurvey.AppContext;
import com.example.subsurvey.MainNewActivity;
import com.example.subsurvey.R;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;
import com.example.subsurvey.odSurvey.ui.ODSurveyTimeRecordedNewActivity;
import com.example.subsurvey.old.ODSurveySettingActivity;
import com.example.subsurvey.old.StaySurveySettingActivity;
import com.example.subsurvey.old.WalkSurveySettingActivity;
import com.example.subsurvey.reverseSurvey.ui.ReverseSurveyTimeRecordedActivity;
import com.example.subsurvey.staySurvey.ui.StaySurveyTimeRecordedNewActivity;
import com.example.subsurvey.transferSurvey.ui.TransferSurveySettingActivity;
import com.example.subsurvey.transferSurvey.ui.TransferSurveyTimeRecordedNewActivity;
import com.example.subsurvey.walkSurvey.ui.WalkSurveyNewTimeRecordeActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class QuickOptionDialog extends Dialog implements OnClickListener {

	private ImageView mClose;
    private AppContext m_appContext;
	public QuickOptionDialog(Context context,AppContext appContext) {
        this(context, R.style.quick_option_dialog,appContext);//调用的下面的那个构造函数
    }
	
	@SuppressLint("NewApi")
	public QuickOptionDialog(Context context, int defStyle,AppContext appContext) {
		//Dialog(Context context, int theme)
        super(context, defStyle);
        m_appContext = appContext;
        View contentView = getLayoutInflater().inflate(
                R.layout.dialog_quick_option, null);
//        BitmapDrawable bd = (BitmapDrawable) contentView.getBackground();
//        Bitmap bitmap = bd.getBitmap();
//        contentView.setAlpha(0);
//        Drawable drawable =new BitmapDrawable(setObscuredGlass(context,bitmap));
//        contentView.setBackground(drawable);
        
        
        contentView.findViewById(R.id.ws_quick_option_ly)
                .setOnClickListener(this);
        contentView.findViewById(R.id.ss_quick_option_ly)
                .setOnClickListener(this);
        contentView.findViewById(R.id.od_quick_option_ly)
                .setOnClickListener(this);
        contentView.findViewById(R.id.ts_quick_option_ly)
                .setOnClickListener(this);
        contentView.findViewById(R.id.rs_quick_option_ly)
                .setOnClickListener(this);
        mClose = (ImageView) contentView.findViewById(R.id.iv_close);

        //加载animation实例
        Animation operatingAnim = AnimationUtils.loadAnimation(getContext(),
                R.anim.quick_option_close);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);

        mClose.startAnimation(operatingAnim);

//        mClose.setOnClickListener(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //触碰 界面消失
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                QuickOptionDialog.this.dismiss();
                return true;
            }
        });
        super.setContentView(contentView);

    }
	
	@SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //dialog在底部显示
        getWindow().setGravity(Gravity.BOTTOM);
        //获得屏幕宽度
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        //设置dialog的宽度
        getWindow().setAttributes(p);
    }

	@Override
	public void onClick(View view) {
		int id = view.getId();
		Intent it = new Intent();
		Class<?> cls = null;
		switch(id){
		case R.id.iv_close:
            dismiss();
            break;
		case R.id.ws_quick_option_ly:
            if(SurveyUtils.isDataNeedExport("走行调查",getContext(),m_appContext.getUser())){
                UIHelper.ToastMessage(getContext(),"开始新的调查之前，请将存储的走行数据导出");
                QuickOptionDialog.this.dismiss();
                return;
            }else
			    cls = WalkSurveyNewTimeRecordeActivity.class;
			break;
		case R.id.ss_quick_option_ly:
			cls = StaySurveyTimeRecordedNewActivity.class;
			break;
		case R.id.od_quick_option_ly:
            if(SurveyUtils.isDataNeedExport("OD调查",getContext(),m_appContext.getUser())){
                UIHelper.ToastMessage(getContext(),"开始新的调查之前，请将存储的OD数据导出");
                QuickOptionDialog.this.dismiss();
                return;
            }else if(!m_appContext.getODTempData().getFinished()){
                //TODO appContext.getODTempData，获取isFinished，并根据其值来弹出对话框
                Dialog dialog = new AlertDialog.Builder(getContext()).setTitle("开始OD调查").setIcon(R.drawable.app_logo)
                        .setMessage("您有未结束的OD调查，是否继续上次调查？").setPositiveButton("继续上次调查", new onODResumeSurveyPositveClickListener())
                        .setNeutralButton("新的调查", new onODNewSurveyNeutralClickListener())
//                        .setNegativeButton("取消", new onNegativeClickListener())
                        .create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                return;
            }else
                cls = ODSurveyTimeRecordedNewActivity.class;
			break;
		case R.id.ts_quick_option_ly:
			cls = TransferSurveyTimeRecordedNewActivity.class;
			break;
        case R.id.rs_quick_option_ly:
            cls = ReverseSurveyTimeRecordedActivity.class;
            break;

		}
		it.setClass(getContext(), cls);
		getContext().startActivity(it);
		QuickOptionDialog.this.dismiss();
		
	}

    /**
     * 继续上次的OD调查
     */
    private class onODResumeSurveyPositveClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Intent intent = new Intent();
            intent.setClass(getContext(),ODSurveyTimeRecordedNewActivity.class);
            intent.putExtra("isNewSurvey",false);
            getContext().startActivity(intent);
            QuickOptionDialog.this.dismiss();
        }
    }

    /**
     * 开启新的OD调查
     */
    private class onODNewSurveyNeutralClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {

            //清空之前保存的数据
            ODSurveyEntity odTempData = new ODSurveyEntity();
            m_appContext.setODTempData(odTempData);
            m_appContext.saveODTempData();

            Intent intent = new Intent();
            intent.setClass(getContext(),ODSurveyTimeRecordedNewActivity.class);
            intent.putExtra("isNewSurvey",true);
            getContext().startActivity(intent);
            QuickOptionDialog.this.dismiss();
        }
    }

    //取消
    private class onNegativeClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    }
	

}
