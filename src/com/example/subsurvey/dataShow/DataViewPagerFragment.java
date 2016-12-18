package com.example.subsurvey.dataShow;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.subsurvey.R;
import com.example.subsurvey.base.BaseViewPagerFragment;
import com.example.subsurvey.dataShow.data.ODDataFragment;
import com.example.subsurvey.dataShow.data.StaySurveyDataFragment;
import com.example.subsurvey.dataShow.data.WalkSurveyDataFragment;
import com.example.subsurvey.dataShow.setting.ODSettingFragment;
import com.example.subsurvey.dataShow.setting.ReverseSettingFragment;
import com.example.subsurvey.dataShow.setting.StaySettingFragment;
import com.example.subsurvey.dataShow.setting.TransferSettingFragment;
import com.example.subsurvey.dataShow.setting.WalkSettingFragment;

public class DataViewPagerFragment extends BaseViewPagerFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	//定义于父类BaseViewPagerFragment的抽象方法
		@Override
		protected void onSetupTabAdapter(ViewPagerFragmentAdapter adapter) {
			String[] title = getResources().getStringArray(
	                R.array.news_viewpage_arrays); //资讯、热点、博客、推荐
			//此函数，title用于显示每个fragment的标题，第二个参数为tag,第三和第四参数用于初始化Fragment
	        adapter.addTab(title[0], "walkTime", WalkSettingFragment.class,
	                null);
	        adapter.addTab(title[1], "stay", StaySettingFragment.class,
	                null);
	        adapter.addTab(title[2], "od", ODSettingFragment.class,
	                null);
	        adapter.addTab(title[3], "transfer", TransferSettingFragment.class,
	                null);
			adapter.addTab(title[4],"reverse", ReverseSettingFragment.class,
					null);
		}
		
		private Bundle getBundle(int newType) {
	        Bundle bundle = new Bundle();
	        //Bundle类是一个key-value对
	        bundle.putInt("BUNDLE_KEY_CATALOG", newType);
	        return bundle;
	    }
		/**
	     * 基类会根据不同的catalog展示相应的数据
	     * 
	     * @param catalog
	     *            要显示的数据类别
	     * @return
	     */
	    private Bundle getBundle(String catalog) {
	        Bundle bundle = new Bundle();
	        bundle.putString("BUNDLE_BLOG_TYPE", catalog);
	        return bundle;
	    }

	    @Override
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);

	        List<Fragment> fragments = getChildFragmentManager().getFragments();
	        if (fragments != null) {
	            for (Fragment fragment : fragments) {
	            	if(fragment==null)
	            		continue;
	                fragment.onActivityResult(requestCode, resultCode, data);
	            }
	        }
	    }

	    
}
