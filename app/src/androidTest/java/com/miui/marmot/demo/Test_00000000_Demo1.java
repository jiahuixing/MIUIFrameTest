package com.miui.marmot.demo;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_Demo1 extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_00000000_Demo1() throws Exception {
        mm.log("Step 1 : Launch calculator Activity.");
        mm.pressHome();
        mm.launchActivity("com.android.calculator2/com.android.calculator2.Calculator");

        mm.log("Step 2 : Clear edit text.");
        mm.click(By.desc("清除"));
        mm.waitFor(5);
        cc.setTestrailResult("c00000", true);

        mm.log("Step 3 : Input '1+2='");
        mm.click(By.text("1"));
        mm.click(By.text("+"));
        mm.click(By.text("2"));
        mm.click(By.desc("等于"));
        mm.waitFor(5);
        cc.assertTextExist("1+2=");

        mm.log("Step 4 : Check result.");
        cc.assertTrue("", mm.exist(By.clazz("android.widget.EditText").text("3")));
        mm.saveScreenshot("demo1.png");
        cc.setTestrailResult("c00001", true);

        mm.log("Step 5 : Quit.");
        mm.pressBack();
        mm.pressHome();
    }

    @Override
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }
}