package com.android.keyguard;

import android.os.RemoteException;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

public class Test_LockScreen_005_LeftUnlockCheck extends
        InstrumentationTestCase {
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
        try {
            mDevice.sleep();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mm.waitFor(2);
        mm.log("Step 2 : WakeUP Unlock & check");
        try {
            mDevice.wakeUp();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mm.waitFor(3);
        int x1 = (int) (mDevice.getDisplayWidth() / 4 * 3);
        int x2 = (int) (mDevice.getDisplayWidth() / 4);
        int y1 = (int) (mDevice.getDisplayHeight() / 4);
        mDevice.drag(x1, y1, x2, y1, 10);
        mm.waitFor(5);
        String currentPackageName = mDevice.getCurrentPackageName();
        Assert.assertEquals("com.android.camera", currentPackageName);

    }

    

    @Override
    protected void tearDown() throws Exception {
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressHome();
        super.tearDown();
    }
}