package com.android.browser;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00050001_BrowserHistory extends InstrumentationTestCase {

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

    public void test_00050001_BrowserHistory() throws Exception {
        mm.log("Step 1 : Launch Browser Activity.");
        Intent intent = new Intent();
        intent.setClassName("com.android.browser",
                "com.android.browser.BrowserActivity");
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        mm.waitFor(5);

        // 点击浏览器选项
        UiObject2 BrowserOpions = mDevice.findObject(By.clazz(
                "android.widget.ImageButton").res(
                "com.android.browser:id/action_more"));
        BrowserOpions.click();
        mm.waitFor(5);


        mm.log("Step 2 : Clear History.");
        // 点击浏览器书签历史
        mDevice.findObject(
                By.clazz("android.widget.TextView").textContains("历史")).click();
        mm.waitFor(5);
        mDevice.findObject(
                By.clazz("android.widget.Button").textContains("历史记录")).click();
        mm.waitFor(5);
        mDevice.findObject(
                By.clazz("android.widget.Button").textContains("清除历史")).click();
        mm.waitFor(5);

        if (mDevice.findObject(By.textContains("要删除浏览器所有的历史")) != null) {
            // log("pass");
            mDevice.findObject(By.text("确定")).click();
        }
        mm.waitFor(5);

        assertTrue(mDevice.findObject(By.text("历史记录为空")) != null);
        mm.pressBack(1);


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
