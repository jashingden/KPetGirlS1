package com.eddy.dicegirl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiceGirlService extends Service {

    private final static String LOG_FILENAME = "DiceGirlService.log";

    private DiceGirl mDiceGirl;

    @Override
    public void onCreate() {
        mDiceGirl = new DiceGirl(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            startCommand(intent);
        }
        return Service.START_STICKY;
    }

    private void startCommand(Intent intent) {
        if (mDiceGirl.readFile(DiceGirl.prefFilename)) {
            String hour = new SimpleDateFormat("HH").format(new Date());
            String lastHour = getLastHour();
            if (TextUtils.isEmpty(lastHour) || !lastHour.equals(hour)) {
                new DiceGirl.SendUserLogon(mDiceGirl).start(mAction);
            } else {
                setAlarm();
            }
        }
    }

    @Override
    public void onDestroy() {
        cancelAlarm();
    }

    public String getLastDate() {
        SharedPreferences pref = this.getSharedPreferences("DiceGirlService", Context.MODE_PRIVATE);
        return pref.getString("lastDate", "");
    }

    public void setLastDate(String today) {
        SharedPreferences.Editor editor = this.getSharedPreferences("DiceGirlService", Context.MODE_PRIVATE).edit();
        editor.putString("lastDate", today);
        editor.commit();
    }

    public String getLastHour() {
        SharedPreferences pref = this.getSharedPreferences("DiceGirlService", Context.MODE_PRIVATE);
        return pref.getString("lastHour", "");
    }

    public void setLastHour(String hour) {
        SharedPreferences.Editor editor = this.getSharedPreferences("DiceGirlService", Context.MODE_PRIVATE).edit();
        editor.putString("lastHour", hour);
        editor.commit();
    }

    private DiceGirl.OnActionHandler mAction = new DiceGirl.OnActionHandler() {
        @Override
        public void onFinish(DiceGirl.MyTask task, boolean bSuccess, String result) {
            String type = task.getType();
            Log.d("Eddy", "type=" + type + " bSuccess=" + bSuccess + " result=" + result);

            if (type.equals("sendUserLogon")) {
                addLog("sendUserLogon");
                String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
                String lastDate = getLastDate();
                if (TextUtils.isEmpty(lastDate) || !lastDate.equals(today)) {
                    new DiceGirl.GetUserInfo(mDiceGirl).start(mAction);
                } else {
                    new DiceGirl.GetUnFinishQuestList(mDiceGirl).start(mAction);
                }
            } else if (type.equals("getUserInfo")) {
                addLog("getUserInfo");
                new DiceGirl.GetUnFinishQuestList(mDiceGirl).start(mAction);
            } else if (type.equals("getUnFinishQuestList")) {
                addLog("getUnFinishQuestList");
                DiceGirl.GetUnFinishQuestList questList = (DiceGirl.GetUnFinishQuestList)task;
                if (questList.DailyLogin_Is_Finish == true) {
                    new DiceGirl.SendQuestFinish(mDiceGirl, "DailyLogin").start(mAction);
                } else if (questList.PrizeByHour_Is_Finish == true) {
                    new DiceGirl.SendQuestFinish(mDiceGirl, "PrizeByHour").start(mAction);
                } else {
                    setAlarm();
                }
            } else if (type.equals("sendQuestFinish")) {
                String QuestID = ((DiceGirl.SendQuestFinish)task).getQuestID();
                addLog("sendQuestFinish "+QuestID);
                if (QuestID.equals("DailyLogin")) {
                    String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
                    setLastDate(today);
                    new DiceGirl.SendQuestFinish(mDiceGirl, "PrizeByHour").start(mAction);
                } else {
                    String hour = new SimpleDateFormat("HH").format(new Date());
                    setLastHour(hour);
                    setAlarm();
                }
            }
        }
    };

    private PendingIntent getAlarmPendingIntent() {
        Intent intent = new Intent(this, DiceGirlService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        return pendingIntent;
    }

    private void setAlarm() {
        AlarmManager am = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        long triggerAtTime = System.currentTimeMillis() + 25*60*1000;
        am.set(AlarmManager.RTC, triggerAtTime, getAlarmPendingIntent());
    }

    private void cancelAlarm() {
        AlarmManager am = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        am.cancel(getAlarmPendingIntent());
    }

    private static void addLog(String text) {
        try {
            String data = new SimpleDateFormat("yyyyMMdd HHmmss ").format(new Date()) + text + "\r\n";
            FileOutputStream os = new FileOutputStream(new File(DiceGirl.sdcardPath+"/"+LOG_FILENAME), true);
            os.write(data.getBytes("UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getLog() {
        StringBuffer sb = new StringBuffer();
        try {
            FileInputStream is = new FileInputStream(new File(DiceGirl.sdcardPath+"/"+LOG_FILENAME));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while (null != (line = br.readLine())) {
                sb.append(line).append("\r\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public static boolean deleteLog() {
        try {
            File f = new File(DiceGirl.sdcardPath+"/"+LOG_FILENAME);
            f.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
