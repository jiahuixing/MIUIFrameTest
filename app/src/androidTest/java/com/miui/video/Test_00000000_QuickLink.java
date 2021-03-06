package com.miui.video;

import android.support.test.uiautomator.By;
import android.test.InstrumentationTestCase;

import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_QuickLink extends InstrumentationTestCase {
    private Marmot mm;
    private Checker cc;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mm = new Marmot(this);
        cc = new Checker(mm);
    }

    @Override
    public void tearDown() throws Exception {
        mm.pressBack();
        mm.pressBack();
        mm.pressBack();

        mm.pressHome();
        super.tearDown();
    }

    public void testQuickLink() throws Exception {
        mm.launchActivity(Lib_VideoConst.HOME_ACTIVITY_NAME);
        mm.log("Step 1 : Open video.");
        mm.log("Step 2 : Check play history.");
        mm.click(By.text("播放历史"));
        cc.assertTextExist("播放历史");
        mm.pressBack();

        mm.log("Step 3 : Check My favourite.");
        mm.click(By.text("我的收藏"));
        cc.assertTextExist("我的收藏");
        mm.pressBack();

        mm.log("Step 4 : Check My offline video.");
        mm.click(By.text("我的离线"));
        cc.assertTextExist("我的离线");
        mm.pressBack();
        cc.setTestrailResult("c1122517", true);
    }
}