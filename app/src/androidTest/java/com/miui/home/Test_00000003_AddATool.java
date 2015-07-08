package com.miui.home;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

import junit.framework.Assert;

public class Test_00000003_AddATool extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }


    public void test_00000001_AddATool() throws Exception {
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

        mm.log("Step 2: C1072230 Launch Settings Activity.  ");
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

        mm.log("Step 4:Add a Gadget");
        mm.pressMenu();
        mm.click(By.text("添加小工具"));
        mm.waitFor(1);
        if (DisplayWidth == 1440) {
            mm.drag(1000, 2300, 500, 2300, 4);
            mm.waitFor(1);
            mm.drag(500, 1500, 1000, 1500, 4);
            mm.waitFor(1);
        }
        if (DisplayWidth == 1080) {
            mm.drag(900, 1700, 200, 1700, 4);
            mm.waitFor(1);
            mm.drag(200, 1000, 900, 1000, 4);
            mm.waitFor(1);
        }
        mm.click(By.text("计算器"));
        mm.waitFor(1);

        mm.log(" Step ：5 Calculate '1.6 + 0.3='");
        if (DisplayWidth == 1080) {
            mm.click(200, 350); //点击清除
            mm.waitFor(1);
            mm.click(200, 920); //1
            mm.waitFor(1);
            mm.click(700, 1100);//.
            mm.waitFor(1);
            mm.click(700, 700);//6
            mm.waitFor(1);
            mm.click(900, 700);//+
            mm.waitFor(1);
            mm.click(400, 1100);//0
            mm.waitFor(1);
            mm.click(700, 1100);//.
            mm.waitFor(1);
            mm.click(700, 920);//3
            mm.waitFor(1);
            ;
            mm.click(900, 1100);//=
            mm.waitFor(1);
            mm.saveScreenshot("Calculate.png");
        }

        if (DisplayWidth == 1440) {
            mm.click(430, 450); //点击清除
            mm.waitFor(1);
            mm.click(400, 1200); //1
            mm.waitFor(1);
            mm.click(900, 1300);//.
            mm.waitFor(1);
            mm.click(900, 900);//6
            mm.waitFor(1);
            mm.click(1100, 900);//+
            mm.waitFor(1);
            mm.click(600, 1300);//0
            mm.waitFor(1);
            mm.click(900, 1300);//.
            mm.waitFor(1);
            mm.click(900, 1100);//3
            mm.waitFor(1);
            ;
            mm.click(1100, 1200);//=
            mm.waitFor(1);
            mm.saveScreenshot("Calculate.png");
        }


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
