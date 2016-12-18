package com.example.subsurvey.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

public class FileUtils {

	//数据导出为csv
    public static void ExportToCSV(Cursor c, String fileName) {  
        
        int rowCount = 0;  
        int colCount = 0;  
        FileWriter fw;  
        BufferedWriter bfw;  
        File sdCardDir = new File(Environment.getExternalStorageDirectory()+"/客流调查") ; 
        if (!sdCardDir.exists()) {
        	sdCardDir.mkdirs();
        }
        File saveFile = new File(sdCardDir, fileName);  
        try {  
            rowCount = c.getCount();  
            colCount = c.getColumnCount();  
            fw = new FileWriter(saveFile);
            bfw = new BufferedWriter(fw);  
            if (rowCount > 0) {  
                c.moveToFirst();
                bfw.write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));
                // 写入表头  
                for (int i = 0; i < colCount; i++) {  
                    if (i != colCount - 1)  
                    	bfw.write(c.getColumnName(i) + ','); 
                    else  
                       bfw.write(c.getColumnName(i));  
                }  
                // 写好表头后换行  
                bfw.newLine(); 
                // 写入数据  
                for (int i = 0; i < rowCount; i++) {  
                    c.moveToPosition(i);  
                    Log.v("导出数据", "正在导出第" + (i + 1) + "条");  
                    for (int j = 0; j < colCount; j++) {  
                        if (j != colCount - 1)  {
                        	String temp =new String((c.getString(j) + ',').getBytes("utf-8"));
                        	bfw.write(temp);
//                          bfw.write(c.getString(j) + ',');  
                        }
                        else  
                           bfw.write(c.getString(j)+"");  //不加""的话，容易造成空指针错误，因为数据库有得项没有
                    }  
                    // 写好每条记录后换行  
                    bfw.newLine();  
                }  
            }  
            // 将缓存数据写入文件  
            bfw.flush();  
            // 释放缓存  
            bfw.close();  
            // Toast.makeText(mContext, "导出完毕！", Toast.LENGTH_SHORT).show();  
            Log.v("导出数据", "导出完毕！");  
        } catch (IOException e) {  
            e.printStackTrace();
        } finally {  
            c.close();  
        }  
    }

    /**
     *  复制单个文件
     *  @param  oldPath  String  原文件路径  如：c:/fqf.txt
     *  @param  newPath  String  复制后路径  如：f:/fqf.txt
     *  @return  boolean
     */
    static public  void  copyFile(String  oldPath,  String  newPath)  {
        try  {
            int  byteread  =  0;
            File  oldfile  =  new  File(oldPath);
            if  (oldfile.exists())  {  //文件存在时
                InputStream inStream  =  new FileInputStream(oldPath);  //读入原文件
                FileOutputStream fs  =  new  FileOutputStream(newPath);
                byte[]  buffer  =  new  byte[1444];
                while  (  (byteread  =  inStream.read(buffer))  !=  -1)  {
                    fs.write(buffer,  0,  byteread);
                }
                inStream.close();
            }
        }
        catch  (Exception  e)  {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    /**
     * 将InputStream转换为File
     * @param ins
     * @param file
     */
    public static void ConcertInputStreamToFile(InputStream ins,File file){
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
