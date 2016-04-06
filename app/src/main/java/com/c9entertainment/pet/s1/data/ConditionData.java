package com.c9entertainment.pet.s1.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.c9entertainment.pet.s1.crypto.SimpleCrypto;
import com.c9entertainment.pet.s1.object.HealthObject;
import java.util.Arrays;

public class ConditionData
{
  public static final int CATEGORY_BORING = 3;
  public static final int CATEGORY_CUTE = 8;
  public static final int CATEGORY_DIRTY = 4;
  public static final int CATEGORY_HUNGER = 2;
  public static final int CATEGORY_POPULARITY = 9;
  public static final int CATEGORY_PURE = 7;
  public static final int CATEGORY_SEXY = 6;
  public static final int CATEGORY_SMART = 5;
  public static final int CATEGORY_TRIED = 1;
  public static final String ENDING_ANNOUNCER = "m_ending_0007";
  public static final int[] ENDING_ANNOUNCER_CONDITION;
  public static final String ENDING_BARTENDER = "m_ending_0005";
  public static final int[] ENDING_BARTENDER_CONDITION;
  public static final String ENDING_HOUSEWIFE = "m_ending_0003";
  public static final int[] ENDING_HOUSEWIFE_CONDITION;
  public static final String ENDING_MODEL = "m_ending_0004";
  public static final int[] ENDING_MODEL_CONDITION;
  public static final String ENDING_NURSE = "m_ending_0001";
  public static final int[] ENDING_NURSE_CONDITION;
  public static final String ENDING_OFFICE = "m_ending_0002";
  public static final String ENDING_SINGER = "m_ending_0006";
  public static final int[] ENDING_SINGER_CONDITION;
  private static final String MAX_BORING = "maxBoring";
  private static final String MAX_DIRTY = "maxDirty";
  private static final String MAX_HUNGER = "maxHunger";
  private static final String MAX_TRIED = "maxTried";
  public static final int TIMER_SEC = 30;
  public static final int TIMER_VALUE = 3;
  private static final String TITLE = "usetDataTitle";
  private static final String VALUE_BORING = "valueBoring";
  private static final String VALUE_CUTE = "valueCute";
  private static final String VALUE_DIRTY = "valueDirty";
  private static final String VALUE_HUNGER = "valueHunger";
  private static final String VALUE_INIT = "0";
  public static final int VALUE_MAX = 300;
  private static final String VALUE_MAX_INIT = "100";
  private static final String VALUE_POPULARITY = "valuePopularity";
  private static final String VALUE_PURE = "valuePure";
  private static final String VALUE_SEXY = "valueSexy";
  private static final String VALUE_SMART = "valueSmart";
  private static final String VALUE_TRIED = "valueTried";
  public static final int VIEW_GAGE_MAX = 500;

  static
  {
    int[] arrayOfInt1 = new int[2];
    arrayOfInt1[0] = 5;
    arrayOfInt1[1] = 8;
    ENDING_NURSE_CONDITION = arrayOfInt1;
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = 8;
    arrayOfInt2[1] = 7;
    ENDING_HOUSEWIFE_CONDITION = arrayOfInt2;
    int[] arrayOfInt3 = new int[2];
    arrayOfInt3[0] = 6;
    arrayOfInt3[1] = 8;
    ENDING_MODEL_CONDITION = arrayOfInt3;
    int[] arrayOfInt4 = new int[2];
    arrayOfInt4[0] = 6;
    arrayOfInt4[1] = 7;
    ENDING_BARTENDER_CONDITION = arrayOfInt4;
    int[] arrayOfInt5 = new int[2];
    arrayOfInt5[0] = 5;
    arrayOfInt5[1] = 6;
    ENDING_SINGER_CONDITION = arrayOfInt5;
    int[] arrayOfInt6 = new int[2];
    arrayOfInt6[0] = 5;
    arrayOfInt6[1] = 7;
    ENDING_ANNOUNCER_CONDITION = arrayOfInt6;
  }

  public static int appendBoring(Context paramContext, int paramInt)
  {
    int i = paramInt + getBoring(paramContext);
    int j = getBoringMax(paramContext);
    if (i >= j)
      i = j;
    if (i <= 0)
      i = 0;
    setInt(paramContext, "valueBoring", i);
    return i;
  }

  public static int appendBoringMax(Context paramContext, int paramInt)
  {
    int i = paramInt + getBoringMax(paramContext);
    if (i >= 300)
      i = 300;
    setInt(paramContext, "maxBoring", i);
    return i;
  }

  public static void appendCute(Context paramContext, int paramInt)
  {
    int i = paramInt + getCute(paramContext);
    if (i <= 0)
      i = 0;
    setInt(paramContext, "valueCute", i);
  }

  public static int appendDirty(Context paramContext, int paramInt)
  {
    int i = paramInt + getDirty(paramContext);
    int j = getDirtyMax(paramContext);
    if (i >= j)
      i = j;
    if (i <= 0)
      i = 0;
    setInt(paramContext, "valueDirty", i);
    return i;
  }

  public static int appendDirtyMax(Context paramContext, int paramInt)
  {
    int i = paramInt + getDirtyMax(paramContext);
    if (i >= 300)
      i = 300;
    setInt(paramContext, "maxDirty", i);
    return i;
  }

  public static int appendHunger(Context paramContext, int paramInt)
  {
    int i = paramInt + getHunger(paramContext);
    int j = getHungerMax(paramContext);
    if (i >= j)
      i = j;
    if (i <= 0)
      i = 0;
    setInt(paramContext, "valueHunger", i);
    return i;
  }

  public static int appendHungerMax(Context paramContext, int paramInt)
  {
    int i = paramInt + getHungerMax(paramContext);
    if (i >= 300)
      i = 300;
    setInt(paramContext, "maxHunger", i);
    return i;
  }

  public static void appendPopularity(Context paramContext, int paramInt)
  {
    int i = paramInt + getPopularity(paramContext);
    if (i <= 0)
      i = 0;
    setInt(paramContext, "valuePopularity", i);
  }

  public static void appendPure(Context paramContext, int paramInt)
  {
    int i = paramInt + getPure(paramContext);
    if (i <= 0)
      i = 0;
    setInt(paramContext, "valuePure", i);
  }

  public static void appendSexy(Context paramContext, int paramInt)
  {
    int i = paramInt + getSexy(paramContext);
    if (i <= 0)
      i = 0;
    setInt(paramContext, "valueSexy", i);
  }

  public static void appendSmart(Context paramContext, int paramInt)
  {
    int i = paramInt + getSmart(paramContext);
    if (i <= 0)
      i = 0;
    setInt(paramContext, "valueSmart", i);
  }

  public static int appendTried(Context paramContext, int paramInt)
  {
    int i = paramInt + getTried(paramContext);
    int j = getTriedMax(paramContext);
    if (i >= j)
      i = j;
    if (i <= 0)
      i = 0;
    setInt(paramContext, "valueTried", i);
    return i;
  }

  public static int appendTriedMax(Context paramContext, int paramInt)
  {
    int i = paramInt + getTriedMax(paramContext);
    if (i >= 300)
      i = 300;
    setInt(paramContext, "maxTried", i);
    return i;
  }

  public static void clear(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("usetDataTitle", 0).edit();
    try
    {
      String str1 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valueTried");
      String str2 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valueHunger");
      String str3 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valueBoring");
      String str4 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valueDirty");
      String str5 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valueSmart");
      String str6 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valueSexy");
      String str7 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valuePure");
      String str8 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valueCute");
      String str9 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valuePopularity");
      String str10 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "maxTried");
      String str11 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "maxHunger");
      String str12 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "maxBoring");
      String str13 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "maxDirty");
      localEditor.remove(str1);
      localEditor.remove(str2);
      localEditor.remove(str3);
      localEditor.remove(str4);
      localEditor.remove(str5);
      localEditor.remove(str6);
      localEditor.remove(str7);
      localEditor.remove(str8);
      localEditor.remove(str9);
      localEditor.remove(str10);
      localEditor.remove(str11);
      localEditor.remove(str12);
      localEditor.remove(str13);
      localEditor.commit();
      return;
    }
    catch (Exception localException)
    {
      localEditor.remove("valueTried");
      localEditor.remove("valueHunger");
      localEditor.remove("valueBoring");
      localEditor.remove("valueDirty");
      localEditor.remove("valueSmart");
      localEditor.remove("valueSexy");
      localEditor.remove("valuePure");
      localEditor.remove("valueCute");
      localEditor.remove("valuePopularity");
      localEditor.remove("maxTried");
      localEditor.remove("maxHunger");
      localEditor.remove("maxBoring");
      localEditor.remove("maxDirty");
      localException.printStackTrace();
    }
  }

  private static void clear(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("usetDataTitle", 0).edit();
    localEditor.remove(paramString);
    localEditor.commit();
  }

  public static int getBoring(Context paramContext)
  {
    return getInt(paramContext, "valueBoring");
  }

  public static int getBoringMax(Context paramContext)
  {
    return getMaxInt(paramContext, "maxBoring");
  }

  public static int getCute(Context paramContext)
  {
    return getInt(paramContext, "valueCute");
  }

  public static int getDirty(Context paramContext)
  {
    return getInt(paramContext, "valueDirty");
  }

  public static int getDirtyMax(Context paramContext)
  {
    return getMaxInt(paramContext, "maxDirty");
  }
/*
  public static String getEnding(Context paramContext)
  {
    int[] arrayOfInt = getTopProperty(paramContext);
    String str = null;
    if (arrayOfInt != null)
    {
      Log.i("ROOEX", "top[2] : " + arrayOfInt[2]);
      if (arrayOfInt[2] >= 500)
        if (((ENDING_ANNOUNCER_CONDITION[0] != arrayOfInt[0]) && (ENDING_ANNOUNCER_CONDITION[0] != arrayOfInt[1])) || ((ENDING_ANNOUNCER_CONDITION[1] != arrayOfInt[0]) && (ENDING_ANNOUNCER_CONDITION[1] != arrayOfInt[1])))
          break label93;
    }
    for (str = "m_ending_0007"; ; str = "m_ending_0001")
      do
        while (true)
        {
          return str;
          if ((((ENDING_BARTENDER_CONDITION[0] == arrayOfInt[0]) || (ENDING_BARTENDER_CONDITION[0] == arrayOfInt[1]))) && (((ENDING_BARTENDER_CONDITION[1] == arrayOfInt[0]) || (ENDING_BARTENDER_CONDITION[1] == arrayOfInt[1]))))
            label93: str = "m_ending_0005";
          if ((((ENDING_HOUSEWIFE_CONDITION[0] == arrayOfInt[0]) || (ENDING_HOUSEWIFE_CONDITION[0] == arrayOfInt[1]))) && (((ENDING_HOUSEWIFE_CONDITION[1] == arrayOfInt[0]) || (ENDING_HOUSEWIFE_CONDITION[1] == arrayOfInt[1]))))
            str = "m_ending_0003";
          if ((((ENDING_MODEL_CONDITION[0] == arrayOfInt[0]) || (ENDING_MODEL_CONDITION[0] == arrayOfInt[1]))) && (((ENDING_MODEL_CONDITION[1] == arrayOfInt[0]) || (ENDING_MODEL_CONDITION[1] == arrayOfInt[1]))))
            str = "m_ending_0004";
          if (((ENDING_SINGER_CONDITION[0] != arrayOfInt[0]) && (ENDING_SINGER_CONDITION[0] != arrayOfInt[1])) || ((ENDING_SINGER_CONDITION[1] != arrayOfInt[0]) && (ENDING_SINGER_CONDITION[1] != arrayOfInt[1])))
            break;
          str = "m_ending_0006";
        }
      while (((ENDING_NURSE_CONDITION[0] != arrayOfInt[0]) && (ENDING_NURSE_CONDITION[0] != arrayOfInt[1])) || ((ENDING_NURSE_CONDITION[1] != arrayOfInt[0]) && (ENDING_NURSE_CONDITION[1] != arrayOfInt[1])));
  }
*/
  public static int getHunger(Context paramContext)
  {
    return getInt(paramContext, "valueHunger");
  }

  public static int getHungerMax(Context paramContext)
  {
    return getMaxInt(paramContext, "maxHunger");
  }

  private static int getInt(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("usetDataTitle", 0);
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

  private static int getMaxInt(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("usetDataTitle", 0);
    int i;
    try
    {
      String str = localSharedPreferences.getString(SimpleCrypto.encrypt("wook4!3@2#1$bong", paramString), "100");
      if (!str.equals("100"))
      {
        i = Integer.parseInt(SimpleCrypto.decrypt("wook4!3@2#1$bong", str));
      }
      else
      {
        i = localSharedPreferences.getInt(paramString, 100);
        setInt(paramContext, paramString, i);
        clear(paramContext, paramString);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      i = localSharedPreferences.getInt(paramString, 100);
    }
    return i;
  }

  public static int getPopularity(Context paramContext)
  {
    return getInt(paramContext, "valuePopularity");
  }

  public static int getPure(Context paramContext)
  {
    return getInt(paramContext, "valuePure");
  }

  public static int getSexy(Context paramContext)
  {
    return getInt(paramContext, "valueSexy");
  }

  public static int getSmart(Context paramContext)
  {
    return getInt(paramContext, "valueSmart");
  }
/*
  public static HealthObject getTopHealth(Context paramContext)
  {
    int i = getBoring(paramContext);
    int j = getDirty(paramContext);
    int k = getHunger(paramContext);
    int l = getTried(paramContext);
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = -1;
    arrayOfInt[1] = -1;
    arrayOfInt[2] = -1;
    arrayOfInt[3] = -1;
    arrayOfInt[0] = i;
    arrayOfInt[1] = j;
    arrayOfInt[2] = k;
    arrayOfInt[3] = l;
    Arrays.sort(arrayOfInt);
    HealthObject localHealthObject = new HealthObject();
    if (arrayOfInt[3] == i)
      localHealthObject.category = 3;
    for (localHealthObject.value = i; ; localHealthObject.value = l)
    {
      do
        while (true)
        {
          return localHealthObject;
          if (arrayOfInt[3] == j)
          {
            localHealthObject.category = 4;
            localHealthObject.value = j;
          }
          if (arrayOfInt[3] != k)
            break;
          localHealthObject.category = 2;
          localHealthObject.value = k;
        }
      while (arrayOfInt[3] != l);
      localHealthObject.category = 1;
    }
  }

  public static int[] getTopProperty(Context paramContext)
  {
    getPopularity(paramContext);
    int i = getSmart(paramContext);
    int j = getSexy(paramContext);
    int k = getCute(paramContext);
    int l = getPure(paramContext);
    int[] arrayOfInt1 = new int[4];
    arrayOfInt1[0] = -1;
    arrayOfInt1[1] = -1;
    arrayOfInt1[2] = -1;
    arrayOfInt1[3] = -1;
    arrayOfInt1[0] = i;
    arrayOfInt1[1] = j;
    arrayOfInt1[2] = k;
    arrayOfInt1[3] = l;
    Arrays.sort(arrayOfInt1);
    int[] arrayOfInt2;
    if (arrayOfInt1[3] == 0)
      arrayOfInt2 = null;
    while (true)
    {
      label93: return arrayOfInt2;
      arrayOfInt2 = new int[3];
      arrayOfInt2[0] = -1;
      arrayOfInt2[1] = -1;
      arrayOfInt2[2] = -1;
      if (arrayOfInt1[3] == i)
      {
        arrayOfInt2[0] = 5;
        i = -1;
      }
      while (arrayOfInt1[2] == i)
      {
        arrayOfInt2[1] = 5;
        arrayOfInt2[2] = i;
        break label93:
        if (arrayOfInt1[3] == j)
        {
          arrayOfInt2[0] = 6;
          j = -1;
        }
        if (arrayOfInt1[3] == k)
        {
          arrayOfInt2[0] = 8;
          k = -1;
        }
        if (arrayOfInt1[3] != l)
          continue;
        arrayOfInt2[0] = 7;
        l = -1;
      }
      if (arrayOfInt1[2] == j)
      {
        arrayOfInt2[1] = 6;
        arrayOfInt2[2] = j;
      }
      if (arrayOfInt1[2] == k)
      {
        arrayOfInt2[1] = 8;
        arrayOfInt2[2] = k;
      }
      if (arrayOfInt1[2] != l)
        continue;
      arrayOfInt2[1] = 7;
      arrayOfInt2[2] = l;
    }
  }
*/
  public static int getTried(Context paramContext)
  {
    return getInt(paramContext, "valueTried");
  }

  public static int getTriedMax(Context paramContext)
  {
    return getMaxInt(paramContext, "maxTried");
  }

  public static void resetFourCondition(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("usetDataTitle", 0).edit();
    try
    {
      String str1 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valueTried");
      String str2 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valueHunger");
      String str3 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valueBoring");
      String str4 = SimpleCrypto.encrypt("wook4!3@2#1$bong", "valueDirty");
      localEditor.remove(str1);
      localEditor.remove(str2);
      localEditor.remove(str3);
      localEditor.remove(str4);
      localEditor.commit();
      return;
    }
    catch (Exception localException)
    {
      localEditor.remove("valueTried");
      localEditor.remove("valueHunger");
      localEditor.remove("valueBoring");
      localEditor.remove("valueDirty");
      localException.printStackTrace();
    }
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
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("usetDataTitle", 0).edit();
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

/* Location:           D:\Android\Tools\APK-Multi-Toolv1.0.11\projects\com.c9entertainment.pet.s1.apk\classes_dex2jar.jar
 * Qualified Name:     com.c9entertainment.pet.s1.data.ConditionData
 * JD-Core Version:    0.5.4
 */