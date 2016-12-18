package com.example.subsurvey;

import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.InjectView;

public class AppConfig {
	//调查类型
	public static final int WALK_SURVEY = 1;
	public static final int STAY_SURVEY = 2;
	public static final int TRANSFER_SURVEY = 3;
	public static final int OD_SURVEY = 4;
	public static final int REVERSE_SURVEY = 5;


	// 数据库相关配置
	public static final String TYPE_TEXT = " TEXT";
	public static final String TYPE_INTEGER = " INTEGER";
	public static final String DATABASE_NAME = "subsurvey.db";
	public static final int DATABASE_VERSION = 8;
	public static final String
			COMMA_SEP = ",";
	public static final String[] pathTypeInfo = {"单路径","换乘一次","换乘两次"};
	public static final String[] wsTimeInfo = {"早高峰","平峰","晚高峰"};
	//	public static final String[] wsTypeInfo = {"进站","出站","换乘"};
	public static final String[] wsTypeInfo = {"进站-出站","换乘"};
//	public static final String[][] wsTodoHints={{"进站刷卡时刻","到达站台时刻","列车开门时刻"},
//											{"列车开门时刻","出站刷卡时刻"},{"列车开门时刻","到达站台时刻","出站刷卡时刻"}};

	public static final String[][] wsTodoHints={{"进站刷卡时刻","到达站台时刻","列车开门时刻","列车开门时刻","出站刷卡时刻"},{"列车开门时刻","到达站台时刻","列车开门时刻"}};
	public static final String[] odTypeInfo = {"无换乘","换乘一次","换乘两次"};
	public static final String[] odToHints = {"到达站台时刻","列车开行时刻","下车时刻"};
	public static final String[] ssTypeInfo = {"进站","换乘"};
	public static final String[][] ssTodoHints = {{"进站刷卡时刻","到达站台时刻","列车开行时刻",""},{"换乘下车时刻","到达站台时刻","列车开行时刻",""}};
	public static final String[] tsTimeInfo = {"早高峰","晚高峰","平峰"};

	public static final int NAME_CODE = 1;
	public static final int DATE_CODE = 2;
	public static final int STATION_CODE = 3;
	public static final int LINE_CODE = 4;
	public static final int TS_LOC_CODE = 5;
	public static final int WS_GATE_LOC_CODE = 6;
	public static final int WS_GATE_LINE_CODE = 7;
	public static final int WS_OFF_DIRE_CODE = 8;
	public static final int WS_OFF_LINE_CODE = 9;
	public static final int WS_ON_DIRE_CODE = 10;
	public static final int WS_ON_LINE_CODE = 11;
	public static final int WS_PER_OPENDOOR1_CODE = 12;
	public static final int WS_PER_GOTOPLAT_CODE = 13;
	public static final int WS_PER_ARRIVEPLAT_CODE = 14;
	public static final int WS_PER_OPENDOOR2_CODE = 15;
	public static final int WS_PER_GOOUTPLAT_CODE = 16;
	//走行：进站-出站路线
	public static final int WS_INOUT_LINE_START_GLOC_CODE = 17;
	public static final int WS_INOUT_LINE_GLINE_CODE = 18;
	public static final int WS_INOUT_LINE_END_GLOC_CODE = 19;
	public static final int WS_INOUT_LINE_DIR_STOE_CODE = 20;
	public static final int WS_INOUT_LINE_TRALINE_CODE = 21;
	public static final int WS_INOUT_LINE_DIR_ETOS_CODE = 22;
	//换乘路线
	public static final int WS_TRANS_LINE_OFF_DIRE_CODE = 23;
	public static final int WS_TRANS_LINE_OFF_LINE_CODE = 24;
	public static final int WS_TRANS_LINE_ON_DIRE_CODE = 25;
	public static final int WS_TRANS_LINE_ON_LINE_CODE = 26;

	public static final int WS_TRANS_LINE_START_LINE_CODE = 78;
    public static final int WS_TRANS_LINE_START_LINE_START_DIRE_CODE = 79;
    public static final int WS_TRANS_LINE_START_LINE_END_DIRE_CODE = 80;
    public static final int WS_TRANS_LINE_END_LINE_CODE = 81;
    public static final int WS_TRANS_LINE_END_LINE_START_DIRE_CODE = 82;
    public static final int WS_TRANS_LINE_END_LINE_END_DIRE_CODE = 83;


	//OD 设置
	public static final int OD_CARD_NUM_CODE = 27;
	public static final int OD_ID_NUM_CODE = 28;
	public static final int OD_DISTANCE_CODE = 29;
	public static final int OD_STATION_COUNT_CODE = 49;
	public static final int OD_TIME_PERIOD_CODE = 50;
	//OD 路线设置
	public static final int OD_LINE_DIRE1_CODE = 30;
	public static final int OD_LINE_OFF_STATION1_CODE = 31;
	public static final int OD_LINE_TRANS_LINE2_CODE = 32;
	public static final int OD_LINE_DIRE2_CODE = 33;
	public static final int OD_LINE_OFF_STATION2_CODE = 34;
	public static final int OD_LINE_TRANS_LINE3_CODE = 35;
	public static final int OD_LINE_DIRE3_CODE = 36;
	public static final int OD_LINE_OFF_STATION3_CODE = 37;
	//OD 时间修改设置
	public static final int OD_DATA_GEI_IN_TIME_CODE = 38;
	public static final int OD_DATA_ARRIVE_PLATFORM1_TIME_CODE = 39;
	public static final int OD_DATA_TRAIN_START_TIME1_CODE = 40;
	public static final int OD_DATA_GET_OFF_TIME1_CODE = 41;
	public static final int OD_DATA_ARRIVE_PLATFORM2_TIME_CODE = 42;
	public static final int OD_DATA_TRAIN_START_TIME2_CODE = 43;
	public static final int OD_DATA_GET_OFF_TIME2_CODE = 44;
	public static final int OD_DATA_ARRIVE_PLATFORM3_TIME_CODE = 45;
	public static final int OD_DATA_TRAIN_START_TIME3_CODE = 46;
	public static final int OD_DATA_GET_OFF_TIME3_CODE = 47;
	public static final int OD_DATA_GET_OUT_TIME_CODE = 48;
	//留乘设置
	public static final int SS_DATA_SETTING_CARRIAGE_LOC_CODE = 51;
	public static final int SS_LINE_FROM_LINE_CODE = 52;
	public static final int SS_LINE_FROM_LOC_CODE = 53;
	public static final int SS_LINE_TO_LINE_CODE = 54;
	public static final int SS_LINE_TO_LOC_CODE = 55;

	public static final int SS_DATA_GO_INTO_STATION_CODE = 56;
	public static final int SS_DATA_GET_OFF_CODE = 57;
	public static final int SS_DATA_ARRIVE_PLATFORM_CODE = 58;
	public static final int SS_DATA_ORIGN_LINE_NUM_CODE = 59;
	public static final int SS_DATA_GET_OFF_NUM1_CODE = 60;
	public static final int SS_DATA_GET_ON_NUM1_CODE = 61;
	public static final int SS_DATA_TRAIN_START_TIME1_CODE = 62;
	public static final int SS_DATA_GET_OFF_NUM2_CODE = 63;
	public static final int SS_DATA_GET_ON_NUM2_CODE = 64;
	public static final int SS_DATA_TRAIN_START_TIME2_CODE = 65;
	public static final int SS_DATA_GET_OFF_NUM3_CODE = 66;
	public static final int SS_DATA_GET_ON_NUM3_CODE = 67;
	public static final int SS_DATA_TRAIN_START_TIME3_CODE = 68;
	public static final int SS_DATA_GET_OFF_NUM4_CODE = 69;
	public static final int SS_DATA_GET_ON_NUM4_CODE = 70;
	public static final int SS_DATA_TRAIN_START_TIME4_CODE = 71;
	public static final int SS_DATA_GET_OFF_NUM5_CODE = 72;
	public static final int SS_DATA_GET_ON_NUM5_CODE = 73;
	public static final int SS_DATA_TRAIN_START_TIME5_CODE = 74;
	public static final int SS_DATA_GET_OFF_NUM6_CODE = 75;
	public static final int SS_DATA_GET_ON_NUM6_CODE = 76;
	public static final int SS_DATA_TRAIN_START_TIME6_CODE = 77;
	//反向乘车设置
	public static final int RS_LOC_CODE = 76;
	public static final int RS_DIRE_CODE = 77;

	//各调查路线类型设置
	public static final int WS_GETON_NO = 0;
	public static final int WS_GETOFF_NO = 1;
	public static final int WS_TRANSFER_NO = 2;
	public static final int WS_ON_TO_OFF_NO = 0;
	public static final int WS_TRANSF_NO = 1;
	public static final int OD_TRANSFER_NONE = 0;
	public static final int OD_TRANSFER_ONCE = 1;
	public static final int OD_TRANSFER_TWICE = 2;
	public static final int SS_GETON_NO = 0;
	public static final int SS_TRANSFER_NO = 1;

	//走行换乘撤销
	public static final int WS_TRANSFER_REVOKE_FORBID = -1;
	public static final int WS_TRANSFER_REVOKE_NORMAL = 0;
		//弹出到达站台前列车已开门的对话框选择"是"触发
	public static final int WS_TRANSFER_REVOKE_EQUAL = 1;
		//弹出到直接开始新的一组数据对话框选择"是"触发
	public static final int WS_TRANSFER_REVOKE_NEXT = 2;
	//走行进出站撤销
	public static final int WS_INOUT_REVOKE_FORBID = -1;
	public static final int WS_INOUT_REVOKE_NORMAL = 0;
		//弹出到达站台前列车已开门的对话框选择"是"触发
	public static final int WS_INOUT_REVOKE_EQUAL = 1;
		//弹出到直接开始新的一组数据对话框选择"是"触发
	public static final int WS_INOUT_REVOKE_NEXT = 2;
		//进行两小组数据后触发
	public static final int WS_INOUT_REVOKE_TWO = 3;
		//进行四小组(一大组)数据后触发
	public static final int WS_INOUT_REVOKE_FOUR = 4;
	//奇数组数组保存
	public static final int WS_INOUT_REVOKE_ARR_ODD = 1;
	//偶数组数组保存
	public static final int WS_INOUT_REVOKE_ARR_EVEN = 2;
	//留乘调查撤销
	public static final int SS_REVOKE_FORBID = -1;
	public static final int SS_REVOKE_NORMAL = 0;
	public static final int SS_REVOKE_SUB = 1;





	public static final String USER_SESSION = "user_session.data"; // 用户缓存文件
	public static final String OD_TEMP_DATA = "od_temp.data";//OD调查最新一次调查临时缓存文件
}
