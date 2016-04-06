package com.c9entertainment.pet.s1.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.c9entertainment.pet.s1.crypto.SimpleCrypto;

public class LevelData
{
  public static final String CRYPTO_KEY = "wook4!3@2#1$bong";

  public static final int BASE_EXP = 30;
  private static final String EXP = "exp";
  private static final String INTRO = "intro";
  private static final String LEVEL = "level";
  public static final int MAX_LEVEL = 185;
  private static final String NEED_EXP = "needExp";
  public static final int NEXT_TEN_LEVEL_EXP = 15;
  private static final String START_EXP = "startExp";
  private static final String STUDY_LEVEL = "study_level";
  private static final String TITLE = "levelDataTitle";
  private static final String VALUE_INIT = "0";

  public static void addExp(Context paramContext, int paramInt)
  {
    if (paramInt <= 0)
      return;
    Log.d("ROOEX", Integer.toString(paramInt));
    while (true)
    {
      if (paramInt <= 30)
        appendExp(paramContext, paramInt);
      appendExp(paramContext, 30);
      paramInt -= 30;
    }
  }

  private static void appendExp(Context paramContext, int paramInt)
  {
    if (paramInt <= 0);
    while (true)
    {
      return;
//      Log.v("ROOEX", paramInt);
//      int i = paramInt + getExp(paramContext);
//      setInt(paramContext, "exp", i);
//      if (i < getNeedExp(paramContext))
//        continue;
//      levelUp(paramContext);
    }
  }

  public static void clear(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("levelDataTitle", 0).edit();
    try
    {
      String str1 = SimpleCrypto.encrypt(CRYPTO_KEY, "level");
      String str2 = SimpleCrypto.encrypt(CRYPTO_KEY, "exp");
      String str3 = SimpleCrypto.encrypt(CRYPTO_KEY, "intro");
      String str4 = SimpleCrypto.encrypt(CRYPTO_KEY, "needExp");
      String str5 = SimpleCrypto.encrypt(CRYPTO_KEY, "startExp");
      String str6 = SimpleCrypto.encrypt(CRYPTO_KEY, "study_level");
      localEditor.remove(str1);
      localEditor.remove(str2);
      localEditor.remove(str3);
      localEditor.remove(str4);
      localEditor.remove(str5);
      localEditor.remove(str6);
      localEditor.commit();
      return;
    }
    catch (Exception localException)
    {
      localEditor.remove("level");
      localEditor.remove("exp");
      localEditor.remove("intro");
      localEditor.remove("needExp");
      localEditor.remove("startExp");
      localEditor.remove("study_level");
      localException.printStackTrace();
    }
  }

  private static void clear(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("levelDataTitle", 0).edit();
    localEditor.remove(paramString);
    localEditor.commit();
  }

  public static int getExp(Context paramContext)
  {
    return getInt(paramContext, "exp");
  }

  private static int getInt(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("levelDataTitle", 0);
    int i;
    try
    {
      String str = localSharedPreferences.getString(SimpleCrypto.encrypt(CRYPTO_KEY, paramString), "0");
      if (!str.equals("0"))
      {
        i = Integer.parseInt(SimpleCrypto.decrypt(CRYPTO_KEY, str));
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

  public static int getIntro(Context paramContext)
  {
    return getInt(paramContext, "intro");
  }

  public static int getLevel(Context paramContext)
  {
    int i = getInt(paramContext, "level");
    if (i == 0)
      i = 1;
    return i;
  }

  public static int getNeedExp(Context paramContext)
  {
    int i = getInt(paramContext, "needExp");
    if (i == 0)
      i = 30;
    return i;
  }

  public static int getStartExp(Context paramContext)
  {
    return getInt(paramContext, "startExp");
  }

  public static int getStudyLevel(Context paramContext)
  {
    int i = getInt(paramContext, "study_level");
    if (i == 0)
      i = 1;
    return i;
  }

  private static int levelUp(Context paramContext)
  {
    int i = getLevel(paramContext);
    int j = 30 + getNeedExp(paramContext);
    if (i >= 10)
      j += 15 * (i / 10);
    setInt(paramContext, "startExp", getNeedExp(paramContext));
    setInt(paramContext, "level", i + 1);
    setInt(paramContext, "needExp", j);
    return i;
  }

  private static void setInt(Context paramContext, String paramString, int paramInt)
  {
    String str1 = Integer.toString(paramInt);
    String str2;
    String str3;
    try
    {
      String str4 = SimpleCrypto.encrypt(CRYPTO_KEY, paramString);
      String str5 = SimpleCrypto.encrypt(CRYPTO_KEY, str1);
      str2 = str4;
      str3 = str5;
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("levelDataTitle", 0).edit();
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

  public static void setIntro(Context paramContext, int paramInt)
  {
    setInt(paramContext, "intro", paramInt);
  }

  public static void setStudyLevel(Context paramContext, int paramInt)
  {
    setInt(paramContext, "study_level", paramInt);
  }
}

/* Location:           D:\Android\Tools\APK-Multi-Toolv1.0.11\projects\com.c9entertainment.pet.s1.apk\classes_dex2jar.jar
 * Qualified Name:     com.c9entertainment.pet.s1.data.LevelData
 * JD-Core Version:    0.5.4
 */