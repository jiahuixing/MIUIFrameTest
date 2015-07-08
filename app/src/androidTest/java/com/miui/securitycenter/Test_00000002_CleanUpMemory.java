package com.miui.securitycenter;

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

public class Test_00000002_CleanUpMemory extends InstrumentationTestCase {
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

    public void test_00000002_CleanUpMemory() {
        mm.log("Step 1 : Launch contacts Activity.");
        mDevice.pressHome();
        mm.waitFor(1);
        Intent intent = new Intent();
        intent.setClassName("com.miui.securitycenter", "com.miui.securitycenter.MainActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        mm.waitFor(5);
        String currentPackageName = mDevice.getCurrentPackageName();
        Assert.assertEquals(currentPackageName, "com.miui.securitycenter");
        mm.waitFor(5);

        mm.log("Step 2 : Start Scan.");
        mDevice.findObject(By.clazz("android.widget.Button").res("com.miui.securitycenter:id/btn_action")).click();
        mm.waitFor(15);

        mm.log("Step 3 : Clean Up Memory");
        mDevice.findObject(By.text("占用内存")).click();
        mm.waitFor(2);
        UiObject2 clearUp = mDevice.findObject(By.text("一键清理"));
        if (clearUp != null && clearUp.isEnabled()) {
//  先不执行此动作，清理内存后自动化case也被清理
//    clearUp.click();
//
            mm.waitFor(5);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }
}

