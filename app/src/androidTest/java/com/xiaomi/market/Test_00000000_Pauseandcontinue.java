package com.xiaomi.market;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_Pauseandcontinue extends InstrumentationTestCase {
    public Marmot mm;
    public Checker cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    public void test_PauseandContinue() throws Exception {
        mm.log("Step 1 : Launch Market Activity and Enter Appdetail.");
        mm.pressHome();
        mm.launchActivity("com.xiaomi.market/com.xiaomi.market.ui.MarketTabActivity");
        mm.waitFor(5);
        mm.click(27, 157);
        mm.waitFor(5);
        mm.log("Step 2 : Install the App.");
        cc.assertTextExist("安装");
        mm.click(By.text("安装"));
        mm.saveScreenshot("安装中.png");
        mm.log("Step 3 : Pause.");
        mm.click(By.text("暂停"));
        mm.saveScreenshot("暂停.png");
        mm.log("Step 4 : continue.");
        mm.click(By.text("继续"));
        mm.saveScreenshot("继续.png");
        cc.setTestrailResult("c512891", true);
        mm.log("Step 5 : Quit.");
        mm.pressBack();
        mm.pressHome();
    }

    @Override
    protected void tearDown() throws Exception {
        mm.pressBack(3);
        super.tearDown();
    }
}