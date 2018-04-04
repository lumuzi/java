package com.example.appinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActivityManager;
import android.app.ListActivity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends ListActivity { 
	private PackageManager pm;
	private static String TAG = "MainActivity";
    @Override 
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main); 
        
//        List<Programe> list = getRunningProcess(); 
//        ListAdapter adapter = new ListAdapter(list,this); 
//        getListView().setAdapter(adapter); 
        
        
        List<ApplicationInfo> listAppcations = 
        		pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        
        
        Map<String, ActivityManager.RunningAppProcessInfo> pgkProcessAppMap = 
        		new HashMap<String, ActivityManager.RunningAppProcessInfo>();
        
        ActivityManager mActivityManager = 
        		(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        
        List<ActivityManager.RunningAppProcessInfo> appProcessList = mActivityManager  
                .getRunningAppProcesses();
        
        
        
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcessList) {  
            int pid = appProcess.pid; // pid   
            String processName = appProcess.processName; // ������   
            Log.i(TAG, "processName: " + processName + "  pid: " + pid);  
  
            String[] pkgNameList = appProcess.pkgList; // ��������ڸý����������Ӧ�ó����   
  
            // �������Ӧ�ó���İ���   
            for (int i = 0; i < pkgNameList.length; i++) {  
                String pkgName = pkgNameList[i];  
                Log.i(TAG, "packageName " + pkgName + " at index " + i+ " in process " + pid);  
                // ������map������   
                pgkProcessAppMap.put(pkgName, appProcess);  
            }  
        }
        
        /*// ���������������е�Ӧ�ó�����Ϣ   
        List<RunningAppInfo> runningAppInfos = new ArrayList<RunningAppInfo>();
        
        for (ApplicationInfo app : listAppcations) {  
            // ����ð������� ����һ��RunningAppInfo����   
            if (pgkProcessAppMap.containsKey(app.packageName)) {  
                // ��ø�packageName�� pid �� processName   
                int pid = pgkProcessAppMap.get(app.packageName).pid;  
                String processName = pgkProcessAppMap.get(app.packageName).processName;  
                runningAppInfos.add(getAppInfo(app, pid, processName));  
            }  
        }  */
    } 
     
    //�������е� 
    /*public List<Programe> getRunningProcess(){ 
        PackagesInfo pi = new PackagesInfo(this); 
         
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE); 
        //��ȡ�������е�Ӧ�� 
        List<RunningAppProcessInfo> run = am.getRunningAppProcesses(); 
        //��ȡ������������������Ҫͨ��������ȡ�����ͼ��ͳ����� 
        PackageManager pm =this.getPackageManager(); 
        List<Programe> list = new ArrayList<Programe>();     
         
        for(RunningAppProcessInfo ra : run){ 
            //������Ҫ�ǹ���ϵͳ��Ӧ�ú͵绰Ӧ�ã���Ȼ��Ҳ���԰���ע�͵��� 
            if(ra.processName.equals("system") || ra.processName.equals("com.Android.phone")){ 
                continue; 
            } 
             
            Programe  pr = new Programe(); 
            pr.setIcon(pi.getInfo(ra.processName).loadIcon(pm)); 
            pr.setName(pi.getInfo(ra.processName).loadLabel(pm).toString()); 
            System.out.println(pi.getInfo(ra.processName).loadLabel(pm).toString()); 
            list.add(pr); 
        } 
        return list; 
    } */
     
} 
