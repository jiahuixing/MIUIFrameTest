package com.com.xiaomi.market;

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;
import android.util.Log;

import junit.framework.Assert;

public class Test_00000000_Unfavourite extends InstrumentationTestCase {
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

    public void testunfavourite() {
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

        log("Step 2 : Enter myaccount.");

        mDevice.findObject(By.clazz("android.widget.TextView").res("com.xiaomi.market:id/text").text("鎴戠殑")).click();
        sleep(8);
        mDevice.click(545, 1000);
        ;
        sleep(5);
        mDevice.findObject(By.textStartsWith("浼戦棽")).longClick();
        ;
        sleep(5);
        mDevice.findObject(By.clazz("android.widget.ImageView").res("com.xiaomi.market:id/first_button")).click();
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
