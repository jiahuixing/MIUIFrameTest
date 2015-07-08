package com.xiaomi.market;

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;
import android.util.Log;

import junit.framework.Assert;

public class Test_00000000_Pauseandcontinue extends InstrumentationTestCase {
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

    public void testmarketsearch() {
        log("Step 1 : Launch supermarket Activity.");
        mDevice.pressHome();
        sleep(1);
        Intent intent = new Intent();
        intent.setClassName("com.xiaomi.market",
                "com.xiaomi.market.ui.MarketTabActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        sleep(5);
        String currentPackageName = mDevice.getCurrentPackageName();
        Assert.assertEquals(currentPackageName, "com.xiaomi.market");

        log("Step 2 : Enter app information and app operation.");

        mDevice.click(545, 1480);
        sleep(5);
        mDevice.findObject(By.clazz("android.widget.Button").text("瀹夎")).click();
        sleep(10);
        mDevice.findObject(By.clazz("android.widget.Button").text("鏆傚仠")).click();
        sleep(5);
        mDevice.findObject(By.clazz("android.widget.Button").text("缁х画")).click();
        sleep(5);

        log("Step 3 : Quit.");
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressHome();
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

    }
}
