package com.eddy.game.util;

import android.content.Context;
import android.util.Log;

import java.io.File;

/**
 * 需要有su權限
 * Created by eddyteng on 2016/3/30.
 */
public class SuperUser {

    public static interface OnErrorHandler {
        void OnException(Exception ex);
        void OnMessage(String message);
    }

    private Context context;
    private String filesDir;
    /**
     * Target App
     */
    private String packageName;
    private String app_prefs_path;
    private String app_files_path;

    public SuperUser(Context context, String packageName) {
        this.context = context;
        this.filesDir = context.getFilesDir().getAbsolutePath()+"/";
        this.packageName = packageName;
        this.app_prefs_path = "/data/data/"+packageName+"/shared_prefs/";
        this.app_files_path = "/data/data/"+packageName+"/files/";
    }

    public boolean copySharedPrefFile(String filename, OnErrorHandler handler) {
        try {
            if (new File(filesDir+filename).exists()) {
                return true;
            }
            String copyCmd = "cp "+ app_prefs_path +filename+" "+filesDir;
            Process p = Runtime.getRuntime().exec("su -c "+copyCmd);
            p.waitFor();
            String chmodCmd = "chmod 666 "+filesDir+filename;
            p = Runtime.getRuntime().exec("su -c "+chmodCmd);
            p.waitFor();
            return true;
        } catch (Exception ex) {
            if (handler != null) handler.OnException(ex);
        }
        return false;
    }

    public void restoreSharedPrefFile(String filename, OnErrorHandler handler) {
        try {
            if (!new File(filesDir+filename).exists()) {
                if (handler != null) handler.OnMessage("File not found: "+filename);
                return;
            }
            String copyCmd = "cp "+filesDir+filename+" "+ app_prefs_path;
            Process p = Runtime.getRuntime().exec("su -c "+ copyCmd);
            p.waitFor();
            if (handler != null) handler.OnMessage("Restore File Done with exit " + p.exitValue());
        } catch (Exception ex) {
            if (handler != null) handler.OnException(ex);
        }
    }

    public void clearFile(String pattern, OnErrorHandler handler) {
        try {
            String rmCmd = "rm "+ filesDir + pattern;
            Process p = Runtime.getRuntime().exec("su -c " + rmCmd);
            p.waitFor();
            if (handler != null) handler.OnMessage("Clear File Done with exit "+p.exitValue());
        } catch (Exception ex) {
            if (handler != null) handler.OnException(ex);
        }
    }
}
