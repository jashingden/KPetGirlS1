package com.eddy.swag;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.eddy.game.MovieActivity;
import com.eddy.game.R;
import com.eddy.game.util.SuperUser;
import com.eddy.game.util.ViewUtility;

import java.io.File;
import java.util.ArrayList;

public class SwagActivity extends Activity {

    private final static String packageName = "com.machipopo.swag";
    private final static String sdcardPath = SuperUser.sdcardPath + "/swag/mp4";

    private SuperUser mSU;
    private ListView mListView;
    private ArrayAdapter<String> mListAdapter;
    private ArrayList<String> mFileList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swag);

        File file = new File(sdcardPath);
        file.mkdirs();

        mSU = new SuperUser(this, packageName);

        this.findViewById(R.id.copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSU.copyFiles("cache", "swag/mp4", "*.mp4", true, mHandler)) {
                    ViewUtility.LogAndToast(SwagActivity.this, "copy mp4 files succeeded");
                }
            }
        });
        this.findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        this.findViewById(R.id.video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listFiles(".mp4");
            }
        });
        mListView = (ListView)this.findViewById(R.id.list_item);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long l) {
                open(pos);
            }
        });
        mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mFileList);
        mListView.setAdapter(mListAdapter);
    }

    @Override
    protected void onResume() {
        this.findViewById(R.id.video).performClick();
        super.onResume();
    }

    private void listFiles(String ext) {
        mFileList.clear();
        File file = new File(sdcardPath);
        ArrayList<File> files = new ArrayList<File>();

        for (File f : file.listFiles()) {
            if (f.getName().endsWith(ext)) {
                // insert into sorted array
                boolean insert = false;
                for (int i=0; i<files.size(); i++) {
                    if (files.get(i).lastModified() < f.lastModified()) {
                        files.set(i, f);
                        insert = true;
                        break;
                    }
                }
                if (!insert) {
                    files.add(f);
                }
            }
        }
        for (File f : files) {
            mFileList.add(f.getName());
        }
        mListAdapter.notifyDataSetChanged();
    }

    private void open(int pos) {
        Intent intent = new Intent(this, MovieActivity.class);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
        String filePath = sdcardPath + "/" + mFileList.get(pos);
        Uri uri = Uri.parse(filePath);
        if (filePath.indexOf(".mp4") >= 0) {
            intent.setDataAndType(uri, "video/mp4");
        } else if (filePath.indexOf("jpg") >= 0) {
            intent.setDataAndType(uri, "video/jpeg");
        } else {
            intent.setData(uri);
        }
        intent.putExtra("orientation", "port");
        intent.putExtra("position", pos);
        intent.putStringArrayListExtra("files", mFileList);
        intent.putExtra("dir", sdcardPath);
        try {
            startActivity(intent);
        } catch (Exception ex) {
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }

    private SuperUser.OnErrorHandler mHandler = new SuperUser.OnErrorHandler() {
        @Override
        public void OnException(Exception ex) {
            ViewUtility.LogAndToast(SwagActivity.this, ex.getMessage());
        }

        @Override
        public void OnMessage(String message) {
            ViewUtility.LogAndToast(SwagActivity.this, message);
        }
    };

}
