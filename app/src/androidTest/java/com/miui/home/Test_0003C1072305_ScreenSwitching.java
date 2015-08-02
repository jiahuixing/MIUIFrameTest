package com.miui.home;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

public class Test_0003C1072305_ScreenSwitching extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_00000001_ScreenSwitching() throws Exception {
        int x2 = mm.getDisplayWidth() - 500;
        int y2 = (int) (mm.getDisplayHeight() / 2);
        mm.log("Step 1 : OpenUnlock");
        int DisplayWidth;
        DisplayWidth = mm.getDisplayWidth();
        if (!mm.isScreenOn()) {
            mm.wakeUp();
            mm.waitFor(1);
            mm.move(Direction.UP);
        }

        mm.log("Step 2:  Launch Settings Activity.  ");
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

        mm.log("Step 4:Enter Screen Switching");
        mm.pressMenu();
        mm.click(By.text("屏幕切换"));
        mm.waitFor(1);
        mm.click(By.text("经典"));
        mm.saveScreenshot("jingdian.png");
        mm.waitFor(1);
        mm.click(By.text("经典(无回弹)"));
        mm.saveScreenshot("jingdianwu.png");
        mm.waitFor(1);
        mm.click(By.text("淡入淡出"));
        mm.saveScreenshot("danrudanchu.png");
        mm.waitFor(1);
        mm.click(By.text("转盘"));
        mm.saveScreenshot("zhuanpan.png");
        mm.waitFor(1);
        if (DisplayWidth == 1080) {
            mm.drag(900, 1700, 200, 1700, 4);
        }
        mm.click(By.text("翻页"));
        mm.saveScreenshot("fanye.png");
        mm.waitFor(1);
        if (DisplayWidth == 1440) {
            mm.drag(1000, 2300, 500, 2300, 4);
            mm.waitFor(1);
        }
        mm.click(By.text("层叠"));
        mm.saveScreenshot("cengdie.png");
        mm.waitFor(1);
        mm.click(By.text("旋转"));
        mm.saveScreenshot("xuanzhuan.png");
        mm.waitFor(1);
        mm.click(By.text("方块"));
        mm.saveScreenshot("fangkuai.png");
        mm.waitFor(1);

        mm.log("Step 5 : Quit.");
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
