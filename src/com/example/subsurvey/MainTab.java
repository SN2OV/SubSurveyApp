package com.example.subsurvey;

import com.example.subsurvey.dataShow.DataViewPagerFragment;
import com.example.subsurvey.personalSetting.PersonalSettingFragment;

public enum MainTab {
	 //序号、显示名称、图标、布局文件
	 NEWS(0, "设置", R.drawable.tab_icon_new,
			 DataViewPagerFragment.class),

	 QUICK(1, "快速", R.drawable.tab_icon_new,
	   null),

	 ME(2, "我", R.drawable.tab_icon_me,
	   PersonalSettingFragment.class);


	 private int idx;
	 private String resName;
	 private int resIcon;
	 private Class<?> clz;

	 private MainTab(int idx, String resName, int resIcon, Class<?> clz) {
	  this.idx = idx;
	  this.resName = resName;
	  this.resIcon = resIcon;
	  this.clz = clz;
	 }

	 public int getIdx() {
	  return idx;
	 }

	 public void setIdx(int idx) {
	  this.idx = idx;
	 }

	 public String getResName() {
	  return resName;
	 }

	 public void setResName(String resName) {
	  this.resName = resName;
	 }

	 public int getResIcon() {
	  return resIcon;
	 }

	 public void setResIcon(int resIcon) {
	  this.resIcon = resIcon;
	 }

	 public Class<?> getClz() {
	  return clz;
	 }

	 public void setClz(Class<?> clz) {
	  this.clz = clz;
	 }
	}