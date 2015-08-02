package com.miui.home;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

public class Test_0011C512241_EmptyFolder extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_00000000_EmptyFolder() throws Exception {
        int DisplayWidth;
        DisplayWidth = mm.getDisplayWidth();
        int x2 = mm.getDisplayWidth() - 500;
        int y2 = (int) (mm.getDisplayHeight() / 2);
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

        mm.log("Step 4:Add a Gadget");
        mm.pressMenu();
        mm.click(By.text("添加小工具"));
        for (int i = 0; i < 6; i++) {
            mm.drag(10, y2, x2, y2, 6);
        }
        mm.waitFor(1);
        mm.click(By.text("开关(19)"));
        mm.click(By.text("飞行模式"));
        mm.click(By.text("蓝牙"));
        mm.click(By.text("勿扰模式"));
        mm.waitFor(1);
        mm.pressBack(2);
        mm.click(By.text("飞行模式"));
        mm.click(By.text("蓝牙"));
        mm.click(By.text("勿扰模式"));
        mm.waitFor(1);
        UiObject view = this.mm.getUiDevice().findObject(
                new UiSelector().className("android.view.View").resourceId(
                        "com.miui.home:id/multi_select_container"));
        view.swipeRight(6);
        mm.waitFor(1);
        mm.click(By.text("文件夹"));
        mm.waitFor(1);
        if (DisplayWidth == 1080) {
            mm.click(200, 280);
            mm.waitFor(1);
            mm.click(400, 1700);
        }
        if (DisplayWidth == 1440) {
            mm.click(300, 350);
            mm.waitFor(1);
            mm.click(500, 2300);
        }
        mm.saveScreenshot("imageview.png");
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
