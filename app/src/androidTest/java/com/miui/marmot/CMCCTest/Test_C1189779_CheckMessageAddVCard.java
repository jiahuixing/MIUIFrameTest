package com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1189779_CheckMessageAddVCard extends InstrumentationTestCase{
	public Marmot mm;
    public Checker cc;
    
    protected void setUp() throws Exception{
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_C1189779_CheckMessageAddVCard() throws Exception {
    	mm.log("打开设置");
    	mm.pressHome();
    	mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings"); 
        mm.waitFor(3);
    	mm.log("找到短信设置");
    	mm.waitFor(2);
    	UiScrollable list = new UiScrollable(new UiSelector().className("android.widget.ListView"));
    	UiObject mms;
    	mms  = list.getChildByText(new UiSelector().className("android.widget.TextView"), "短信", true);
    	mms.click();
        mm.waitFor(2);
    	UiScrollable list1 = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        UiObject Gaoji;
        Gaoji  = list1.getChildByText(new UiSelector().className("android.widget.TextView"), "高级设置", true);
        Gaoji.click();
        mm.waitFor(2);
        UiScrollable list2 = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        list2.getChildByText(new UiSelector().className("android.widget.CheckedTextView"), "使用vCard文件发送", true);
    }

    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }

}
