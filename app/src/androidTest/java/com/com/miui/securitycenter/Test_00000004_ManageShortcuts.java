package com.com.miui.securitycenter;

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

public class Test_00000004_ManageShortcuts extends InstrumentationTestCase {
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

    public void test_3_CleanRubishCache() {
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

        mm.log("Step 2 : creat shortcuts");
        mDevice.findObject(By.text("垃圾清理")).longClick();
        mm.waitFor(2);
        mDevice.findObject(By.text("创建桌面快捷方式")).click();
        mm.waitFor(2);

        mm.log("Step 3 : Delete shortcuts");
        mDevice.findObject(By.clazz("android.widget.Button").res("com.miui.securitycenter:id/settings")).click();
        mm.waitFor(2);
        mDevice.findObject(By.text("添加桌面快捷方式")).click();
        mm.waitFor(2);

        UiObject2 cleaner = mDevice.findObject(By.text("垃圾清理")).getParent().findObject(By.clazz("android.widget.CheckBox"));
        if (cleaner != null) {
            assertTrue(cleaner.isChecked());
            cleaner.click();
            mm.waitFor(2);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }
}
