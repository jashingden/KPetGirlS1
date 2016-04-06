package com.c9entertainment.pet.s1.data;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class UserData
{
  public static final String BAD_ANDROID_ID = "9774d56d682e549c";
  private static final String CHECK_REGISTER_IMEI_AND_ACCOUNT = "check_register_imei_and_account";
  public static final int NOT_REGISTER = -1;
  public static final int REGISTER = 1;
  private static final String TITLE = "userDataTitle";
  private static final String USER_IDX = "userIdx";

  public static void clear(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("userDataTitle", 0).edit();
    localEditor.remove("userIdx");
    localEditor.remove("check_register_imei_and_account");
    localEditor.commit();
  }

//  public static boolean getCheckRegister(Context paramContext)
//  {
//    if ((getUserIdx(paramContext) == -1) || (getCheckRegisterIMEIandACCOUNT(paramContext) == -1));
//    for (int i = 0; ; i = 1)
//      return i;
//  }

  public static int getCheckRegisterIMEIandACCOUNT(Context paramContext)
  {
    return getInt(paramContext, "check_register_imei_and_account");
  }

  private static int getInt(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("userDataTitle", 0).getInt(paramString, -1);
  }

  public static String getUserAndroidID(ContentResolver paramContentResolver)
  {
    return Settings.Secure.getString(paramContentResolver, "android_id");
  }

  public static String getUserAndroidIMEI(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService(Context.TELEPHONY_SERVICE);
    if (localTelephonyManager.getDeviceId() == null);
    for (String str = "none"; ; str = localTelephonyManager.getDeviceId())
      return str;
  }

  public static String getUserEmailAccountIdent(Context paramContext)
  {
    Account[] arrayOfAccount = AccountManager.get(paramContext).getAccounts();
    if (arrayOfAccount.length > 0);
    for (String str = arrayOfAccount[0].name; ; str = "none")
      return str;
  }

  public static int getUserIdx(Context paramContext)
  {
    return getInt(paramContext, "userIdx");
  }

  public static int setCheckRegisterIMEIandACCOUNT(Context paramContext, int paramInt)
  {
    setInt(paramContext, "check_register_imei_and_account", paramInt);
    return paramInt;
  }

  private static void setInt(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("userDataTitle", 0).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }

  public static int setUserIdx(Context paramContext, int paramInt)
  {
    setInt(paramContext, "userIdx", paramInt);
    return paramInt;
  }
}

/* Location:           D:\GPhoneFans.net\Cracked\classes_dex2jar.jar
 * Qualified Name:     com.c9entertainment.pet.s1.data.UserData
 * JD-Core Version:    0.5.4
 */