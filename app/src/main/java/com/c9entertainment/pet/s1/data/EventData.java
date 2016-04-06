package com.c9entertainment.pet.s1.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.c9entertainment.pet.s1.object.DateObject;
import com.c9entertainment.pet.s1.object.LevelUpObject;
import com.c9entertainment.pet.s1.object.RandomEventObject;
import java.io.StringReader;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class EventData
{
  private static final String LAST_DATE = "lastDate";
  private static final String LAST_LEVEL_UP_EVENT = "lastLevelUpEvent";
  private static final String LAST_RANDOM_EVENT = "lastRandomEvent";
  private static final String TITLE = "eventDataTitle";
  private static ArrayList<DateObject> dateEventList;
  private static ArrayList<LevelUpObject> levelUpEventList = null;
  private static ArrayList<RandomEventObject> randomEventList;

  static
  {
    dateEventList = null;
    randomEventList = null;
  }

  public static void clear(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("eventDataTitle", 0).edit();
    localEditor.remove("lastLevelUpEvent");
    localEditor.remove("lastDate");
    localEditor.remove("lastRandomEvent");
    localEditor.commit();
  }
/*
  public static DateObject getDate(Context paramContext)
  {
    if (dateEventList == null)
      initDate(paramContext);
    int i = getLastDate(paramContext);
    int j = LevelData.getLevel(paramContext);
    int k;
    if (i != j)
      k = dateEventList.size();
    for (int l = 0; ; ++l)
    {
      if (l >= k);
      DateObject localDateObject;
      for (Object localObject = null; ; localObject = localDateObject)
      {
        return localObject;
        localDateObject = (DateObject)dateEventList.get(l);
        if (localDateObject.level != j)
          break;
        setInt(paramContext, "lastDate", j);
      }
    }
  }
*/
  private static int getInt(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("eventDataTitle", 0).getInt(paramString, 0);
  }

  private static int getLastDate(Context paramContext)
  {
    return getInt(paramContext, "lastDate");
  }

  private static int getLastLevelUpEvent(Context paramContext)
  {
    return getInt(paramContext, "lastLevelUpEvent");
  }

  private static int getLastRandomEvent(Context paramContext)
  {
    return getInt(paramContext, "lastRandomEvent");
  }
/*
  public static LevelUpObject getLevelUpEvent(Context paramContext)
  {
    if (levelUpEventList == null)
      initLevelUpEvent(paramContext);
    int i = getLastLevelUpEvent(paramContext);
    int j = LevelData.getLevel(paramContext);
    int l;
    if (i != j)
    {
      int k = levelUpEventList.size();
      l = 0;
      if (l >= k)
      {
        label36: if (j <= 1)
          break label109;
        setInt(paramContext, "lastLevelUpEvent", j);
      }
    }
    for (Object localObject = (LevelUpObject)levelUpEventList.get(0); ; localObject = null)
    {
      while (true)
      {
        return localObject;
        LevelUpObject localLevelUpObject = (LevelUpObject)levelUpEventList.get(l);
        if (localLevelUpObject.level != j)
          break;
        setInt(paramContext, "lastLevelUpEvent", j);
        localObject = localLevelUpObject;
      }
      ++l;
      label109: break label36:
    }
  }

  public static RandomEventObject getRandomEvent(Context paramContext)
  {
    if (randomEventList == null)
      initRandomEvent(paramContext);
    int i = getLastRandomEvent(paramContext);
    int j = LevelData.getLevel(paramContext);
    int k;
    if (i != j)
      k = randomEventList.size();
    for (int l = 0; ; ++l)
    {
      if (l >= k);
      RandomEventObject localRandomEventObject;
      for (Object localObject = null; ; localObject = localRandomEventObject)
      {
        return localObject;
        localRandomEventObject = (RandomEventObject)randomEventList.get(l);
        if ((localRandomEventObject.minLevel > j) || (localRandomEventObject.maxLevel < j))
          break;
        setInt(paramContext, "lastRandomEvent", j);
      }
    }
  }
*/
  // ERROR //
  private static void initDate(Context paramContext)
  {
    // Byte code:
    //   0: new 75	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 130	java/util/ArrayList:<init>	()V
    //   7: putstatic 30	com/c9entertainment/pet/s1/data/EventData:dateEventList	Ljava/util/ArrayList;
    //   10: aload_0
    //   11: invokevirtual 134	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   14: ldc 135
    //   16: invokevirtual 141	android/content/res/Resources:getXml	(I)Landroid/content/res/XmlResourceParser;
    //   19: astore_1
    //   20: aconst_null
    //   21: astore_2
    //   22: aload_1
    //   23: invokeinterface 146 1 0
    //   28: iconst_1
    //   29: if_icmpne +8 -> 37
    //   32: aload_2
    //   33: pop
    //   34: goto +157 -> 191
    //   37: aload_1
    //   38: invokeinterface 146 1 0
    //   43: iconst_2
    //   44: if_icmpne +92 -> 136
    //   47: aload_1
    //   48: invokeinterface 150 1 0
    //   53: ldc 152
    //   55: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   58: ifeq +127 -> 185
    //   61: new 85	com/c9entertainment/pet/s1/object/DateObject
    //   64: dup
    //   65: invokespecial 159	com/c9entertainment/pet/s1/object/DateObject:<init>	()V
    //   68: astore 5
    //   70: aload_1
    //   71: invokeinterface 150 1 0
    //   76: ldc 161
    //   78: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   81: ifeq +14 -> 95
    //   84: aload 5
    //   86: aload_1
    //   87: invokeinterface 164 1 0
    //   92: putfield 166	com/c9entertainment/pet/s1/object/DateObject:movie	Ljava/lang/String;
    //   95: aload_1
    //   96: invokeinterface 150 1 0
    //   101: ldc 167
    //   103: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   106: ifeq +17 -> 123
    //   109: aload 5
    //   111: aload_1
    //   112: invokeinterface 164 1 0
    //   117: invokestatic 173	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   120: putfield 89	com/c9entertainment/pet/s1/object/DateObject:level	I
    //   123: aload_1
    //   124: invokeinterface 176 1 0
    //   129: pop
    //   130: aload 5
    //   132: astore_2
    //   133: goto -111 -> 22
    //   136: aload_1
    //   137: invokeinterface 146 1 0
    //   142: iconst_3
    //   143: if_icmpne +25 -> 168
    //   146: aload_1
    //   147: invokeinterface 150 1 0
    //   152: ldc 152
    //   154: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   157: ifeq +11 -> 168
    //   160: getstatic 30	com/c9entertainment/pet/s1/data/EventData:dateEventList	Ljava/util/ArrayList;
    //   163: aload_2
    //   164: invokevirtual 179	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   167: pop
    //   168: aload_2
    //   169: astore 5
    //   171: goto -48 -> 123
    //   174: astore_3
    //   175: aload_2
    //   176: pop
    //   177: goto +14 -> 191
    //   180: astore 6
    //   182: goto +9 -> 191
    //   185: aload_2
    //   186: astore 5
    //   188: goto -118 -> 70
    //   191: return
    //
    // Exception table:
    //   from	to	target	type
    //   22	70	174	java/lang/Exception
    //   136	168	174	java/lang/Exception
    //   70	130	180	java/lang/Exception
  }
/*
  private static void initLevelUpEvent(Context paramContext)
  {
    XmlPullParser localXmlPullParser = null;
    Object localObject1;
    try
    {
      XmlPullParserFactory localXmlPullParserFactory = XmlPullParserFactory.newInstance();
      localXmlPullParserFactory.setNamespaceAware(true);
      levelUpEventList = new ArrayList();
      localXmlPullParser = localXmlPullParserFactory.newPullParser();
      localXmlPullParser.setInput(new StringReader(XMLStorage.Level(0)));
      label275: label48: label64: localObject1 = null;
    }
    catch (XmlPullParserException localXmlPullParserException)
    {
      Object localObject2;
      try
      {
        int i = localXmlPullParser.getEventType();
        if (i == 1)
        {
          return;
          localXmlPullParserException = localXmlPullParserException;
          localXmlPullParserException.printStackTrace();
        }
        if (localXmlPullParser.getEventType() == 2)
        {
          if (!localXmlPullParser.getName().equals("item"))
            break label275;
          localObject2 = new LevelUpObject();
        }
      }
      catch (Exception localException1)
      {
        try
        {
          if (localXmlPullParser.getName().equals("title"))
            ((LevelUpObject)localObject2).title = localXmlPullParser.nextText();
          if (localXmlPullParser.getName().equals("msg"))
            ((LevelUpObject)localObject2).msg = localXmlPullParser.nextText();
          if (localXmlPullParser.getName().equals("level"))
            ((LevelUpObject)localObject2).level = Integer.parseInt(localXmlPullParser.nextText());
          if (localXmlPullParser.getName().equals("event"))
            ((LevelUpObject)localObject2).event = Integer.parseInt(localXmlPullParser.nextText());
          while (true)
          {
            localXmlPullParser.next();
            localObject1 = localObject2;
            break label48:
            if ((localXmlPullParser.getEventType() == 3) && (localXmlPullParser.getName().equals("item")))
              levelUpEventList.add(localObject1);
            localObject2 = localObject1;
          }
          localException1 = localException1;
        }
        catch (Exception localException2)
        {
        }
        break label64:
        localObject2 = localObject1;
      }
    }
  }
*/
  // ERROR //
  private static void initRandomEvent(Context paramContext)
  {
    // Byte code:
    //   0: new 75	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 130	java/util/ArrayList:<init>	()V
    //   7: putstatic 32	com/c9entertainment/pet/s1/data/EventData:randomEventList	Ljava/util/ArrayList;
    //   10: aload_0
    //   11: invokevirtual 134	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   14: ldc 227
    //   16: invokevirtual 141	android/content/res/Resources:getXml	(I)Landroid/content/res/XmlResourceParser;
    //   19: astore_1
    //   20: aconst_null
    //   21: astore_2
    //   22: aload_1
    //   23: invokeinterface 146 1 0
    //   28: iconst_1
    //   29: if_icmpne +8 -> 37
    //   32: aload_2
    //   33: pop
    //   34: goto +328 -> 362
    //   37: aload_1
    //   38: invokeinterface 146 1 0
    //   43: iconst_2
    //   44: if_icmpne +263 -> 307
    //   47: aload_1
    //   48: invokeinterface 150 1 0
    //   53: ldc 152
    //   55: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   58: ifeq +298 -> 356
    //   61: new 121	com/c9entertainment/pet/s1/object/RandomEventObject
    //   64: dup
    //   65: invokespecial 228	com/c9entertainment/pet/s1/object/RandomEventObject:<init>	()V
    //   68: astore 5
    //   70: aload_1
    //   71: invokeinterface 150 1 0
    //   76: ldc 229
    //   78: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   81: ifeq +17 -> 98
    //   84: aload 5
    //   86: aload_1
    //   87: invokeinterface 164 1 0
    //   92: invokestatic 173	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   95: putfield 124	com/c9entertainment/pet/s1/object/RandomEventObject:minLevel	I
    //   98: aload_1
    //   99: invokeinterface 150 1 0
    //   104: ldc 230
    //   106: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   109: ifeq +17 -> 126
    //   112: aload 5
    //   114: aload_1
    //   115: invokeinterface 164 1 0
    //   120: invokestatic 173	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   123: putfield 127	com/c9entertainment/pet/s1/object/RandomEventObject:maxLevel	I
    //   126: aload_1
    //   127: invokeinterface 150 1 0
    //   132: ldc 232
    //   134: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: ifeq +17 -> 154
    //   140: aload 5
    //   142: aload_1
    //   143: invokeinterface 164 1 0
    //   148: invokestatic 173	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   151: putfield 234	com/c9entertainment/pet/s1/object/RandomEventObject:none	I
    //   154: aload_1
    //   155: invokeinterface 150 1 0
    //   160: ldc 161
    //   162: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   165: ifeq +17 -> 182
    //   168: aload 5
    //   170: aload_1
    //   171: invokeinterface 164 1 0
    //   176: invokestatic 173	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   179: putfield 236	com/c9entertainment/pet/s1/object/RandomEventObject:movie	I
    //   182: aload_1
    //   183: invokeinterface 150 1 0
    //   188: ldc 238
    //   190: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   193: ifeq +17 -> 210
    //   196: aload 5
    //   198: aload_1
    //   199: invokeinterface 164 1 0
    //   204: invokestatic 173	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   207: putfield 240	com/c9entertainment/pet/s1/object/RandomEventObject:train	I
    //   210: aload_1
    //   211: invokeinterface 150 1 0
    //   216: ldc 242
    //   218: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   221: ifeq +17 -> 238
    //   224: aload 5
    //   226: aload_1
    //   227: invokeinterface 164 1 0
    //   232: invokestatic 173	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   235: putfield 244	com/c9entertainment/pet/s1/object/RandomEventObject:star	I
    //   238: aload_1
    //   239: invokeinterface 150 1 0
    //   244: ldc 246
    //   246: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   249: ifeq +17 -> 266
    //   252: aload 5
    //   254: aload_1
    //   255: invokeinterface 164 1 0
    //   260: invokestatic 173	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   263: putfield 248	com/c9entertainment/pet/s1/object/RandomEventObject:club	I
    //   266: aload_1
    //   267: invokeinterface 150 1 0
    //   272: ldc 250
    //   274: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   277: ifeq +17 -> 294
    //   280: aload 5
    //   282: aload_1
    //   283: invokeinterface 164 1 0
    //   288: invokestatic 173	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   291: putfield 252	com/c9entertainment/pet/s1/object/RandomEventObject:ball	I
    //   294: aload_1
    //   295: invokeinterface 176 1 0
    //   300: pop
    //   301: aload 5
    //   303: astore_2
    //   304: goto -282 -> 22
    //   307: aload_1
    //   308: invokeinterface 146 1 0
    //   313: iconst_3
    //   314: if_icmpne +25 -> 339
    //   317: aload_1
    //   318: invokeinterface 150 1 0
    //   323: ldc 152
    //   325: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   328: ifeq +11 -> 339
    //   331: getstatic 32	com/c9entertainment/pet/s1/data/EventData:randomEventList	Ljava/util/ArrayList;
    //   334: aload_2
    //   335: invokevirtual 179	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   338: pop
    //   339: aload_2
    //   340: astore 5
    //   342: goto -48 -> 294
    //   345: astore_3
    //   346: aload_2
    //   347: pop
    //   348: goto +14 -> 362
    //   351: astore 6
    //   353: goto +9 -> 362
    //   356: aload_2
    //   357: astore 5
    //   359: goto -289 -> 70
    //   362: return
    //
    // Exception table:
    //   from	to	target	type
    //   22	70	345	java/lang/Exception
    //   307	339	345	java/lang/Exception
    //   70	301	351	java/lang/Exception
  }

  private static void setInt(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("eventDataTitle", 0).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }
}

/* Location:           D:\Android\Tools\APK-Multi-Toolv1.0.11\projects\com.c9entertainment.pet.s1.apk\classes_dex2jar.jar
 * Qualified Name:     com.c9entertainment.pet.s1.data.EventData
 * JD-Core Version:    0.5.4
 */