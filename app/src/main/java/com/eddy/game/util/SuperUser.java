package com.eddy.game.util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * 需要有su權限
 * Created by eddyteng on 2016/3/30.
 */
public class SuperUser {

    public final static String sdcardPath = "/sdcard/data/com.eddy.game";
    //public final static String sdcardPath = "/storage/sdcard1/data/com.eddy.game";

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
    private String app_data_path;
    private String app_prefs_path;
    private String app_files_path;

    public SuperUser(Context context, String packageName) {
        this.context = context;
        this.filesDir = context.getFilesDir().getAbsolutePath()+"/";
        this.packageName = packageName;
        this.app_data_path = "/data/data/"+packageName;
        this.app_prefs_path = app_data_path+"/shared_prefs/";
        this.app_files_path = app_data_path+"/files/";
    }

    public boolean copyFiles(String fromDir, String intoDir, String files, boolean delete, OnErrorHandler handler) {
        try {
            String FROM = app_data_path + "/" + fromDir;
            String INTO = sdcardPath + "/" + intoDir;
            exec("cp -f "+FROM+"/"+files+" "+INTO);
            exec("rm -f "+FROM+"/"+files);
            return true;
        } catch (Exception ex) {
            if (handler != null) handler.OnException(ex);
        }
        return false;
    }

    public boolean copySharedPrefFile(String filename, OnErrorHandler handler) {
        try {
            if (new File(filesDir+filename).exists()) {
                return true;
            }
            exec("cp -f "+ app_prefs_path +filename+" "+filesDir);
            exec("chmod 666 "+filesDir+filename);
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
            int ret = exec("cp -f "+filesDir+filename+" "+ app_prefs_path);
            if (handler != null) handler.OnMessage("Restore File Done with exit " + ret);
        } catch (Exception ex) {
            if (handler != null) handler.OnException(ex);
        }
    }

    public void clearFile(String pattern, OnErrorHandler handler) {
        try {
            int ret = exec("rm -f "+ filesDir + pattern);
            if (handler != null) handler.OnMessage("Clear File Done with exit "+ret);
        } catch (Exception ex) {
            if (handler != null) handler.OnException(ex);
        }
    }

    private int exec(String cmd) throws Exception {
        Process p = Runtime.getRuntime().exec("su -c "+cmd);
        p.waitFor();
        return p.exitValue();
    }
}
