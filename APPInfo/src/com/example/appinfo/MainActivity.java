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
            String processName = appProcess.processName; // 进程名   
            Log.i(TAG, "processName: " + processName + "  pid: " + pid);  
  
            String[] pkgNameList = appProcess.pkgList; // 获得运行在该进程里的所有应用程序包   
  
            // 输出所有应用程序的包名   
            for (int i = 0; i < pkgNameList.length; i++) {  
                String pkgName = pkgNameList[i];  
                Log.i(TAG, "packageName " + pkgName + " at index " + i+ " in process " + pid);  
                // 加入至map对象里   
                pgkProcessAppMap.put(pkgName, appProcess);  
            }  
        }
        
        /*// 保存所有正在运行的应用程序信息   
        List<RunningAppInfo> runningAppInfos = new ArrayList<RunningAppInfo>();
        
        for (ApplicationInfo app : listAppcations) {  
            // 如果该包名存在 则构造一个RunningAppInfo对象   
            if (pgkProcessAppMap.containsKey(app.packageName)) {  
                // 获得该packageName的 pid 和 processName   
                int pid = pgkProcessAppMap.get(app.packageName).pid;  
                String processName = pgkProcessAppMap.get(app.packageName).processName;  
                runningAppInfos.add(getAppInfo(app, pid, processName));  
            }  
        }  */
    } 
     
    //正在运行的 
    /*public List<Programe> getRunningProcess(){ 
        PackagesInfo pi = new PackagesInfo(this); 
         
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE); 
        //获取正在运行的应用 
        List<RunningAppProcessInfo> run = am.getRunningAppProcesses(); 
        //获取包管理器，在这里主要通过包名获取程序的图标和程序名 
        PackageManager pm =this.getPackageManager(); 
        List<Programe> list = new ArrayList<Programe>();     
         
        for(RunningAppProcessInfo ra : run){ 
            //这里主要是过滤系统的应用和电话应用，当然你也可以把它注释掉。 
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
