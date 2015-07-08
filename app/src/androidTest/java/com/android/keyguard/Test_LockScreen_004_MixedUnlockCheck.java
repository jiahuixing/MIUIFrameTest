package com.android.keyguard;

import android.os.RemoteException;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;
import android.util.Log;

import junit.framework.Assert;

import java.io.IOException;


public class Test_LockScreen_004_MixedUnlockCheck extends InstrumentationTestCase {
    private UiDevice mDevice;

    //    private static final String KEY1 = "l1234";
    protected void setUp() {
        try {
            super.setUp();
            this.getInstrumentation().getContext();
            mDevice = UiDevice.getInstance(getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testUnlock() throws RemoteException, IOException {
        log("Step 1 : Sleep.");
        try {
            mDevice.sleep();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        sleep(2);
        log("Step 2 : WakeUP");
        try {
            mDevice.wakeUp();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        sleep(3);
        int x1 = (int) (mDevice.getDisplayWidth() / 2);
        int y1 = (int) (mDevice.getDisplayHeight() / 3 * 2);
        int y2 = (int) (mDevice.getDisplayHeight() / 3);
        mDevice.drag(x1, y1, x1, y2, 10);
        sleep(2);
        log("Step 3 : numUnlock & check");
//        UiObject2 password = mDevice.findObject(By.clazz("android.widget.EditText"));
//        password.setText(KEY1);
//        Process execute = Runtime.getRuntime().exec("input text " + KEY1);
        for (int i = 1; i <= 4; i++) {
            mDevice.findObject(By.clazz("android.widget.TextView").text("l")).click();
        }
        sleep(1);
        mDevice.findObject(By.clazz("android.widget.TextView").res("android.miui:id/btn_letter_ok")).click();
        sleep(5);
        mDevice.pressHome();
        String currentPackageName = mDevice.getCurrentPackageName();
        Assert.assertEquals("com.miui.home", currentPackageName);

    }

    private void log(String message) {
        Log.i("MIUIAUTOTest", message);
    }

    private void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressHome();
        super.tearDown();
    }
}