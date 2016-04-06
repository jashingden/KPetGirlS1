package com.c9entertainment.pet.s1.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.c9entertainment.pet.s1.crypto.SimpleCrypto;

public class CashData
{
  private static final String CASH = "cash";
  private static final String TITLE = "cashDataTitle";
  private static final String VALUE_INIT = "0";

  public static int appendCash(Context paramContext, int paramInt)
  {
    setInt(paramContext, "cash", paramInt + getCash(paramContext));
    return paramInt;
  }

  public static void clear(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("cashDataTitle", 0).edit();
    try
    {
      localEditor.remove(SimpleCrypto.encrypt("wook4!3@2#1$bong", "cash"));
      localEditor.commit();
      return;
    }
    catch (Exception localException)
    {
      localEditor.remove("cash");
      localException.printStackTrace();
    }
  }

  private static void clear(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("cashDataTitle", 0).edit();
    localEditor.remove(paramString);
    localEditor.commit();
  }

  public static int getCash(Context paramContext)
  {
    return getInt(paramContext, "cash");
  }

  private static int getInt(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("cashDataTitle", 0);
    int i;
    try
    {
      String str = localSharedPreferences.getString(SimpleCrypto.encrypt("wook4!3@2#1$bong", paramString), "0");
      if (!str.equals("0"))
      {
        i = Integer.parseInt(SimpleCrypto.decrypt("wook4!3@2#1$bong", str));
      }
      else
      {
        i = localSharedPreferences.getInt(paramString, 0);
        setInt(paramContext, paramString, i);
        clear(paramContext, paramString);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      i = localSharedPreferences.getInt(paramString, 0);
    }
    return i;
  }

  private static void setInt(Context paramContext, String paramString, int paramInt)
  {
    String str1 = Integer.toString(paramInt);
    String str2;
    String str3;
    try
    {
      String str4 = SimpleCrypto.encrypt("wook4!3@2#1$bong", paramString);
      String str5 = SimpleCrypto.encrypt("wook4!3@2#1$bong", str1);
      str2 = str4;
      str3 = str5;
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("cashDataTitle", 0).edit();
      localEditor.putString(str2, str3);
      localEditor.commit();
      return;
    }
    catch (Exception localException)
    {
      str2 = paramString;
      str3 = Integer.toString(paramInt);
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\GPhoneFans.net\Cracked\classes_dex2jar.jar
 * Qualified Name:     com.c9entertainment.pet.s1.data.CashData
 * JD-Core Version:    0.5.4
 */