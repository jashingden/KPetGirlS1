package com.eddy.game.util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 檔案工具類別 (原需要 su 權限，現已修改為一般檔案操作)
 * Created by eddyteng on 2016/3/30.
 */
public class SuperUser {

    public final static String sdcardPath = "/sdcard/data/com.eddy.game";

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
        this.filesDir = context.getFilesDir().getAbsolutePath() + "/";
        this.packageName = packageName;
        this.app_data_path = "/data/data/" + packageName;
        this.app_prefs_path = app_data_path + "/shared_prefs/";
        this.app_files_path = app_data_path + "/files/";
    }

    public boolean copyFiles(String fromDir, String intoDir, String files, boolean delete, OnErrorHandler handler) {
        try {
            File srcDir = new File(app_data_path, fromDir);
            File dstDir = new File(sdcardPath, intoDir);
            if (!dstDir.exists()) dstDir.mkdirs();

            File srcFile = new File(srcDir, files);
            File dstFile = new File(dstDir, files);

            if (copyFile(srcFile, dstFile, handler)) {
                if (delete) {
                    srcFile.delete();
                }
                return true;
            }
        } catch (Exception ex) {
            if (handler != null) handler.OnException(ex);
        }
        return false;
    }

    public boolean copySharedPrefFile(String filename, OnErrorHandler handler) {
        try {
            File dst = new File(filesDir, filename);
            if (dst.exists()) {
                return true;
            }
            File src = new File(app_prefs_path, filename);
            return copyFile(src, dst, handler);
        } catch (Exception ex) {
            if (handler != null) handler.OnException(ex);
        }
        return false;
    }

    public void restoreSharedPrefFile(String filename, OnErrorHandler handler) {
        try {
            File src = new File(filesDir, filename);
            if (!src.exists()) {
                if (handler != null) handler.OnMessage("File not found: " + filename);
                return;
            }
            File dst = new File(app_prefs_path, filename);
            if (copyFile(src, dst, handler)) {
                if (handler != null) handler.OnMessage("Restore File Done");
            }
        } catch (Exception ex) {
            if (handler != null) handler.OnException(ex);
        }
    }

    public void clearFile(String pattern, OnErrorHandler handler) {
        try {
            File dir = new File(filesDir);
            File[] files = dir.listFiles();
            if (files != null) {
                String regex = pattern.replace(".", "\\.").replace("*", ".*");
                for (File f : files) {
                    if (f.getName().matches(regex)) {
                        f.delete();
                    }
                }
            }
            if (handler != null) handler.OnMessage("Clear File Done");
        } catch (Exception ex) {
            if (handler != null) handler.OnException(ex);
        }
    }

    public boolean copyFromSdcard(String filename, OnErrorHandler handler) {
        try {
            File src = new File(sdcardPath, filename);
            File dst = new File(filesDir, filename);
            return copyFile(src, dst, handler);
        } catch (Exception ex) {
            if (handler != null) handler.OnException(ex);
        }
        return false;
    }

    private boolean copyFile(File src, File dst, OnErrorHandler handler) {
        InputStream in = null;
        OutputStream out = null;
        try {
            if (!src.exists()) {
                if (handler != null) handler.OnMessage("Source file not found: " + src.getAbsolutePath());
                return false;
            }
            in = new FileInputStream(src);
            out = new FileOutputStream(dst);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            return true;
        } catch (Exception ex) {
            if (handler != null) handler.OnException(ex);
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int exec(String cmd) {
        try {
            Process p = Runtime.getRuntime().exec("su -c " + cmd);
            p.waitFor();
            return p.exitValue();
        } catch (Exception ex) {
            Log.e("SuperUser", "exec failed: " + ex.getMessage());
            return -1;
        }
    }
}
