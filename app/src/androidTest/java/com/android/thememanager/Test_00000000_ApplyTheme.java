package com.android.thememanager;

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;


public class Test_00000000_ApplyTheme extends InstrumentationTestCase {

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

    //  应用主题
    public void testApplyTheme() throws Exception {
        mDevice.pressHome();
        mm.waitFor(2);
        Intent intent = new Intent();
        intent.setClassName("com.android.thememanager",
                "com.android.thememanager.ThemeResourceTabActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        mm.waitFor(5);

        UiObject2 theme3 = mDevice.findObject(By.clazz("android.widget.TextView").text("本地"));
        theme3.click();
        mm.waitFor(10);

        UiObject2 theme4 = mDevice.findObject(By.clazz("android.widget.FrameLayout").res("com.android.thememanager:id/root_flag"));
        theme4.click();
        mm.waitFor(10);

        UiObject2 theme5 = mDevice.findObject(By.clazz("android.widget.Button").res("com.android.thememanager:id/applyBtn"));
        theme5.click();
        mm.waitFor(5);

        mContext.startActivity(intent);

        mDevice.pressBack();
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


