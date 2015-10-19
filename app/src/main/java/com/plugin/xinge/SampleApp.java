package com.plugin.xinge;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGNotifaction;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushNotifactionCallback;

import java.util.List;

/**
 * app
 * Created by killer on 15/10/19..
 */
public class SampleApp extends Application {

    static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        if (isMainProcess()) {
            XGPushManager.setNotifactionCallback(new XGPushNotifactionCallback() {

                @Override
                public void handleNotify(XGNotifaction xgNotifaction) {
                    xgNotifaction.doNotify();
                }
            });
        }
    }

    public boolean isMainProcess() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public static void registerPush(Context context, String account) {
        XGPushManager.registerPush(context.getApplicationContext(), account, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {
                Toast.makeText(appContext, o.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Object o, int i, String s) {

            }
        });
    }
}
