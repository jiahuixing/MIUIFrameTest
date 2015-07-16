package com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1176964_CheckSavePage extends InstrumentationTestCase{

	public Marmot mm;
    public Checker cc;
    
    protected void setUp() throws Exception{
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_C1176964_CheckSavePage() throws Exception {
    	mm.log("打开浏览器");
    	mm.pressHome();
    	mm.launchActivity("com.android.browser/com.android.browser.BrowserActivity");
    	mm.waitFor(2);
     	mm.log("打开小米商城");
    	mm.setText(By.clazz("android.widget.EditText"), "mi.com");
    	mm.waitFor(5);
     	mm.click(By.res("com.android.browser", "rightBtn"));
    	mm.waitFor(2);
    	mm.click(By.res("com.android.browser", "action_more"));
    	mm.log("保存网页");
     	int height = mm.getDisplayHeight()-50;
     	int width = mm.getDisplayWidth()-10;
    	mm.drag(width, height, 1, height, 5);
    	mm.waitFor(1);
    	mm.click(By.text("保存网页"));
    	mm.waitFor(3);
    	mm.log("查看已保存网页");
    	mm.click(By.res("com.android.browser", "action_more"));
    	mm.click(By.text("书签/历史"));
    	mm.click(By.text("保存网页"));
    	mm.waitFor(1);
    	cc.assertTextExist("小米商城");
    }

    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }
    
}
