package com.eddy.kpetgirls1.net;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;
import com.c9entertainment.pet.s1.crypto.Crypto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

public class MovieUtil
{
/*
  public static void deleteLastFile(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    XmlResourceParser localXmlResourceParser = paramContext.getResources().getXml(2131034130);
    while (true)
    {
      label32: int j;
      try
      {
        int k = localXmlResourceParser.getEventType();
        if (k == 1)
        {
          int i = localArrayList.size();
          j = 0;
          label41: if (j < i)
            break label116;
          return;
        }
        if (localXmlResourceParser.getEventType() != 2)
          break label106;
        if (localXmlResourceParser.getName().equals("name"))
          localArrayList.add(localXmlResourceParser.nextText());
        label84: label106: label116: localXmlResourceParser.next();
      }
      catch (Throwable localThrowable)
      {
        Log.e("ROOEX", "ERROR");
        break label32:
        localXmlResourceParser.getEventType();
        break label84:
        paramContext.deleteFile((String)localArrayList.get(j) + ".mp4");
        ++j;
        break label41:
      }
    }
  }
*/
  public static String getSavePath(String paramString, Context paramContext)
  {
    return paramContext.getFilesDir().getAbsolutePath() + "/" + paramString + ".mp4";
  }
/*
  public static String openMovie(String paramString, Context paramContext)
  {
    String str = paramContext.getString(2131165193) + paramContext.getString(2131165194) + paramContext.getString(2131165195);
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(str + paramString + ".mp4.rox");
      FileOutputStream localFileOutputStream = paramContext.openFileOutput(paramString + ".mp4", 1);
      byte[] arrayOfByte = new byte[1024];
      int i = localFileInputStream.read(arrayOfByte);
      if (i <= 0)
      {
        localFileInputStream.close();
        localFileOutputStream.close();
        label124: return getSavePath(paramString, paramContext);
      }
      arrayOfByte = Crypto.decrypt(ByteBuffer.wrap(arrayOfByte));
      localFileOutputStream.write(arrayOfByte, 0, i);
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      break label124:
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      break label124:
    }
  }
*/
}

/* Location:           D:\Android\Tools\APK-Multi-Toolv1.0.11\projects\com.c9entertainment.pet.s1.apk\classes_dex2jar.jar
 * Qualified Name:     com.c9entertainment.pet.s1.util.MovieUtil
 * JD-Core Version:    0.5.4
 */