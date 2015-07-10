package com.com.miui.marmot.ETCase;

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;
import android.util.Log;

import junit.framework.Assert;


public class Test_C2152102_RecordingVideo extends InstrumentationTestCase {
    private Context mContext;
    private UiDevice mDevice;

    protected void setUp() throws Exception {
        super.setUp();
        mContext = this.getInstrumentation().getContext();
        mDevice = UiDevice.getInstance(getInstrumentation());
    }

    public void test_c2152102_recordingvideo() throws Exception {
        mDevice.pressHome();
        sleep(5);
        Intent intent = new Intent();
        intent.setClassName("com.android.camera",
                "com.android.camera.Camera");
        sleep(2);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        sleep(2);
        String currentPackageName = mDevice.getCurrentPackageName();
        Assert.assertEquals(currentPackageName, "com.android.camera");
        log(currentPackageName);
        sleep(2);
        log("step1: paizhao");
        mDevice.findObject(By.res("ccom.android.camera:id/v6_shutter_button_audio_sound")).click();
        sleep(2);
        log("step2:luzhi");
        mDevice.findObject(By.res("com.android.camera:id/v6_module_picker")).click();
        sleep(2);
        mDevice.findObject(By.res("com.android.camera:id/v6_shutter_button_internal")).click();
        sleep(5);
        mDevice.findObject(By.res("com.android.camera:id/v6_shutter_button_internal")).click();
        sleep(3);
        mDevice.pressBack();
        mDevice.pressHome();


    }

    private void log(String message) {
        // TODO Auto-generated method stub
        Log.i("MIUIAUTOTest", message);
    }

    private void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void tearDown() throws Exception {
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressHome();
        super.tearDown();
    }
}
