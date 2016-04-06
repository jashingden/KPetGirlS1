package com.eddy.kpetgirls1;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.c9entertainment.pet.s1.crypto.SimpleCrypto;
import com.c9entertainment.pet.s1.data.LevelData;
import com.eddy.game.R;
import com.eddy.game.util.SuperUser;
import com.eddy.kpetgirls1.net.DownloaderTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class KPetGirlS1Activity extends Activity {

    private SuperUser mSU;
    private Spinner mFileSpinner;
    private Spinner mFileSpinner2;
    private TextView mResultText;
    private EditText mEdit;
    private Button mButton;
    private LinearLayout mModify;
    private EditText mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mSU = new SuperUser(this, "com.c9entertainment.pet.s1");
        initUI();
    }

    private void initUI() {
        LinearLayout root = (LinearLayout)this.findViewById(R.id.root);

        final String[] files = new String[]{
                "--",
                "levelDataTitle.xml",
                "goldDataTitle.xml",
                "cashDataTitle.xml",
                "dressDataTitle.xml",
                "usetDataTitle.xml"};
        mFileSpinner = (Spinner)this.findViewById(R.id.sp);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_view, files);
        mFileSpinner.setAdapter(adapter);
        mFileSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String file = files[position];
                if (!file.equals("--") && mSU.copySharedPrefFile(file, mHandler)) {
                    dumpFile(file, true);
                    mFileSpinner2.setSelection(0);
                    mModify.removeAllViews();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mFileSpinner.requestFocus();
        final String[] files2 = new String[]{
                "--",
                "eventDataTitle.xml",
                "userDataTitle.xml"};
        mFileSpinner2 = (Spinner)this.findViewById(R.id.sp2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.item_view, files2);
        mFileSpinner2.setAdapter(adapter2);
        mFileSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String file = files2[position];
                if (!file.equals("--") && mSU.copySharedPrefFile(file, mHandler)) {
                    dumpFile(file, false);
                    mFileSpinner.setSelection(0);
                    mModify.removeAllViews();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mResultText = (TextView)this.findViewById(R.id.result);
        mModify = (LinearLayout)this.findViewById(R.id.modify_layout);
        mEdit = (EditText)this.findViewById(R.id.edit);
        mButton = (Button)this.findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEdit.getText().toString();
                if (input.length() > 0) {
                    try {
                        String output = SimpleCrypto.encrypt(LevelData.CRYPTO_KEY, input);
                        mEdit.setText(output);
                    } catch (Exception ex) {
                        mResultText.setText(ex.getMessage());
                    }
                }
            }
        });
        this.findViewById(R.id.modify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyFile();
            }
        });
        this.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFileSpinner.getSelectedItemPosition() > 0) {
                    saveFile(files[mFileSpinner.getSelectedItemPosition()], true);
                } else if (mFileSpinner2.getSelectedItemPosition() > 0) {
                    saveFile(files2[mFileSpinner2.getSelectedItemPosition()], false);
                }
            }
        });
        this.findViewById(R.id.restore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFileSpinner.getSelectedItemPosition() > 0) {
                    mSU.restoreSharedPrefFile(files[mFileSpinner.getSelectedItemPosition()], mHandler);
                } else if (mFileSpinner2.getSelectedItemPosition() > 0) {
                    mSU.restoreSharedPrefFile(files2[mFileSpinner2.getSelectedItemPosition()], mHandler);
                }
            }
        });
        this.findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFile();
            }
        });
        mMovie = (EditText)this.findViewById(R.id.movie);
        this.findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadMovie(mMovie.getText().toString());
            }
        });
    }

    public void updateResult(final String message) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mResultText.append(message + "\n");
            }
        });
    }

    private void downloadMovie(String text) {
        mResultText.setText("Start downloading...\n");
        if (TextUtils.isEmpty(text)) {
            String[] movie = new String[]{
                    "m_dating_0001", "m_dating_0002", "m_dating_0003", "m_dating_0004", "m_dating_0005",
                    "m_dating_0006", "m_dating_0007", "m_dating_0008", "m_dating_0009", "m_dating_0010",
                    "m_dating_0011", "m_dating_0012", "m_dating_0013", "m_dating_0014", "m_dating_0015",
                    "m_dating_0016", "m_dating_0017", "m_dating_0018", "m_dating_0019", "m_dating_0020",
                    "m_dating_0021", "m_dating_0022", "m_dating_0023", "m_dating_0024", "m_dating_0025",
                    "m_dating_0026", "m_dating_0027", "m_dating_0028", "m_dating_0029", "m_dating_0030",
                    "m_dating_0031", "m_dating_0032", "m_dating_0033", "m_dating_0034", "m_dating_0035",
                    "m_dating_0036",
                    "m_ending_0001", "m_ending_0003", "m_ending_0004", "m_ending_0005", "m_ending_0006",
                    "m_ending_0007",
                    "m_gift_0001", "m_gift_0002", "m_gift_0003", "m_gift_0004", "m_gift_0005",
                    "m_gift_0006", "m_gift_0007", "m_gift_0008", "m_gift_0009", "m_gift_0010",
                    "m_main_bikini", "m_main_coy", "m_main_sexy", "m_mode_pet", "m_mode_sports",
                    "m_paparazzi_0001", "m_paparazzi_0002", "m_paparazzi_0003", "m_paparazzi_0004", "m_paparazzi_0005",
                    "m_paparazzi_0006", "m_paparazzi_0007", "m_paparazzi_0008", "m_paparazzi_0009", "m_paparazzi_0010",
                    "m_secret_0001", "m_secret_0002", "m_secret_0003", "m_secret_0004", "m_secret_0005",
                    "m_secret_0006", "m_secret_0007", "m_secret_0008", "m_secret_0009", "m_secret_0010",
                    "m_secret_0011", "m_secret_0012", "m_secret_0013", "m_secret_0014"
            };
            DownloaderTask.askDownload(this, movie);
        } else {
            DownloaderTask.askDownload(this, new String[]{text});
        }
    }

    private SuperUser.OnErrorHandler mHandler = new SuperUser.OnErrorHandler() {
        @Override
        public void OnException(Exception ex) {
            Log.e("Eddy", ex.getMessage());
            mResultText.setText(ex.getMessage());
        }
        @Override
        public void OnMessage(String message) {
            mResultText.setText(message);
        }
    };

    private void clearFile() {
        mModify.removeAllViews();
        mSU.clearFile("*.xml", mHandler);
    }

    private void dumpFile(String filename, boolean decrypt) {
        StringBuffer sb = new StringBuffer();
        try {
            InputStream is = openFileInput(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            NodeList nodes = doc.getElementsByTagName(decrypt ? "string" : "int");

            for (int i = 0; i < nodes.getLength(); i++) {
                Element elem = (Element)nodes.item(i);

                if (decrypt) {
                    // string
                    sb.append(SimpleCrypto.decrypt(LevelData.CRYPTO_KEY, elem.getAttribute("name")));
                    sb.append("=");
                    sb.append(SimpleCrypto.decrypt(LevelData.CRYPTO_KEY, elem.getTextContent()));
                } else {
                    // int
                    sb.append(elem.getAttribute("name"));
                    sb.append("=");
                    sb.append(elem.getAttribute("value"));
                }
                sb.append("\n");
            }
        } catch (Exception ex) {
            sb.append(ex.getMessage());
        }
        mResultText.setText(sb.toString());
    }

    private void modifyFile() {
        String[] text = mResultText.getText().toString().split("\n");
        for (int i = 0; i < text.length; i++) {
            String[] val = text[i].split("=");
            LinearLayout item = new LinearLayout(this);
            item.setOrientation(LinearLayout.HORIZONTAL);
            View v = this.getLayoutInflater().inflate(R.layout.item_modify, item);
            ((TextView)v.findViewById(R.id.name)).setText(val[0]);
            ((EditText)v.findViewById(R.id.value)).setText(val[1]);
            mModify.addView(item);
        }
    }

    private void saveFile(String filename, boolean encrypt) {
        try {
            InputStream is = openFileInput(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            NodeList nodes = doc.getElementsByTagName(encrypt ? "string" : "int");

            for (int i = 0; i < nodes.getLength(); i++) {
                Element elem = (Element)nodes.item(i);
                View view = mModify.getChildAt(i);

                if (encrypt) {
                    // string
                    String name = ((TextView)view.findViewById(R.id.name)).getText().toString();
                    String value = ((EditText)view.findViewById(R.id.value)).getText().toString();
                    if (SimpleCrypto.encrypt(LevelData.CRYPTO_KEY, name).equals(elem.getAttribute("name"))) {
                        elem.setTextContent(SimpleCrypto.encrypt(LevelData.CRYPTO_KEY, value));
                    }
                } else {
                    // int
//                    sb.append(elem.getAttribute("name"));
//                    sb.append("=");
//                    sb.append(elem.getAttribute("value"));
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(this.getFilesDir().getAbsolutePath()+"/"+filename));
            Source input = new DOMSource(doc);
            transformer.transform(input, output);
            mResultText.setText("Save File Done");
        } catch (Exception ex) {
            mResultText.setText(ex.getMessage());
        }
    }
}
