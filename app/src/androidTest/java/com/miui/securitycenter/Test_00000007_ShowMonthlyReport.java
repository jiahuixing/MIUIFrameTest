
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

public class Test_00000007_ShowMonthlyReport extends InstrumentationTestCase {
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

    public void test_00000007_ShowMonthlyReport() throws Exception {
        mm.log("Step 1 : Launch Activity.");
        mm.pressHome();
        mm.launchActivity("com.miui.securitycenter/com.miui.securitycenter.MainActivity");
        mm.waitFor(2);
        if (mm.exist(By.text("确定"))) {
            mm.click(By.text("确定"));
        }
        mm.waitFor(2);
        String currentPackageName = mDevice.getCurrentPackageName();
        Assert.assertEquals(currentPackageName, "com.miui.securitycenter");
        mm.waitFor(2);

        mm.log("Step 2 : Enter the settings page.");
        mDevice.findObject(
                By.clazz("android.widget.Button").res("com.miui.securitycenter:id/settings"))
                .click();
        mm.waitFor(2);

        mm.log("Step 3: Show Monthly Report .");
        listView = new UiCollection(new UiSelector().className("android.widget.ListView"));
        UiObject checkbox = listView.getFromParent(new UiSelector().className(
                "android.widget.CheckBox").instance(2));

        Boolean isCheck = checkbox.isChecked();
        if (!isCheck) {
            checkbox.click();
            mm.waitFor(3);
            isCheck = checkbox.isChecked();
        }
        Assert.assertTrue(isCheck);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mm.pressHome();
    }

}
