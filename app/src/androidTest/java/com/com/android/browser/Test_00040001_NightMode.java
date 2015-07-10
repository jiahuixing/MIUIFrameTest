package com.com.android.browser;

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00040001_NightMode extends InstrumentationTestCase {

    public Marmot mm;
    public Checker cc;
    private Context mContext;
    private UiDevice mDevice;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);

        try {
            super.setUp();
            mContext = this.getInstrumentation().getContext();
            mDevice = UiDevice.getInstance(getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test_00040001_NightMode() throws Exception {
        mm.log("Step 1 : Launch Browser Activity.");
        Intent intent = new Intent();
        intent.setClassName("com.android.browser",
                "com.android.browser.BrowserActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        mm.waitFor(5);

        // 点击浏览器选项
        UiObject2 BrowserOpions = mDevice.findObject(By.clazz(
                "android.widget.ImageButton").res(
                "com.android.browser:id/action_more"));
        BrowserOpions.click();
        mm.waitFor(5);

        mm.log("Step 2 : Open NightMode.");
        // 点击浏览器夜间模式
        mDevice.findObject(
                By.clazz("android.widget.TextView").textContains("夜间模式"))
                .click();
        mm.waitFor(5);

        // 点击浏览器选项
        BrowserOpions.click();
        mm.waitFor(5);

        UiObject2 BrowserNightModeFlag = mDevice.findObject(By.clazz(
                "android.widget.TextView").text("夜间模式/开"));

        mm.waitFor(5);

        if (BrowserNightModeFlag != null) {

            assertTrue(Boolean.TRUE);
        } else {

            assertTrue(Boolean.FALSE);
        }

        // 点击浏览器夜间模式
        mDevice.findObject(
                By.clazz("android.widget.TextView").textContains("夜间模式"))
                .click();

        mm.waitFor(3);

    }

    @Override
    protected void tearDown() throws Exception {
        if (mDevice.findObject(By.clazz("android.widget.ImageButton").res(
                "com.android.browser:id/action_more")) != null) {
            // log("pass");
            mDevice.findObject(
                    By.clazz("android.widget.ImageButton").res(
                            "com.android.browser:id/action_more")).click();
            mm.waitFor(3);
            mDevice.findObject(
                    By.clazz("android.widget.TextView").textContains("退出"))
                    .click();
            mm.waitFor(3);
        }

        mm.pressBack(3);
        super.tearDown();
    }

}
