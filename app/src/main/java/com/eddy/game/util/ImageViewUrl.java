package com.eddy.game.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eddyteng on 2016/4/7.
 */
public class ImageViewUrl extends ImageView implements Runnable {

    private String mUrl;
    private Bitmap mBitmap;
    private BitmapFactory.Options mBitmapOptions;
    private File mFile;

    public ImageViewUrl(Context context) {
        super(context);
    }

    public ImageViewUrl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewUrl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBitmapOptions(BitmapFactory.Options options) {
        mBitmapOptions = options;
    }

    public void load(String url) {
        load(url, null);
    }

    public void load(String url, String fileDir) {
        mUrl = url;
        if (!TextUtils.isEmpty(fileDir)) {
            String filename = url.substring(url.lastIndexOf("/")+1, url.length());
            mFile = new File(fileDir+"/"+filename);
            if (mFile.exists()) {
                mBitmap = BitmapFactory.decodeFile(mFile.getAbsolutePath());
                this.setImageBitmap(mBitmap);
                return;
            }
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (download() && mBitmap != null) {
            this.post(new Runnable() {
                @Override
                public void run() {
                    ImageViewUrl.this.setImageBitmap(mBitmap);
                }
            });
        }
    }

    private boolean download() {
        try {
            HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(mUrl).openConnection();
            localHttpURLConnection.setConnectTimeout(600000);
            localHttpURLConnection.setUseCaches(false);
            FileOutputStream localFileOutputStream = (mFile == null) ? null : new FileOutputStream(mFile);
            if (localHttpURLConnection.getResponseCode() != 200) {
                return false;
            }
            InputStream localInputStream = localHttpURLConnection.getInputStream();
            byte[] arrayOfByte = new byte[1024];
            byte[] arrayOfByte2 = (mFile != null) ? null : new byte[localHttpURLConnection.getContentLength()];
            int j = 0;
            boolean bool = false;
            do {
                int i = localInputStream.read(arrayOfByte);
                if (i <= 0) {
                    if (localFileOutputStream != null) {
                        localFileOutputStream.close();
                        mBitmap = BitmapFactory.decodeFile(mFile.getAbsolutePath(), mBitmapOptions);
                    } else if (arrayOfByte2 != null) {
                        mBitmap = BitmapFactory.decodeByteArray(arrayOfByte2, 0, arrayOfByte2.length, mBitmapOptions);
                    }
                    localHttpURLConnection.disconnect();
                    return true;
                }
                if (localFileOutputStream != null) {
                    localFileOutputStream.write(arrayOfByte, 0, i);
                } else if (arrayOfByte2 != null) {
                    System.arraycopy(arrayOfByte, 0, arrayOfByte2, j, i);
                    j += i;
                }
            }
            while (!bool);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
