package com.android.keyguard;

import android.os.RemoteException;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

public class Test_LockScreen_002_NumUnlockCheck extends InstrumentationTestCase {
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
        mm.log("Step 2 : WakeUP");
        try {
            mDevice.wakeUp();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mm.waitFor(3);
        int x1 = (int) (mDevice.getDisplayWidth() / 2);
        int y1 = (int) (mDevice.getDisplayHeight() / 3 * 2);
        int y2 = (int) (mDevice.getDisplayHeight() / 3);
        mDevice.drag(x1, y1, x1, y2, 10);
        mm.waitFor(2);
        mm.log("Step 3 : numUnlock & check");
        mDevice.findObject(By.clazz("android.widget.TextView").text("1"))
                .click();
        mDevice.findObject(By.clazz("android.widget.TextView").text("2"))
                .click();
        mDevice.findObject(By.clazz("android.widget.TextView").text("3"))
                .click();
        mDevice.findObject(By.clazz("android.widget.TextView").text("4"))
                .click();
        mm.waitFor(5);
        mDevice.pressHome();
        String currentPackageName = mDevice.getCurrentPackageName();
        Assert.assertEquals("com.miui.home", currentPackageName);

    }

   
    @Override
    protected void tearDown() throws Exception {
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressHome();
        super.tearDown();
    }
}