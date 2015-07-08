package com.miui.jr;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.widget.Toast;


public class Test_C172763_CheckNetConnect01 extends InstrumentationTestCase {
    private Context mContext;
    private UiDevice mDevice;

    @Override
    protected void setUp() {
        try {
            super.setUp();
            mContext = this.getInstrumentation().getContext();
            mDevice = UiDevice.getInstance(getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void log(String msg) {
        Log.i("YU", msg);
    }


    private void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //case1:清除用户数据，点击apk，出现联网提示，点击“同意并继续”进入apk。
    public void testCheckNetConnect() {
        //清除小米金融数据
        log("Step 1 : clean up jr cahce.");
        testReSet();
        //点击apk
        log("Step 2 : open apk.");
        sleep(1);
        try {
            Intent intent = mContext.getPackageManager().getLaunchIntentForPackage("com.xiaomi.jr");
            mContext.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(mContext, "没有安装", Toast.LENGTH_LONG).show();
        }
        sleep(5);

        //点击同意并继续，如果能打开apk，就pass，没有打开apk就fail。
        log("Step 3 : Choose NetConnetToast Choice.");

        mDevice.findObject(By.text("同意并继续")).click();
        sleep(5);
        if ("com.xiaomi.jr".equals(getCurRunningActivityName())) {
            Toast.makeText(mContext, "pass", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(mContext, "fail", Toast.LENGTH_LONG).show();
        }
        mDevice.pressHome();
    }

    //获取当前运行的app
    public String getCurRunningActivityPackageName() {
        ActivityManager mgr = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = mgr.getRunningTasks(1).get(0);
        return info.topActivity.getPackageName();
    }

    public String getCurRunningActivityName() {

        return mDevice.getCurrentActivityName();
    }

    public void testReSet() {
        mDevice.pressMenu();
        sleep(5);
        mDevice.findObject(By.text("小米金融")).longClick();
        sleep(9);

        try {
            mDevice.findObject(By.text("清除数据")).click();
            sleep(5);
            mDevice.findObject(By.text("确定")).click();
            sleep(5);
            mDevice.findObject(By.text("结束运行")).click();
            sleep(5);
            mDevice.findObject(By.text("确定")).click();
            sleep(5);
            mDevice.pressHome();
            sleep(5);
        } catch (Exception e) {
            mDevice.pressHome();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        mDevice.pressHome();
        super.tearDown();
    }
}
