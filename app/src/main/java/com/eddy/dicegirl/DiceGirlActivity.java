package com.eddy.dicegirl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.eddy.game.R;
import com.eddy.game.util.SuperUser;

public class DiceGirlActivity extends Activity {

    private SuperUser mSU;
    private DiceGirl mDiceGirl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_girl);

        mSU = new SuperUser(this, DiceGirl.packageName);
        mDiceGirl = new DiceGirl(this);

        this.findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiceGirlActivity.this.startService(new Intent(DiceGirlActivity.this, DiceGirlService.class));
            }
        });
        this.findViewById(R.id.copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSU.copySharedPrefFile(DiceGirl.prefFilename, mHandler);
            }
        });
        this.findViewById(R.id.userinfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDiceGirl.readFile(DiceGirl.prefFilename)) {
                    new DiceGirl.SendUserLogon(mDiceGirl).start(mAction);
                }
            }
        });
    }

    private SuperUser.OnErrorHandler mHandler = new SuperUser.OnErrorHandler() {
        @Override
        public void OnException(Exception ex) {
            Log.d("Eddy", ex.getMessage());
        }

        @Override
        public void OnMessage(String message) {
            Log.d("Eddy", message);
        }
    };

    private DiceGirl.OnActionHandler mAction = new DiceGirl.OnActionHandler() {
        @Override
        public void onFinish(DiceGirl.MyTask task, boolean bSuccess, String result) {
            String type = task.getType();
            Log.d("Eddy", "type="+type+" result="+result);

            if (type.equals("sendUserLogon")) {
                new DiceGirl.GetUserInfo(mDiceGirl).start(mAction);
            } else if (type.equals("getUserInfo")) {

            } else if (type.equals("getRoleFiles")) {

            }
        }
    };

}
