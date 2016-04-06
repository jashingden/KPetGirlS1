package com.eddy.kpetgirls1.net;

import android.util.Log;

import java.util.ArrayList;
import org.apache.http.message.BasicNameValuePair;

public class RegisterTaskObject extends HttpTaskObject
{
  public static final String RESPONSE_GCM_REGID_YES = "y";
  public static final String RESPONSE_MSG_ALREADY = "already";
  public static final String RESPONSE_MSG_NEW = "new";
  public static final String RESPONSE_MSG_SYSTEM_ERROR = "system error";
  private final String android_id;
  private final String email;
  private final String imei;
  private final int user_idx;

  public RegisterTaskObject(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
  {
    super(paramString1, "/pet/api2/register.php");
    this.user_idx = paramInt;
    this.android_id = paramString2;
    this.imei = paramString3;
    this.email = paramString4;
    Log.d("ROOEX", "[user_idx] : " + paramInt + "[android_id] : " + paramString2 + " [imei] : " + paramString3 + " [email] : " + paramString4);
  }

  public ArrayList<BasicNameValuePair> getData()
  {
    ArrayList localArrayList = super.getData();
    localArrayList.add(new BasicNameValuePair("user_idx", String.valueOf(this.user_idx)));
    localArrayList.add(new BasicNameValuePair("android_id", this.android_id));
    localArrayList.add(new BasicNameValuePair("imei", this.imei));
    localArrayList.add(new BasicNameValuePair("email", this.email));
    localArrayList.add(new BasicNameValuePair("country", "0"));//DomainConfig.IME_CODE()));
    return localArrayList;
  }
}

/* Location:           D:\GPhoneFans.net\Cracked\classes_dex2jar.jar
 * Qualified Name:     com.c9entertainment.pet.s1.net.RegisterTaskObject
 * JD-Core Version:    0.5.4
 */