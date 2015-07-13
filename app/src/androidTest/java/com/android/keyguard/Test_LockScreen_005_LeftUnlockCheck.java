package com.android.keyguard;

import android.os.RemoteException;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;
import android.util.Log;

import junit.framework.Assert;

public class Test_LockScreen_005_LeftUnlockCheck extends InstrumentationTestCase{
    private UiDevice mDevice;

    @Override
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
        log("Step 2 : WakeUP Unlock & check");
        try {
            mDevice.wakeUp();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        sleep(3);
        int x1 = (int)(mDevice.getDisplayWidth()/4*3);
        int x2 = (int)(mDevice.getDisplayWidth()/4);
        int y1 = (int)(mDevice.getDisplayHeight()/4);
        mDevice.drag(x1, y1, x2, y1, 10);
        sleep(5);
        String currentPackageName = mDevice.getCurrentPackageName();
        Assert.assertEquals("com.android.camera",currentPackageName);

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