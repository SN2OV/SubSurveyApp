package com.example.subsurvey.staySurvey.beans;

import java.io.Serializable;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.base.BaseSurveyEntity;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;

import android.provider.BaseColumns;

public class StaySurveyEntity extends BaseSurveyEntity implements Serializable, BaseColumns{
public static final String TABLE_NAME_TSTAYSURVEYINFO = "t_staySurveyInfo"; // 留乘调查信息的表
public static final String SQL_DROP_TABLE_TSTAYSURVEINFO = "drop table if exists"+StaySurveyEntity.TABLE_NAME_TSTAYSURVEYINFO;
	
	public static int ID=1;
	//doorNo,subNo整合成carriageLoc 去往号线、方向整合成survet=yLoc,countStop为所等的班车数
	//staySurveyDoorNoET,staySurveySubNoET,staySurveySubDireET
	int countStop;
	public String 	rowID,name,date,station,carriageNo,doorNo,subNo,subDire,weekdayIf,travelTime,walkType,age,bagageType,sex,startLineNum,
					carriageLoc,surveyLoc,goinLoc,getoffLoc,goinLineNo,getOffLineNo,goinLineDire,getoffLineDire,
					getOnNum1,getOnNum2,getOnNum3,getOnNum4,getOnNum5,getOnNum6,getOffNum1,getOffNum2,getOffNum3,getOffNum4,getOffNum5,getOffNum6,
					gointoStationTime,getoffTime,arriveStationTime,trainStartTime1,trainStartTime2,trainStartTime3,trainStartTime4,trainStartTime5,trainStartTime6;
	
	public static final String TName = "调查员";//用户姓名
	public static final String TDate = "调查日期";//日期
	public static final String TStation = "车站名称";//车站名称
	public static final String TCarriageLoc = "车厢位置";//车厢位置
	public static final String TPlatformLoc = "调查站台位置";//调查站台位置
	public static final String TWeekDayIF = "是否工作日";//是否工作日
	public static final String TTravelTime = "出行时间段";//出行时间段
	public static final String TSurveyType = "调查类型";//走行类型
	
	public static final String TSex = "乘客性别";//乘客性别
	public static final String TAge = "年龄";//乘客年龄
	public static final String TBagageType = "行李类型";//行李类型
	public static final String TGotoPlatformLoc = "进站闸机位置";
	public static final String TTransferLoc = "换乘下车位置";
	
	public static final String TGotoPlatformTime = "进站刷卡时刻";//进站刷卡时刻
	public static final String TArrivePlatformTime = "到达站台时刻";//到达站台时刻
	public static final String TGetoffTransferTime = "换乘下车时刻";//换乘下车时刻
	
	public static final String TOrignLineNum = "起始排队人数";
	public static final String TTrainStartNO1 ="第一班车开车时刻";
	public static final String TGetonNumNO1 ="上车人数1";
	public static final String TGetoffNumNO1 ="下车人数1";
	public static final String TTrainStartNO2 ="第二班车开车时刻";
	public static final String TGetonNumNO2 ="上车人数2";
	public static final String TGetoffNumNO2 ="下车人数2";
	public static final String TTrainStartNO3 ="第三班车开车时刻";
	public static final String TGetonNumNO3 ="上车人数3";
	public static final String TGetoffNumNO3 ="下车人数3";
	public static final String TTrainStartNO4 ="第四班车开车时刻";
	public static final String TGetonNumNO4 ="上车人数4";
	public static final String TGetoffNumNO4 ="下车人数4";
	public static final String TTrainStartNO5 ="第五班车开车时刻";
	public static final String TGetonNumNO5 ="上车人数5";
	public static final String TGetoffNumNO5 ="下车人数5";
	public static final String TTrainStartNO6 ="第六班车开车时刻";
	public static final String TGetonNumNO6 ="上车人数6";
	public static final String TGetoffNumNO6 ="下车人数6";
	
	//建表 _ID为主键
		public static final String SQL_CREATE_TABLE_TSTAYSURVEYINFO = "create table if not exists "
				+StaySurveyEntity.TABLE_NAME_TSTAYSURVEYINFO+" (" + _ID + " INTEGER PRIMARY KEY"+AppConfig.COMMA_SEP
				+StaySurveyEntity.TName+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TDate+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TStation+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TCarriageLoc+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TPlatformLoc+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TWeekDayIF+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TTravelTime+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TSurveyType+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				
				+StaySurveyEntity.TSex+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TAge+ AppConfig.TYPE_INTEGER + AppConfig.COMMA_SEP
				+StaySurveyEntity.TBagageType+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGotoPlatformLoc+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TTransferLoc+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				
				+StaySurveyEntity.TGotoPlatformTime+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetoffTransferTime+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TArrivePlatformTime+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				
				
				+StaySurveyEntity.TOrignLineNum+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TTrainStartNO1+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetonNumNO1+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetoffNumNO1+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TTrainStartNO2+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetonNumNO2+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetoffNumNO2+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TTrainStartNO3+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetonNumNO3+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetoffNumNO3+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TTrainStartNO4+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetonNumNO4+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetoffNumNO4+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TTrainStartNO5+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetonNumNO5+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetoffNumNO5+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TTrainStartNO6+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetonNumNO6+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
				+StaySurveyEntity.TGetoffNumNO6+ AppConfig.TYPE_TEXT 
				+ ");";
		
	
	
		public String getRowID() {
			return rowID;
		}
		public void setRowID(String rowID) {
			this.rowID = rowID;
		}
		
		public String getCarriageLoc() {
			return carriageLoc;
		}
		public void setCarriageLoc(String carriageLoc) {
			this.carriageLoc = carriageLoc;
		}
		public String getSurveyLoc() {
			return surveyLoc;
		}
		public void setSurveyLoc(String surveyLoc) {
			this.surveyLoc = surveyLoc;
		}
		public String getGoinLoc() {
			return goinLoc;
		}
		public void setGoinLoc(String goinLoc) {
			this.goinLoc = goinLoc;
		}
		public String getGetoffLoc() {
			return getoffLoc;
		}
		public void setGetoffLoc(String getoffLoc) {
			this.getoffLoc = getoffLoc;
		}
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
	public int getCountStop() {
		return countStop;
	}
	public void setCountStop(int countStop) {
		this.countStop = countStop;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getSubNo() {
		return subNo;
	}
	public void setSubNo(String subNo) {
		this.subNo = subNo;
	}
	public String getSubDire() {
		return subDire;
	}
	public void setSubDire(String subDire) {
		this.subDire = subDire;
	}
	public String getGoinLineNo() {
		return goinLineNo;
	}
	public void setGoinLineNo(String goinLineNo) {
		this.goinLineNo = goinLineNo;
	}
	public String getGetOffLineNo() {
		return getOffLineNo;
	}
	public void setGetOffLineNo(String getOffLineNo) {
		this.getOffLineNo = getOffLineNo;
	}
	public String getGoinLineDire() {
		return goinLineDire;
	}
	public void setGoinLineDire(String goinLineDire) {
		this.goinLineDire = goinLineDire;
	}
	public String getGetoffLineDire() {
		return getoffLineDire;
	}
	public void setGetoffLineDire(String getoffLineDire) {
		this.getoffLineDire = getoffLineDire;
	}
	public String getGointoStationTime() {
		return gointoStationTime;
	}
	public void setGointoStationTime(String gointoStationTime) {
		this.gointoStationTime = gointoStationTime;
	}
	public String getGetoffTime() {
		return getoffTime;
	}
	public void setGetoffTime(String getoffTime) {
		this.getoffTime = getoffTime;
	}
	public String getArriveStationTime() {
		return arriveStationTime;
	}
	public void setArriveStationTime(String arriveStationTime) {
		this.arriveStationTime = arriveStationTime;
	}
	public String getTrainStartTime1() {
		return trainStartTime1;
	}
	public void setTrainStartTime1(String trainStartTime1) {
		this.trainStartTime1 = trainStartTime1;
	}
	public String getTrainStartTime2() {
		return trainStartTime2;
	}
	public void setTrainStartTime2(String trainStartTime2) {
		this.trainStartTime2 = trainStartTime2;
	}
	public String getTrainStartTime3() {
		return trainStartTime3;
	}
	public void setTrainStartTime3(String trainStartTime3) {
		this.trainStartTime3 = trainStartTime3;
	}
	public String getTrainStartTime4() {
		return trainStartTime4;
	}
	public void setTrainStartTime4(String trainStartTime4) {
		this.trainStartTime4 = trainStartTime4;
	}
	public String getTrainStartTime5() {
		return trainStartTime5;
	}
	public void setTrainStartTime5(String trainStartTime5) {
		this.trainStartTime5 = trainStartTime5;
	}
	public String getTrainStartTime6() {
		return trainStartTime6;
	}
	public void setTrainStartTime6(String trainStartTime6) {
		this.trainStartTime6 = trainStartTime6;
	}
	public String getStartLineNum() {
		return startLineNum;
	}
	public void setStartLineNum(String startLineNum) {
		this.startLineNum = startLineNum;
	}
	public String getGetOnNum1() {
		return getOnNum1;
	}
	public void setGetOnNum1(String getOnNum1) {
		this.getOnNum1 = getOnNum1;
	}
	public String getGetOnNum2() {
		return getOnNum2;
	}
	public void setGetOnNum2(String getOnNum2) {
		this.getOnNum2 = getOnNum2;
	}
	public String getGetOnNum3() {
		return getOnNum3;
	}
	public void setGetOnNum3(String getOnNum3) {
		this.getOnNum3 = getOnNum3;
	}
	public String getGetOnNum4() {
		return getOnNum4;
	}
	public void setGetOnNum4(String getOnNum4) {
		this.getOnNum4 = getOnNum4;
	}
	public String getGetOnNum5() {
		return getOnNum5;
	}
	public void setGetOnNum5(String getOnNum5) {
		this.getOnNum5 = getOnNum5;
	}
	public String getGetOnNum6() {
		return getOnNum6;
	}
	public void setGetOnNum6(String getOnNum6) {
		this.getOnNum6 = getOnNum6;
	}
	public String getGetOffNum1() {
		return getOffNum1;
	}
	public void setGetOffNum1(String getOffNum1) {
		this.getOffNum1 = getOffNum1;
	}
	public String getGetOffNum2() {
		return getOffNum2;
	}
	public void setGetOffNum2(String getOffNum2) {
		this.getOffNum2 = getOffNum2;
	}
	public String getGetOffNum3() {
		return getOffNum3;
	}
	public void setGetOffNum3(String getOffNum3) {
		this.getOffNum3 = getOffNum3;
	}
	public String getGetOffNum4() {
		return getOffNum4;
	}
	public void setGetOffNum4(String getOffNum4) {
		this.getOffNum4 = getOffNum4;
	}
	public String getGetOffNum5() {
		return getOffNum5;
	}
	public void setGetOffNum5(String getOffNum5) {
		this.getOffNum5 = getOffNum5;
	}
	public String getGetOffNum6() {
		return getOffNum6;
	}
	public void setGetOffNum6(String getOffNum6) {
		this.getOffNum6 = getOffNum6;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getCarriageNo() {
		return carriageNo;
	}
	public void setCarriageNo(String carriageNo) {
		this.carriageNo = carriageNo;
	}
	public String getWeekdayIf() {
		return weekdayIf;
	}
	public void setWeekdayIf(String weekdayIf) {
		this.weekdayIf = weekdayIf;
	}
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
	
	












}
