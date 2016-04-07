package com.eddy.dicegirl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eddy.game.R;
import com.eddy.game.util.ImageViewUrl;
import com.eddy.game.util.OpenMovie;
import com.eddy.game.util.SuperUser;
import com.eddy.game.util.ViewUtility;

import java.io.File;

public class DiceGirlActivity extends Activity {

    private final static String SDCARD_PATH = "/storage/sdcard1/data/com.eddy.game/dicegirl";

    private SuperUser mSU;
    private DiceGirl mDiceGirl;
    private LinearLayout mModels;
    private LinearLayout mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_girl);

        File file = new File(SDCARD_PATH);
        file.mkdirs();

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

        mModels = (LinearLayout)this.findViewById(R.id.models);
        mMovies = (LinearLayout)this.findViewById(R.id.movies);
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
            Log.d("Eddy", "type=" + type + " result=" + result);

            if (type.equals("sendUserLogon")) {
                new DiceGirl.GetUserInfo(mDiceGirl).start(mAction);
            } else if (type.equals("getUserInfo")) {
                if (bSuccess) {
                    createModels((DiceGirl.GetUserInfo)task);
                }
            } else if (type.equals("getRoleFiles")) {
                if (bSuccess) {
                    addRoleInfo((DiceGirl.GetRoleFiles)task);
                }
            }
        }
    };

    private void createModels(final DiceGirl.GetUserInfo task) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mModels.removeAllViews();
                mMovies.removeAllViews();
                for (String[] model : task.models) {
                    Button btn = new Button(DiceGirlActivity.this);
                    btn.setText(model[1]);
                    btn.setTag(model[0]);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new DiceGirl.GetRoleFiles(mDiceGirl, (String) v.getTag()).start(mAction);
                        }
                    });
                    mModels.addView(btn);
                }
            }
        });
    }

    private void addRoleInfo(final DiceGirl.GetRoleFiles task) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMovies.removeAllViews();
                Activity activity = DiceGirlActivity.this;
                LinearLayout role = (LinearLayout)activity.getLayoutInflater().inflate(R.layout.item_role, mMovies);

                int px_200 = ViewUtility.getDimensionPixelSize(activity, 200);
                int padding = ViewUtility.getDimensionPixelSize(activity, 10);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                ImageViewUrl image = (ImageViewUrl)role.findViewById(R.id.role_img);
                image.setBitmapOptions(options);
                image.load(task.Image, SDCARD_PATH);
                image.setAdjustViewBounds(true);
                image.setMaxWidth(px_200);
                image.setMaxHeight(px_200);

                StringBuffer sb = new StringBuffer();
                for (String val : task.Info) {
                    sb.append(val).append("\n");
                }
                ((TextView)role.findViewById(R.id.role_info)).setText(sb.toString());

                for (int i=0; i<10; i++) {
                    LinearLayout layout = new LinearLayout(activity);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    layout.setPadding(padding, padding, padding, padding);
                    layout.setGravity(Gravity.CENTER_VERTICAL);
                    if (task.Video.length > i) {
                        View preview = null;
                        if (task.Preview.length > i) {
                            ImageViewUrl img = new ImageViewUrl(activity);
                            img.load(task.Preview[i], SDCARD_PATH);
                            img.setAdjustViewBounds(true);
                            img.setMaxWidth(px_200);
                            img.setMaxHeight(px_200);
                            preview = img;
                        } else {
                            Button btn = new Button(activity);
                            btn.setText("影片");
                            preview = btn;
                        }
                        preview.setTag(task.Video[i]);
                        preview.setOnClickListener(onClick);
                        layout.addView(preview);
                        if (task.OpenFlag.length > i) {
                            TextView open = new TextView(activity);
                            open.setText(task.OpenFlag[i]);
                            open.setPadding(padding, padding, padding, padding);
                            layout.addView(open);
                        }
                    }
                    role.addView(layout);
                }
            }
        });
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = (String)v.getTag();
            new OpenMovie(DiceGirlActivity.this).start(url, SDCARD_PATH);
        }
    };

}
