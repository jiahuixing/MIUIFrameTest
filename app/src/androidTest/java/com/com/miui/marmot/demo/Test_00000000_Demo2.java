package com.com.miui.marmot.demo;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_Demo2 extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_00000000_Demo2() throws Exception {
        mm.log("Step 1 : Launch settings Activity.");
        mm.pressHome();
        mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings");

        mm.log("Step 2 : Click 'About Phone'.");
        mm.scrollListViewToEnd();
        mm.waitFor(5);
        mm.click(By.text("关于手机"));

        mm.log("Step 3 : Click 'name'.");
        cc.assertTextExist("手机名称");
        mm.click(By.text("手机名称"));

        mm.log("Step 4 : Rename 'Test'.");
        mm.setText(By.clazz("android.widget.EditText"), "AUTO");
        cc.assertTextExist("AUTO");
        mm.click(By.clazz("android.widget.Button").text("确定"));

        mm.log("Step 5 : Quit.");
        mm.pressBack(3);
        mm.pressHome();
    }

    @Override
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }
}