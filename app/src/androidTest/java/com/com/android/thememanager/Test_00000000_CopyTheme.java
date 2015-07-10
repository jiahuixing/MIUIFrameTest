package com.com.android.thememanager;

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;


public class Test_00000000_CopyTheme extends InstrumentationTestCase {

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

    //  备份主题
    public void testCopyTheme() throws Exception {
        mDevice.pressHome();
        mm.waitFor(2);
        Intent intent = new Intent();
        intent.setClassName("com.android.thememanager",
                "com.android.thememanager.ThemeResourceTabActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        mm.waitFor(5);

        UiObject2 theme8 = mDevice.findObject(By.clazz("android.widget.TextView").text("混搭"));
        theme8.click();
        mm.waitFor(10);

        UiObject2 theme9 = mDevice.findObject(By.clazz("android.widget.Button").text("更多"));
        theme9.click();
        mm.waitFor(5);

        UiObject2 theme10 = mDevice.findObject(By.clazz("android.widget.TextView").text("备份当前主题设置"));
        theme10.click();
        mm.waitFor(5);

        UiObject2 theme11 = mDevice.findObject(By.clazz("android.widget.Button").text("备份"));
        theme11.click();
        mm.waitFor(5);

        UiObject2 theme12 = mDevice.findObject(By.clazz("android.widget.Button").text("确定"));
        theme12.click();
        mm.waitFor(5);

        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressHome();
    }

    @Override
    protected void tearDown() throws Exception {
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressHome();
        super.tearDown();
    }


}


