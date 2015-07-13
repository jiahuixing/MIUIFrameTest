package com.android.keyguard;

import android.os.RemoteException;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;
import android.util.Log;

import junit.framework.Assert;

public class Test_LockScreen_002_NumUnlockCheck extends InstrumentationTestCase {
    private UiDevice mDevice;
    protected void setUp() {
        try {
            super.setUp();
            this.getInstrumentation().getContext();
            mDevice = UiDevice.getInstance(getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testUnlock() throws RemoteException {
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
        mDevice.findObject(By.clazz("android.widget.TextView").text("1")).click();
        mDevice.findObject(By.clazz("android.widget.TextView").text("2")).click();
        mDevice.findObject(By.clazz("android.widget.TextView").text("3")).click();
        mDevice.findObject(By.clazz("android.widget.TextView").text("4")).click();
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