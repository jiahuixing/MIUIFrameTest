package com.miui.home;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

public class Test_0008C512230_ManuallyDrag extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_00000000_ManuallyDrag() throws Exception {
        int DisplayWidth;
        DisplayWidth = mm.getDisplayWidth();
        mm.log("Step 1 : OpenUnlock");
        if (!mm.isScreenOn()) {
            mm.wakeUp();
            mm.waitFor(1);
            mm.move(Direction.UP);
        }

        mm.log("Step 2: Launch Settings Activity.");
        mm.pressHome();
        mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings");
        String currentPackageName = mm.getCurrentPackageName();
        Assert.assertEquals(currentPackageName, "com.android.settings");

        mm.log("Step 3: Set edit mode ");
        UiScrollable listView = new UiScrollable(
                new UiSelector().className("android.widget.ListView"));
        listView.scrollTextIntoView("其他高级设置");
        mm.click(By.text("其他高级设置"));
        mm.click(By.text("按键"));
        if (mm.exist(By.text("启动近期任务"))) {
            mm.click(By.text("启动近期任务"));
            mm.click(By.text("显示菜单"));
        }
        mm.pressHome();

        mm.log("Step 4:Manually Drag");
        mm.pressMenu();
        mm.click(By.text("桌面整理"));
        // 区分分辨率
        if (DisplayWidth == 1080) {
            mm.drag(200, 1000, 500, 1800, 4);
            mm.saveScreenshot("drag.png");
        }
        if (DisplayWidth == 1440) {
            mm.drag(300, 1200, 700, 2300, 4);
            mm.saveScreenshot("drag.png");
        }

        mm.log("Step 5:Manually Click On");
        mm.click(By.text("音乐"));
        mm.click(By.text("图库"));
        mm.waitFor(1);

        mm.log("Step 6:Manually Drag Again");
        if (DisplayWidth == 1080) {
            mm.drag(200, 1000, 500, 1800, 4);
            mm.saveScreenshot("drag1.png");
        }
        if (DisplayWidth == 1440) {
            mm.drag(300, 1200, 700, 2300, 4);
            mm.saveScreenshot("drag1.png");
        }
        mm.waitFor(1);
        mm.pressBack();
        mm.saveScreenshot("back.png");
        mm.waitFor(1);

        mm.log("Step 7:Quit.");
        mm.pressBack();
        mm.pressBack();
        mm.pressBack();
        mm.pressHome();

    }

    @Override
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }
}
