
package com.miui.securitycenter;

import android.content.Context;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

//C507225
public class Test_00000001_CancelScanning extends InstrumentationTestCase {

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

    public void test_0_ApplicationTest() {
        mm.log("Step 1 : Launch contacts Activity.");
        mDevice.pressHome();
        mm.launchActivity("com.miui.securitycenter/com.miui.securitycenter.MainActivity");
        mm.waitFor(2);
        if (mm.exist(By.text("取消"))) {
            mm.click(By.text("取消"));
        }
        mm.waitFor(5);
        String currentPackageName = mDevice.getCurrentPackageName();
        Assert.assertEquals(currentPackageName, "com.miui.securitycenter");
        mm.waitFor(5);

        mm.log("Step 2 : Start Scan.");
        mDevice.findObject(By.text("开始体检")).click();
        mm.waitFor(3);

        mm.log("Step 3 : Cancel");
        mDevice.findObject(By.text("取消体检")).click();
        mm.waitFor(1);
    }

    @Override
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }
}
