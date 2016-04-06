package com.eddy.kpetgirls1.net;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

public class HttpTask extends AsyncTask<HttpTaskObject, Integer, String>
{
  public static final int FAILED = 0;
  public static final int SUCCESSED = 1;
  public static final int TIME_OUT = 10000;
  private int RESPONE_RESULT = 0;
  private Handler completeHandler;

  // ERROR //
  protected String doInBackground(HttpTaskObject[] paramArrayOfHttpTaskObject)
  {
    // Byte code:
    //   0: getstatic 38	com/rooex/DebugConfig:HTTP	Z
    //   3: ifeq +30 -> 33
    //   6: ldc 40
    //   8: new 42	java/lang/StringBuilder
    //   11: dup
    //   12: ldc 44
    //   14: invokespecial 47	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   17: aload_1
    //   18: iconst_0
    //   19: aaload
    //   20: invokevirtual 53	com/rooex/net/HttpTaskObject:getUrl	()Ljava/lang/String;
    //   23: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: invokestatic 66	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   32: pop
    //   33: new 68	org/apache/http/impl/client/DefaultHttpClient
    //   36: dup
    //   37: invokespecial 69	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   40: astore_2
    //   41: ldc 71
    //   43: astore_3
    //   44: aload_2
    //   45: invokeinterface 77 1 0
    //   50: astore 7
    //   52: aload 7
    //   54: sipush 10000
    //   57: invokestatic 83	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   60: aload 7
    //   62: sipush 10000
    //   65: invokestatic 86	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   68: new 88	org/apache/http/client/methods/HttpPost
    //   71: dup
    //   72: aload_1
    //   73: iconst_0
    //   74: aaload
    //   75: invokevirtual 53	com/rooex/net/HttpTaskObject:getUrl	()Ljava/lang/String;
    //   78: invokespecial 89	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   81: astore 8
    //   83: aload 8
    //   85: new 91	org/apache/http/client/entity/UrlEncodedFormEntity
    //   88: dup
    //   89: aload_1
    //   90: iconst_0
    //   91: aaload
    //   92: invokevirtual 95	com/rooex/net/HttpTaskObject:getData	()Ljava/util/ArrayList;
    //   95: ldc 97
    //   97: invokespecial 100	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   100: invokevirtual 104	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   103: aload_2
    //   104: aload 8
    //   106: invokeinterface 108 2 0
    //   111: invokeinterface 114 1 0
    //   116: invokeinterface 120 1 0
    //   121: astore 9
    //   123: new 122	java/io/BufferedReader
    //   126: dup
    //   127: new 124	java/io/InputStreamReader
    //   130: dup
    //   131: aload 9
    //   133: ldc 97
    //   135: invokespecial 127	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   138: bipush 8
    //   140: invokespecial 130	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   143: astore 10
    //   145: new 42	java/lang/StringBuilder
    //   148: dup
    //   149: invokespecial 131	java/lang/StringBuilder:<init>	()V
    //   152: astore 11
    //   154: aload 10
    //   156: invokevirtual 134	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   159: astore 12
    //   161: aload 12
    //   163: ifnonnull +32 -> 195
    //   166: aload 9
    //   168: invokevirtual 139	java/io/InputStream:close	()V
    //   171: aload 11
    //   173: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   176: astore_3
    //   177: aload_0
    //   178: iconst_1
    //   179: putfield 21	com/rooex/net/HttpTask:RESPONE_RESULT	I
    //   182: aload_2
    //   183: invokeinterface 143 1 0
    //   188: invokeinterface 148 1 0
    //   193: aload_3
    //   194: areturn
    //   195: aload 11
    //   197: aload 12
    //   199: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: ldc 150
    //   204: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: pop
    //   208: goto -54 -> 154
    //   211: astore 6
    //   213: aload 6
    //   215: invokevirtual 153	java/io/IOException:printStackTrace	()V
    //   218: aload_2
    //   219: invokeinterface 143 1 0
    //   224: invokeinterface 148 1 0
    //   229: goto -36 -> 193
    //   232: astore 5
    //   234: aload 5
    //   236: invokevirtual 154	java/lang/Exception:printStackTrace	()V
    //   239: aload_2
    //   240: invokeinterface 143 1 0
    //   245: invokeinterface 148 1 0
    //   250: goto -57 -> 193
    //   253: astore 4
    //   255: aload_2
    //   256: invokeinterface 143 1 0
    //   261: invokeinterface 148 1 0
    //   266: aload 4
    //   268: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   44	182	211	java/io/IOException
    //   195	208	211	java/io/IOException
    //   44	182	232	java/lang/Exception
    //   195	208	232	java/lang/Exception
    //   44	182	253	finally
    //   195	208	253	finally
    //   213	218	253	finally
    //   234	239	253	finally
    return "";
  }

  protected void onPostExecute(String paramString)
  {
    Message localMessage = new Message();
    localMessage.arg1 = this.RESPONE_RESULT;
    localMessage.obj = paramString;
    this.completeHandler.sendMessage(localMessage);
  }

  public void setCompleteHandler(Handler paramHandler)
  {
    this.completeHandler = paramHandler;
  }
}

/* Location:           D:\GPhoneFans.net\Cracked\classes_dex2jar.jar
 * Qualified Name:     com.rooex.net.HttpTask
 * JD-Core Version:    0.5.4
 */