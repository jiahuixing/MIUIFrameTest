
package com.miui.securitycenter;

import android.content.Context;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

//C507260
public class Test_00000005_ShowMenu extends InstrumentationTestCase {

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

    public void test_1_Showmenu() {
        mm.log("Step 1 : Launch Activity.");
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

        mm.log("Step 2 : Enter the settings page.");
        mDevice.findObject(
                By.clazz("android.widget.Button").res("com.miui.securitycenter:id/settings"))
                .click();
        mm.waitFor(2);

        mm.log("Step 3 : Show menu in notification shade.");
        UiObject2 showBox = mDevice.findObject(By.clazz("android.widget.CheckBox").res(
                "android:id/checkbox"));
        Boolean isShow = showBox.isChecked();
        if (!isShow) {
            showBox.click();
            mm.waitFor(3);
            isShow = showBox.isChecked();
        }
        Assert.assertTrue(isShow);
    }

    @Override
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }

}
