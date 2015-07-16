
package com.miui.securitycenter;

import android.content.Context;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

//C507227
public class test_00000009_IgnoreOptimizes extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;
    private Context mContext;
    private UiDevice mDevice;
    UiCollection listView;

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

    public void test_00000009_IgnoreOptimizes() throws Exception {
        mm.log("Step 1 : Launch contacts Activity.");
        mDevice.pressHome();
        mm.waitFor(1);
        mm.launchActivity("com.miui.securitycenter/com.miui.securitycenter.MainActivity");
        mm.waitFor(2);
        if (mm.exist(By.text("确定"))) {
            mm.click(By.text("确定"));
        }
        mm.waitFor(5);
        String currentPackageName = mDevice.getCurrentPackageName();
        Assert.assertEquals(currentPackageName, "com.miui.securitycenter");
        mm.waitFor(5);

        mm.log("Step 2 : Start Scan.");
        mDevice.findObject(
                By.clazz("android.widget.Button").res("com.miui.securitycenter:id/btn_action"))
                .click();
        mm.waitFor(15);

        mm.log("Step 3 : Ignore Optimizes");
        listView = new UiCollection(new UiSelector().className("android.widget.ListView"));
        UiObject layout = listView.getFromParent(new UiSelector().className(
                "android.widget.LinearLayout").index(5));
        layout.longClick();
        mm.waitFor(2);
        mm.click(By.text("忽略"));
        mm.waitFor(2);
    }
    
    @Override
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }
}
