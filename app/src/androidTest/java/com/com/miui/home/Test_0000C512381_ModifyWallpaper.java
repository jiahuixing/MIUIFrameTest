package com.com.miui.home;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

public class Test_0000C512381_ModifyWallpaper extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }


    public void test_00000001_ModifyWallpaper() throws Exception {
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

        mm.log("Step 2:   Launch Settings Activity.  ");
        mm.pressHome();
        mm.launchActivity("com.android.settings/com.android.settings.MiuiSettings");
        String currentPackageName = mm.getCurrentPackageName();
        Assert.assertEquals(currentPackageName, "com.android.settings");

        mm.log("Step 3: Set edit mode ");
        UiScrollable listView = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        listView.scrollTextIntoView("其他高级设置");
        mm.click(By.text("其他高级设置"));
        mm.click(By.text("按键"));
        if (mm.exist(By.text("启动近期任务"))) {
            mm.click(By.text("启动近期任务"));
            mm.click(By.text("显示菜单"));
        }
        mm.pressHome();

        mm.log("Step 4:Enter Modify allpaper");
        mm.pressMenu();
        mm.click(By.text("修改壁纸"));
        mm.waitFor(1);
        if (DisplayWidth == 1440) {
            for (int i = 0; i < 4; i++) {
                mm.move(1000, 2300, 500, 2300);

                mm.waitFor(2);
            }
        }
        if (DisplayWidth == 1080) {
            for (int i = 0; i < 4; i++) {
                mm.move(900, 1700, 200, 1700);
                mm.waitFor(2);
            }
        }
        mm.click(By.text("其他"));
        mm.waitFor(1);

        mm.log("Step 5： Set Home Live Wallpaper");
        mm.click(By.text("动态壁纸"));
        mm.click(By.text("光环螺旋"));
        mm.click(By.text("应用"));
        mm.waitFor(1);
        //全部应用
        if (DisplayWidth == 1080) {
            mm.click(800, 1500);
        }
        if (DisplayWidth == 1440) {
            mm.click(900, 1900);
        }
        mm.pressHome();
        mm.waitFor(1);
        mm.saveScreenshot("dpngti.png");
        // unlock check

        mm.log("Step 6 : Quit.");
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
