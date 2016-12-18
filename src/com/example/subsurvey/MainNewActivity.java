package com.example.subsurvey;

import com.example.subsurvey.dataShow.MyFragmentTabHost;
import com.example.subsurvey.dataShow.QuickOptionDialog;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.TabHost.TabContentFactory;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;

public class MainNewActivity extends ActionBarActivity implements OnTabChangeListener, View.OnClickListener,
OnTouchListener{
	
	@InjectView(android.R.id.tabhost)
    public MyFragmentTabHost mTabHost;
	@InjectView(R.id.quick_option_iv)
    View mAddBt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_new);

		ButterKnife.inject(this);
        initView();
	}

	
	
	@Override
	protected void onResume() {
		Intent it = getIntent();
		String from = it.getStringExtra("from");
		 if(from!=null&&from.equals("transfer"))
			 mTabHost.setCurrentTab(2);
		super.onResume();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_new, menu);
		return true;
	}
	
	 public void initView() {
		//realtabcontent为frameLayout 具体哪个仍不详
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		if (android.os.Build.VERSION.SDK_INT > 10) {
		    mTabHost.getTabWidget().setShowDividers(0);
		}
	    initTabs();
		// 中间按键图片触发
		 mAddBt.setOnClickListener(this);
		 mTabHost.setCurrentTab(0);
		 mTabHost.setOnTabChangedListener(this);

	}

	//初始化tabhost
	 private void initTabs() {
	     MainTab[] tabs = MainTab.values();
	     final int size = tabs.length;
	     for (int i = 0; i < size; i++) {
	      //MainTab为之前定义的枚举类，存放各个tab的信息
	    	MainTab mainTab = tabs[i];
	        TabSpec tab = mTabHost.newTabSpec(mainTab.getResName().toString());
	        View indicator = LayoutInflater.from(getApplicationContext())
	                .inflate(R.layout.tab_indicator, null);
	        TextView title = (TextView) indicator.findViewById(R.id.tab_title);
	        Drawable drawable = this.getResources().getDrawable(
	                 mainTab.getResIcon());
	        //图片显示在title（标题）的上边
	        title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, //分别左、上、右、下
	                null);
	        //给快速按钮设置
	        if (i == 1) {
	            indicator.setVisibility(View.INVISIBLE);
	            mTabHost.setNoTabChangedTag(mainTab.getResName().toString());
	        }
	        title.setText(mainTab.getResName().toString());
	        tab.setIndicator(indicator);
	        tab.setContent(new TabContentFactory() {

	            @Override
	            public View createTabContent(String tag) {
	                return new View(MainNewActivity.this);
	            }
	        });
	        mTabHost.addTab(tab, mainTab.getClz(), null);

	        mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
	    }
	}	 
	 
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	}

	@Override
	public void onClick(View view) {
        int id = view.getId();
        switch (id) {
        // 点击了快速操作按钮
        case R.id.quick_option_iv:
            showQuickOption();
            break;

        default:
            break;
        }
	}

	// 显示快速调查界面
    private void showQuickOption() {
        final QuickOptionDialog dialog = new QuickOptionDialog(
                MainNewActivity.this,(AppContext) getApplication());
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

	
	@Override
	public void onTabChanged(String tabId) {

	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_exit:
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
