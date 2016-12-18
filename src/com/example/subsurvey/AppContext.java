package com.example.subsurvey;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.io.StreamCorruptedException;

import android.util.Log;

import com.example.subsurvey.base.BaseApplication;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;
import com.example.subsurvey.personalSetting.UserEntity;


public class AppContext extends BaseApplication{

	private static AppContext instance;
	private UserEntity user = new UserEntity();
	private ODSurveyEntity odTempData = new ODSurveyEntity();

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		init();
	}
	
	private void init() {
    }
	
	public static AppContext getInstance() {
        return instance;
    }
	
	private Object OLock = new Object();
	
	/**
	 * 保存对象
	 * @param ser
	 * @param file
	 * @throws IOException
	 */
	public boolean saveObject(Serializable ser,String file){
		synchronized (OLock) {
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;
			try{
				fos = openFileOutput(file, MODE_PRIVATE);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(ser);
				oos.flush();//清空缓存数据
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}finally{
				try {
					oos.close();
				} catch (Exception e) { }
				try {
					fos.close();
				} catch (Exception e) { }
			}
		}
	}
    
	/**
	 * 读取对象
	 */
	public Serializable readObject(String file) {
		synchronized(OLock){
			if(!isExistDataCache(file))
				return null;
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try{
				fis = openFileInput(file);
				ois = new ObjectInputStream(fis);
				Log.i("", "s");
			} catch (FileNotFoundException e){
				//null;
			} catch (Exception e) {
				e.printStackTrace();
				//方序列化失败，删除缓存
				if(e instanceof InvalidClassException) {
					File data = getFileStreamPath(file);
					data.delete();
				}
			} finally {
				try{
					ois.close();
				} catch (Exception e) {
				}
				try{
					fis.close();
				} catch (Exception e) {
				} 
			}
			return null;
		}
	}
	
	/**
	 * 判断缓存存在性
	 */
	public boolean isExistDataCache(String cacheFile){
		boolean exist = false;
		File data = getFileStreamPath(cacheFile);
		if(data.exists())
			exist = true;
		return exist;
	}
	
	/**
	 * 设置当前用户
	 * @param user
	 */
	public void setUser(UserEntity user){
		this.user = user;
	}
	
	public void saveUser(){
		if(user != null)
			saveObject(user, AppConfig.USER_SESSION);
	}
	
	public UserEntity getUser(){
		if(isExistDataCache(AppConfig.USER_SESSION)){
//			readObject封装方法不会改
//			user = (UserEntity)readObject(AppConfig.USER_SESSION);
			FileInputStream fis;
			try {
				fis = openFileInput(AppConfig.USER_SESSION);
				ObjectInputStream ois = new ObjectInputStream(fis);
				user = (UserEntity)ois.readObject();
			} catch (OptionalDataException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(user == null)
			user = new UserEntity();
		return user;
	}

	/**
	 * 获取最新一条OD调查数据，防止程序突然中断
	 * @return
     */
	public ODSurveyEntity getODTempData(){
		if(isExistDataCache(AppConfig.OD_TEMP_DATA)){
			FileInputStream fis;
			try {
				fis = openFileInput(AppConfig.OD_TEMP_DATA);
				ObjectInputStream ois = new ObjectInputStream(fis);
				odTempData = (ODSurveyEntity) ois.readObject();
			} catch (OptionalDataException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(odTempData == null)
			odTempData = new ODSurveyEntity();
		return odTempData;
	}

	public void setODTempData(ODSurveyEntity odTempData){
		this.odTempData = odTempData;
	}

	public void saveODTempData(){
		if(odTempData != null)
			saveObject(odTempData, AppConfig.OD_TEMP_DATA);
	}
	
} 
    
