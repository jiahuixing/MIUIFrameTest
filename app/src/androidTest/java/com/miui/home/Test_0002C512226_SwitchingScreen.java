package com.miui.home;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

public class Test_0002C512226_SwitchingScreen extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_00000000_SwitchingScreen() throws Exception {
        int x2 = mm.getDisplayWidth() - 500;
        int y2 = (int) (mm.getDisplayHeight() / 2);
        mm.log("Step 1 : OpenUnlock");
        int DisplayWidth;
        DisplayWidth = mm.getDisplayWidth();
        if (!mm.isScreenOn()) {
            mm.wakeUp();
            mm.waitFor(1);
            mm.move(Direction.UP);
            /*
             * if(DisplayWidth <=480){ mm.drag(300, 700, 300, 200, 4); }
             * if(DisplayWidth <=540){ mm.drag(300, 900, 300, 200, 4); }
             * if(DisplayWidth <=720){ mm.drag(300, 1000, 300, 500, 4); }
             * if(DisplayWidth <=1080){ mm.drag(500, 1700, 500, 500, 4); }else{
             * mm.drag(700, 2000, 700, 500, 4); }
             */
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

        mm.log("Step 4:Slide the Desktop");
        mm.pressMenu();
        mm.click(By.text("桌面整理"));
        for (int i = 0; i < 4; i++) {
            mm.drag(x2, y2, 10, y2, 8);
            // mm.move(Direction.LEFT);
        }
        mm.waitFor(2);
        for (int i = 0; i < 4; i++) {
            mm.drag(10, y2, x2, y2, 8);
            // mm.move(Direction.RIGHT);
        }
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
