package com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1189822_CheckAPPriorityManually extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_C1189822_CheckAPPriorityManually() throws Exception {
        mm.log("打开设置");
        mm.pressHome();
        mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings");
        mm.waitFor(3);
        mm.log("检查WLAN设置是否有手动设定接入点的优先级设定");
        mm.click(By.text("WLAN"));
        UiScrollable list = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        UiObject wlan;
        wlan = list.getChildByText(new UiSelector().className("android.widget.TextView"), "高级设置", true);
        wlan.click();
        mm.waitFor(2);
        UiScrollable list1 = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        UiObject prioritymanually;
        prioritymanually = list1.getChildByText(new UiSelector().className("android.widget.TextView"), "手动设定接入点的优先级", true);
        prioritymanually.click();
        mm.waitFor(1);
        mm.click(By.text("接入点优先级设定"));
        mm.waitFor(1);
        cc.assertTextExist("已保存的接入点列表");
        mm.pressBack(2);

    }

    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }

}
