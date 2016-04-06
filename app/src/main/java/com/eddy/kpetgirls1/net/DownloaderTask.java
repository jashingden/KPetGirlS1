package com.eddy.kpetgirls1.net;

import android.content.Context;
import android.util.Log;
import com.c9entertainment.pet.s1.config.DomainConfig;
import com.c9entertainment.pet.s1.crypto.Crypto;
import com.eddy.game.R;
import com.eddy.kpetgirls1.KPetGirlS1Activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;

public class DownloaderTask implements Runnable//AsyncTask<String, Integer, Boolean>
{
//  private Handler progressHandler;
//  private boolean stop = false;

  public static void askDownload(Context paramContext, String[] movie) {
    new Thread(new DownloaderTask(paramContext, movie)).start();
  }

  private Context paramContext;
  private String paramFileDir;
  private String[] paramFilename;

  public DownloaderTask(Context paramContext, String[] paramFilename) {
    this.paramContext = paramContext;
    this.paramFilename = paramFilename;
    this.paramFileDir = paramContext.getString(R.string.movie_root_path) + paramContext.getString(R.string.movie_app_path) + paramContext.getString(R.string.movie_ver_path);
  }

  @Override
  public void run() {
    for (String filename : paramFilename) {
      String movie = filename + ".mp4.rox";
      File paramFile = new File(paramFileDir + movie);
      int server = 1;

      String message = "";
//      while (true) {
        String paramString = DomainConfig.DOWNLOAD_DOMAIN(server) + movie;

        if (download(paramString, paramFile)) {
          if (openMovie(filename)) {
            message = " Done";
//            break;
          } else {
//            if (server<2) {
//              server++;
//              continue;
//            } else {
              message = " Decrypt Fail";
//              break;
//            }
          }
        } else {
//          if (server<2) {
//            server++;
//            continue;
//          } else {
            message = " Download Fail";
//            break;
//          }
        }
//      }
      ((KPetGirlS1Activity) paramContext).updateResult(filename + message);
    }
  }

  private boolean download(String paramString, File paramFile)
  {
    try
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
      Log.v("ROOEX", "download : " + paramString);
      localHttpURLConnection.setConnectTimeout(600000);
      localHttpURLConnection.setUseCaches(false);
//      Log.v("ROOEX", "conn : " + localHttpURLConnection.getResponseCode());
      FileOutputStream localFileOutputStream=null;
      InputStream localInputStream=null;
      byte[] arrayOfByte=null;
      if (localHttpURLConnection.getResponseCode() == 200)
      {
        localFileOutputStream = new FileOutputStream(paramFile);
        localInputStream = localHttpURLConnection.getInputStream();
        arrayOfByte = new byte[1024];
      }
      boolean bool=false;
      do
      {
        int i = localInputStream.read(arrayOfByte);
        if (i <= 0)
        {
          localFileOutputStream.close();
          localHttpURLConnection.disconnect();
          return true;
        }
        localFileOutputStream.write(arrayOfByte, 0, i);
        Integer[] arrayOfInteger = new Integer[1];
        arrayOfInteger[0] = Integer.valueOf(i);
//        publishProgress(arrayOfInteger);
//        bool = this.stop;
      }
      while (!bool);
    }
    catch (Exception localException)
    {
//      Log.e("ROOEX", localException.getMessage());
    }
    return false;
  }

  private boolean openMovie(String paramString)
  {
    String str = paramContext.getString(R.string.movie_root_path) + paramContext.getString(R.string.movie_app_path) + paramContext.getString(R.string.movie_ver_path);
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(str + paramString + ".mp4.rox");
      FileOutputStream localFileOutputStream = paramContext.openFileOutput(paramString + ".mp4", 1);
      byte[] arrayOfByte = new byte[1024];
      while (true) {
        int i = localFileInputStream.read(arrayOfByte);
        if (i <= 0) {
          localFileInputStream.close();
          localFileOutputStream.close();
          return true;
        }
        arrayOfByte = Crypto.decrypt(ByteBuffer.wrap(arrayOfByte), i);
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return false;
  }

/*
  protected Boolean doInBackground(String[] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    int j = 1;
    if (j >= i)
      label5: if (!this.stop)
        break label94;
    for (Boolean localBoolean = Boolean.valueOf(false); ; localBoolean = Boolean.valueOf(true))
    {
      return localBoolean;
      download(DomainConfig.DOWNLOAD_DOMAIN() + paramArrayOfString[j], new File(paramArrayOfString[0] + paramArrayOfString[j]));
      if (!this.stop);
      ++j;
      label94: break label5:
    } return true;
  }

  protected void onPostExecute(Boolean paramBoolean)
  {
    Message localMessage = new Message();
    if (paramBoolean.booleanValue());
    for (int i = 1; ; i = 0)
    {
      localMessage.what = i;
      this.progressHandler.sendMessage(localMessage);
      return;
    }
  }

  protected void onProgressUpdate(Integer[] paramArrayOfInteger)
  {
    super.onProgressUpdate(paramArrayOfInteger);
    Message localMessage = new Message();
    localMessage.what = 0;
    localMessage.arg1 = paramArrayOfInteger[0].intValue();
    this.progressHandler.sendMessage(localMessage);
  }

  public void setProgressHandler(Handler paramHandler)
  {
    this.progressHandler = paramHandler;
  }

  public void stop()
  {
    this.stop = true;
  }
*/
}

/* Location:           D:\Android\Tools\APK-Multi-Toolv1.0.11\projects\com.c9entertainment.pet.s1.apk\classes_dex2jar.jar
 * Qualified Name:     com.c9entertainment.pet.s1.util.DownloaderTask
 * JD-Core Version:    0.5.4
 */