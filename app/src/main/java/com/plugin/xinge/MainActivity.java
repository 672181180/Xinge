package com.plugin.xinge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.android.tpush.XGPushManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XGPushManager.registerPush(getApplicationContext());
        // SampleApp.registerPush(this, "222");
    }
}
