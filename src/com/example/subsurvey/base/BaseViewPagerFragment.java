package com.example.subsurvey.base;

import com.example.subsurvey.R;
import com.example.subsurvey.dataShow.PagerSlidingTabStrip;
import com.example.subsurvey.dataShow.ViewPagerFragmentAdapter;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * *
 * 带有导航条的基类
 * 
 */
public abstract class BaseViewPagerFragment extends BaseFragment {
	
	protected PagerSlidingTabStrip mTabStrip;
    protected ViewPager mViewPager;
    protected ViewPagerFragmentAdapter mTabsAdapter;
//    protected EmptyLayout mErrorLayout;

	public BaseViewPagerFragment() {
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.base_viewpage_fragment, null);
		return view; 
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		 mTabStrip = (PagerSlidingTabStrip) view
	                .findViewById(R.id.pager_tabstrip);

	     mViewPager = (ViewPager) view.findViewById(R.id.pager);
//	     mErrorLayout = (EmptyLayout) view.findViewById(R.id.error_layout);
	     mTabsAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(),
	             mTabStrip, mViewPager);
	     setScreenPageLimit();
	     onSetupTabAdapter(mTabsAdapter);
	}
	 protected void setScreenPageLimit() {
	    }
	 protected abstract void onSetupTabAdapter(ViewPagerFragmentAdapter adapter);

}
