package com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1176955_CheckRecorderFormat extends InstrumentationTestCase{
	public Marmot mm;
    public Checker cc;
    
    protected void setUp() throws Exception{
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }
    
    public void test_C1176955_CheckRecorderFormat() throws Exception {
    	mm.log("打开设置");
    	mm.pressHome();
    	mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings"); 
        mm.waitFor(3);
    	mm.log("找到录音机设置");
    	mm.waitFor(2);
    	UiScrollable list = new UiScrollable(new UiSelector().className("android.widget.ListView"));
           UiObject mms;
           mms  = list.getChildByText(new UiSelector().className("android.widget.TextView"), "录音机", true);
           mms.click();
        mm.waitFor(2);
    	mm.log("检查是否能够选择录音格式");
    	cc.assertTextNotExist("录音格式");
    	mm.log("验证成功：不能选择录音格式");
    	    	
    	    	  	
    }   
    
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }

}
