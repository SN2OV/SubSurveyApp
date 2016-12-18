package com.example.subsurvey.walkSurvey.beans;

import java.io.Serializable;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.base.BaseSurveyEntity;

import android.provider.BaseColumns;
import android.widget.BaseExpandableListAdapter;

public class WalkSurveyEntity extends BaseSurveyEntity implements Serializable, BaseColumns{
	public static final String TABLE_NAME_TWALKSURVEYINFO = "t_walkSurveyInfo"; // 走行调查信息的表
	public static final String SQL_DROP_TABLE_TWALKSURVEINFO = "drop table if exists"+WalkSurveyEntity.TABLE_NAME_TWALKSURVEYINFO;
	public static int ID=1;
	
	public String travelTime,walkType,age,sex,telConcern,speed,bagageType,walkTool,timeType,time,
				openDoorTime1,gotoPlatformTime,arrivePlatformTime,openDoorTime2,gooutPlatformTime,getOnLine,
				getOnDire,getOffLine,getOffDire,MachineLoc,MachineLine,routeNo;
//				,name,date,station,line,weekdayIf;
	public String rowID;
	public String getOpenDoorTime1() {
		return openDoorTime1;
	}
	
	public static final String TName = "用户姓名";//用户姓名
	public static final String TDate = "日期";//日期
	public static final String TStation = "车站名称";//车站名称
	public static final String TLine = "所属线路";//所属线路
	public static final String TWeekDayIF = "是否工作日";//是否工作日
	public static final String TTravelTime = "出行时间段";//出行时间段
	public static final String TWalkType = "走行类型";//走行类型
	
	public static final String TAge = "乘客年龄";//乘客年龄
	public static final String TBagageType = "行李类型";//行李类型
	public static final String TWalkTool = "走行工具";//走行工具
	public static final String TSex = "乘客性别";//性别
	public static final String TTelConcern = "手持设备";//手机关注情况
	public static final String TSpeed = "走行速度";//走行速度
	public static final String TOpenDoorTime1 = "列车开门时刻";//列车开门时刻
	public static final String TGotoPlatformTime = "进站刷卡时刻";//进站刷卡时刻
	public static final String TArrivePlatformTime = "到达站台时刻";//到达站台时刻
	public static final String TOpenDoorTime2 = "列车开门时刻2";//列车开门时刻
	public static final String TGooutPlatformTime = "出站刷卡时刻";//出站刷卡时刻
	
	public static final String TGetOnLine = "上车线路";
	public static final String TGetOnDire = "上车线路方向";
	public static final String TGetOffLine = "下车线路";
	public static final String TGetOffDire = "下车线路方向";
	public static final String TMachineLoc = "闸机位置";
	public static final String TMachineLine = "闸机线路";

	public static final String TRounteNo = "调查路线序号";
	//建表 _ID为主键
	public static final String SQL_CREATE_TABLE_TWALKSURVETINFO = "create table if not exists "
			+WalkSurveyEntity.TABLE_NAME_TWALKSURVEYINFO+" (" + _ID + " INTEGER PRIMARY KEY"+AppConfig.COMMA_SEP
			+WalkSurveyEntity.TName+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TDate+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TStation+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TLine+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TWeekDayIF+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TTravelTime+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TWalkType+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TAge+ AppConfig.TYPE_INTEGER + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TBagageType+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TWalkTool+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TSex+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TTelConcern+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TSpeed+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TGetOffLine+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TGetOffDire+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TOpenDoorTime1+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TMachineLine+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TMachineLoc+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TGotoPlatformTime+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TGetOnLine+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TGetOnDire+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TArrivePlatformTime+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TOpenDoorTime2+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TRounteNo+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+WalkSurveyEntity.TGooutPlatformTime+ AppConfig.TYPE_TEXT 
			+ ");";
	
	public String getGetOnLine() {
		return getOnLine;
	}



	public void setGetOnLine(String getOnLine) {
		this.getOnLine = getOnLine;
	}



	public String getGetOnDire() {
		return getOnDire;
	}



	public void setGetOnDire(String getOnDire) {
		this.getOnDire = getOnDire;
	}



	public String getGetOffLine() {
		return getOffLine;
	}



	public void setGetOffLine(String getOffLine) {
		this.getOffLine = getOffLine;
	}



	public String getGetOffDire() {
		return getOffDire;
	}



	public void setGetOffDire(String getOffDire) {
		this.getOffDire = getOffDire;
	}



	public String getMachineLoc() {
		return MachineLoc;
	}



	public void setMachineLoc(String machineLoc) {
		MachineLoc = machineLoc;
	}



	public String getMachineLine() {
		return MachineLine;
	}



	public void setMachineLine(String machineLine) {
		MachineLine = machineLine;
	}

	public void setOpenDoorTime1(String openDoorTime1) {
		this.openDoorTime1 = openDoorTime1;
	}



	public String getGotoPlatformTime() {
		return gotoPlatformTime;
	}



	public void setGotoPlatformTime(String gotoPlatformTime) {
		this.gotoPlatformTime = gotoPlatformTime;
	}



	public String getArrivePlatformTime() {
		return arrivePlatformTime;
	}



	public void setArrivePlatformTime(String arrivePlatformTime) {
		this.arrivePlatformTime = arrivePlatformTime;
	}



	public String getOpenDoorTime2() {
		return openDoorTime2;
	}



	public void setOpenDoorTime2(String openDoorTime2) {
		this.openDoorTime2 = openDoorTime2;
	}



	public String getGooutPlatformTime() {
		return gooutPlatformTime;
	}



	public void setGooutPlatformTime(String gooutPlatformTime) {
		this.gooutPlatformTime = gooutPlatformTime;
	}



	public String getWalkTool() {
		return walkTool;
	}



	public void setWalkTool(String walkTool) {
		this.walkTool = walkTool;
	}



	



	public int getID() {
		return ID;
	}



	public static void setID(int iD) {
		ID = iD;
	}


	public String getRowID() {
		return rowID;
	}



	public void setRowID(String rowID) {
		this.rowID = rowID;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelConcern() {
		return telConcern;
	}

	public void setTelConcern(String telConcern) {
		this.telConcern = telConcern;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

//	public String getName() {
//		return name;
//	}
//
//
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//
//
//	public String getDate() {
//		return date;
//	}
//
//
//
//	public void setDate(String date) {
//		this.date = date;
//	}
//
//
//
//	public String getStation() {
//		return station;
//	}
//
//
//
//	public void setStation(String station) {
//		this.station = station;
//	}
//
//
//
//	public String getLine() {
//		return line;
//	}
//
//
//
//	public void setLine(String line) {
//		this.line = line;
//	}
//
//
//
//	public String getWeekdayIf() {
//		return weekdayIf;
//	}
//
//
//
//	public void setWeekdayIf(String weekdayIf) {
//		this.weekdayIf = weekdayIf;
//	}



	public String getTravelTime() {
		return travelTime;
	}



	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}



	public String getWalkType() {
		return walkType;
	}



	public void setWalkType(String walkType) {
		this.walkType = walkType;
	}



	public String getAge() {
		return age;
	}



	public void setAge(String age) {
		this.age = age;
	}



	public String getBagageType() {
		return bagageType;
	}



	public void setBagageType(String bagageType) {
		this.bagageType = bagageType;
	}



	public String getTimeType() {
		return timeType;
	}



	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}

	public String getRouteNo() {
		return routeNo;
	}

	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}
}
