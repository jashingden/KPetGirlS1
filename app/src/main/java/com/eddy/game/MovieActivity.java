package com.eddy.game;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

public class MovieActivity extends Activity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        mVideoView = new VideoView(this);
        setContentView(mVideoView);

        Uri uri = this.getIntent().getData();
        MediaController localMediaController = new MediaController(this);
        localMediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(localMediaController);
        mVideoView.setVideoURI(uri);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer paramMediaPlayer) {
            }
        });
        mVideoView.start();
    }

}
