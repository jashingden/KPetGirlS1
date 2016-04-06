package com.eddy.dicegirl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class DiceGirlService extends Service {

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
            new DiceGirl.SendUserLogon(mDiceGirl).start(mAction);
        }
    }

    @Override
    public void onDestroy() {
        cancelAlarm();
    }

    private DiceGirl.OnActionHandler mAction = new DiceGirl.OnActionHandler() {
        @Override
        public void onFinish(DiceGirl.MyTask task, boolean bSuccess, String result) {
            String type = task.getType();
            Log.d("Eddy", "type=" + type + " bSuccess=" + bSuccess + " result=" + result);

            if (type.equals("sendUserLogon")) {
                new DiceGirl.GetUnFinishQuestList(mDiceGirl).start(mAction);
            } else if (type.equals("getUnFinishQuestList")) {
                if (((DiceGirl.GetUnFinishQuestList)task).PrizeByHour_Is_Finish == true) {
                    new DiceGirl.SendQuestFinish(mDiceGirl, "PrizeByHour").start(mAction);
                } else {
                    setAlarm();
                }
            } else if (type.equals("sendQuestFinish")) {
                setAlarm();
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

}
