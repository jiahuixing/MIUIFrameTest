package com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1138976_CheckNoMiMessage extends InstrumentationTestCase{

	public Marmot mm;
    public Checker cc;
    
    protected void setUp() throws Exception{
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }
    
    public void test_C1138976_CheckNoMiMessage() throws Exception {
    	mm.log("打开设置");
    	mm.pressHome();
    	mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings"); 
        mm.waitFor(3);
    	mm.log("找到短信设置");
    //	mm.drag(50, 1700, 50, 600, 50);
    //	mm.waitFor(2);
    //	mm.drag(50, 1700, 50, 600, 50);
    //	mm.waitFor(2);
    // 	mm.click(By.text("短信"));	
    	mm.waitFor(2);
    	UiScrollable list = new UiScrollable(new UiSelector().className("android.widget.ListView"));
           UiObject mms;
           mms  = list.getChildByText(new UiSelector().className("android.widget.TextView"), "短信", true);
           mms.click();
        mm.waitFor(2);
    	mm.log("检查是否有网络短信");
    	cc.assertTextNotExist("免费网络短信");
    	cc.assertTextNotExist("MIUI免费网络短信");
    	    	
    }   
    
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }

}
