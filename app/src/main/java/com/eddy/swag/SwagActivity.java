package com.eddy.swag;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.eddy.game.MovieActivity;
import com.eddy.game.R;
import com.eddy.game.util.SuperUser;
import com.eddy.game.util.ViewUtility;

import java.io.File;
import java.util.ArrayList;

public class SwagActivity extends Activity {

    private final static String packageName = "com.machipopo.swag";
    private final static String imagePath = SuperUser.sdcardPath + "/swag/image";
    private final static String moviePath = SuperUser.sdcardPath + "/swag/mp4";

    private SuperUser mSU;
    private ListView mListView;
    private ArrayAdapter<String> mListAdapter;
    private String mDir;
    private ArrayList<String> mFileList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swag);

        File file = new File(moviePath);
        file.mkdirs();
        file = new File(imagePath);
        file.mkdirs();

        mSU = new SuperUser(this, packageName);

        this.findViewById(R.id.copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSU.copyFiles("cache", "swag/mp4", "*.mp4", true, mHandler)) {
                    ViewUtility.LogAndToast(SwagActivity.this, "copy mp4 files succeeded");
                }
                if (mSU.copyFiles("cache/image_manager_disk_cache", "swag/image", "*", true, mHandler)) {
                    ViewUtility.LogAndToast(SwagActivity.this, "copy jpg files succeeded");
                }
            }
        });
        this.findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDir = imagePath;
                listFiles(".jpg", true);
            }
        });
        this.findViewById(R.id.video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDir = moviePath;
                listFiles(".mp4", false);
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
        if (mDir == null || mDir.equals(moviePath)) {
            this.findViewById(R.id.video).performClick();
        } else {
            this.findViewById(R.id.image).performClick();
        }
        super.onResume();
    }

    private void listFiles(String ext, boolean rename) {
        mFileList.clear();
        File file = new File(mDir);
        ArrayList<File> files = new ArrayList<File>();

        for (File f : file.listFiles()) {
            if (!f.getName().endsWith(ext)) {
                if (rename) {
                    String path = f.getPath();
                    f = new File(path.substring(0, path.lastIndexOf("."))+ext);
                    if (!new File(path).renameTo(f)) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            // insert into sorted array
            boolean insert = false;
            for (int i=0; i<files.size(); i++) {
                if (files.get(i).lastModified() < f.lastModified()) {
                    files.add(i, f);
                    insert = true;
                    break;
                }
            }
            if (!insert) {
                files.add(f);
            }
        }
        for (File f : files) {
            mFileList.add(f.getName());
        }
        mListAdapter.notifyDataSetChanged();
    }

    private void open(int pos) {
        String filePath = mDir + "/" + mFileList.get(pos);
        Intent intent = null;
        if (filePath.endsWith(".mp4")) {
            intent = new Intent(this, MovieActivity.class);
            intent.setDataAndType(Uri.parse(filePath), "video/mp4");
            intent.putExtra("orientation", "port");
            intent.putExtra("position", pos);
            intent.putStringArrayListExtra("files", mFileList);
            intent.putExtra("dir", mDir);
        } else if (filePath.endsWith(".jpg")) {
//            intent = new Intent(Intent.ACTION_VIEW);
//            intent.setDataAndType(Uri.parse(filePath), "image/jpeg");
            ((ImageView)this.findViewById(R.id.img)).setImageURI(Uri.parse(filePath));
        }
        if (intent != null) {
            startActivity(intent);
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
