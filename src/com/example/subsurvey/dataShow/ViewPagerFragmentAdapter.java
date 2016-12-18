package com.example.subsurvey.dataShow;

import java.util.ArrayList;
import com.example.subsurvey.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

	private final Context mContext;
	protected PagerSlidingTabStrip mPagerStrip;
	private final ViewPager mViewPager;
	private final ArrayList<ViewPageInfo> mTabs = new ArrayList<ViewPageInfo>();
	
	//一定要初始化mViewPager
	public ViewPagerFragmentAdapter(FragmentManager fm,PagerSlidingTabStrip pageStrip
			,ViewPager pager) {
		super(fm);
		mContext = pager.getContext();
        mPagerStrip = pageStrip;
        mViewPager = pager;
        mViewPager.setAdapter(this);
        mPagerStrip.setViewPager(mViewPager);
	}
	
	@Override
	public Fragment getItem(int pos) {
		//mTabs的类型为ArrayList<ViewPageInfo>
		ViewPageInfo info = mTabs.get(pos);
		//实例化一个Fragment对象
		return Fragment.instantiate(mContext, info.clss.getName(),info.args);
	}

	@Override
	public int getCount() {
		return mTabs.size();
	}

	//addTab在NewsViewPagerFragment等中调用
	public void addTab(String title, String tag, Class<?> clss, Bundle args) {
        ViewPageInfo viewPageInfo = new ViewPageInfo(title, tag, clss, args);
        addFragment(viewPageInfo);
    }
	
	private void addFragment(ViewPageInfo info) {
        if (info == null) {
            return;
        }
        // 加入tab title
        View v = LayoutInflater.from(mContext).inflate(
                R.layout.base_viewpage_fragment_tab_item, null, false);
        TextView title = (TextView) v.findViewById(R.id.tab_title);
        title.setText(info.title);
        //专为ViewPager定制的滑动选项卡
        mPagerStrip.addTab(v);

        mTabs.add(info);
        notifyDataSetChanged();
    }
}
