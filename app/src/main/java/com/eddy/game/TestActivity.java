package com.eddy.game;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class TestActivity extends Activity {

    private LinearLayout root;
    private WebView wv;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        this.setContentView(root);

        final TextView info_view = new TextView(this);
        info_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent in = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    startActivity(in);
                } catch (Exception ex) {
                    info_view.append("Exceptoin:"+ex.getMessage()+"\n");
                }
            }
        });
        root.addView(info_view);
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                int enable = Settings.Secure.getInt(this.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
                info_view.append("ACCESSIBILITY_ENABLED=\n"+enable+"\n");
            } catch (Exception ex) {
                info_view.append("Exceptoin:"+ex.getMessage()+"\n");
            }
        }
        AccessibilityManager am = (AccessibilityManager)this.getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> infos = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK);
        for (AccessibilityServiceInfo asi : infos) {
            info_view.append(asi.getId()+"\n");
            info_view.append(asi.getDescription()+"\n");
        }

        View font_view = this.getLayoutInflater().inflate(R.layout.test_font, null);
        root.addView(font_view);
        final LinearLayout test_font = (LinearLayout)font_view.findViewById(R.id.font_list);
        final LinearLayout test_text = (LinearLayout)this.getLayoutInflater().inflate(R.layout.test_text, null);
        root.addView(test_text);
        for (int i = 0; i < test_font.getChildCount(); i++) {
            test_font.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String font = ((Button) v).getText().toString();
                    Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/" + font + ".ttf");
                    for (int i = 0; i < test_text.getChildCount(); i++) {
                        if (test_text.getChildAt(i) instanceof TextView) {
                            ((TextView) test_text.getChildAt(i)).setTypeface(tf);
                        }
                    }
                }
            });
        }

        back = new Button(this);
        back.setText("Back");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wv.canGoBack()) {
                    wv.goBack();
                }
            }
        });
        root.addView(back, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        wv = new WebView(this);
        wv.setWebViewClient(new MyWebViewClient());
        wv.setWebChromeClient(new WebChromeClient());
        wv.loadUrl("http://10.1.4.117/apk/test.html");
        root.addView(wv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("Eddy", "shouldOverrideUrlLoading=" + url);
            if (url.contains("play.google.com") || url.contains(".pdf") || url.contains(".apk")) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

}
