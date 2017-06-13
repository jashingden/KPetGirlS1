package com.eddy.game.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.eddy.game.MovieActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eddyteng on 2016/4/7.
 */
public class OpenMovie implements Runnable {

    private Activity mActivity;
    private String mUrl;
    private File mFile;

    public OpenMovie(Activity activity) {
        mActivity = activity;
    }

    public void start(String url, String fileDir) {
        mUrl = url;
        if (!TextUtils.isEmpty(fileDir)) {
            String filename = url.substring(url.lastIndexOf("/")+1, url.length());
            mFile = new File(fileDir+"/"+filename);
            if (mFile.exists()) {
                open();
                return;
            }
        }
        new Thread(this).start();
        Toast.makeText(mActivity, "影片下載中...", Toast.LENGTH_SHORT).show();
    }

    private void open() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mActivity, MovieActivity.class);
//                Intent intent = new Intent(Intent.ACTION_VIEW);
                String filePath = mFile.getAbsolutePath();
                Uri uri = Uri.parse(filePath);
                if (filePath.indexOf(".mp4") >= 0) {
                    intent.setDataAndType(uri, "video/mp4");
                } else if (filePath.indexOf("jpg") >= 0) {
                    intent.setDataAndType(uri, "video/jpeg");
                } else {
                    intent.setData(uri);
                }
                intent.putExtra("orientation", "land");
                try {
                    mActivity.startActivity(intent);
                } catch (Exception ex) {
                    mActivity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }

            }
        });
    }

    @Override
    public void run() {
        if (download()) {
            open();
        }
    }

    private boolean download() {
        try {
            HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(mUrl).openConnection();
            localHttpURLConnection.setConnectTimeout(600000);
            localHttpURLConnection.setUseCaches(false);
            FileOutputStream localFileOutputStream = new FileOutputStream(mFile);
            if (localHttpURLConnection.getResponseCode() != 200) {
                return false;
            }
            InputStream localInputStream = localHttpURLConnection.getInputStream();
            byte[] arrayOfByte = new byte[1024];
            boolean bool = false;
            do {
                int i = localInputStream.read(arrayOfByte);
                if (i <= 0) {
                    localFileOutputStream.close();
                    localHttpURLConnection.disconnect();
                    return true;
                }
                localFileOutputStream.write(arrayOfByte, 0, i);
            }
            while (!bool);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
