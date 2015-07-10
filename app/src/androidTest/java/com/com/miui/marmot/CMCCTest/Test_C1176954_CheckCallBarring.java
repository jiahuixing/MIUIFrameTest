package com.com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1176954_CheckCallBarring extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_C1176954_CheckCallBarring() throws Exception {
        mm.log("打开设置");
        mm.pressHome();
        mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings");
        mm.waitFor(3);
        mm.log("找到电话设置");
        mm.waitFor(2);
        UiScrollable list = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        UiObject mms;
        mms = list.getChildByText(new UiSelector().className("android.widget.TextView"), "电话", true);
        mms.click();
        mm.waitFor(2);
        mm.log("查找是否有呼叫限制选项");
        mm.waitFor(2);
        UiScrollable list1 = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        UiObject callbarring;
        callbarring = list1.getChildByText(new UiSelector().className("android.widget.TextView"), "呼叫限制", true);
        callbarring.click();
        mm.waitFor(1);
        mm.pressHome();

    }

    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }

}
