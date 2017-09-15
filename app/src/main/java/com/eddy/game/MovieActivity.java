package com.eddy.game;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.eddy.game.util.ViewUtility;

import java.io.File;
import java.util.ArrayList;

public class MovieActivity extends Activity {

    private LinearLayout mControl;
    private VideoView mVideoView;
    private Uri mUri;
    private int mPosition;
    private String mDir;
    private ArrayList<String> mFileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = this.getIntent();
        if (intent.getStringExtra("orientation").equals("port")) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        setContentView(R.layout.activity_movie);

        this.findViewById(R.id.replay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVideoView.seekTo(0);
                mVideoView.start();
                mControl.setVisibility(View.GONE);
            }
        });
        this.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File f = new File(mUri.getPath());
                if (f.delete()) {
                    ViewUtility.LogAndToast(MovieActivity.this, "File: "+f.getName()+" 已刪除!");
                    mControl.findViewById(R.id.next).performClick();
                }
            }
        });
        this.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFileList != null && mPosition + 1 < mFileList.size()) {
                    mPosition++;
                    String filePath = mDir + "/" + mFileList.get(mPosition);
                    mUri = Uri.parse(filePath);
                    mVideoView.setVideoURI(mUri);
                    mControl.findViewById(R.id.replay).performClick();
                } else {
                    finish();
                }
            }
        });

        mControl = (LinearLayout)this.findViewById(R.id.control);
        mVideoView = (VideoView)this.findViewById(R.id.video);
        mUri = intent.getData();
        mPosition = intent.getIntExtra("position", -1);
        if (mPosition >= 0) {
            mDir = intent.getStringExtra("dir");
            mFileList = intent.getStringArrayListExtra("files");
        }

        MediaController localMediaController = new MediaController(this);
        localMediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(localMediaController);
        mVideoView.setVideoURI(mUri);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer paramMediaPlayer) {
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mControl.setVisibility(View.VISIBLE);
            }
        });
        mVideoView.start();

        if (intent.getBooleanExtra("auto", false)) {
            handler.sendEmptyMessageDelayed(0, 3000);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                if (mControl.getVisibility() == View.VISIBLE) {
                    mControl.findViewById(R.id.delete).performClick();
                }
                this.sendEmptyMessageDelayed(0, 3000);
            }
        }
    };
}
