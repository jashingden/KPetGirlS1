package com.eddy.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eddy.dicegirl.DiceGirlActivity;
import com.eddy.kpetgirls1.KPetGirlS1Activity;

public class MainActivity extends Activity {

    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        this.setContentView(root);

        addCrackedApp(R.drawable.app_icon_kpetgirls1, R.string.app_name_kpetgirls1, KPetGirlS1Activity.class);
        addCrackedApp(R.drawable.app_icon_dicegirl, R.string.app_name_dicegirl, DiceGirlActivity.class);
    }

    private void addCrackedApp(int iconId, int nameId, final Class<?> activityClass) {
        LinearLayout item = (LinearLayout)this.getLayoutInflater().inflate(R.layout.item_app, null);
        ((ImageView)item.findViewById(R.id.icon)).setImageResource(iconId);
        ((TextView)item.findViewById(R.id.name)).setText(nameId);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activityClass));
                MainActivity.this.finish();
            }
        });
        root.addView(item);
    }

}
