package com.android.keyguard;

import android.os.RemoteException;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

public class Test_LockScreen_001_UnlockCheck extends InstrumentationTestCase {
    private UiDevice mDevice;
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
        try {
            super.setUp();
            // mContext = this.getInstrumentation().getContext();
            mDevice = UiDevice.getInstance(getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testUnlock() throws RemoteException {
        mm.log("Step 1 : Sleep.");
        mDevice.sleep();
        mm.waitFor(2);
        mm.log("Step 2 : WakeUP");
        mDevice.wakeUp();      
        mm.waitFor(3);
        int x1 = (int) (mDevice.getDisplayWidth() / 2);
        int y1 = (int) (mDevice.getDisplayHeight() / 5 * 4);
        int y2 = (int) (mDevice.getDisplayHeight() / 5 * 4 - mDevice.getDisplayWidth()/2);//防误触判断距离大于宽度的1/3
        mDevice.swipe(x1, y1, x1, y2, 10);
        mDevice.pressHome();
        mm.waitFor(5);
        String currentPackageName = mDevice.getCurrentPackageName();
        Assert.assertEquals("com.miui.home", currentPackageName);

    }

    // private void log(String message) {
    // Log.i("MIUIAUTOTest", message);
    // }
    //
    // private void sleep(long seconds) {
    // try {
    // Thread.sleep(seconds * 1000);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }

    @Override
    protected void tearDown() throws Exception {
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressHome();
        super.tearDown();
    }
}