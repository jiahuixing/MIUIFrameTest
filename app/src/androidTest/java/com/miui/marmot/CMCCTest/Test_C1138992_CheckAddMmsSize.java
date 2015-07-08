package com.miui.marmot.CMCCTest;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_C1138992_CheckAddMmsSize extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_C1138992_CheckAddMmsSize() throws Exception {
        mm.log("清除相机缓存");
        mm.pressHome();
        mm.launchActivity("com.android.camera/com.android.camera.Camera");
        mm.waitFor(2);
        mm.pressHome();
        mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings");
        mm.waitFor(2);
        UiScrollable list = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        UiObject guanli;
        guanli = list.getChildByText(new UiSelector().className("android.widget.TextView"), "其他应用管理", true);
        guanli.click();
        mm.waitFor(2);
        UiScrollable list1 = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        UiObject camera;
        camera = list1.getChildByText(new UiSelector().className("android.widget.TextView"), "相机", true);
        camera.click();
        mm.waitFor(2);
        //  mm.click(By.text("清除数据"));有些小屏幕的手机，清除数据按钮不在屏幕上，需要下滑查找一下。
        UiScrollable list3 = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        UiObject clear;
        clear = list3.getChildByText(new UiSelector().className("android.widget.Button"), "清除数据", true);
        clear.click();
        mm.waitFor(2);
        mm.click(By.text("确定"));
        mm.waitFor(1);
        mm.click(By.text("结束运行"));
        mm.click(By.text("确定"));
        mm.waitFor(2);
        mm.pressHome();
        mm.log("打开短信");
        mm.waitFor(2);
        mm.click(By.text("短信"));
        mm.waitFor(1);
        mm.log("写彩信");
        mm.click(By.text("写短信"));
        mm.click(By.desc("添加附件"));
        mm.click(By.text("拍照"));
        mm.waitFor(5);
        mm.click(By.text("开始拍照"));
        mm.waitFor(1);
        mm.click(By.desc("Shutter button"));
        mm.waitFor(5);
        mm.click(By.res("com.android.camera", "v6_btn_done"));
        mm.waitFor(2);
        mm.log("检查是否有彩信容量显示");
        cc.assertUiObejctExist(By.res("com.android.mms", "text_counter"));
        cc.assertUiObejctExist(By.res("com.android.mms", "mms_size"));
        mm.pressHome();


    }

    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }


}
